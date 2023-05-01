package residentialarea.service;

import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import residentialarea.bean.ResidentBean;
import residentialarea.bean.ResidentCredentialBean;
import residentialarea.bean.VisitorPassBean;
import residentialarea.constant.VisitorPassStatusEnum;
import residentialarea.dao.ResidentCredentialDao;
import residentialarea.dao.ResidentDao;
import residentialarea.dao.VisitorPassDao;
import residentialarea.model.CreateVisitorPassRequestModel;
import residentialarea.model.VisitorPassResponseModel;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.*;

import static com.google.zxing.BarcodeFormat.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class VisitorServiceImpl implements VisitorPassService{

    private final VisitorPassDao visitorPassDao;
    private final ResidentCredentialDao residentCredentialDao;
    private final ResidentDao residentDao;


    @Override
    @Transactional(readOnly = true)
    public Page<VisitorPassResponseModel> readAll(int pageSize, int pageNumber) {
        log.info("VisitorServiceImpl#readAll");
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        Page<VisitorPassBean> visitorPassBeans = visitorPassDao.findAll(pageable);
        List<VisitorPassResponseModel> visitorPassResponseModels = new ArrayList<>();
        visitorPassBeans.forEach(visitorPassBean -> {
            VisitorPassResponseModel visitorPassResponseModel = new VisitorPassResponseModel();
            BeanUtils.copyProperties(visitorPassBean, visitorPassResponseModel);
            ResidentBean residentBean = residentDao.getOne(visitorPassBean.getResidentId());
            visitorPassResponseModel.setResidentUnit(residentBean.getUnitNo());
            visitorPassResponseModels.add(visitorPassResponseModel);
        });
        return new PageImpl<>(visitorPassResponseModels, pageable, visitorPassDao.findAll().size());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String create(CreateVisitorPassRequestModel createVisitorPassRequestModel) {
        log.info("createVisitorPassRequestModel: " + createVisitorPassRequestModel);
        int width = 300;
        int height = 300;

        try {
            UUID uuid = UUID.randomUUID();
            // Set QR code parameters
            Map<EncodeHintType, Object> hints = new HashMap<>();
            hints.put(EncodeHintType.ERROR_CORRECTION, com.google.zxing.qrcode.decoder.ErrorCorrectionLevel.L);
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");

            // Generate QR code image
            com.google.zxing.Writer writer = new MultiFormatWriter();
            BitMatrix bitMatrix = writer.encode(uuid.toString(), QR_CODE, width, height, hints);
            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    int color = bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF;
                    image.setRGB(x, y, color);
                }
            }

            // Convert QR code image to Base64
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            javax.imageio.ImageIO.write(image, "png", baos);
            byte[] imageBytes = baos.toByteArray();
            String base64Image = Base64.encodeBase64String(imageBytes);

            log.info("QR code generated successfully.");
            log.info("Base64 representation: " + base64Image);

            VisitorPassBean visitorPassBean = new VisitorPassBean();
            visitorPassBean.setUuid(uuid.toString());
            ResidentCredentialBean residentCredentialBean = residentCredentialDao.findByUsername(createVisitorPassRequestModel.getUsername());
            visitorPassBean.setResidentId(residentCredentialBean.getResidentId());
            visitorPassBean.setImageStr(base64Image);
            visitorPassBean.setStatus(VisitorPassStatusEnum.PENDING_IN.getStatus());
            visitorPassBean.setVisitorName(createVisitorPassRequestModel.getName());
            ResidentBean residentBean = residentDao.getOne(residentCredentialBean.getResidentId());
            visitorPassBean.setResidentUnit(residentBean.getUnitNo());
            visitorPassDao.save(visitorPassBean);
            return base64Image;
        } catch (Exception e) {
            log.error("Failed to generate QR code: ", e);
            return null;
        }
    }
}

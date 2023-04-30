package residentialarea.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import residentialarea.bean.EmergencyRequestBean;
import residentialarea.bean.ResidentBean;
import residentialarea.bean.VisitorPassBean;
import residentialarea.constant.EmergencyRequestStatusEnum;
import residentialarea.dao.EmergencyRequestDao;
import residentialarea.dao.ResidentDao;
import residentialarea.model.EmergencyResponseModel;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmergencyRequestServiceImpl implements EmergencyRequestService{

    private final EmergencyRequestDao emergencyRequestDao;
    private final ResidentDao residentDao;

    @Override
    @Transactional(readOnly = true)
    public Page<EmergencyResponseModel> findAllEmergencyRequest(int pageSize, int pageNumber) {
        log.info("findAllEmergencyRequest");
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        Page<EmergencyRequestBean> emergencyRequestBeans = emergencyRequestDao.findAll(pageable);
        List<EmergencyResponseModel> emergencyResponseModels = new ArrayList<>();
        emergencyRequestBeans.forEach(emergencyRequestBean -> {
            EmergencyResponseModel emergencyResponseModel = new EmergencyResponseModel();
            BeanUtils.copyProperties(emergencyRequestBean, emergencyResponseModel);
            ResidentBean residentBean = residentDao.getOne(emergencyRequestBean.getResidentId());
            emergencyResponseModel.setResidentName(residentBean.getName());
            emergencyResponseModel.setUnitNo(residentBean.getUnitNo());
            emergencyResponseModels.add(emergencyResponseModel);
        });
        return new PageImpl<>(emergencyResponseModels, pageable, emergencyRequestDao.findAll().size());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void resolve(int id) {
        EmergencyRequestBean emergencyRequestBean = emergencyRequestDao.getOne(id);
        emergencyRequestBean.setStatus(EmergencyRequestStatusEnum.RESOLVED.getStatus());
        emergencyRequestDao.save(emergencyRequestBean);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void reject(int id) {
        EmergencyRequestBean emergencyRequestBean = emergencyRequestDao.getOne(id);
        emergencyRequestBean.setStatus(EmergencyRequestStatusEnum.REJECTED.getStatus());
        emergencyRequestDao.save(emergencyRequestBean);
    }
}

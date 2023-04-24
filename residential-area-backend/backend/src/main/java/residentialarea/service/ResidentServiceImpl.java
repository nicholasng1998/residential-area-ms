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
import org.springframework.web.bind.annotation.RequestParam;
import residentialarea.bean.ResidentBean;
import residentialarea.bean.ResidentCredentialBean;
import residentialarea.constant.ResidentCredentialStatusEnum;
import residentialarea.constant.ResidentStatusEnum;
import residentialarea.dao.ResidentCredentialDao;
import residentialarea.dao.ResidentDao;
import residentialarea.model.PageableRequestModel;
import residentialarea.model.ResidentCreateRequestModel;
import residentialarea.model.ResidentEditRequestModel;
import residentialarea.model.ResidentResponseModel;

import java.util.ArrayList;
import java.util.List;

@Slf4j  
@Service
@RequiredArgsConstructor
public class ResidentServiceImpl implements ResidentService {

    private final ResidentDao residentDao;
    private final ResidentCredentialDao residentCredentialDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createResident(ResidentCreateRequestModel residentCreateRequestModel) {
        ResidentBean residentBean = new ResidentBean();
        BeanUtils.copyProperties(residentCreateRequestModel, residentBean);
        residentBean.setStatus(ResidentStatusEnum.ACTIVE.getStatus());
        log.info("residentBean: " + residentBean);
        residentDao.save(residentBean);

        ResidentCredentialBean residentCredentialBean = new ResidentCredentialBean();
        BeanUtils.copyProperties(residentCreateRequestModel, residentCredentialBean);
        residentCredentialBean.setResidentId(residentBean.getId());
        residentBean.setStatus(ResidentCredentialStatusEnum.ACTIVE.getStatus());
        residentCredentialDao.save(residentCredentialBean);
        log.info("residentCredentialBean: " + residentCredentialBean);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ResidentResponseModel> readResident(int pageNumber, int pageSize) {
        log.info("readResident");
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        Page<ResidentBean> residentBeans = residentDao.findAll(pageable);
        List<ResidentResponseModel> residentResponseModels = new ArrayList<>();
        residentBeans.getContent().forEach((residentBean -> {
            ResidentResponseModel residentResponseModel = new ResidentResponseModel();
            BeanUtils.copyProperties(residentBean, residentResponseModel);
            residentResponseModels.add(residentResponseModel);
        }));
        return new PageImpl<>(residentResponseModels, pageable, residentDao.findAll().size());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteResident(int id) {
        log.info("deleteResident");
        ResidentCredentialBean residentCredentialBean = residentCredentialDao.findByResidentId(id);
        if (residentCredentialBean != null) {
            residentCredentialDao.delete(residentCredentialBean);
        }

        ResidentBean residentBean = residentDao.getOne(id);
        residentDao.delete(residentBean);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateResident(ResidentEditRequestModel residentEditRequestModel) {
        ResidentBean residentBean = residentDao.getOne(residentEditRequestModel.getId());
        BeanUtils.copyProperties(residentEditRequestModel, residentBean);
        residentBean.setStatus(ResidentStatusEnum.ACTIVE.getStatus());
        log.info("residentBean: " + residentBean);
        residentDao.save(residentBean);
    }
}

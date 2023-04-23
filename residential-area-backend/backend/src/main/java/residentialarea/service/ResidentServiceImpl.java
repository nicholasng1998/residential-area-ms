package residentialarea.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import residentialarea.bean.ResidentBean;
import residentialarea.bean.ResidentCredentialBean;
import residentialarea.constant.ResidentCredentialStatusEnum;
import residentialarea.constant.ResidentStatusEnum;
import residentialarea.dao.ResidentCredentialDao;
import residentialarea.dao.ResidentDao;
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
    public List<ResidentResponseModel> readResident() {
        log.info("readResident");
        List<ResidentBean> residentBeans = residentDao.findAll();
        List<ResidentResponseModel> residentResponseModels = new ArrayList<>();
        residentBeans.forEach((residentBean -> {
            ResidentResponseModel residentResponseModel = new ResidentResponseModel();
            BeanUtils.copyProperties(residentBean, residentResponseModel);
            residentResponseModels.add(residentResponseModel);
        }));
        return residentResponseModels;
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

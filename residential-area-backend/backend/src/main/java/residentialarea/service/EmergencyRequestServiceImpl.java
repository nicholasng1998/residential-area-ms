package residentialarea.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import residentialarea.bean.EmergencyRequestBean;
import residentialarea.bean.ResidentBean;
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
    public List<EmergencyResponseModel> findAllEmergencyRequest() {
        List<EmergencyRequestBean> emergencyRequestBeans = emergencyRequestDao.findAll();
        List<EmergencyResponseModel> emergencyResponseModels = new ArrayList<>();
        emergencyRequestBeans.forEach(emergencyRequestBean -> {
            EmergencyResponseModel emergencyResponseModel = new EmergencyResponseModel();
            BeanUtils.copyProperties(emergencyRequestBean, emergencyResponseModel);
            ResidentBean residentBean = residentDao.getOne(emergencyRequestBean.getResidentId());
            emergencyResponseModel.setResidentName(residentBean.getName());
            emergencyResponseModel.setUnitNo(residentBean.getUnitNo());
            emergencyResponseModels.add(emergencyResponseModel);
        });
        return emergencyResponseModels;
    }
}

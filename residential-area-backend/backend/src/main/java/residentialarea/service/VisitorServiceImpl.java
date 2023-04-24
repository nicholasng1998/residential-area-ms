package residentialarea.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import residentialarea.bean.VisitorPassBean;
import residentialarea.dao.VisitorPassDao;
import residentialarea.model.VisitorPassResponseModel;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class VisitorServiceImpl implements VisitorPassService{

    private final VisitorPassDao visitorPassDao;

    @Override
    @Transactional(readOnly = true)
    public List<VisitorPassResponseModel> readAll() {
        log.info("VisitorServiceImpl#readAll");
        List<VisitorPassBean> visitorPassBeans = visitorPassDao.findAll();
        List<VisitorPassResponseModel> visitorPassResponseModels = new ArrayList<>();
        visitorPassBeans.forEach(visitorPassBean -> {
            VisitorPassResponseModel visitorPassResponseModel = new VisitorPassResponseModel();
            BeanUtils.copyProperties(visitorPassBean, visitorPassResponseModel);
            visitorPassResponseModels.add(visitorPassResponseModel);
        });
        return visitorPassResponseModels;
    }
}

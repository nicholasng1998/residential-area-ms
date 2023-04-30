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
import residentialarea.bean.ResidentBean;
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
    public Page<VisitorPassResponseModel> readAll(int pageSize, int pageNumber) {
        log.info("VisitorServiceImpl#readAll");
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        Page<VisitorPassBean> visitorPassBeans = visitorPassDao.findAll(pageable);
        List<VisitorPassResponseModel> visitorPassResponseModels = new ArrayList<>();
        visitorPassBeans.forEach(visitorPassBean -> {
            VisitorPassResponseModel visitorPassResponseModel = new VisitorPassResponseModel();
            BeanUtils.copyProperties(visitorPassBean, visitorPassResponseModel);
            visitorPassResponseModels.add(visitorPassResponseModel);
        });
        return new PageImpl<>(visitorPassResponseModels, pageable, visitorPassDao.findAll().size());
    }
}

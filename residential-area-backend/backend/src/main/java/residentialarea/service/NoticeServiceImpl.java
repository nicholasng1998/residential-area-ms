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
import residentialarea.bean.NoticeBean;
import residentialarea.bean.ResidentBean;
import residentialarea.dao.NoticeDao;
import residentialarea.model.EmergencyResponseModel;
import residentialarea.model.NoticeCreateRequestBody;
import residentialarea.model.NoticeResponseModel;
import residentialarea.model.NoticeUpdateRequestBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {

    private final NoticeDao noticeDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateNotice(NoticeUpdateRequestBody noticeUpdateRequestBody) {
        NoticeBean noticeBean = noticeDao.findById(noticeUpdateRequestBody.getId());
        if (noticeBean == null) {
            log.info("noticeBean is null");
            return;
        }

        noticeBean.setContent(noticeUpdateRequestBody.getContent());
        noticeBean.setTitle(noticeUpdateRequestBody.getTitle());
        noticeBean.setExpiryDate(noticeUpdateRequestBody.getExpiryDate());
        noticeBean.setIsActive(noticeUpdateRequestBody.getIsActive() ? "Y" : "N");
        noticeDao.save(noticeBean);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createNotice(NoticeCreateRequestBody noticeCreateRequestBody) {
        log.info("noticeCreateRequestBody: " + noticeCreateRequestBody);
        NoticeBean noticeBean = new NoticeBean();
        noticeBean.setContent(noticeCreateRequestBody.getContent());
        noticeBean.setTitle(noticeCreateRequestBody.getTitle());
        noticeBean.setExpiryDate(noticeCreateRequestBody.getExpiryDate());
        noticeBean.setIsActive(noticeCreateRequestBody.getIsActive() ? "Y" : "N");
        noticeBean.setCreatedBy("SYSTEM");
        noticeBean.setCreatedDate(new Date());
        noticeDao.save(noticeBean);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void voidNotice(int id) {
        NoticeBean noticeBean = noticeDao.findById(id);
        if (noticeBean == null) {
            log.info("noticeBean is null");
            return;
        }
        noticeBean.setIsActive("N");
        noticeDao.save(noticeBean);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void activateNotice(int id) {
        NoticeBean noticeBean = noticeDao.findById(id);
        if (noticeBean == null) {
            log.info("noticeBean is null");
            return;
        }
        noticeBean.setIsActive("Y");
        noticeDao.save(noticeBean);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<NoticeResponseModel> findByIsActiveAndExpiryDateAfter() {
        log.info("findAll#NoticeResponseModel");
        Pageable pageable = PageRequest.of(0, 999);
        List<NoticeBean> noticeBeans = noticeDao.findByIsActiveAndExpiryDateAfter("Y", new Date());
        List<NoticeResponseModel> noticeResponseModels = new ArrayList<>();
        noticeBeans.forEach(noticeBean -> {
            NoticeResponseModel noticeResponseModel = new NoticeResponseModel();
            BeanUtils.copyProperties(noticeBean, noticeResponseModel);
            noticeResponseModels.add(noticeResponseModel);
        });
        return new PageImpl<>(noticeResponseModels, pageable, noticeDao.findByIsActiveAndExpiryDateAfter("Y", new Date()).size());
    }

    @Override
    @Transactional(readOnly = true)
    public Page<NoticeResponseModel> findAll(int pageSize, int pageNumber) {
        log.info("findAll#NoticeResponseModel");
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        Page<NoticeBean> noticeBeans = noticeDao.findAll(pageable);

        List<NoticeResponseModel> noticeResponseModels = new ArrayList<>();
        noticeBeans.forEach(noticeBean -> {
            NoticeResponseModel noticeResponseModel = new NoticeResponseModel();
            BeanUtils.copyProperties(noticeBean, noticeResponseModel);
            if ("Y".equalsIgnoreCase(noticeBean.getIsActive())) {
                noticeResponseModel.setActive(true);
            }
            noticeResponseModels.add(noticeResponseModel);
        });
        return new PageImpl<>(noticeResponseModels, pageable, noticeDao.findAll().size());
    }
}

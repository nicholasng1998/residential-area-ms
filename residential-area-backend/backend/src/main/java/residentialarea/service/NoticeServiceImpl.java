package residentialarea.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import residentialarea.bean.NoticeBean;
import residentialarea.dao.NoticeDao;
import residentialarea.model.NoticeCreateRequestBody;
import residentialarea.model.NoticeUpdateRequestBody;

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

        noticeBean.setMessage(noticeUpdateRequestBody.getMessage());
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
        noticeBean.setMessage(noticeCreateRequestBody.getMessage());
        noticeBean.setTitle(noticeCreateRequestBody.getTitle());
        noticeBean.setExpiryDate(noticeCreateRequestBody.getExpiryDate());
        noticeBean.setIsActive(noticeCreateRequestBody.getIsActive() ? "Y" : "N");
        noticeBean.setCreatedBy("SYSTEM");
        noticeBean.setCreatedDate(new Date());
        noticeDao.save(noticeBean);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void voidNotice(NoticeUpdateRequestBody noticeUpdateRequestBody) {
        NoticeBean noticeBean = noticeDao.findById(noticeUpdateRequestBody.getId());
        if (noticeBean == null) {
            log.info("noticeBean is null");
            return;
        }

        noticeBean.setMessage(noticeUpdateRequestBody.getMessage());
        noticeBean.setTitle(noticeUpdateRequestBody.getTitle());
        noticeBean.setExpiryDate(noticeUpdateRequestBody.getExpiryDate());
        noticeBean.setIsActive("N");
        noticeDao.save(noticeBean);
    }

    @Override
    public void activateNotice(NoticeUpdateRequestBody noticeUpdateRequestBody) {
        NoticeBean noticeBean = noticeDao.findById(noticeUpdateRequestBody.getId());
        if (noticeBean == null) {
            log.info("noticeBean is null");
            return;
        }

        noticeBean.setMessage(noticeUpdateRequestBody.getMessage());
        noticeBean.setTitle(noticeUpdateRequestBody.getTitle());
        noticeBean.setExpiryDate(noticeUpdateRequestBody.getExpiryDate());
        noticeBean.setIsActive("Y");
        noticeDao.save(noticeBean);
    }

    @Override
    @Transactional(readOnly = true)
    public List<NoticeBean> findByIsActiveAndExpiryDateAfter() {
        return noticeDao.findByIsActiveAndExpiryDateAfter("Y", new Date());
    }
}

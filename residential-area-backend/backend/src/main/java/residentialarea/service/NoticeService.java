package residentialarea.service;

import residentialarea.bean.NoticeBean;
import residentialarea.model.NoticeCreateRequestBody;
import residentialarea.model.NoticeUpdateRequestBody;

import java.util.List;

public interface NoticeService {

    void updateNotice(NoticeUpdateRequestBody noticeUpdateRequestBody);
    void createNotice(NoticeCreateRequestBody noticeCreateRequestBody);
    void voidNotice(NoticeUpdateRequestBody noticeUpdateRequestBody);
    void activateNotice(NoticeUpdateRequestBody noticeUpdateRequestBody);

    List<NoticeBean> findByIsActiveAndExpiryDateAfter();
}

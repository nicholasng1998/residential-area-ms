package residentialarea.service;

import org.springframework.data.domain.Page;
import residentialarea.bean.NoticeBean;
import residentialarea.model.EmergencyResponseModel;
import residentialarea.model.NoticeCreateRequestBody;
import residentialarea.model.NoticeResponseModel;
import residentialarea.model.NoticeUpdateRequestBody;

import java.util.List;

public interface NoticeService {

    void updateNotice(NoticeUpdateRequestBody noticeUpdateRequestBody);
    void createNotice(NoticeCreateRequestBody noticeCreateRequestBody);
    void voidNotice(NoticeUpdateRequestBody noticeUpdateRequestBody);
    void activateNotice(NoticeUpdateRequestBody noticeUpdateRequestBody);

    List<NoticeBean> findByIsActiveAndExpiryDateAfter();

    Page<NoticeResponseModel> findAll(int pageSize, int pageNumber);
}

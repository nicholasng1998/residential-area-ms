package org.residentialarea.feign;

import org.residentialarea.model.NoticeResponseModel;
import org.residentialarea.model.PageModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "NOTICE", url = "http://localhost:8081", path = "/v1/notice")
public interface NoticeFeignService {
    @GetMapping(value = "/read")
    PageModel<NoticeResponseModel> findByIsActiveAndExpiryDateAfter();
}

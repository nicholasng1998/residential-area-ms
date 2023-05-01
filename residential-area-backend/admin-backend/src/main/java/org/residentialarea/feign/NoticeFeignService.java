package org.residentialarea.feign;

import org.residentialarea.model.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "NOTICE", url = "http://localhost:8081", path = "/v1/notice")
public interface NoticeFeignService {
    @GetMapping(value = "/read")
    List<NoticeModel> findByIsActiveAndExpiryDateAfter();

    @GetMapping(value = "/create")
    String createNotice(@RequestBody NoticeCreateRequestBody noticeCreateRequestBody);

    @GetMapping(value = "/update")
    String updateNotice(@RequestBody NoticeUpdateRequestBody noticeCreateRequestBody);

    @GetMapping(value = "/read-all")
    PageModel<NoticeResponseModel> findAllNotice(@RequestParam("pageSize") int pageSize, @RequestParam("pageNumber") int pageNumber);
}

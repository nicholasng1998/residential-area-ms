package org.residentialarea.feign;

import org.residentialarea.model.NoticeModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "NOTICE", url = "http://localhost:8081", path = "/v1/notice")
public interface NoticeFeignService {
    @GetMapping(value = "/read")
    List<NoticeModel> findByIsActiveAndExpiryDateAfter();
}

package org.residentialarea.feign;

import org.residentialarea.model.PageModel;
import org.residentialarea.model.VisitorPassResponseModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "VISITOR-PASS", url = "http://localhost:8081", path = "/v1/visitor-pass")
public interface VisitorPassFeignService {

    @GetMapping(value = "/read")
    PageModel<VisitorPassResponseModel> readAll(@RequestParam("pageSize") int pageSize, @RequestParam("pageNumber") int pageNumber);
}

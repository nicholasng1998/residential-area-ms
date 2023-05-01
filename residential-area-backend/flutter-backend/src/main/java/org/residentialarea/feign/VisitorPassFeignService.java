package org.residentialarea.feign;

import org.residentialarea.model.CreateVisitorPassRequestModel;
import org.residentialarea.model.EmergencyResponseModel;
import org.residentialarea.model.PageModel;
import org.residentialarea.model.VisitorPassResponseModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "VISITOR-PASS", url = "http://localhost:8081", path = "/v1/visitor-pass")
public interface VisitorPassFeignService {

    @GetMapping(value = "/read")
    PageModel<VisitorPassResponseModel> readAll(@RequestParam("pageSize") int pageSize, @RequestParam("pageNumber") int pageNumber);

    @PostMapping(value = "/create")
    String create(@RequestBody CreateVisitorPassRequestModel createVisitorPassRequestModel);

    @GetMapping(value = "/read-by-username")
    PageModel<VisitorPassResponseModel> findAllVisitorPassByUsername(@RequestParam("username") String username);
}

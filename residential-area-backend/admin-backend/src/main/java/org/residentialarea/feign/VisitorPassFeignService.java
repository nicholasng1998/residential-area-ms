package org.residentialarea.feign;

import org.residentialarea.model.VisitorPassResponseModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name = "VISITOR-PASS", url = "http://localhost:8081", path = "/v1/visitor-pass")
public interface VisitorPassFeignService {

    @GetMapping(value = "/read")
    List<VisitorPassResponseModel> readAll();
}
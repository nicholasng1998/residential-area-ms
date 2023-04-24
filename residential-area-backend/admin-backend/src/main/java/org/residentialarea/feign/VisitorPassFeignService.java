package org.residentialarea.feign;

import org.residentialarea.model.VisitorPassResponseModel;
import org.springframework.cloud.openfeign.FeignClient;

import java.util.List;

@FeignClient(name = "VISITOR-PASS", url = "http://localhost:8081", path = "/v1/visitor-pass")
public interface VisitorPassFeignService {

    List<VisitorPassResponseModel> readAll();
}

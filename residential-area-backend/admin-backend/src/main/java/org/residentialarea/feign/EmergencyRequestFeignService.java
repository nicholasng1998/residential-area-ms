package org.residentialarea.feign;

import org.residentialarea.model.EmergencyResponseModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "EMERGENCY", url = "http://localhost:8081", path = "/v1/emergency-request")
public interface EmergencyRequestFeignService {

    @GetMapping(value = "/read")
    List<EmergencyResponseModel> findAllEmergencyRequest();
}

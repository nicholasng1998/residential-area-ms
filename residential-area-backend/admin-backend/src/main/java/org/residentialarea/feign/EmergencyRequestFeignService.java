package org.residentialarea.feign;

import org.residentialarea.model.CommonResponseModel;
import org.residentialarea.model.EmergencyResponseModel;
import org.residentialarea.model.PageModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "EMERGENCY", url = "http://localhost:8081", path = "/v1/emergency-request")
public interface EmergencyRequestFeignService {

    @GetMapping(value = "/read")
    PageModel<EmergencyResponseModel> findAllEmergencyRequest(@RequestParam(defaultValue = "10") Integer pageSize,
                                                              @RequestParam(defaultValue = "1") Integer pageNumber);

    @PostMapping(value = "/resolve")
    CommonResponseModel resolveEmergencyRequest(@RequestParam("id") Integer id);

    @PostMapping(value = "/reject")
    CommonResponseModel rejectEmergencyRequest(@RequestParam("id") Integer id);
}

package org.residentialarea.feign;

import org.residentialarea.model.CommonResponseModel;
import org.residentialarea.model.EmergencyRequestCreateRequestBody;
import org.residentialarea.model.EmergencyResponseModel;
import org.residentialarea.model.PageModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "EMERGENCY", url = "http://localhost:8081", path = "/v1/emergency-request")
public interface EmergencyRequestFeignService {

    @GetMapping(value = "/read")
    PageModel<EmergencyResponseModel> findAllEmergencyRequest(@RequestParam(defaultValue = "10") Integer pageSize,
                                                              @RequestParam(defaultValue = "1") Integer pageNumber);

    @PostMapping(value = "/resolve")
    CommonResponseModel resolveEmergencyRequest(@RequestParam("id") Integer id);

    @PostMapping(value = "/reject")
    CommonResponseModel rejectEmergencyRequest(@RequestParam("id") Integer id);

    @PostMapping(value = "/create")
    CommonResponseModel createEmergencyRequest(@RequestBody EmergencyRequestCreateRequestBody requestCreateRequestBody);

    @GetMapping(value = "/read-by-username")
    PageModel<EmergencyResponseModel> findAllEmergencyRequestByUsername(@RequestParam("username") String username);
}

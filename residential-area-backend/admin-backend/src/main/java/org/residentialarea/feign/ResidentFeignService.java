package org.residentialarea.feign;

import org.residentialarea.model.ResidentCreateRequestModel;
import org.residentialarea.model.CommonResponseModel;
import org.residentialarea.model.ResidentEditRequestModel;
import org.residentialarea.model.ResidentResponseModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "RESIDENT", url = "http://localhost:8081", path = "/v1/resident")
public interface ResidentFeignService {

    @PostMapping(value = "/create")
    CommonResponseModel createResident(@RequestBody ResidentCreateRequestModel requestBody);

    @GetMapping(value = "/read")
    List<ResidentResponseModel> readResident();

    @DeleteMapping(value = "/delete")
    CommonResponseModel deleteResident(@RequestParam("id") Integer id);

    @PostMapping(value = "/update")
    CommonResponseModel updateResident(@RequestBody ResidentEditRequestModel requestBody);
}

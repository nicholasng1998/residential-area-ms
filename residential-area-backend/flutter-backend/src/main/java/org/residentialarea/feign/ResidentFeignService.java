package org.residentialarea.feign;

import org.residentialarea.model.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "RESIDENT", url = "http://localhost:8081", path = "/v1/resident")
public interface ResidentFeignService {

    @PostMapping(value = "/create")
    CommonResponseModel createResident(@RequestBody ResidentCreateRequestModel requestBody);

    @GetMapping(value = "/read")
    PageModel<ResidentResponseModel> readResident(@RequestParam("pageSize") int pageSize, @RequestParam("pageNumber") int pageNumber);

    @DeleteMapping(value = "/delete")
    CommonResponseModel deleteResident(@RequestParam("id") Integer id);

    @PostMapping(value = "/update")
    CommonResponseModel updateResident(@RequestBody ResidentEditRequestModel requestBody);
}

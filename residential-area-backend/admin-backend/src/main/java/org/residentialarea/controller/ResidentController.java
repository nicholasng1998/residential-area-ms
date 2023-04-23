package org.residentialarea.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.residentialarea.feign.ResidentFeignService;
import org.residentialarea.model.ResidentCreateRequestModel;
import org.residentialarea.model.CommonResponseModel;
import org.residentialarea.model.ResidentialResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/protected/resident")
public class ResidentController {

    private final ResidentFeignService residentFeignService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<CommonResponseModel> createResident(@RequestBody ResidentCreateRequestModel requestModel) {
        return new ResponseEntity<>(residentFeignService.createResident(requestModel), HttpStatus.OK);
    }

    @RequestMapping(value = "/read", method = RequestMethod.GET)
    public ResponseEntity<List<ResidentialResponseModel>> readResident() {
        return new ResponseEntity<>(residentFeignService.readResident(), HttpStatus.OK);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResponseEntity<CommonResponseModel> deleteResident(@RequestParam Integer id) {
        log.info("deleteResident: " + id);
        return new ResponseEntity<>(residentFeignService.deleteResident(id), HttpStatus.OK);
    }
}

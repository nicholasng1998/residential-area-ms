package org.residentialarea.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.residentialarea.feign.EmergencyRequestFeignService;
import org.residentialarea.model.EmergencyResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/protected/emergency-request")
public class EmergencyRequestController {

    private final EmergencyRequestFeignService emergencyRequestFeignService;

    @RequestMapping(value = "/read", method = RequestMethod.GET)
    @SuppressWarnings("unused")
    public ResponseEntity<List<EmergencyResponseModel>> findAllEmergencyRequest() {
        return new ResponseEntity<>(emergencyRequestFeignService.findAllEmergencyRequest(), HttpStatus.OK);
    }
}

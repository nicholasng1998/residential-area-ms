package org.residentialarea.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.residentialarea.feign.VisitorPassFeignService;
import org.residentialarea.model.EmergencyResponseModel;
import org.residentialarea.model.VisitorPassResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/protected/visitor-pass")
public class VisitorPassController {

    private final VisitorPassFeignService visitorPassFeignService;
    @RequestMapping(value = "/read", method = RequestMethod.GET)
    @SuppressWarnings("unused")
    public ResponseEntity<List<VisitorPassResponseModel>> findAllEmergencyRequest() {
        return new ResponseEntity<>(visitorPassFeignService.readAll(), HttpStatus.OK);
    }
}

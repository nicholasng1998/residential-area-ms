package org.residentialarea.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.residentialarea.feign.VisitorPassFeignService;
import org.residentialarea.model.CreateVisitorPassRequestModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/protected/visitor-pass")
public class VisitorPassController {

    private final VisitorPassFeignService visitorPassFeignService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<String> create(@RequestBody CreateVisitorPassRequestModel requestBody) {
        return new ResponseEntity<>(visitorPassFeignService.create(requestBody), HttpStatus.OK);
    }
}

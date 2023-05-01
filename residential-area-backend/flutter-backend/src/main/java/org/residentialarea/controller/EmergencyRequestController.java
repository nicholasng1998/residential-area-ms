package org.residentialarea.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.residentialarea.feign.EmergencyRequestFeignService;
import org.residentialarea.model.CommonResponseModel;
import org.residentialarea.model.EmergencyRequestCreateRequestBody;
import org.residentialarea.model.EmergencyResponseModel;
import org.residentialarea.model.PageModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/protected/emergency-request")
public class EmergencyRequestController {

    private final EmergencyRequestFeignService emergencyRequestFeignService;

    @RequestMapping(value = "/read", method = RequestMethod.GET)
    @SuppressWarnings("unused")
    public ResponseEntity<PageModel<EmergencyResponseModel>> findAllEmergencyRequest(@RequestParam(defaultValue = "10") Integer pageSize,
                                                                                     @RequestParam(defaultValue = "1") Integer pageNumber) {
        return new ResponseEntity<>(emergencyRequestFeignService.findAllEmergencyRequest(pageSize, pageNumber), HttpStatus.OK);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @SuppressWarnings("unused")
    public ResponseEntity<CommonResponseModel> createEmergencyRequest(@RequestBody EmergencyRequestCreateRequestBody requestCreateRequestBody) {
        log.info("requestCreateRequestBody: " + requestCreateRequestBody);
        if (StringUtils.isEmpty(requestCreateRequestBody.getMessage()) || StringUtils.isEmpty(requestCreateRequestBody.getTitle())) {
            return new ResponseEntity<>(emergencyRequestFeignService.createEmergencyRequest(requestCreateRequestBody), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(emergencyRequestFeignService.createEmergencyRequest(requestCreateRequestBody), HttpStatus.OK);
    }

    @RequestMapping(value = "/read-by-username", method = RequestMethod.GET)
    @SuppressWarnings("unused")
    public ResponseEntity<PageModel<EmergencyResponseModel>> findAllEmergencyRequestByUsername(@RequestParam String username) {
        return new ResponseEntity<>(emergencyRequestFeignService.findAllEmergencyRequestByUsername(username), HttpStatus.OK);
    }
}

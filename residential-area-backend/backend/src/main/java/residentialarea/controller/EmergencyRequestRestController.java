package residentialarea.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import residentialarea.model.CommonResponseModel;
import residentialarea.model.EmergencyResponseModel;
import residentialarea.service.EmergencyRequestService;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1/emergency-request")
@RequiredArgsConstructor
public class EmergencyRequestRestController {

    private final EmergencyRequestService emergencyRequestService;

    @GetMapping(value = "/read")
    @SuppressWarnings("unused")
    public ResponseEntity<List<EmergencyResponseModel>> findAllEmergencyRequest() {
        log.info("/read");
        try {
            return new ResponseEntity<>(emergencyRequestService.findAllEmergencyRequest(), HttpStatus.OK);
        } catch (Exception e) {
            log.error("error: ", e);
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/resolve")
    @SuppressWarnings("unused")
    public ResponseEntity<CommonResponseModel> resolveEmergencyRequest(@RequestParam Integer id) {
        log.info("/resolve");
        try {
            emergencyRequestService.resolve(id);
        } catch (Exception e) {
            log.error("error: ", e);
            return new ResponseEntity<>(new CommonResponseModel("Fail"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new CommonResponseModel("Fail"), HttpStatus.OK);
    }

    @PostMapping(value = "/reject")
    @SuppressWarnings("unused")
    public ResponseEntity<CommonResponseModel> rejectEmergencyRequest(@RequestParam Integer id) {
        log.info("/reject");
        try {
            emergencyRequestService.reject(id);
        } catch (Exception e) {
            log.error("error: ", e);
            return new ResponseEntity<>(new CommonResponseModel("Fail"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new CommonResponseModel("Fail"), HttpStatus.OK);
    }
}

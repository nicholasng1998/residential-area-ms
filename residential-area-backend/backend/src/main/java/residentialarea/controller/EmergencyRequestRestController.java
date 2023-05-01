package residentialarea.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import residentialarea.model.CommonResponseModel;
import residentialarea.model.EmergencyRequestCreateRequestBody;
import residentialarea.model.EmergencyResponseModel;
import residentialarea.service.EmergencyRequestService;

@Slf4j
@RestController
@RequestMapping("/v1/emergency-request")
@RequiredArgsConstructor
public class EmergencyRequestRestController {

    private final EmergencyRequestService emergencyRequestService;

    @GetMapping(value = "/read")
    @SuppressWarnings("unused")
    public ResponseEntity<Page<EmergencyResponseModel>> findAllEmergencyRequest(@RequestParam(defaultValue = "10") Integer pageSize,
                                                                                @RequestParam(defaultValue = "1") Integer pageNumber) {
        log.info("/read");
        try {
            return new ResponseEntity<>(emergencyRequestService.findAllEmergencyRequest(pageSize, pageNumber), HttpStatus.OK);
        } catch (Exception e) {
            log.error("error: ", e);
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
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

    @PostMapping(value = "/create")
    @SuppressWarnings("unused")
    public ResponseEntity<CommonResponseModel> createEmergencyRequest(@RequestBody EmergencyRequestCreateRequestBody requestCreateRequestBody) {
        log.info("/create");
        try {
            emergencyRequestService.create(requestCreateRequestBody);
        } catch (Exception e) {
            log.error("error: ", e);
            return new ResponseEntity<>(new CommonResponseModel("Fail"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new CommonResponseModel("Fail"), HttpStatus.OK);
    }

    @GetMapping(value = "/read-by-username")
    @SuppressWarnings("unused")
    public ResponseEntity<Page<EmergencyResponseModel>> findAllEmergencyRequestByUsername(@RequestParam("username") String username) {
        log.info("/read-by-username");
        try {
            return new ResponseEntity<>(emergencyRequestService.findAllEmergencyRequestByUsername(username), HttpStatus.OK);
        } catch (Exception e) {
            log.error("error: ", e);
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}

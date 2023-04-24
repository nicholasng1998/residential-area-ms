package residentialarea.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
}

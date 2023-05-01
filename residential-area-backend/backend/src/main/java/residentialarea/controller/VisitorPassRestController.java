package residentialarea.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import residentialarea.model.CreateVisitorPassRequestModel;
import residentialarea.model.VisitorPassResponseModel;
import residentialarea.service.VisitorPassService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1/visitor-pass")
@RequiredArgsConstructor
public class VisitorPassRestController {

    private final VisitorPassService visitorPassService;

    @GetMapping(value = "/read")
    @SuppressWarnings("unused")
    public ResponseEntity<Page<VisitorPassResponseModel>> readVisitorPass(@RequestParam(defaultValue = "10") Integer pageSize,
                                                                          @RequestParam(defaultValue = "1") Integer pageNumber) {
        Page<VisitorPassResponseModel> visitorPassResponseModels;
        try {
            visitorPassResponseModels = visitorPassService.readAll(pageSize, pageNumber);
            log.info("visitorPassResponseModels: " + visitorPassResponseModels);
        } catch (Exception e) {
            log.error("error: ", e);
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(visitorPassResponseModels, HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    @SuppressWarnings("unused")
    public ResponseEntity<String> createVisitorPass(@RequestBody CreateVisitorPassRequestModel createVisitorPassRequestModel) {
        String base64 = "";
        try {
            base64 = visitorPassService.create(createVisitorPassRequestModel);
            log.info("base64: " + base64);
        } catch (Exception e) {
            log.error("error: ", e);
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(base64, HttpStatus.OK);
    }

    @GetMapping(value = "/read-by-username")
    @SuppressWarnings("unused")
    public ResponseEntity<Page<VisitorPassResponseModel>> readVisitorPassByUsername(@RequestParam("username") String username) {
        Page<VisitorPassResponseModel> visitorPassResponseModels;
        try {
            visitorPassResponseModels = visitorPassService.readVisitorPassByUsername(username);
            log.info("visitorPassResponseModels: " + visitorPassResponseModels);
        } catch (Exception e) {
            log.error("error: ", e);
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(visitorPassResponseModels, HttpStatus.OK);
    }
}

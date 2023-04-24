package residentialarea.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    public ResponseEntity<List<VisitorPassResponseModel>> readVisitorPass() {
        List<VisitorPassResponseModel> visitorPassResponseModels;
        try {
            visitorPassResponseModels = visitorPassService.readAll();
            log.info("visitorPassResponseModels: " + visitorPassResponseModels);
        } catch (Exception e) {
            log.error("error: ", e);
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(visitorPassResponseModels, HttpStatus.OK);
    }
}

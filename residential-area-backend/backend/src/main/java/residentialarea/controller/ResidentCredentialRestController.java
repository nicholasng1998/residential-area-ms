package residentialarea.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import residentialarea.model.ResidentCredentialModel;
import residentialarea.model.ResidentCredentialCreateRequestBody;
import residentialarea.service.ResidentCredentialService;

@Slf4j
@RestController
@RequestMapping("/v1/resident-credential")
@RequiredArgsConstructor
public class ResidentCredentialRestController {

    private final ResidentCredentialService residentCredentialService;

    @PostMapping(value = "/create")
    @SuppressWarnings("unused")
    public ResponseEntity<String> createClientCredential(@RequestBody ResidentCredentialCreateRequestBody requestBody) {
        try {
            residentCredentialService.createClientCredential(requestBody);
        } catch (Exception e) {
            log.error("error: ", e);
            return new ResponseEntity<>("Fail", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @GetMapping(value = "/get")
    @SuppressWarnings("unused")
    public ResponseEntity<ResidentCredentialModel> getClientCredential(@RequestParam String username) {
        ResidentCredentialModel residentCredentialModel = new ResidentCredentialModel();
        try {
            residentCredentialModel = residentCredentialService.getClientCredential(username);
        } catch (Exception e) {
            log.error("error: ",  e);
            return new ResponseEntity<>(residentCredentialModel, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(residentCredentialModel, HttpStatus.OK);
    }
}

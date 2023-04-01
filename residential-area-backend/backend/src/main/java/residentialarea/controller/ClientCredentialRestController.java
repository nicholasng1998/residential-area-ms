package residentialarea.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import residentialarea.model.ClientCredentialModel;
import residentialarea.model.CreateClientCredentialRequestBody;
import residentialarea.service.ClientCredentialService;

@Slf4j
@RestController
@RequestMapping("/v1/client-credential")
@RequiredArgsConstructor
public class ClientCredentialRestController {

    private final ClientCredentialService clientCredentialService;

    @PostMapping(value = "/create")
    public ResponseEntity<String> createClientCredential(@RequestBody CreateClientCredentialRequestBody requestBody) {
        try {
            clientCredentialService.createClientCredential(requestBody);
        } catch (Exception e) {
            log.error("error: ", e);
            return new ResponseEntity<>("Fail", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @GetMapping(value = "/get")
    public ResponseEntity<ClientCredentialModel> getClientCredential(@RequestParam String username) {
        ClientCredentialModel clientCredentialModel = new ClientCredentialModel();
        try {
            clientCredentialModel = clientCredentialService.getClientCredential(username);
        } catch (Exception e) {
            log.error("error: ",  e);
            return new ResponseEntity<>(clientCredentialModel, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(clientCredentialModel, HttpStatus.OK);
    }
}

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
@RequestMapping("/client-credential")
@RequiredArgsConstructor
public class ClientCredentialRestController {

    private ClientCredentialService clientCredentialService;

    @PostMapping(value = "/create")
    public ResponseEntity<String> createClientCredential(@RequestBody CreateClientCredentialRequestBody requestBody) {
        return new ResponseEntity<>(clientCredentialService.createClientCredential(requestBody), HttpStatus.OK);
    }

    @GetMapping(value = "/get")
    public ResponseEntity<ClientCredentialModel> getClientCredential(@RequestParam String username) {
        return new ResponseEntity<>(clientCredentialService.getClientCredential(username), HttpStatus.OK);
    }
}

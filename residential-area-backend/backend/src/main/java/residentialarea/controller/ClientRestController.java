package residentialarea.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import residentialarea.model.ClientCreateRequestModel;
import residentialarea.model.ClientCredentialCreateRequestBody;
import residentialarea.service.ClientService;

@Slf4j
@RestController
@RequestMapping("/v1/client")
@RequiredArgsConstructor
public class ClientRestController {

    private final ClientService clientService;

    @PostMapping(value = "/create")
    @SuppressWarnings("unused")
    public ResponseEntity<String> createClient(@RequestBody ClientCreateRequestModel requestBody) {
        try {
            clientService.createClient(requestBody);
        } catch (Exception e) {
            log.error("error: ", e);
            return new ResponseEntity<>("Fail", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }
}

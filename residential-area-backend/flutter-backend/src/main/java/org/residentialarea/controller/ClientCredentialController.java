package org.residentialarea.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.residentialarea.model.CreateClientCredentialRequestBody;
import org.residentialarea.service.ClientCredentialService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/client-credential")
public class ClientCredentialController {

    private final ClientCredentialService clientCredentialService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<String> register(@RequestBody CreateClientCredentialRequestBody requestBody) {
        return new ResponseEntity<>(clientCredentialService.registerClientCredential(requestBody), HttpStatus.OK);
    }
}

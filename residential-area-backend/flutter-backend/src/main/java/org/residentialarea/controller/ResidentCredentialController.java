package org.residentialarea.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.residentialarea.model.CreateResidentCredentialRequestBody;
import org.residentialarea.service.ResidentCredentialService;
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
public class ResidentCredentialController {

    private final ResidentCredentialService residentCredentialService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<String> register(@RequestBody CreateResidentCredentialRequestBody requestBody) {
        return new ResponseEntity<>(residentCredentialService.registerClientCredential(requestBody), HttpStatus.OK);
    }
}

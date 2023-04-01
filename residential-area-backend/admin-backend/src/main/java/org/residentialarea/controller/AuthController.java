package org.residentialarea.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/protected/auth")
public class AuthController {

    @RequestMapping(value = "/check-token", method = RequestMethod.GET)
    public ResponseEntity<String> checkToken() {
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }
}

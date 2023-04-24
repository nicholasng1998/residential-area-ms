package org.residentialarea.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.residentialarea.feign.StatementFeignService;
import org.residentialarea.model.VisitorPassResponseModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/protected/statement")
public class StatementController {

    private final StatementFeignService statementFeignService;

    @RequestMapping(value = "/read", method = RequestMethod.GET)
    @SuppressWarnings("unused")
    public ResponseEntity<byte[]> getStatement(@RequestParam Integer year, @RequestParam Integer month) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "statement.pdf");
        return new ResponseEntity<>(statementFeignService.getStatement(year, month), HttpStatus.OK);
    }
}

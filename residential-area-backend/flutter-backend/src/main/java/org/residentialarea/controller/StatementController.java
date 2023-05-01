package org.residentialarea.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.residentialarea.feign.StatementFeignService;
import org.residentialarea.model.PageModel;
import org.residentialarea.model.StatementResponseModel;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/protected/statement")
public class StatementController {

    private final StatementFeignService statementFeignService;

    @RequestMapping(value = "/read-by-username", method = RequestMethod.GET)
    @SuppressWarnings("unused")
    public ResponseEntity<PageModel<StatementResponseModel>> getPendingStatement(@RequestParam("username") String username) {
        log.info("username: " + username);
        return new ResponseEntity<>(statementFeignService.getPendingStatement(username), HttpStatus.OK);
    }
}

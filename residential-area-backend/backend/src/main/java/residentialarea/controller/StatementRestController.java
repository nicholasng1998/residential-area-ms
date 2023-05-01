package residentialarea.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import residentialarea.model.StatementResponseModel;
import residentialarea.service.StatementService;

@Slf4j
@RestController
@RequestMapping("/v1/statement")
@RequiredArgsConstructor
public class StatementRestController {

    private final StatementService statementService;

    @GetMapping(value = "/read")
    @SuppressWarnings("unused")
    public ResponseEntity<Resource> getStatement(@RequestParam Integer year, @RequestParam Integer month) {
        log.info("year: " + year + " month: " + month);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=example.pdf");
        return new ResponseEntity<>(statementService.generateStatement(year, month), headers, HttpStatus.OK);
    }

    @GetMapping(value = "/read-pending-statement")
    @SuppressWarnings("unused")
    public ResponseEntity<Page<StatementResponseModel>> getPendingStatement(@RequestParam String username) {
        return new ResponseEntity<>(statementService.getStatementByUsername(username), HttpStatus.OK);
    }

}

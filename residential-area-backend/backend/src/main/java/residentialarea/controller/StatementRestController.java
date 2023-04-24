package residentialarea.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import residentialarea.model.NoticeCreateRequestBody;
import residentialarea.service.StatementService;

import java.io.ByteArrayOutputStream;

@Slf4j
@RestController
@RequestMapping("/v1/statement")
@RequiredArgsConstructor
public class StatementRestController {

    private final StatementService statementService;

    @GetMapping(value = "/read")
    @SuppressWarnings("unused")
    public ResponseEntity<byte[]> getStatement(@RequestParam Integer year, @RequestParam Integer month) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            byte[] statementBytes = statementService.generateStatement(year, month);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "statement.pdf");
            return new ResponseEntity<>(statementBytes, HttpStatus.OK);
        } catch (Exception e) {
            log.error("error: ",  e);
            return new ResponseEntity<>(baos.toByteArray(), HttpStatus.BAD_REQUEST);
        }
    }

}

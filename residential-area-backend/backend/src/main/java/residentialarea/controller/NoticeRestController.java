package residentialarea.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import residentialarea.bean.NoticeBean;
import residentialarea.model.NoticeCreateRequestBody;
import residentialarea.model.NoticeUpdateRequestBody;
import residentialarea.service.NoticeService;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1/notice")
@RequiredArgsConstructor
public class NoticeRestController {

    private final NoticeService noticeService;

    @GetMapping(value = "/read")
    @SuppressWarnings("unused")
    public ResponseEntity<List<NoticeBean>> findByIsActiveAndExpiryDateAfter() {
        log.info("/read");
        try {
            return new ResponseEntity<>(noticeService.findByIsActiveAndExpiryDateAfter(), HttpStatus.OK);
        } catch (Exception e) {
            log.error("error: ", e);
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/create")
    @SuppressWarnings("unused")
    public ResponseEntity<String> createNotice(@RequestBody NoticeCreateRequestBody noticeCreateRequestBody) {
        try {
            noticeService.createNotice(noticeCreateRequestBody);
        } catch (Exception e) {
            log.error("error: ",  e);
            return new ResponseEntity<>("Fail", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @PostMapping(value = "/update")
    @SuppressWarnings("unused")
    public ResponseEntity<String> updateNotice(@RequestBody NoticeUpdateRequestBody noticeUpdateRequestBody) {
        try {
            noticeService.updateNotice(noticeUpdateRequestBody);
        } catch (Exception e) {
            log.error("error: ",  e);
            return new ResponseEntity<>("Fail", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @PostMapping(value = "/void")
    @SuppressWarnings("unused")
    public ResponseEntity<String> voidNotice(@RequestBody NoticeUpdateRequestBody noticeUpdateRequestBody) {
        try {
            noticeService.voidNotice(noticeUpdateRequestBody);
        } catch (Exception e) {
            log.error("error: ",  e);
            return new ResponseEntity<>("Fail", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @PostMapping(value = "/activate")
    @SuppressWarnings("unused")
    public ResponseEntity<String> activateNotice(@RequestBody NoticeUpdateRequestBody noticeUpdateRequestBody) {
        try {
            noticeService.activateNotice(noticeUpdateRequestBody);
        } catch (Exception e) {
            log.error("error: ",  e);
            return new ResponseEntity<>("Fail", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }
}

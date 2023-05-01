package residentialarea.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import residentialarea.bean.NoticeBean;
import residentialarea.model.*;
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
    public ResponseEntity<Page<NoticeResponseModel>> findByIsActiveAndExpiryDateAfter() {
        log.info("/read");
        try {
            return new ResponseEntity<>(noticeService.findByIsActiveAndExpiryDateAfter(), HttpStatus.OK);
        } catch (Exception e) {
            log.error("error: ", e);
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/read-all")
    @SuppressWarnings("unused")
    public ResponseEntity<Page<NoticeResponseModel>> findAll(@RequestParam(defaultValue = "10") Integer pageSize,
                                                             @RequestParam(defaultValue = "1") Integer pageNumber) {
        log.info("/read");
        try {
            return new ResponseEntity<>(noticeService.findAll(pageSize, pageNumber), HttpStatus.OK);
        } catch (Exception e) {
            log.error("error: ", e);
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/create")
    @SuppressWarnings("unused")
    public ResponseEntity<CommonResponseModel> createNotice(@RequestBody NoticeCreateRequestBody noticeCreateRequestBody) {
        try {
            noticeService.createNotice(noticeCreateRequestBody);
        } catch (Exception e) {
            log.error("error: ",  e);
            return new ResponseEntity<>(new CommonResponseModel("Fail"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new CommonResponseModel("Success"), HttpStatus.OK);
    }

    @PostMapping(value = "/update")
    @SuppressWarnings("unused")
    public ResponseEntity<CommonResponseModel> updateNotice(@RequestBody NoticeUpdateRequestBody noticeUpdateRequestBody) {
        try {
            noticeService.updateNotice(noticeUpdateRequestBody);
        } catch (Exception e) {
            log.error("error: ",  e);
            return new ResponseEntity<>(new CommonResponseModel("Fail"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new CommonResponseModel("Success"), HttpStatus.OK);
    }

    @PostMapping(value = "/void")
    @SuppressWarnings("unused")
    public ResponseEntity<CommonResponseModel> voidNotice(@RequestParam("id") int id) {
        try {
            noticeService.voidNotice(id);
        } catch (Exception e) {
            log.error("error: ",  e);
            return new ResponseEntity<>(new CommonResponseModel("Fail"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new CommonResponseModel("Success"), HttpStatus.OK);
    }

    @PostMapping(value = "/activate")
    @SuppressWarnings("unused")
    public ResponseEntity<CommonResponseModel> activateNotice(@RequestParam("id") int id) {
        try {
            noticeService.activateNotice(id);
        } catch (Exception e) {
            log.error("error: ",  e);
            return new ResponseEntity<>(new CommonResponseModel("Fail"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new CommonResponseModel("Success"), HttpStatus.OK);
    }
}

package org.residentialarea.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.residentialarea.feign.NoticeFeignService;
import org.residentialarea.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/protected/notice")
public class NoticeController {

    private final NoticeFeignService noticeFeignService;

    @RequestMapping(value = "/read", method = RequestMethod.GET)
    @SuppressWarnings("unused")
    public ResponseEntity<PageModel<NoticeResponseModel>> findByIsActiveAndExpiryDateAfter() {
        return new ResponseEntity<>(noticeFeignService.findByIsActiveAndExpiryDateAfter(), HttpStatus.OK);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @SuppressWarnings("unused")
    public ResponseEntity<CommonResponseModel> createNotice(@RequestBody NoticeCreateRequestBody noticeCreateRequestBody) {
        log.info("noticeCreateRequestBody: " + noticeCreateRequestBody);
        return new ResponseEntity<>(noticeFeignService.createNotice(noticeCreateRequestBody), HttpStatus.OK);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @SuppressWarnings("unused")
    public ResponseEntity<CommonResponseModel> updateNotice(@RequestBody NoticeUpdateRequestBody noticeUpdateRequestBody) {
        return new ResponseEntity<>(noticeFeignService.updateNotice(noticeUpdateRequestBody), HttpStatus.OK);
    }

    @RequestMapping(value = "/read-all", method = RequestMethod.GET)
    @SuppressWarnings("unused")
    public ResponseEntity<PageModel<NoticeResponseModel>> readAllNotice(@RequestParam(defaultValue = "10") Integer pageSize,
                                                                        @RequestParam(defaultValue = "1") Integer pageNumber) {
        return new ResponseEntity<>(noticeFeignService.findAllNotice(pageSize, pageNumber), HttpStatus.OK);
    }

    @RequestMapping(value = "/deactivate", method = RequestMethod.POST)
    @SuppressWarnings("unused")
    public ResponseEntity<CommonResponseModel> deactivateNotice(@RequestParam Integer id) {
        return new ResponseEntity<>(noticeFeignService.deactivateNotice(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/activate", method = RequestMethod.POST)
    @SuppressWarnings("unused")
    public ResponseEntity<CommonResponseModel> activateNotice(@RequestParam Integer id) {
        return new ResponseEntity<>(noticeFeignService.activateNotice(id), HttpStatus.OK);
    }
}

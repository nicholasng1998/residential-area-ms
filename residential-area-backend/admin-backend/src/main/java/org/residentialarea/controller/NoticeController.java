package org.residentialarea.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.residentialarea.feign.NoticeFeignService;
import org.residentialarea.model.NoticeCreateRequestBody;
import org.residentialarea.model.NoticeModel;
import org.residentialarea.model.NoticeUpdateRequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/protected/notice")
public class NoticeController {

    private final NoticeFeignService noticeFeignService;

    @RequestMapping(value = "/read", method = RequestMethod.GET)
    @SuppressWarnings("unused")
    public ResponseEntity<List<NoticeModel>> findByIsActiveAndExpiryDateAfter() {
        return new ResponseEntity<>(noticeFeignService.findByIsActiveAndExpiryDateAfter(), HttpStatus.OK);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @SuppressWarnings("unused")
    public ResponseEntity<String> createNotice(@RequestBody NoticeCreateRequestBody noticeCreateRequestBody) {
        return new ResponseEntity<>(noticeFeignService.createNotice(noticeCreateRequestBody), HttpStatus.OK);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @SuppressWarnings("unused")
    public ResponseEntity<String> updateNotice(@RequestBody NoticeUpdateRequestBody noticeUpdateRequestBody) {
        return new ResponseEntity<>(noticeFeignService.updateNotice(noticeUpdateRequestBody), HttpStatus.OK);
    }
}
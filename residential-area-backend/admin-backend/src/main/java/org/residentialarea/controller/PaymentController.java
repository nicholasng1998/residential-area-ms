package org.residentialarea.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.residentialarea.feign.PaymentFeignService;
import org.residentialarea.model.CommonResponseModel;
import org.residentialarea.model.PageModel;
import org.residentialarea.model.PaymentResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/protected/payment")
public class PaymentController {
    private final PaymentFeignService paymentFeignService;

    @RequestMapping(value = "/read", method = RequestMethod.GET)
    public ResponseEntity<PageModel<PaymentResponseModel>> readPayment(@RequestParam(defaultValue = "10") Integer pageSize,
                                                                        @RequestParam(defaultValue = "1") Integer pageNumber) {
        return new ResponseEntity<>(paymentFeignService.readPayment(pageSize, pageNumber), HttpStatus.OK);
    }

    @RequestMapping(value = "/resolve", method = RequestMethod.POST)
    @SuppressWarnings("unused")
    public ResponseEntity<CommonResponseModel> completePayment(@RequestParam Integer id) {
        return new ResponseEntity<>(paymentFeignService.completePayment(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/reject", method = RequestMethod.POST)
    @SuppressWarnings("unused")
    public ResponseEntity<CommonResponseModel> rejectPayment(@RequestParam Integer id) {
        return new ResponseEntity<>(paymentFeignService.rejectPayment(id), HttpStatus.OK);
    }

}

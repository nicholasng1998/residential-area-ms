package org.residentialarea.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.residentialarea.feign.PaymentFeignService;
import org.residentialarea.model.CommonResponseModel;
import org.residentialarea.model.CreatePaymentRequestModel;
import org.residentialarea.model.PageModel;
import org.residentialarea.model.PaymentResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/protected/payment")
public class PaymentController {
    private final PaymentFeignService paymentFeignService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<CommonResponseModel> create(@RequestBody CreatePaymentRequestModel createPaymentRequestModel) {
        return new ResponseEntity<>(paymentFeignService.createPayment(createPaymentRequestModel), HttpStatus.OK);
    }

}

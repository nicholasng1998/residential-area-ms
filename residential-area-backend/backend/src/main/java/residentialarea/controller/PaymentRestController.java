package residentialarea.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import residentialarea.model.CommonResponseModel;
import residentialarea.model.CreatePaymentRequestModel;
import residentialarea.model.PaymentResponseModel;
import residentialarea.service.PaymentService;

@Slf4j
@RestController
@RequestMapping("/v1/payment")
@RequiredArgsConstructor
public class PaymentRestController {

    private final PaymentService paymentService;

    @GetMapping(value = "/read")
    @SuppressWarnings("unused")
    public ResponseEntity<Page<PaymentResponseModel>> readPayment(@RequestParam(defaultValue = "10") Integer pageSize,
                                                                   @RequestParam(defaultValue = "1") Integer pageNumber) {
        log.info("pageSize: " + pageSize + " pageNumber: " + pageNumber);
        Page<PaymentResponseModel> paymentResponseModels;
        try {
            paymentResponseModels = paymentService.readPayments(pageSize, pageNumber);
            log.info("paymentResponseModels: " + paymentResponseModels);
        } catch (Exception e) {
            log.error("error: ", e);
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(paymentResponseModels, HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    @SuppressWarnings("unused")
    public ResponseEntity<CommonResponseModel> createPayment(@RequestBody CreatePaymentRequestModel createPaymentRequestModel) {
        Page<PaymentResponseModel> paymentResponseModels;
        try {
            paymentService.create(createPaymentRequestModel);
        } catch (Exception e) {
            log.error("error: ", e);
            return new ResponseEntity<>(new CommonResponseModel("Fail"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new CommonResponseModel("Success"), HttpStatus.OK);
    }

    @PostMapping(value = "/complete")
    @SuppressWarnings("unused")
    public ResponseEntity<CommonResponseModel> completePayment(@RequestParam Integer id) {
        log.info("/complete");
        try {
            paymentService.complete(id);
        } catch (Exception e) {
            log.error("error: ", e);
            return new ResponseEntity<>(new CommonResponseModel("Fail"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new CommonResponseModel("Fail"), HttpStatus.OK);
    }

    @PostMapping(value = "/reject")
    @SuppressWarnings("unused")
    public ResponseEntity<CommonResponseModel> rejectEmergencyRequest(@RequestParam Integer id) {
        log.info("/reject");
        try {
            paymentService.reject(id);
        } catch (Exception e) {
            log.error("error: ", e);
            return new ResponseEntity<>(new CommonResponseModel("Fail"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new CommonResponseModel("Fail"), HttpStatus.OK);
    }
}

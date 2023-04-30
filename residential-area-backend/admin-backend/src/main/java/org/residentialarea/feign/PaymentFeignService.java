package org.residentialarea.feign;

import org.residentialarea.model.CommonResponseModel;
import org.residentialarea.model.PageModel;
import org.residentialarea.model.PaymentResponseModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "PAYMENT", url = "http://localhost:8081", path = "/v1/payment")
public interface PaymentFeignService {
    @GetMapping(value = "/read")
    PageModel<PaymentResponseModel> readPayment(@RequestParam("pageSize") int pageSize, @RequestParam("pageNumber") int pageNumber);

    @PostMapping(value = "/complete")
    CommonResponseModel completePayment(@RequestParam("id") Integer id);

    @PostMapping(value = "/reject")
    CommonResponseModel rejectPayment(@RequestParam("id") Integer id);
}

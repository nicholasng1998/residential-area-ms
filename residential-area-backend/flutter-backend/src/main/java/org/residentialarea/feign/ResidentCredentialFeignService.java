package org.residentialarea.feign;

import org.residentialarea.model.ResidentCredentialModel;
import org.residentialarea.model.CreateResidentCredentialRequestBody;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "RESIDENT-CREDENTIAL", url = "http://localhost:8081", path = "/v1/resident-credential")
public interface ResidentCredentialFeignService {

    @GetMapping(value = "/get")
    ResidentCredentialModel getResidentCredentialModel(@RequestParam String username);

    @PostMapping(value = "/create")
    String createResidentCredential(@RequestBody CreateResidentCredentialRequestBody requestBody);
}

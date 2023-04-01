package org.residentialarea.feign;

import org.residentialarea.model.ClientCredentialModel;
import org.residentialarea.model.CreateClientCredentialRequestBody;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "CLIENT-CREDENTIAL", url = "http://localhost:8081", path = "/v1/client-credential")
public interface ClientCredentialFeignService {

    @GetMapping(value = "/get")
    ClientCredentialModel getClientCredentialModel(@RequestParam String username);

    @PostMapping(value = "/create")
    String createClientCredential(@RequestBody CreateClientCredentialRequestBody requestBody);
}

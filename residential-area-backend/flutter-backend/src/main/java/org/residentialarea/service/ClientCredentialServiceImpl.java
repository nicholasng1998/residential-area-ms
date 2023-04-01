package org.residentialarea.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.residentialarea.feign.ClientCredentialFeignService;
import org.residentialarea.model.CreateClientCredentialRequestBody;
import org.springframework.stereotype.Service;
@Slf4j
@Service
@RequiredArgsConstructor
public class ClientCredentialServiceImpl implements ClientCredentialService {

    private final ClientCredentialFeignService clientCredentialFeignService;

    @Override
    public String registerClientCredential(CreateClientCredentialRequestBody requestBody) {
        return clientCredentialFeignService.createClientCredential(requestBody);
    }
}

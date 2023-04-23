package org.residentialarea.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.residentialarea.feign.ClientCredentialFeignService;
import org.residentialarea.model.CreateResidentCredentialRequestBody;
import org.springframework.stereotype.Service;
@Slf4j
@Service
@RequiredArgsConstructor
public class ResidentCredentialServiceImpl implements ResidentCredentialService {

    private final ClientCredentialFeignService clientCredentialFeignService;

    @Override
    public String registerClientCredential(CreateResidentCredentialRequestBody requestBody) {
        return clientCredentialFeignService.createClientCredential(requestBody);
    }
}

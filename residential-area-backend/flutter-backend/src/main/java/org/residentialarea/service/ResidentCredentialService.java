package org.residentialarea.service;

import org.residentialarea.model.CreateResidentCredentialRequestBody;

public interface ResidentCredentialService {

    String registerClientCredential(CreateResidentCredentialRequestBody requestModel);
}

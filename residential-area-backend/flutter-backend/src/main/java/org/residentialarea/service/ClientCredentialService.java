package org.residentialarea.service;

import org.residentialarea.model.CreateClientCredentialRequestBody;

public interface ClientCredentialService {

    String registerClientCredential(CreateClientCredentialRequestBody requestModel);
}

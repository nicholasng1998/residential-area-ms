package residentialarea.service;

import residentialarea.model.ClientCredentialModel;
import residentialarea.model.CreateClientCredentialRequestBody;

public interface ClientCredentialService {

    void createClientCredential(CreateClientCredentialRequestBody requestBody) throws Exception;

    ClientCredentialModel getClientCredential(String username) throws Exception;
}

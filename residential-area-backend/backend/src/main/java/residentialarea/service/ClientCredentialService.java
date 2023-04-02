package residentialarea.service;

import residentialarea.model.ClientCredentialModel;
import residentialarea.model.ClientCredentialCreateRequestBody;

public interface ClientCredentialService {

    void createClientCredential(ClientCredentialCreateRequestBody requestBody) throws Exception;

    ClientCredentialModel getClientCredential(String username) throws Exception;
}

package residentialarea.service;

import residentialarea.model.ClientCredentialModel;
import residentialarea.model.CreateClientCredentialRequestBody;

public interface ClientCredentialService {

    String createClientCredential(CreateClientCredentialRequestBody requestBody);

    ClientCredentialModel getClientCredential(String username);
}

package residentialarea.service;

import residentialarea.model.ResidentCredentialModel;
import residentialarea.model.ResidentCredentialCreateRequestBody;

public interface ResidentCredentialService {

    void createClientCredential(ResidentCredentialCreateRequestBody requestBody) throws Exception;

    ResidentCredentialModel getClientCredential(String username) throws Exception;
}

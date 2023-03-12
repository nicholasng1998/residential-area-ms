package residentialarea.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import residentialarea.dao.ClientCredentialDao;
import residentialarea.model.ClientCredentialModel;
import residentialarea.model.CreateClientCredentialRequestBody;

@Service
@Slf4j
@RequiredArgsConstructor
public class ClientCredentialServiceImpl implements ClientCredentialService{

    private ClientCredentialDao clientCredentialDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String createClientCredential(CreateClientCredentialRequestBody requestBody) {

        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public ClientCredentialModel getClientCredential(String username) {
        return null;
    }
}

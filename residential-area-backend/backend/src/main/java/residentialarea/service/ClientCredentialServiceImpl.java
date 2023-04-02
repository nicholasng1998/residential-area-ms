package residentialarea.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import residentialarea.bean.ClientCredentialBean;
import residentialarea.dao.ClientCredentialDao;
import residentialarea.model.ClientCredentialModel;
import residentialarea.model.ClientCredentialCreateRequestBody;

import java.util.Date;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientCredentialServiceImpl implements ClientCredentialService{

    private final ClientCredentialDao clientCredentialDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createClientCredential(ClientCredentialCreateRequestBody requestBody) throws Exception {
        if (!requestBody.getPassword().equals(requestBody.getConfirmPassword())) {
            log.info("Both password mismatched.");
            throw new Exception("Both password mismatched.");
        }

        ClientCredentialBean clientCredentialBean = new ClientCredentialBean();
        clientCredentialBean.setUsername(requestBody.getUsername());
        clientCredentialBean.setPassword(requestBody.getPassword());
        clientCredentialBean.setCreatedBy(requestBody.getUsername());
        clientCredentialBean.setCreatedDate(new Date());
        clientCredentialDao.save(clientCredentialBean);
    }

    @Override
    @Transactional(readOnly = true)
    public ClientCredentialModel getClientCredential(String username) throws Exception {
        ClientCredentialModel model = new ClientCredentialModel();
        log.info("username: " + username);
        ClientCredentialBean clientCredentialBean = clientCredentialDao.findByUsername(username);
        if (clientCredentialBean == null) {
            throw new Exception("Client credential not found.");
        }

        model.setUsername(clientCredentialBean.getUsername());
        model.setPassword(clientCredentialBean.getPassword());
        return model;
    }
}

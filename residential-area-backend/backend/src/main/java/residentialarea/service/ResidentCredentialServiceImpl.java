package residentialarea.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import residentialarea.bean.ResidentCredentialBean;
import residentialarea.dao.ResidentCredentialDao;
import residentialarea.model.ResidentCredentialModel;
import residentialarea.model.ResidentCredentialCreateRequestBody;

import java.util.Date;

@Slf4j
@Service
@RequiredArgsConstructor
public class ResidentCredentialServiceImpl implements ResidentCredentialService {

    private final ResidentCredentialDao residentCredentialDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createClientCredential(ResidentCredentialCreateRequestBody requestBody) throws Exception {
        if (!requestBody.getPassword().equals(requestBody.getConfirmPassword())) {
            log.info("Both password mismatched.");
            throw new Exception("Both password mismatched.");
        }

        ResidentCredentialBean residentCredentialBean = new ResidentCredentialBean();
        residentCredentialBean.setUsername(requestBody.getUsername());
        residentCredentialBean.setPassword(requestBody.getPassword());
        residentCredentialBean.setCreatedBy(requestBody.getUsername());
        residentCredentialBean.setCreatedDate(new Date());
        residentCredentialDao.save(residentCredentialBean);
    }

    @Override
    @Transactional(readOnly = true)
    public ResidentCredentialModel getClientCredential(String username) throws Exception {
        ResidentCredentialModel model = new ResidentCredentialModel();
        log.info("username: " + username);
        ResidentCredentialBean residentCredentialBean = residentCredentialDao.findByUsername(username);
        if (residentCredentialBean == null) {
            throw new Exception("Client credential not found.");
        }

        model.setUsername(residentCredentialBean.getUsername());
        model.setPassword(residentCredentialBean.getPassword());
        return model;
    }
}

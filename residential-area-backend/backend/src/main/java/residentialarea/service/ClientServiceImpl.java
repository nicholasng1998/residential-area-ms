package residentialarea.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import residentialarea.bean.ClientBean;
import residentialarea.dao.ClientDao;
import residentialarea.model.ClientCreateRequestModel;

@Slf4j  
@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientDao clientDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createClient(ClientCreateRequestModel clientCreateRequestModel) {
        ClientBean clientBean = new ClientBean();
        BeanUtils.copyProperties(clientCreateRequestModel, clientBean);
        log.info("clientBean: " + clientBean);
        clientDao.save(clientBean);
    }

}

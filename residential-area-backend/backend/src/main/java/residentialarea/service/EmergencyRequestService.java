package residentialarea.service;

import org.springframework.data.domain.Page;
import residentialarea.model.EmergencyRequestCreateRequestBody;
import residentialarea.model.EmergencyResponseModel;

public interface EmergencyRequestService {

    Page<EmergencyResponseModel> findAllEmergencyRequest(int pageSize, int pageNumber);

    void resolve(int id);

    void reject(int id);

    void create(EmergencyRequestCreateRequestBody requestCreateRequestBody);

    Page<EmergencyResponseModel> findAllEmergencyRequestByUsername(String username);

}

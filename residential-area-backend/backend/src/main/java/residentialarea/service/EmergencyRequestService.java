package residentialarea.service;

import org.springframework.data.domain.Page;
import residentialarea.model.EmergencyResponseModel;

import java.util.List;

public interface EmergencyRequestService {

    Page<EmergencyResponseModel> findAllEmergencyRequest(int pageSize, int pageNumber);

    void resolve(int id);

    void reject(int id);
}

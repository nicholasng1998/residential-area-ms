package residentialarea.service;

import residentialarea.model.EmergencyResponseModel;

import java.util.List;

public interface EmergencyRequestService {

    List<EmergencyResponseModel> findAllEmergencyRequest();

    void resolve(int id);

    void reject(int id);
}

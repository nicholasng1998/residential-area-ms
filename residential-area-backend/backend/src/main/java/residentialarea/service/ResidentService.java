package residentialarea.service;

import residentialarea.model.ResidentCreateRequestModel;
import residentialarea.model.ResidentialResponseModel;

import java.util.List;

public interface ResidentService {

    void createResident(ResidentCreateRequestModel residentCreateRequestModel);

    List<ResidentialResponseModel> readResident();

    void deleteResident(int id);
}

package residentialarea.service;

import residentialarea.model.ResidentCreateRequestModel;
import residentialarea.model.ResidentEditRequestModel;
import residentialarea.model.ResidentResponseModel;

import java.util.List;

public interface ResidentService {

    void createResident(ResidentCreateRequestModel residentCreateRequestModel);

    List<ResidentResponseModel> readResident();

    void deleteResident(int id);

    void updateResident(ResidentEditRequestModel residentEditRequestModel);
}

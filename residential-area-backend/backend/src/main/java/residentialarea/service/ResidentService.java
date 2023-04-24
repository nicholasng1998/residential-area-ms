package residentialarea.service;

import org.springframework.data.domain.Page;
import residentialarea.model.PageableRequestModel;
import residentialarea.model.ResidentCreateRequestModel;
import residentialarea.model.ResidentEditRequestModel;
import residentialarea.model.ResidentResponseModel;

import java.util.List;

public interface ResidentService {

    void createResident(ResidentCreateRequestModel residentCreateRequestModel);

    Page<ResidentResponseModel> readResident(int pageSize, int pageNumber);

    void deleteResident(int id);

    void updateResident(ResidentEditRequestModel residentEditRequestModel);
}

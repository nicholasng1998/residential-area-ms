package residentialarea.service;

import org.springframework.data.domain.Page;
import residentialarea.model.CreateVisitorPassRequestModel;
import residentialarea.model.EmergencyResponseModel;
import residentialarea.model.VisitorPassResponseModel;

import java.util.List;

public interface VisitorPassService {

    Page<VisitorPassResponseModel> readAll(int pageSize, int pageNumber);

    String create(CreateVisitorPassRequestModel createVisitorPassRequestModel);

    Page<VisitorPassResponseModel> readVisitorPassByUsername(String username);

    void visitorComeIn(String uuid);

    void visitorGoOut(String uuid);

}

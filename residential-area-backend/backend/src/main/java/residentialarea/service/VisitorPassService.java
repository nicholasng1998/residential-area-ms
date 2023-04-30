package residentialarea.service;

import org.springframework.data.domain.Page;
import residentialarea.model.VisitorPassResponseModel;

import java.util.List;

public interface VisitorPassService {

    Page<VisitorPassResponseModel> readAll(int pageSize, int pageNumber);
}

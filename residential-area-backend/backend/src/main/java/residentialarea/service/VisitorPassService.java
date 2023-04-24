package residentialarea.service;

import residentialarea.model.VisitorPassResponseModel;

import java.util.List;

public interface VisitorPassService {

    List<VisitorPassResponseModel> readAll();
}

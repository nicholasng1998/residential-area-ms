package residentialarea.service;

import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import residentialarea.model.StatementResponseModel;

public interface StatementService {

    Resource generateStatement(Integer year, Integer month);

    Page<StatementResponseModel> getStatementByUsername(String username);
}

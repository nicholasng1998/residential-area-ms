package residentialarea.service;

import org.springframework.core.io.Resource;

public interface StatementService {

    Resource generateStatement(Integer year, Integer month);
}

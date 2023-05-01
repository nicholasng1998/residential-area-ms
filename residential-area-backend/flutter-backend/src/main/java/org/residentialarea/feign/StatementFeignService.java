package org.residentialarea.feign;

import org.residentialarea.model.PageModel;
import org.residentialarea.model.StatementResponseModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "STATEMENT", url = "http://localhost:8081", path = "/v1/statement")

public interface StatementFeignService {

    @GetMapping(value = "/read")
    Resource getStatement(@RequestParam("year") Integer year, @RequestParam("month") Integer month);

    @GetMapping(value = "/read-pending-statement")
    PageModel<StatementResponseModel> getPendingStatement(@RequestParam("username") String username);
}

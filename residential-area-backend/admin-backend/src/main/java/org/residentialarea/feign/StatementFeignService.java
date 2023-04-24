package org.residentialarea.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "STATEMENT", url = "http://localhost:8081", path = "/v1/statement")

public interface StatementFeignService {

    @GetMapping(value = "/read")
    byte[] getStatement(@RequestParam("year") Integer year, @RequestParam("year") Integer month);
}

package org.residentialarea;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@Slf4j
@EnableFeignClients
@SpringBootApplication
public class AdminBackendApplication {
    public static void main(String[] args) {
        log.info("starting up AdminBackendApplication...");
        SpringApplication.run(AdminBackendApplication.class, args);
    }
}
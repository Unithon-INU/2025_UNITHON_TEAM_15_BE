package com.unithon.team15_server;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@OpenAPIDefinition(servers = {
        @Server(url = "/", description = "Default Server URL")
})
@SpringBootApplication
@EnableFeignClients
public class Team15ServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(Team15ServerApplication.class, args);
    }

}
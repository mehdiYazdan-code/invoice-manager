package com.example.invoicemanager;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InvoiceManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(InvoiceManagerApplication.class, args);
    }

}

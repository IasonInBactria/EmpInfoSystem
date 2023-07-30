package com.example.empinfosystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class EmpInfoSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmpInfoSystemApplication.class, args);
    }

}

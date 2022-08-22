package uz.pdp.springbootexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import uz.pdp.springbootexample.controller.EmployeeController;

import java.io.File;

@SpringBootApplication
public class SpringBootExampleApplication {

    public static void main(String[] args) {


        SpringApplication.run(SpringBootExampleApplication.class, args);
    }

}

package com.connexion.open.api.demo;

//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 *  Program entry
 * @author connextion
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class ConnexionOpenApiApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(ConnexionOpenApiApplication.class, args);
    }
}
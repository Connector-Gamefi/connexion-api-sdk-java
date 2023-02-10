package com.connexion.open.api.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ConnexionOpenApiApplication.class)
public class OpenApiDemoServiceTest {

    @Autowired
    private OpenApiDemoService openApiDemoService;

    @Test
    public void openApiTest(){
        this.openApiDemoService.requestGetOpenApi();
    }
}

package com.example.faceittest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class FaceItTestApplication {

  public static void main(String[] args) {
    SpringApplication.run(FaceItTestApplication.class, args);
  }

}

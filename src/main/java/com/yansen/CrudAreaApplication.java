package com.yansen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CrudAreaApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrudAreaApplication.class, args);
    }


  /*  @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }*/


}

package com.classichotel.config;

import javax.validation.Validation;
import javax.validation.Validator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidationBean {

  @Bean
  Validator validator() {
    return Validation.buildDefaultValidatorFactory().getValidator();
  }
}

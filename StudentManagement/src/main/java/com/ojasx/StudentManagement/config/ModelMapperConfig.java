package com.ojasx.StudentManagement.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.ModelMap;

@Configuration
public class ModelMapperConfig {

    // initilized a third party library
    @Bean
    ModelMapper modelMapper(){
        return new ModelMapper();
    }
}

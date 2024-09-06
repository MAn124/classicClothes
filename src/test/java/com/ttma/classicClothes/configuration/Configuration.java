package com.ttma.classicClothes.configuration;

import com.ttma.classicClothes.Service.Impl.UserServiceImpl;
import com.ttma.classicClothes.Service.UserService;
import com.ttma.classicClothes.repository.UserRespository;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class Configuration {
    @Bean
    public UserService userService(UserRespository userRespository){
        return new UserServiceImpl(userRespository);
    }
}

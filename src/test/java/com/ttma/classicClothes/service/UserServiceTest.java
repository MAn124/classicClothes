package com.ttma.classicClothes.service;

import com.ttma.classicClothes.Service.UserService;
import com.ttma.classicClothes.configuration.Configuration;
import com.ttma.classicClothes.dto.request.UserRequest;
import com.ttma.classicClothes.dto.response.ResponseUser;
import com.ttma.classicClothes.enums.RoleEnum;
import com.ttma.classicClothes.model.User;
import com.ttma.classicClothes.repository.UserRespository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

@WebMvcTest(UserService.class)
@ContextConfiguration(classes = Configuration.class)
public class UserServiceTest {
    @MockBean
    private UserRespository userRespository;

    @Autowired
    private UserService userService;

    private UserRequest request(){
        return new UserRequest(
                "minh",
                "an",
                "an@gmail.com",
                "minhan12",
                "123456",
                RoleEnum.USER
        );
    }
    private User user(){
        return new User("minh","an","an@gmail.com","minhan12","123456",RoleEnum.USER,null,null);
    }
    @Test
    public void  testCreate_WhenSuccess_ReturnUserResponse(){
        UserRequest request= new UserRequest();
        User mockEntity = user();
        Mockito.when(userRespository.save(mockEntity)).thenReturn(mockEntity);

        long responseUser =  userService.createUser(request);
        Assertions.assertEquals(mockEntity.getEmail(),responseUser);
    }
}

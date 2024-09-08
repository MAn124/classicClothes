package com.ttma.classicClothes.Service;

import com.ttma.classicClothes.dto.request.UserRequest;
import com.ttma.classicClothes.dto.response.ResponsePage;
import com.ttma.classicClothes.dto.response.ResponseUser;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {
    UserDetailsService userDetailsService();
    long createUser(UserRequest request);
    ResponsePage<?> getAllUser(int pageNo, int pageSize);
    ResponseUser getUser(long id);
    void deleteUser(long id);
    long updateUser(long id, UserRequest request);
}

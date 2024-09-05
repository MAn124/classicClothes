package com.ttma.classicClothes.Service.Impl;

import com.ttma.classicClothes.Service.UserService;
import com.ttma.classicClothes.dto.request.UserRequest;
import com.ttma.classicClothes.dto.response.ResponseUser;
import com.ttma.classicClothes.enums.RoleEnum;
import com.ttma.classicClothes.model.User;
import com.ttma.classicClothes.repository.UserRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRespository userRespository;


    @Override
    public UserDetailsService userDetailsService() {
        return username -> userRespository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public long createUser(UserRequest request) {
        if(userRespository.findByEmail(request.getEmail()).isPresent()){
            throw  new RuntimeException("Email already exist");
        }
        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .username(request.getUsername())
                .password(request.getPassword())
                .role(RoleEnum.USER)
                .email(request.getEmail())
                .build();
        userRespository.save(user);
        return user.getId();
    }
    @Override
    public List<ResponseUser> getAllUser(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<User> users =  userRespository.findAll(pageable);
        return users.map(user -> ResponseUser.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .username(user.getUsername())
                .password(user.getPassword())
                .role(user.getRole())
                .email(user.getEmail())
                .build()).toList();
    }

    @Override
    public ResponseUser getUser(long id) {
        User user = getUserById(id);
        return ResponseUser.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .username(user.getUsername())
                .password(user.getPassword())
                .role(user.getRole())
                .email(user.getEmail())
                .build();
    }

    @Override
    public void deleteUser(long id) {
        userRespository.deleteById(id);
    }

    @Override
    public long updateUser(long id, UserRequest request) {
         User user = getUserById(id);
         user.setFirstName(request.getFirstName());
         user.setLastName(request.getLastName());
         user.setUsername(request.getUsername());
         user.setPassword(request.getPassword());
         user.setRole(request.getRole());
         user.setEmail(request.getEmail());
         userRespository.save(user);
         return user.getId();
    }


    private User getUserById(long id){
        return userRespository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }
}

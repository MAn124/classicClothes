package com.ttma.classicClothes.Service.Impl;

import com.ttma.classicClothes.Service.EmailService;
import com.ttma.classicClothes.Service.UserService;
import com.ttma.classicClothes.dto.request.ChangePasswordRequest;
import com.ttma.classicClothes.dto.request.UserRequest;
import com.ttma.classicClothes.dto.response.ResponsePage;
import com.ttma.classicClothes.dto.response.ResponseUser;
import com.ttma.classicClothes.enums.RoleEnum;
import com.ttma.classicClothes.model.User;
import com.ttma.classicClothes.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRespository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    @Override
    public UserDetailsService userDetailsService() {
        return username -> userRespository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public long createUser(UserRequest request) {
        if(userRespository.findByEmail(request.getEmail()).isPresent()){
            throw new RuntimeException("Email already exist");
        }
        if(userRespository.findByUsername(request.getUsername()).isPresent()){
            throw new RuntimeException("Username already exist");
        }
        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(RoleEnum.USER)
                .confirmCode(generateConfirmCode())
                .emailConfirmation(false)
                .email(request.getEmail())
                .build();
        emailService.sendConfirmCode(user);
        userRespository.save(user);
        return user.getId();
    }
    @Override
    public ResponsePage<?> getAllUser(int pageNo, int pageSize) {
        if(pageNo > 0){
            pageNo = pageNo - 1;
        }
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<User> users =  userRespository.findAll(pageable);
       List<ResponseUser> userList = users.map(user -> ResponseUser.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .username(user.getUsername())
                .password(user.getPassword())
                .role(user.getRole())
                .emailConfirmation(user.isEmailConfirmation())
                .confirmCode(user.getConfirmCode())
                .email(user.getEmail())
                .build()).toList();
         return ResponsePage.builder()
                 .pageNo(pageNo)
                 .pageSize(pageSize)
                 .totalPage(users.getTotalPages())
                 .items(userList)
                 .build();
    }

    @Override
    public ResponseUser getUser(long id) {
        User user = getUserById(id);
        return ResponseUser.builder()
                .id(user.getId())
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
         user.setPassword(passwordEncoder.encode(request.getPassword()));
         user.setRole(request.getRole());
         user.setEmail(request.getEmail());
         userRespository.save(user);
         return user.getId();
    }
    public void changePassword(String email, ChangePasswordRequest request){
        User user = getUserByEmail(email);
        if(!passwordEncoder.matches(request.getCurrentPassword(),user.getPassword())){
            throw new RuntimeException("password not  match");
        }
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRespository.save(user);
    }

    @Override
    public void confirmEmail(String email, String confirmCode) {
        User user = getUserByEmail(email);
        if(user.getConfirmCode().equals(confirmCode)){
            user.setEmailConfirmation(true);
            user.setConfirmCode(null);
            userRespository.save(user);
        } else {
            throw new RuntimeException("Invalid");
        }
    }
    private String generateConfirmCode(){
        Random random= new Random();
        int code = 100000 + random.nextInt(900000);
        return String.valueOf(code);
    }
    private User getUserByEmail(String email){
        return userRespository.findByEmail(email).orElseThrow(() -> new RuntimeException("Email not found"));
    }

    private User getUserById(long id){
        return userRespository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }


}

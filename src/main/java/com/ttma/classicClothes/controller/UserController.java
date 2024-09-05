package com.ttma.classicClothes.controller;

import com.ttma.classicClothes.Service.UserService;
import com.ttma.classicClothes.dto.request.UserRequest;
import com.ttma.classicClothes.dto.response.ResponseData;
import com.ttma.classicClothes.dto.response.ResponseError;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;
    @PostMapping("/create")
    public ResponseData<?> createUser(@RequestBody UserRequest request){
        try {
            return new ResponseData<>(HttpStatus.CREATED.value(),"success",userService.createUser(request));
        } catch (Exception e){
            return new ResponseError(HttpStatus.BAD_REQUEST.value(),"Failed");
        }
    }
    @GetMapping("/")
    public ResponseData<?> getAllUSer(@RequestParam(defaultValue = "0", required = false) int pageNo,
                                      @RequestParam(defaultValue = "20", required = false) int pageSize){
        try {
            return new ResponseData<>(HttpStatus.OK.value(), "success",userService.getAllUser(pageNo, pageSize));
        } catch (Exception e){
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), "Failed");
        }
    }
    @GetMapping("/{id}")
    public ResponseData<?> getUserById(@PathVariable("id") long id){
        try {
            return new ResponseData<>(HttpStatus.OK.value(), "success",userService.getUser(id));
        }catch (Exception e){
            return new ResponseError(HttpStatus.NO_CONTENT.value(), "Failed");
        }
    }
}

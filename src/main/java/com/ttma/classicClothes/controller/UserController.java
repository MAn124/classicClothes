package com.ttma.classicClothes.controller;

import com.ttma.classicClothes.Service.UserService;
import com.ttma.classicClothes.dto.request.UserRequest;
import com.ttma.classicClothes.dto.response.ResponseData;
import com.ttma.classicClothes.dto.response.ResponseError;
import io.swagger.v3.oas.annotations.Operation;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;
    @Operation(summary = "Create new user ", description = "Create new user")
    @PostMapping("/create")
    public ResponseData<?> createUser(@RequestBody UserRequest request){
        try {
            return new ResponseData<>(HttpStatus.CREATED.value(),"success",userService.createUser(request));
        } catch (Exception e){
            return new ResponseError(HttpStatus.BAD_REQUEST.value(),"Failed");
        }
    }
    @Operation(summary = "Get all user ", description = "Get all user with pageNo and pageSize")
    @GetMapping("/")
    public ResponseData<?> getAllUSer(@RequestParam(defaultValue = "0", required = false) int pageNo,
                                      @RequestParam(defaultValue = "20", required = false) int pageSize){
        try {
            return new ResponseData<>(HttpStatus.OK.value(), "success",userService.getAllUser(pageNo, pageSize));
        } catch (Exception e){
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), "Failed");
        }
    }
    @Operation(summary = "Find user by id", description = "Get user by id")
    @GetMapping("/{id}")
    public ResponseData<?> getUserById(@PathVariable("id") long id){
        try {
            return new ResponseData<>(HttpStatus.OK.value(), "success",userService.getUser(id));
        }catch (Exception e){
            return new ResponseError(HttpStatus.NO_CONTENT.value(), "Failed");
        }
    }
    @Operation(summary = "Update user by id ", description = "Update user by find id ")
    @PutMapping("update/{id}")
    public ResponseData<?> updateUser(@PathVariable("id") long id,
                                      @RequestBody UserRequest request){
        try {
            return new ResponseData<>(HttpStatus.CREATED.value(), "success",userService.updateUser(id, request));
        }catch (Exception e){
            return new ResponseError(HttpStatus.NO_CONTENT.value(), "Failed");
        }
    }
    @Operation(summary = "Delete user ", description = "Delete user by id")
    @DeleteMapping("/{id}")
    public ResponseData<?> deleteUser(@PathVariable("id") long id){
        try {
            userService.deleteUser(id);
            return new ResponseData<>(HttpStatus.CREATED.value(), "success");
        }catch (Exception e){
            return new ResponseError(HttpStatus.NO_CONTENT.value(), "Failed");
        }
    }
}

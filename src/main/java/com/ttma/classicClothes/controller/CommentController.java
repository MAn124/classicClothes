package com.ttma.classicClothes.controller;

import com.ttma.classicClothes.Service.CommentService;
import com.ttma.classicClothes.dto.request.CommentRequest;
import com.ttma.classicClothes.dto.response.ResponseData;
import com.ttma.classicClothes.dto.response.ResponseError;
import com.ttma.classicClothes.model.User;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comment")
public class CommentController {
    private final CommentService commentService;
    @Operation(summary = "Add new comment ", description = "create new comment with product id")
    @PostMapping("/product/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseData<?> addComment(@PathVariable("id") long id,
                                      @AuthenticationPrincipal UserDetails userDetails,
                                      @RequestBody CommentRequest request){
        try {
            Long userId = ((User) userDetails).getId();
            return new ResponseData<>(HttpStatus.CREATED.value(), "success",commentService.addComment(userId,id,request));
        } catch (Exception e){
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), "Failed");
        }
    }
    @Operation(summary = "Get all comment ", description = "Get all comment with product id")
    @GetMapping("/product/{id}")
    public ResponseData<?> getCommentByProductId(@PathVariable("id")long id){
        try {
            return new ResponseData<>(HttpStatus.OK.value(), "success",commentService.getCommentByProductId(id));
        }catch (Exception e){
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), "Failed");
        }
    }
}

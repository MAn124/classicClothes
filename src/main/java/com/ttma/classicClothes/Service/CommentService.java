package com.ttma.classicClothes.Service;

import com.ttma.classicClothes.dto.request.CommentRequest;
import com.ttma.classicClothes.dto.response.ResponseComment;
import com.ttma.classicClothes.model.Comment;

import java.util.List;

public interface CommentService {
    long addComment(long userId, long productId, CommentRequest request);

    List<ResponseComment> getCommentByProductId(long id);
}

package com.ttma.classicClothes.Service.Impl;

import com.ttma.classicClothes.Service.CommentService;
import com.ttma.classicClothes.dto.request.CommentRequest;
import com.ttma.classicClothes.dto.response.ResponseComment;
import com.ttma.classicClothes.model.Comment;
import com.ttma.classicClothes.model.Product;
import com.ttma.classicClothes.model.User;
import com.ttma.classicClothes.repository.CommentRepository;
import com.ttma.classicClothes.repository.ProductRepository;
import com.ttma.classicClothes.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CommentRepository commentRepository;
    @Override
    public long addComment(long userId, long productId, CommentRequest request) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));
        Comment comment = Comment.builder()
                .content(request.getContent())
                .score(request.getScore())
                .user(user)
                .product(product)
                .build();
        commentRepository.save(comment);
        return comment.getId();
    }

    @Override
    public List<ResponseComment> getCommentByProductId(long id) {
        List<Comment> comments = commentRepository.findByProductId(id);
        return comments.stream().map(comment -> ResponseComment.builder()
                .id(comment.getId())
                .userId(comment.getUser().getId())
                .score(comment.getScore())
                .content(comment.getContent())
                .build()).toList();
    }
}

package com.ttma.classicClothes.repository;

import com.ttma.classicClothes.dto.request.CommentRequest;
import com.ttma.classicClothes.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByProductId(long id);
}

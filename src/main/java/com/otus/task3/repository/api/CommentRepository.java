package com.otus.task3.repository.api;

import com.otus.task3.model.entity.Comment;

import java.util.List;

public interface CommentRepository {

    List<Comment> findAllByBookId(Long bookId);

    Comment findCommentByIdInBookById(Long bookId, Long commentId);

    void deleteCommentByIdInBookById(Long bookId, Long commentId);

    Comment save(Long bookId, String comment);

    Comment update(Long commentId,String comment);
}

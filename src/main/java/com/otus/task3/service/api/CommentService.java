package com.otus.task3.service.api;

import com.otus.task3.model.dto.CommentDto;

import java.util.List;

public interface CommentService {

    List<CommentDto> findAllByBookId(Long bookId);

    CommentDto findCommentByIdInBookById(Long bookId, Long commentId);

    void deleteCommentByIdInBookById(Long bookId, Long commentId);

    CommentDto save(Long bookId, String comment);

    CommentDto update(Long commentId, String comment);

}

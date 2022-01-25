package com.repositories;

import com.pojos.Comment;

import java.util.Set;

public interface CommentRepository {
    boolean addComment(Comment comment);

    Set<Comment> listComment(int statsType, String kw, int page, int pageSize, int codeType, String code);

    int countComments(int statsType, String kw, int codeType, String code);

    Set<Comment> getComments(String kw, int page, int limit);

    int getTotal(String kw);

    Comment getCommentByCode(String code);

    boolean updateComment(Comment comment);
}

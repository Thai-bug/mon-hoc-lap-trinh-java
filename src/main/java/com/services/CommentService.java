package com.services;

import com.pojos.Comment;

import java.util.Set;

public interface CommentService {
    boolean addComment(int type, String code, String content);

    Set<Comment> listComments(int statusType , String kw, int page, int limit, int codeType, String code);

    int countComments(int statusType, String kw, int codeType, String code);
}

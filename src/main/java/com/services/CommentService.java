package com.services;

import com.pojos.Comment;
import com.pojos.Drink;

import java.util.Set;

public interface CommentService {
    boolean addComment(int type, String code, String content, int stars);

    Set<Comment> listComments(int statusType , String kw, int page, int limit, int codeType, String code);

    int countComments(int statusType, String kw, int codeType, String code);

    Set<Comment> getComments(String kw, int page, int length);

    int getTotal(String kw);
}

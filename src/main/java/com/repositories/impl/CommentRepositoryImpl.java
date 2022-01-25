package com.repositories.impl;

import com.pojos.Comment;
import com.repositories.CommentRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.util.HashSet;
import java.util.Set;

@Repository
@Transactional
public class CommentRepositoryImpl implements CommentRepository {
    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Override
    public boolean addComment(Comment comment) {
        Session session = sessionFactory.getObject().getCurrentSession();
        Transaction tx;
        try {
            session.save(String.valueOf(Comment.class), comment);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public Set<Comment> listComment(int statsType, String kw, int page, int limit, int codeType, String code) {
        Session session = sessionFactory.getObject().getCurrentSession();
        String queryStr = "select comment.* from comment\n";
        switch (codeType) {
            case 1: // sanh
                queryStr += " inner join lobby on lobby.id = comment.lobby_id\n";
                break;
        }

        queryStr += "where lower(comment.content) like :kw and lobby.code like :code\n";

        queryStr += (statsType == 1) ? " and comment.status = true\n" : "";

        Query q = session.createNativeQuery(
                queryStr + "order by comment.created_at desc\n " +
                        "limit :limit offset :offset", Comment.class
        );
        q.setParameter("kw", "%" + kw + "%");
        q.setParameter("code", code);
        q.setParameter("limit", limit);
        q.setParameter("offset", (page - 1) * limit);
        return (Set<Comment>) new HashSet<>(q.getResultList());
    }

    @Override
    public int countComments(int statsType, String kw , int codeType, String code) {
        Session session = sessionFactory.getObject().getCurrentSession();
        String queryStr = "select count(*) from comment\n";
        switch (codeType) {
            case 1: // sanh
                queryStr += " inner join lobby on lobby.id = comment.lobby_id\n";
                break;
        }

        queryStr += "where lower(comment.content) like :kw and lobby.code like :code\n";

        queryStr += (statsType == 1) ? " and comment.status = true\n" : "";

        Query q = session.createNativeQuery(
                queryStr
        );
        q.setParameter("kw", "%" + kw + "%");
        q.setParameter("code", code);
        return ((Number) q.getSingleResult()).intValue();
    }
}

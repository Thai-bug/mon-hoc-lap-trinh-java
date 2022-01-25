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
        String typeStr = "";

        switch (codeType) {
            case 1: // sanh
                queryStr += " inner join lobby on lobby.id = comment.lobby_id\n";
                typeStr = "and lobby.code like :code\n";
                break;
            case 2: // do uong
                queryStr += " inner join drink on drink.id = comment.drink_id\n";
                typeStr = "and drink.code like :code\n";
                break;
            case 3: // thuc an
                queryStr += " inner join food on food.id = comment.food_id\n";
                typeStr = "and food.code like :code\n";
                break;
            case 4: // dich vu
                queryStr += " inner join service on service.id = comment.service_id\n";
                typeStr = "and service.code like :code\n";
                break;
        }

        queryStr += "where lower(comment.content) like :kw\n" + typeStr;

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
        String typeStr = "";
        switch (codeType) {
            case 1: // sanh
                queryStr += " inner join lobby on lobby.id = comment.lobby_id\n";
                typeStr = "and lobby.code like :code\n";
                break;
            case 2: // do uong
                queryStr += " inner join drink on drink.id = comment.drink_id\n";
                typeStr = "and drink.code like :code\n";
                break;
            case 3: // thuc an
                queryStr += " inner join food on food.id = comment.food_id\n";
                typeStr = "and food.code like :code\n";
                break;
            case 4: // dich vu
                queryStr += " inner join service on service.id = comment.service_id\n";
                typeStr = "and service.code like :code\n";
                break;
        }

        queryStr += "where lower(comment.content) like :kw\n" + typeStr;

        queryStr += (statsType == 1) ? " and comment.status = true\n" : "";

        Query q = session.createNativeQuery(
                queryStr
        );
        q.setParameter("kw", "%" + kw + "%");
        q.setParameter("code", code);
        return ((Number) q.getSingleResult()).intValue();
    }
}

package com.repositories.impl;

import com.pojos.Comment;
import com.pojos.Lobby;
import com.repositories.CommentRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
}

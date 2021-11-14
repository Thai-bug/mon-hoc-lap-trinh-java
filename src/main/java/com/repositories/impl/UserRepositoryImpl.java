package com.repositories.impl;

import com.pojos.Product;
import com.pojos.User;
import com.repositories.UserRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {
    @Autowired
    LocalSessionFactoryBean sessionFactory;

    @Override
    public List<User> getUserByPhone(String phone) {
        Session session = sessionFactory.getObject().openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Product> query = builder.createQuery(Product.class);
        Root root = query.from(Product.class);
        query = query.select(root);

        Predicate p = builder.like(root.get("phone_number").as(String.class), phone);
        query = query.where(p);
        Query q = session.createQuery(query);
        return q.getResultList();
    }
}
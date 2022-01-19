package com.repositories.impl;

import com.pojos.Bill;
import com.pojos.Drink;
import com.pojos.Type;
import com.repositories.TypeRepository;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.HashSet;
import java.util.Set;

@Repository
@Transactional
public class TypeRepositoryImpl implements TypeRepository {
    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Override
    public Set<Type> getActiveType(String name, int page) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Type> query = builder.createQuery(Type.class);
        Root root = query.from(Type.class);
        query = query.select(root);

        Predicate p =
                builder.and(
                        builder.like(
                                builder.lower(root.get("title").as(String.class)
                                ), "%" + name + "%")
                );
        query = query.where(p);
        Query q = session
                .createQuery(query)
                .setFirstResult((page - 1) * 5)
                .setMaxResults(5);

        return (Set<Type>) new HashSet<>(q.getResultList());
    }

}

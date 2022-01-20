package com.repositories.impl;

import com.pojos.Employee;
import com.pojos.Food;
import com.repositories.FoodRepository;
import org.hibernate.Session;
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
public class FoodRepositoryImpl implements FoodRepository {
    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Override
    public Set<Food> getFoods(String kw, int page) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Food> query = builder.createQuery(Food.class);
        Root root = query.from(Food.class);
        query = query.select(root);

        Predicate p = builder.like(root.get("name").as(String.class), "%" + kw + "%");
        query = query.where(p);
        Query q = session.createQuery(query);
        return (Set<Food>) new HashSet<>(q.getResultList());
    }

    @Override
    public int getFoodCount(String kw) {
        Session session = sessionFactory.getObject().getCurrentSession();
        Query q = session.createQuery("select count(*) from Food where name like :kw");

        q.setParameter("kw", "%" + kw + "%");
        return Integer.parseInt(q.getSingleResult().toString());
    }

    @Override
    public Food getFoodById(int id) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Food> query = builder.createQuery(Food.class);
        Root root = query.from(Food.class);
        query = query.select(root);

        Predicate p = builder.equal(root.get("id").as(Integer.class), id);
        query = query.where(p);

        return session.createQuery(query).getSingleResult();
    }

    @Override
    public boolean update(Food food) {
        Session session = sessionFactory.getObject().getCurrentSession();
        try {
            session.update(food);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean add(Food food) {
        Session session = sessionFactory.getObject().getCurrentSession();
        try {
            session.save(food);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Set<Food> getFoodsByName(String name, boolean status, int page) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Food> query = builder.createQuery(Food.class);
        Root root = query.from(Food.class);
        query = query.select(root);

        Predicate p =
                builder.and(
                        builder.like(
                                builder.lower(root.get("name").as(String.class)
                                ), "%" + name + "%"),
                        status ?
                                builder.isTrue(root.<Boolean>get("status")) :
                                builder.isFalse(root.<Boolean>get("status"))
                );
        query = query.where(p);
        Query q = session
                .createQuery(query)
                .setFirstResult((page - 1) * 5)
                .setMaxResults(5);

        return (Set<Food>) new HashSet<>(q.getResultList());
    }
}

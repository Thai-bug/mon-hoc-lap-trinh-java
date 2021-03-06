package com.repositories.impl;

import com.pojos.Drink;
import com.pojos.Employee;
import com.pojos.Food;
import com.pojos.Lobby;
import com.repositories.DrinkRepository;
import org.hibernate.Session;
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
public class DrinkRepositoryImpl implements DrinkRepository {
    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Override
    public Set<Drink> getDrinks(String kw, int page, int length) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Drink> query = builder.createQuery(Drink.class);
        Root root = query.from(Drink.class);
        query = query.select(root);

        Predicate p = builder.like(root.get("name").as(String.class), "%" + kw +"%");
        query = query.where(p);
        Query q = session.createQuery(query).setFirstResult((page - 1) * length).setMaxResults(length);
        return (Set<Drink>) new HashSet<>(q.getResultList());
    }

    @Override
    public int getCountDrinks(String kw) {
        Session session = sessionFactory.getObject().getCurrentSession();
        Query q = session.createQuery("select count(*) from Drink where name like :kw");

        q.setParameter("kw", "%" + kw + "%");
        return Integer.parseInt(q.getSingleResult().toString());
    }

    @Override
    public Drink getDrinkByCode(String code) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Drink> query = builder.createQuery(Drink.class);
        Root root = query.from(Drink.class);
        query = query.select(root);

        Predicate p = builder.equal(root.get("code").as(String.class), code);
        query = query.where(p);

        return session.createQuery(query).getSingleResult();
    }

    @Override
    public boolean updateDrink(Drink drink) {
        Session session = sessionFactory.getObject().getCurrentSession();
        try{
            session.update(drink);
        }
        catch (Exception err){
            return false;
        }
        return true;
    }

    @Override
    public boolean add(Drink drink) {
        Session session = sessionFactory.getObject().getCurrentSession();
        try{
            session.save(drink);
        }
        catch (Exception err){
            return false;
        }
        return true;
    }

    @Override
    public Set<Drink> getDrinksByName(String name, boolean status, int page) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Drink> query = builder.createQuery(Drink.class);
        Root root = query.from(Drink.class);
        query = query.select(root);

        Predicate p =
                builder.and(
                        builder.like(
                                builder.lower(root.get("name").as(String.class)
                                ), "%" + name + "%"),
                        status ?
                                builder.isTrue(root.<Boolean> get("status")) :
                                builder.isFalse(root.<Boolean> get("status"))
                );
        query = query.where(p);
        Query q = session
                .createQuery(query)
                .setFirstResult((page - 1) * 5)
                .setMaxResults(5);

        return (Set<Drink>) new HashSet<>( q.getResultList());
    }

    @Override
    public Set<Drink> getDrinksForClient(int page, int limit, String kw) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Drink> query = builder.createQuery(Drink.class);
        Root root = query.from(Drink.class);
        query = query.select(root);

        Expression<String> lower = builder.lower(root.get("name"));

        Predicate p = builder.and(
                builder.isTrue(root.<Boolean>get("status")),
                builder.like(
                        lower, "%" + kw + "%")
        );

        query = query.where(p);
        Query q = session.createQuery(query).setFirstResult((page - 1) * limit).setMaxResults(limit);
        return (Set<Drink>) new HashSet<>(q.getResultList());
    }

    @Override
    public int countDrinkClient(String kw) {
        Session session = sessionFactory.getObject().getCurrentSession();
        Query q = session.createQuery("select count(*) from Drink where lower(name) like :kw and status = true");

        q.setParameter("kw", "%" + kw + "%");
        return Integer.parseInt(q.getSingleResult().toString());
    }


    @Override
    public Drink getClientDrinkByCode(String code) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Drink> query = builder.createQuery(Drink.class);
        Root root = query.from(Drink.class);
        query = query.select(root);

        Predicate p = builder.and(
                builder.equal(root.get("code").as(String.class), code),
                builder.isTrue(root.<Boolean>get("status"))
        );

        query = query.where(p);
        Query q = session.createQuery(query);
        return (Drink) q.getSingleResult();
    }
}

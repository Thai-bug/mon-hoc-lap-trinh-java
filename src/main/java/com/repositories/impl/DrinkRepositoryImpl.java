package com.repositories.impl;

import com.pojos.Drink;
import com.pojos.Employee;
import com.repositories.DrinkRepository;
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
import java.util.List;

@Repository
@Transactional
public class DrinkRepositoryImpl implements DrinkRepository {
    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Override
    public List<Drink> getDrinks(String kw, int page) {
        Session session = sessionFactory.getObject().openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Drink> query = builder.createQuery(Drink.class);
        Root root = query.from(Drink.class);
        query = query.select(root);

        Predicate p = builder.like(root.get("name").as(String.class), "%" + kw +"%");
        query = query.where(p);
        Query q = session.createQuery(query);
        return q.getResultList();
    }

    @Override
    public int getCountDrinks(String kw) {
        Session session = sessionFactory.getObject().openSession();
        Query q = session.createQuery("select count(*) from Drink where name like :kw");

        q.setParameter("kw", "%" + kw + "%");
        return Integer.parseInt(q.getSingleResult().toString());
    }

    @Override
    public Drink getDrinkById(int id) {
        Session session = sessionFactory.getObject().openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Drink> query = builder.createQuery(Drink.class);
        Root root = query.from(Drink.class);
        query = query.select(root);

        Predicate p = builder.equal(root.get("id").as(Integer.class), id);
        query = query.where(p);

        return session.createQuery(query).getSingleResult();
    }

    @Override
    public boolean updateDrink(Drink drink) {
        Session session = sessionFactory.getObject().openSession();
        try{
            session.getTransaction().begin();
            session.update(drink);
            session.getTransaction().commit();
        }
        catch (Exception err){
            return false;
        }
        return false;
    }
}

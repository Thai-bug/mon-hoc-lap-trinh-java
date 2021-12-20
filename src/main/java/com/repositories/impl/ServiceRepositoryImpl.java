package com.repositories.impl;

import com.pojos.Food;
import com.pojos.Service;
import com.repositories.ServiceRepository;
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
public class ServiceRepositoryImpl implements ServiceRepository {
    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Override
    public Set<Service> getServices(String kw, int pgae) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Service> query = builder.createQuery(Service.class);
        Root root = query.from(Service.class);
        query = query.select(root);

        Predicate p = builder.like(root.get("name").as(String.class), "%" + kw + "%");
        query = query.where(p);
        Query q = session.createQuery(query);
        return (Set<Service>) new HashSet<>(q.getResultList());
    }

    @Override
    public int getServicesCount(String kw) {
        Session session = sessionFactory.getObject().getCurrentSession();
        Query q = session.createQuery("select count(*) from Service where name like :kw");

        q.setParameter("kw", "%" + kw + "%");
        return Integer.parseInt(q.getSingleResult().toString());
    }

    @Override
    public Service getServiceById(int id) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Service> query = builder.createQuery(Service.class);
        Root root = query.from(Service.class);
        query = query.select(root);

        Predicate p = builder.equal(root.get("id").as(Integer.class), id);
        query = query.where(p);

        return session.createQuery(query).getSingleResult();
    }

    @Override
    public boolean update(Service service) {
        Session session = sessionFactory.getObject().getCurrentSession();
        try {
            session.getTransaction().begin();
            session.update(service);
            session.getTransaction().commit();
        } catch (Exception err) {
            return false;
        }
        return true;
    }

    @Override
    public boolean add(Service service) {
        Session session = sessionFactory.getObject().getCurrentSession();
        try {
            session.getTransaction().begin();
            session.save(service);
            session.getTransaction().commit();
        } catch (Exception err) {
            System.out.println(err.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public Set<Service> getServicesByName(String name, boolean status, int page) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Service> query = builder.createQuery(Service.class);
        Root root = query.from(Service.class);
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

        return (Set<Service>) new HashSet<>(q.getResultList());
    }
}

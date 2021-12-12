package com.repositories.impl;

import com.pojos.Bill;
import com.pojos.Drink;
import com.pojos.Employee;
import com.repositories.BillRepository;
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
public class BillRepositoryImpl implements BillRepository {
    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Override
    public List<Bill> getBills() {
        Session session = sessionFactory.getObject().openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Bill> query = builder.createQuery(Bill.class);
        Root root = query.from(Bill.class);
        root.join("employee");
        query = query.select(root);

        Predicate p = builder.equal(root.get("id").as(Integer.class), 1);
        query = query.where(p);
        Query q = session.createQuery(query);
        return q.getResultList();
    }
}

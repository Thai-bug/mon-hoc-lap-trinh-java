package com.repositories.impl;

import com.pojos.Bill;
import com.pojos.Drink;
import com.pojos.Employee;
import com.repositories.BillRepository;
import com.repositories.EmployeeRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;
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
import java.util.List;

@Repository
@Transactional
public class BillRepositoryImpl implements BillRepository {
    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Bill> getBills(int page) {
        Employee loginEmployee = employeeRepository.loadLoginEmployee();
        Session session = sessionFactory.getObject().getCurrentSession();
        String query = "" +
                "with recursive child as (select\n" +
                "    employee.* from employee where employee.id = :id union\n" +
                "    select e.* from employee e join child on child.id = e.parent_id)\n" +
                "select b.* from child\n" +
                "inner join bill b on b.sale_id = child.id\n" +
                "where child.id != :id or child.id = :id \n" +
                "limit 5 offset :offset ";

        NativeQuery q = session.createNativeQuery(
                query, Bill.class
        );

        q.setParameter("id", loginEmployee.getId());
        q.setParameter("offset", (page - 1) * 5);
        return q.getResultList();
    }

    @Override
    public Bill getBill(int id) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Bill> query = builder.createQuery(Bill.class);
        Root root = query.from(Bill.class);
        root.join("employee");
        query = query.select(root);

        Predicate p = builder.equal(root.get("id").as(Integer.class), id);
        query = query.where(p);
        Query q = session.createQuery(query);
        return (Bill) q.getSingleResult();
    }


    @Override
    public Bill getBill(String code) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Bill> query = builder.createQuery(Bill.class);
        Root root = query.from(Bill.class);
        root.join("employee");
        query = query.select(root);

        Predicate p = builder.equal(root.get("code").as(String.class), code);
        query = query.where(p);
        Query q = session.createQuery(query);
        return (Bill) q.getSingleResult();
    }

    @Override
    public boolean update(Bill bill) {
        Session session = sessionFactory.getObject().getCurrentSession();
        try{

            session.update(bill);
//            session.beginTransaction();
//            session.merge(bill);
//            session.getTransaction().commit();
        }
        catch (Exception err){
            System.out.println(err.getMessage());
            return false;
        }
        return true;
    }
}

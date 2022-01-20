package com.repositories.impl;

import com.SubClass;
import com.pojos.Bill;
import com.pojos.Employee;
import com.repositories.BillRepository;
import com.repositories.EmployeeRepository;
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
import java.math.BigDecimal;
import java.util.*;

@Repository
@Transactional
public class BillRepositoryImpl implements BillRepository {
    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Set<Bill> getBills(int page) {
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
        return (Set<Bill>) new HashSet<>(q.getResultList());
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
            session.saveOrUpdate(bill);
        }
        catch (Exception err){
            System.out.println(err.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean create(Bill bill) {
        Session session = sessionFactory.getObject().getCurrentSession();
        try{
            session.save(bill);
        }
        catch (Exception err){
            System.out.println(err.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public Set<Object> countBillsByTypes() {

        Session session = sessionFactory.getObject().getCurrentSession();
        String query =  "select count(bill.id) as total, type.* from bill \n" +
                "\tjoin type on type.id = bill.type_id\n" +
                "\tGROUP BY type.id";

        NativeQuery q = session.createNativeQuery(
                query
        );

        return (Set<Object>) new HashSet<>(q.getResultList());
    }

    @Override
    public Set<SubClass> totalMoneyBillsByDays() {
        Session session = sessionFactory.getObject().getCurrentSession();
        String query =  "select sum(bill.final_money) as total, Date(bill.created_at) as date from bill \n" +
                "\tGROUP BY DATE (bill.created_at)\n" +
                "ORDER BY date ASC";

        NativeQuery q = session.createNativeQuery(
                query
        );
        List<Object> countList=q.getResultList();
        LinkedList<SubClass> lists = new LinkedList<>();

        for(Object obj: countList){
            if (obj.getClass().isArray()) {
                Object[] objects = (Object[]) obj;
                SubClass subClass = new SubClass();
                subClass.setTotal((new Double((double) objects[0])).longValue());
                subClass.setCreatedAt((Date) objects[1]);
                lists.add(subClass);
            }
        }

        return (Set<SubClass>) new HashSet<>(lists);
    }

    @Override
    public Set<SubClass> totalMoneyBillsByMonths() {
        Session session = sessionFactory.getObject().getCurrentSession();
        String query =  "select sum(bill.final_money) as total, MONTH (bill.created_at) as month, YEAR(bill.created_at) as year\n" +
                "from bill\n" +
                "GROUP BY month, year\n" +
                "order by year, month;";

        NativeQuery q = session.createNativeQuery(
                query
        );

        List<Object> countList=q.getResultList();
        LinkedList<SubClass> lists = new LinkedList<>();

        for(Object obj: countList){
            if (obj.getClass().isArray()) {
                Object[] objects = (Object[]) obj;
                SubClass subClass = new SubClass();
                subClass.setTotal((new Double((double) objects[0])).longValue());
                subClass.setMonth((int) objects[1]);
                subClass.setYear((int) objects[2]);
                lists.add(subClass);
            }
        }

        return (Set<SubClass>) new HashSet<>(lists);
    }

    @Override
    public Set<SubClass> totalMoneyBillsByQuarter() {
        Session session = sessionFactory.getObject().getCurrentSession();
        String query =  "select sum(bill.final_money) as total,QUARTER(bill.created_at) as quarter, YEAR(bill.created_at) as year\n" +
                "from bill\n" +
                "GROUP BY quarter, year\n" +
                "order by year, quarter;";

        NativeQuery q = session.createNativeQuery(
                query
        );
        List<Object> countList=q.getResultList();
        LinkedList<SubClass> lists = new LinkedList<>();

        for(Object obj: countList){
            if (obj.getClass().isArray()) {
                Object[] objects = (Object[]) obj;
                SubClass subClass = new SubClass();
                subClass.setTotal((new Double((double) objects[0])).longValue());
                subClass.setQuarter((int) objects[1]);
                subClass.setYear((int) objects[2]);
                lists.add(subClass);
            }
        }

        return (Set<SubClass>) new HashSet<>(lists);
    }
}

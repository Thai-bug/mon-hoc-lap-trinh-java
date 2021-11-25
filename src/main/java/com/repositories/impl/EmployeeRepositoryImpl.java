package com.repositories.impl;

import com.pojos.Employee;
import com.repositories.EmployeeRepository;
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
public class EmployeeRepositoryImpl implements EmployeeRepository {
    @Autowired
    LocalSessionFactoryBean sessionFactory;

    @Override
    public List<Employee> getEmployeesByPhone(String phone) {
        Session session = sessionFactory.getObject().openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Employee> query = builder.createQuery(Employee.class);
        Root root = query.from(Employee.class);
        query = query.select(root);

        Predicate p = builder.like(root.get("phoneNumber").as(String.class), phone);
        query = query.where(p);
        Query q = session.createQuery(query);
        return q.getResultList();
    }

    @Override
    public List<Employee> getEmployeesByEmail(String email) {
        Session session = sessionFactory.getObject().openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Employee> query = builder.createQuery(Employee.class);
        Root root = query.from(Employee.class);
        query = query.select(root);

        Predicate p = builder.like(root.get("email").as(String.class), email);
        query = query.where(p);
        Query q = session.createQuery(query);
        return q.getResultList();
    }

    @Override
    public List<Employee> getEmployees(int page, String kw) {
        Session session = sessionFactory.getObject().openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Employee> query = builder.createQuery(Employee.class);
        Root root = query.from(Employee.class);
        query = query.select(root);

        query.where(
                builder.or(
                        builder.like(root.get("firstName").as(String.class), String.format("%%%s%%", kw)),
                        builder.like(root.get("lastName").as(String.class), String.format("%%%s%%", kw))
                ));

        Query q = session.createQuery(query);
        q.setMaxResults(5);
        q.setFirstResult((page - 1) * 5);
        return q.getResultList();
    }

    @Override
    public long getCountAllEmployees(String kw) {
        Session session = sessionFactory.getObject().openSession();
        Query q = session.createQuery("Select Count(*) From Employee Where lastName like :kw or firstName like :kw");
        q.setParameter("kw", "%" + kw +"%");
        return Long.parseLong(q.getSingleResult().toString());
    }

    @Override
    public Employee getEmployeeById(int id) {
        Session session = sessionFactory.getObject().openSession();
        Employee emp = session.get(Employee.class, id);

        return emp;
    }

    @Override
    public boolean updateEmployeeAvatar(Employee employee) {
        Session session = sessionFactory.getObject().openSession();
        try {
//            Employee emp = session.get(Employee.class, employee.getId());
//            emp.setStatus(employee.getStatus());
            session.getTransaction().begin();
            session.update(employee);
            session.getTransaction().commit();
            return true;
        }
        catch (Exception e) {
            System.out.print(e.getMessage());
            return false;
        }
    }
}

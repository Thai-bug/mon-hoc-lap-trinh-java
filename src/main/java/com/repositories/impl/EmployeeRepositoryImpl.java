package com.repositories.impl;

import com.pojos.Employee;
import com.repositories.EmployeeRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.util.HashSet;
import java.util.Set;
import java.util.Set;

@Repository
@Transactional
public class EmployeeRepositoryImpl implements EmployeeRepository {
    @Autowired
    LocalSessionFactoryBean sessionFactory;

    @Override
    public Set<Employee> getEmployeesByPhone(String phone) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Employee> query = builder.createQuery(Employee.class);
        Root root = query.from(Employee.class);
        query = query.select(root);

        Predicate p = builder.like(root.get("phoneNumber").as(String.class), phone);
        query = query.where(p);
        Query q = session.createQuery(query);
        return (Set<Employee>) new HashSet<>(q.getResultList());
    }

    @Override
    public Employee getEmployeeByEmail(String email) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Employee> query = builder.createQuery(Employee.class);
        Root root = query.from(Employee.class);
        query = query.select(root);

        Predicate p = builder.equal(root.get("email").as(String.class), email);
        query = query.where(p);
        Query q = session.createQuery(query);
        Employee emp = (Employee) q.getSingleResult();
        return emp;
    }

    @Override
    public Set<Employee> getEmployees(int page, String kw) {
        Employee loginEmployee = loadLoginEmployee();
        Session session = sessionFactory.getObject().getCurrentSession();
        String query = "" +
                "WITH RECURSIVE child AS ( SELECT employee.*  FROM employee WHERE employee.id = :id \n" +
                "and (employee.last_name like :kw or employee.first_name like :kw) " +
                " UNION\n" +
                "                          SELECT\n" +
                "                              e.*\n" +
                "                          FROM\n" +
                "                              employee e\n" +
                "                                  JOIN child ON child.id = e.parent_id\n" +
                ")SELECT\n" +
                "      *\n" +
                "FROM\n" +
                "    child where child.id != :id \n" +
                "order by child.id \n" +
                "limit 5 offset :offset ;\n";
        NativeQuery q = session.createNativeQuery(
                query, Employee.class
        );

        q.setParameter("kw", "%" + kw + "%");
        q.setParameter("id", loginEmployee.getId());
        q.setParameter("offset", (page - 1) * 5);
        return (Set<Employee>) new HashSet<>(q.getResultList());
    }

    @Override
    public long getCountAllEmployees(String kw) {
        Employee loginEmployee = loadLoginEmployee();
        Session session = sessionFactory.getObject().getCurrentSession();
        String query = "WITH RECURSIVE child AS ( SELECT employee.*  FROM employee WHERE " +
                "employee.id = :id " +
                "and (employee.last_name like :kw or employee.first_name like :kw) " +
                "UNION\n" +
                "                          SELECT\n" +
                "                              e.*\n" +
                "                          FROM\n" +
                "                              employee e\n" +
                "                                  JOIN child ON child.id = e.parent_id\n" +
                ")SELECT\n" +
                "      count(*)\n" +
                "FROM\n" +
                "    child where child.id != :id";
        NativeQuery q = session.createNativeQuery(
                query
        );
        q.setParameter("kw", "%" + kw + "%");
        q.setParameter("id", loginEmployee.getId());
        return Long.parseLong(q.getSingleResult().toString());
    }

    @Override
    public Employee getEmployeeById(int id) {
        Session session = sessionFactory.getObject().getCurrentSession();
        Employee emp = session.get(Employee.class, id);
        return emp;
    }

    @Override
    public boolean updateEmployeeAvatar(Employee employee) {
        Session session = sessionFactory.getObject().getCurrentSession();
        try {
            Employee emp = session.get(Employee.class, employee.getId());
            emp.setAvatarLink(employee.getAvatarLink());
            session.update(emp);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateEmployee(Employee employee) {
        Session session = sessionFactory.getObject().getCurrentSession();
        try {
            session.update(employee);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Set<Employee> getParentsList() {
        Session session = sessionFactory.getObject().getCurrentSession();
        String query = "select * from employee where role like '%_MANAGER';";
        NativeQuery q = session.createNativeQuery(query, Employee.class);
        return (Set<Employee>) new HashSet<>(q.getResultList());
    }

    @Override
    public boolean checkChildInParent(int childId) {
        Employee employee = loadLoginEmployee();
        Session session = sessionFactory.getObject().getCurrentSession();
        String query = "WITH RECURSIVE child AS ( SELECT employee.*  FROM employee WHERE " +
                "employee.id = :parentID " +
                "UNION\n" +
                "                          SELECT\n" +
                "                              e.*\n" +
                "                          FROM\n" +
                "                              employee e\n" +
                "                                  JOIN child ON child.id = e.parent_id\n" +
                ")SELECT\n" +
                "      count(*)\n" +
                "FROM\n" +
                "    child where child.id != :parentID and child.id = :id";
        NativeQuery q = session.createNativeQuery(
                query
        );
        q.setParameter("parentID", employee.getId());
        q.setParameter("id", childId);
        long result = Long.parseLong(q.getSingleResult().toString());
        return result != 0;
    }

    @Override
    public boolean createEmployee(Employee employee) {
        Session session = sessionFactory.getObject().getCurrentSession();
        Transaction tx;
        try {
            session.save(String.valueOf(Employee.class), employee);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public Employee loadLoginEmployee() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return getEmployeeByEmail(authentication.getName());
    }
}

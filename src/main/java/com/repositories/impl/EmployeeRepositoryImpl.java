package com.repositories.impl;

import com.pojos.Employee;
import com.repositories.EmployeeRepository;
import org.hibernate.Session;
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
import java.util.List;
import java.util.Set;

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
    public Employee getEmployeeByEmail(String email) {
        Session session = sessionFactory.getObject().openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Employee> query = builder.createQuery(Employee.class);
        Root root = query.from(Employee.class);
        query = query.select(root);

        Predicate p = builder.equal(root.get("email").as(String.class), email);
        query = query.where(p);
        Query q = session.createQuery(query);
        Employee emp =  (Employee) q.getSingleResult();
        return emp;
    }

    @Override
    public List<Employee> getEmployees(int page, String kw) {
        Employee loginEmployee = loadLoginEmployee();
        Session session = sessionFactory.getObject().openSession();
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
                "      *\n" +
                "FROM\n" +
                "     child where child.id != :id";
        NativeQuery q = session.createNativeQuery(
                query, Employee.class
        );

        q.setParameter("kw", "%" + kw + "%");
        q.setParameter("id", loginEmployee.getId());
        return q.getResultList();
    }

    @Override
    public long getCountAllEmployees(String kw) {
        Employee loginEmployee = loadLoginEmployee();
        Session session = sessionFactory.getObject().openSession();
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
        Session session = sessionFactory.getObject().openSession();
        Employee emp = session.get(Employee.class, id);
        return emp;
    }

    @Override
    public boolean updateEmployeeAvatar(Employee employee) {
        Session session = sessionFactory.getObject().openSession();
        try {
            Employee emp = session.get(Employee.class, employee.getId());
            emp.setAvatarLink(employee.getAvatarLink());
            session.getTransaction().begin();
            session.update(emp);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateEmployee(Employee employee) {
        Session session = sessionFactory.getObject().openSession();
        try {
            session.getTransaction().begin();
            session.update(employee);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<Employee> getParentsList() {
        Session session = sessionFactory.getObject().openSession();
        String query = "select * from employee where role like '%_MANAGER';";
        NativeQuery q = session.createNativeQuery(query, Employee.class);
        return q.getResultList();
    }

    @Override
    public boolean checkChildInParent(int childId) {
        Employee employee = loadLoginEmployee();
        Session session = sessionFactory.getObject().openSession();
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
        long result =Long.parseLong(q.getSingleResult().toString());
        return result != 0;
    }

    @Override
    public Employee loadLoginEmployee() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return getEmployeeByEmail(authentication.getName());
    }
}

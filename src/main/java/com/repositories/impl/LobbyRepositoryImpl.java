package com.repositories.impl;

import com.pojos.Bill;
import com.pojos.Employee;
import com.pojos.Lobby;
import com.repositories.LobbyRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Repository
@Transactional
public class LobbyRepositoryImpl implements LobbyRepository {
    @Autowired
    LocalSessionFactoryBean sessionFactory;

    @Override
    public Set<Lobby> getLobbies(String kw, int page, int length) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Lobby> query = builder.createQuery(Lobby.class);

        Root root = query.from(Lobby.class);
        query = query.select(root);

        Predicate p = builder.like(root.get("name").as(String.class), "%" + kw + "%");
        query = query.where(p);


        return new HashSet<>(session.createQuery(query)
                .setFirstResult((page - 1) * length)
                .setMaxResults(length).getResultList());
    }

    @Override
    public int countLobby(String kw) {
        Session session = sessionFactory.getObject().getCurrentSession();
        Query q = session.createQuery("select count(*) from Lobby where name like :kw");

        q.setParameter("kw", "%" + kw + "%");
        return Integer.parseInt(q.getSingleResult().toString());
    }

    @Override
    public Lobby getLobbyByCode(String code) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Lobby> query = builder.createQuery(Lobby.class);
        Root root = query.from(Lobby.class);
        query = query.select(root);

        Predicate p = builder.equal(root.get("code").as(String.class), code);
        query = query.where(p);
        Query q = session.createQuery(query);
        return (Lobby) q.getSingleResult();
    }

    @Override
    public boolean updateLobby(Lobby lobby) {
        Session session = sessionFactory.getObject().getCurrentSession();
        try {
            session.update(lobby);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean createLobby(Lobby lobby) {
        Session session = sessionFactory.getObject().getCurrentSession();
        Transaction tx;
        try {
            session.save(String.valueOf(Lobby.class), lobby);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public Set<Lobby> getByNameWithDate(String name, Date beginDate, Date endDate, int page, int id, int seats) {
        Session session = sessionFactory.getObject().getCurrentSession();

        String additional = id == 0 ? "" : "or l.id = :id\n";

        Query q = session.createNativeQuery("select l.* from lobby l\n" +
                "left join bill b on b.lobby_id = l.id\n" +
                "where lower(l.name) like :name and l.status = true\n" +
                "and l.seats >= :seats \n" +
                "and( b.begin_date is null\n" +
                "or (b.begin_date > :endDate or b.end_date < :beginDate)\n" +
                "or(b.begin_date <= :beginDate and b.end_date >= :beginDate and b.status_id = 4)\n" +
                "or (b.begin_date <= :endDate and b.end_date >= :endDate and b.status_id = 4)\n" +
                "or (b.begin_date >= :beginDate and b.end_date <= :endDate and b.status_id = 4)\n" +
                additional +
                ")\n" +
                "group by l.id\n" +
                "limit 5 offset :page", Lobby.class);
        q.setParameter("name", "%" + name + "%");
        q.setParameter("beginDate", beginDate);
        q.setParameter("endDate", endDate);
        q.setParameter("page", (page - 1) * 5);
        q.setParameter("seats", seats);
        if (id != 0)
            q.setParameter("id", id);
        return (Set<Lobby>) new HashSet<>(q.getResultList());
    }

    @Override
    public Set<Lobby> getLobbiesForClient(int page, int limit, String kw) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Lobby> query = builder.createQuery(Lobby.class);
        Root root = query.from(Lobby.class);
        query = query.select(root);

        Expression<String> lower = builder.lower(root.get("name"));

        Predicate p = builder.and(
                builder.isTrue(root.<Boolean>get("status")),
                builder.like(
                        lower, "%" + kw + "%")
        );

        query = query.where(p);
        Query q = session.createQuery(query).setFirstResult((page - 1) * limit).setMaxResults(limit);
        return (Set<Lobby>) new HashSet<>(q.getResultList());
    }

    @Override
    public int countLobbyClient(String kw) {
        Session session = sessionFactory.getObject().getCurrentSession();
        Query q = session.createQuery("select count(*) from Lobby where lower(name) like :kw and status = true");

        q.setParameter("kw", "%" + kw + "%");
        return Integer.parseInt(q.getSingleResult().toString());
    }

    @Override
    public Lobby getClientLobbyByCode(String code) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Lobby> query = builder.createQuery(Lobby.class);
        Root root = query.from(Lobby.class);
        query = query.select(root);

        Predicate p = builder.and(
                builder.equal(root.get("code").as(String.class), code),
                builder.isTrue(root.<Boolean>get("status"))
        );

        query = query.where(p);
        Query q = session.createQuery(query);
        return (Lobby) q.getSingleResult();
    }
}

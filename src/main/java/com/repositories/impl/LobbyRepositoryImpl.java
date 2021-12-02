package com.repositories.impl;

import com.pojos.Employee;
import com.pojos.Lobby;
import com.repositories.LobbyRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
public class LobbyRepositoryImpl implements LobbyRepository {
    @Autowired
    LocalSessionFactoryBean sessionFactory;

    @Override
    public List<Lobby> getLobbies(String kw, int page) {
        Session session = sessionFactory.getObject().openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Lobby> query = builder.createQuery(Lobby.class);

        Root root = query.from(Lobby.class);
        query = query.select(root);

        Predicate p = builder.like(root.get("name").as(String.class), "%" + kw + "%");
        query = query.where(p);

        return session.createQuery(query).getResultList();
    }

    @Override
    public int countLobby(String kw) {
        Session session = sessionFactory.getObject().openSession();
        Query q = session.createQuery("select count(*) from Lobby where name like :kw");

        q.setParameter("kw", "%" + kw + "%");
        return Integer.parseInt(q.getSingleResult().toString());
    }

    @Override
    public Lobby getLobbyById(int id) {
        Session session = sessionFactory.getObject().openSession();
        Query q = session.createQuery("Select Lobby from Lobby where id = :id");
        q.setParameter("id", id);

        return (Lobby) q.getSingleResult();
    }
}

package com.repositories.impl;

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
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Lobby> query = builder.createQuery(Lobby.class);
        Root root = query.from(Lobby.class);
        query = query.select(root);

        Predicate p = builder.equal(root.get("id").as(Integer.class), id);
        query = query.where(p);
        Query q = session.createQuery(query);
        return (Lobby) q.getSingleResult();
    }

    @Override
    public boolean updateLobby(Lobby lobby) {
        Session session = sessionFactory.getObject().openSession();
        try {
            session.getTransaction().begin();
            session.update(lobby);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean createLobby(Lobby lobby) {
        Session session = sessionFactory.getObject().openSession();
        Transaction tx;
        try {
            tx = session.beginTransaction();
            session.save(String.valueOf(Lobby.class), lobby);
            tx.commit();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}

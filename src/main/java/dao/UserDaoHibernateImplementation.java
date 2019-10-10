package dao;

import models.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

// SQL key-words is UPPER CASE now
public class UserDaoHibernateImplementation implements UserDao {
    private Session session;

    public UserDaoHibernateImplementation(Session session) {
        this.session = session;
    }

    @Override
    public Optional<User> get(long id) {
        Transaction transaction = session.beginTransaction();
        Optional<User> user = Optional.of(session.get(User.class, id));

        transaction.commit();
        return user;
    }

    @Override
    public long getId(User user) {
        Transaction transaction = session.beginTransaction();
        List<User> users = session.createQuery("FROM User WHERE login=:login")
                .setParameter("login", user.getLogin())
                .list();
        transaction.commit();

        return users.size() > 0 ? users.get(0).getId() : -1;
    }

    @Override
    public List<User> getAll() {
        Transaction transaction = session.beginTransaction();
        List<User> users = session.createQuery("from User").list();
        transaction.commit();

        return users;
    }

    @Override
    public void add(User user) {
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
    }

    @Override
    public void update(User user, String[] params) {
        Transaction transaction = session.beginTransaction();
        session.createQuery("UPDATE User SET name=:name, login=:login, password=:password WHERE id=:id")
                .setParameter("name", params[0])
                .setParameter("login", params[1])
                .setParameter("password", params[2])
                .setParameter("id", user.getId())
                .executeUpdate();

        transaction.commit();
    }

    @Override
    public void delete(User user) {
        Transaction transaction = session.beginTransaction();
        session.createQuery("DELETE User WHERE id=:id")
                .setParameter("id", user.getId())
                .executeUpdate();

        transaction.commit();
    }

    @Override
    public void createTable() {
        // hbm2ddl -> create
    }

    @Override
    public void dropTable() {
        // hbm2ddl -> create (create-drop)
    }
}

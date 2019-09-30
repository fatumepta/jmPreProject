package dao;

import models.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

public class UserDaoHibernateImpl implements Dao<User> {
    private Session session;

    public UserDaoHibernateImpl(Session session) {
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
    public List<User> getAll() {
        Transaction transaction = session.beginTransaction();
        List<User> users = (List<User>) session.createQuery("from User").list();
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
        session.createQuery("update User set name=:name, login=:login, password=:password where id=:id")
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
        session.createQuery("delete User where id=:id")
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

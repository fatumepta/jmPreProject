package dao;

import models.User;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public class UserDaoHibernate implements Dao<User> {
    private Session session;

    public UserDaoHibernate(Session session) {
        this.session = session;
    }

    @Override
    public Optional<User> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public void add(User user) {

    }

    @Override
    public void update(User user, String[] params) {

    }

    @Override
    public void delete(User user) {

    }

    @Override
    public void createTable() {

    }

    @Override
    public void dropTable() {

    }
}

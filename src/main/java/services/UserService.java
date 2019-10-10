package services;

import dao.UserDao;
import models.User;
import util.UserDaoFactory;

import java.util.List;

// properties moved to the UserDaoFactory
// interface Service implemented
// add method now requests ONLY ONE user to check if it already exists (all users were requested before)
public class UserService implements Service<User> {
    private static UserService userService;
    private UserDao dao;

    private UserService(UserDao dao) {
        this.dao = dao;
    }

    public static UserService getInstance() {
        return userService == null ? userService = new UserService(UserDaoFactory.getDao()) : userService;
    }

    @Override
    public List<User> getAll() {
        return dao.getAll();
    }

    @Override
    public boolean add(User user) {
        if (getId(user) > 0) {
            return false;
        } else {
            dao.add(user);
            return true;
        }
    }

    @Override
    public long getId(User user) {
        return dao.getId(user); // -1 if the user doesn't exist
    }

    @Override
    public void edit(User user, String[] params) {
        dao.update(user, params);
    }

    @Override
    public void delete(long id) {
        dao.delete(get(id));
    }

    @Override
    public User get(long id) {
        return dao.get(id).orElse(new User());
    }

    @Override
    public void createTable() {
        dao.createTable();
    }

    @Override
    public void dropTable() {
        dao.dropTable();
    }
}

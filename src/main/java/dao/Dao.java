package dao;

import util.TableUtility;

import java.util.List;


public interface Dao<T> extends TableUtility {
    T get(long id);

    List<T> getAll();

    void add(T t);

    void update(T t, String[] params);

    void delete(T t);
}

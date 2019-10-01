package dao;

import util.TableUtility;

import java.util.List;
import java.util.Optional;


public interface Dao<T> extends TableUtility {
    Optional<T> get(long id);

    List<T> getAll();

    void add(T t);

    void update(T t, String[] params);

    void delete(T t);
}

package services;

import util.TableUtility;

import java.util.List;

public interface Service<T> extends TableUtility {
    List<T> getAll();

    boolean add(T object);

    void edit(T object, String[] params);

    void delete(long id);

    T get(long id);

    long getId(T object);
}

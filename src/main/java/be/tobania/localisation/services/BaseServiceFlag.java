package be.tobania.localisation.services;

import java.util.List;

public interface BaseServiceFlag<T> {

    void save(T t);
    void saveList(List<T> t);
    void truncateTable();
    T findByName(String name);
    List<T> findAll();
}

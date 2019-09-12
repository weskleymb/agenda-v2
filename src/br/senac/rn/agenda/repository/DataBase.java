package br.senac.rn.agenda.repository;

import java.util.List;

public interface DataBase<T> {

    List<T> findAll();

    T findById(Long id);

    void save(T t);

    void delete(T t);

    void deleteById(Long id);

}

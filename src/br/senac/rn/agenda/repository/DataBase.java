package br.senac.rn.agenda.repository;

import java.util.List;
import java.util.Optional;

public interface DataBase<T> {

    List<T> findAll();

    Optional<T> findById(Long id);

    void save(T t);

    void delete(T t);

    void deleteById(Long id);

}

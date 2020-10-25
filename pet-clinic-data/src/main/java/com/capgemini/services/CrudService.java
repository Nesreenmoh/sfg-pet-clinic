package com.capgemini.services;


import java.util.Set;

public interface CrudService<T,ID> {

    T findById(ID id);

    T save(T t);

    Set<T> findAll();

    void deleteById(ID id);

   void delete(T o);
}

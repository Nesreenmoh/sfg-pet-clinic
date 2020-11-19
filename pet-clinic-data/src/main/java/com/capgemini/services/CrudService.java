package com.capgemini.services;


import java.util.List;

public interface CrudService<T,ID> {

    T findById(ID id);

    T save(T t);

    List<T> findAll();

    void deleteById(ID id);

   void delete(T o);
}

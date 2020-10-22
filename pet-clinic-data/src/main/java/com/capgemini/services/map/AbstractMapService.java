package com.capgemini.services.map;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class AbstractMapService<T, ID> {

    protected Map<ID, T> map =  new HashMap<>();

    Set<T> findAll(){
        return new HashSet<>(map.values());
    }

    T findById(ID id){
        return map.get(id);
    }

    T save(ID id, T o){
       map.put(id, o);
       return o;
    }

    void deleteById(ID id){
        map.remove(id);
    }

    void delete(T o){
        map.entrySet().removeIf(entry -> entry.getValue().equals(o));
    }
}


package com.capgemini.services.map;

import com.capgemini.models.BaseEntity;

import java.util.*;

public abstract class AbstractMapService<T extends BaseEntity, ID extends Long> {

    protected Map<Long, T> map =  new HashMap<>();

    List<T> findAll(){
        return new ArrayList<>(map.values());
    }

    T findById(ID id){
        return map.get(id);
    }

    T save(T o){
        if(o!=null){
            if(o.getId()==null){
                o.setId(getNextID());
            }
            map.put(o.getId(), o);
        }else {
            throw new RuntimeException("Object cannot be null");
        }
       return o;
    }

    void deleteById(ID id){
        map.remove(id);
    }

    void delete(T o){
        map.entrySet().removeIf(entry -> entry.getValue().equals(o));
    }


    private long getNextID(){
        Long nextId=null;
                try{
                    nextId=   Collections.max(map.keySet())+1;
                }catch (NoSuchElementException ex){
                    nextId=1L;
                }
        return nextId;
    }
}


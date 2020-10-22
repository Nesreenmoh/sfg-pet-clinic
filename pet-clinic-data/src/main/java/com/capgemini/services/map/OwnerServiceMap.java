package com.capgemini.services.map;

import com.capgemini.models.Owner;
import com.capgemini.services.CrudService;

import java.util.Set;

public class OwnerServiceMap extends AbstractMapService<Owner, Long> implements CrudService<Owner, Long> {


    @Override
    public Owner findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Owner save(Owner owner) {
        return super.save(owner.getId(),owner);
    }

    @Override
    public Set<Owner> findAll() {
        return super.findAll();
    }

    @Override
    public void delete(Owner o) {
      super.delete(o);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}

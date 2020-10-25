package com.capgemini.services.map;


import com.capgemini.models.PetType;
import com.capgemini.services.PetTypeService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class PetTypeServiceMap extends AbstractMapService<PetType, Long>implements PetTypeService {
    @Override
    public PetType findById(Long id) {
        return super.findById(id);
    }

    @Override
    public PetType save(PetType t) {
        return super.save(t);
    }

    @Override
    public Set<PetType> findAll() {
        return super.findAll();
    }

    @Override
    public void delete(PetType o) {
        super.delete(o);
    }

    @Override
    public void deleteById(Long id) {
       super.deleteById(id);
    }
}

package com.capgemini.services.map;

import com.capgemini.models.Pet;
import com.capgemini.services.PetService;
import org.springframework.stereotype.Service;

import java.util.Set;
@Service
public class PetServiceMap extends AbstractMapService<Pet, Long> implements PetService {
    @Override
    public Pet findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Pet save(Pet pet) {
        return super.save(pet);
    }

    @Override
    public Set<Pet> findAll() {
        return super.findAll();
    }

    @Override
    public void delete(Pet o) {
        super.delete(o);
    }

    @Override
    public void deleteById(Long id) {
         super.deleteById(id);
    }
}

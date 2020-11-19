package com.capgemini.services.springdatajpa;

import com.capgemini.models.PetType;
import com.capgemini.repositories.PetTypeRepository;
import com.capgemini.services.PetTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Profile("springdatajpa")
public class PetTypeSDJpaService implements PetTypeService {

    private final PetTypeRepository petTypeRepository;

    public PetTypeSDJpaService(PetTypeRepository petTypeRepository) {
        this.petTypeRepository = petTypeRepository;
    }

    @Override
    public PetType findById(Long id) {
        return petTypeRepository.findById(id).orElse(null);
    }

    @Override
    public PetType save(PetType petType) {
        return petTypeRepository.save(petType);
    }

    @Override
    public List<PetType> findAll() {
        List<PetType> petTypes = new ArrayList<>();
         petTypeRepository.findAll().forEach(petTypes::add);
         return petTypes;

    }

    @Override
    public void deleteById(Long id) {
   petTypeRepository.deleteById(id);
    }

    @Override
    public void delete(PetType petType) {
   petTypeRepository.delete(petType);
    }
}

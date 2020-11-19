package com.capgemini.services.springdatajpa;

import com.capgemini.models.Owner;
import com.capgemini.repositories.OwnerRepository;
import com.capgemini.repositories.PetRepository;
import com.capgemini.repositories.PetTypeRepository;
import com.capgemini.services.OwnerService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Profile("springdatajpa")
public class OwnerSDJpaService implements OwnerService {

    private final OwnerRepository ownerRepository;
    private final PetRepository petRepository;
    private final PetTypeRepository petTypeRepository;

    public OwnerSDJpaService(OwnerRepository ownerRepository, PetRepository petRepository, PetTypeRepository petTypeRepository) {
        this.ownerRepository = ownerRepository;
        this.petRepository = petRepository;
        this.petTypeRepository = petTypeRepository;
    }

    @Override
    public Owner findByLastName(String lastName) {
        return ownerRepository.findByLastName(lastName);
    }

    @Override
    public List<Owner> findAllByLastNameContaining(String lastName) {

        return ownerRepository.findAllByLastNameContaining(lastName);
    }

    @Override
    public Owner findById(Long id) {
        return ownerRepository.findById(id).orElse(null);
    }

    @Override
    public Owner save(Owner owner) {
        return ownerRepository.save(owner);
    }

    @Override
    public List<Owner> findAll() {
        List<Owner> owners = new ArrayList<>();
        ownerRepository.findAll().forEach(owners::add);
        return owners;
    }

    @Override
    public void deleteById(Long id) {
       ownerRepository.deleteById(id);
    }

    @Override
    public void delete(Owner o) {
       ownerRepository.delete(o);
    }
}

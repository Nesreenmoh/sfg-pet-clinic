package com.capgemini.repositories;

import com.capgemini.models.Pet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepository extends CrudRepository<Pet,Long> {
}

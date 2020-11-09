package com.capgemini.repositories;

import com.capgemini.models.Owner;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository extends CrudRepository<Owner, Long> {

    Owner findByLastName(String firstName);
}

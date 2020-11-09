package com.capgemini.repositories;

import com.capgemini.models.Visit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitRepository  extends CrudRepository<Visit, Long> {
}

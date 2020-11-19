package com.capgemini.services.springdatajpa;

import com.capgemini.models.Visit;
import com.capgemini.repositories.VisitRepository;
import com.capgemini.services.VisitService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Profile("springdatajpa")
public class VisitSDJpaService implements VisitService {

    private final VisitRepository visitRepository;

    public VisitSDJpaService(VisitRepository visitRepository) {
        this.visitRepository = visitRepository;
    }

    @Override
    public Visit findById(Long id) {
        return visitRepository.findById(id).orElse(null);
    }

    @Override
    public Visit save(Visit visit) {

        return visitRepository.save(visit);
    }

    @Override
    public List<Visit> findAll() {
        List<Visit> visits = new ArrayList<>();
        visitRepository.findAll().forEach(visits::add);
        return visits;
    }

    @Override
    public void deleteById(Long id) {
        visitRepository.deleteById(id);
    }

    @Override
    public void delete(Visit visit) {
        visitRepository.delete(visit);
    }
}

package com.capgemini.services.map;

import com.capgemini.models.Speciality;
import com.capgemini.models.Vet;
import com.capgemini.services.SpecialityService;
import com.capgemini.services.VetService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class VetMapService extends AbstractMapService<Vet, Long> implements VetService {

    private final SpecialityService specialityService;

    public VetMapService(SpecialityService specialityService) {
        this.specialityService = specialityService;
    }

    @Override
    public Vet findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Vet save(Vet vet) {

        if(vet.getSpecialities().size()>0){
            vet.getSpecialities().forEach(speciality -> {
                 if(speciality.getId()==null){
                     Speciality savedSpeciality= specialityService.save(speciality);
                     speciality.setId(savedSpeciality.getId());
                 }
            });
        }
        return super.save(vet);
    }

    @Override
    public Set<Vet> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
       super.deleteById(id);
    }

    @Override
    public void delete(Vet o) {
        super.delete(o);
    }
}

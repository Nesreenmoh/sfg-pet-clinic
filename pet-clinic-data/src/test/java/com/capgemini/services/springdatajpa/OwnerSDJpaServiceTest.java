package com.capgemini.services.springdatajpa;

import com.capgemini.models.Owner;
import com.capgemini.repositories.OwnerRepository;
import com.capgemini.repositories.PetRepository;
import com.capgemini.repositories.PetTypeRepository;
import javafx.scene.effect.SepiaTone;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class OwnerSDJpaServiceTest {

    @Mock
    OwnerRepository ownerRepository;
    @Mock
    PetRepository petRepository;

    @Mock
    PetTypeRepository petTypeRepository;

    @InjectMocks
    OwnerSDJpaService ownerSDJpaService;

    Set<Owner> owners;
    Owner owner2;

    @BeforeEach
    void setUp() {
        Owner owner1 = new Owner();
        owner1.setId(3L);
        owner2 = new Owner();
        owner2.setId(4L);
        owners = new HashSet<>();
        owners.add(owner1);
        owners.add(owner2);
           }

    @Test
    void findByLastName() {

        Owner owner1= new Owner();
        owner1.setId(1L);
        owner1.setLastName("Mohd");
        ownerRepository.save(owner1);

        //Owner getOwner = ownerSDJpaService.findByLastName("Mohd");

        when(ownerRepository.findByLastName("Mohd")).thenReturn(owner1);

        Owner mohd = ownerSDJpaService.findByLastName("Mohd");

        assertEquals(owner1,mohd);
        verify(ownerRepository).findByLastName(any());

    }

    @Test
    void findById() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.of(owner2));

        Owner findOwner = ownerSDJpaService.findById(owner2.getId());

        assertEquals(owner2, findOwner);
        assertNotNull(findOwner);
    }

    @Test
    void findByIdNotFound() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.empty());

        Owner findOwner = ownerSDJpaService.findById(owner2.getId());

        assertNull(findOwner);

    }

    @Test
    void save() {
       Owner owner = new Owner();
       when(ownerRepository.save(any())).thenReturn(owner);
       Owner savedOwner = ownerSDJpaService.save(owner);
       assertNotNull(savedOwner);
       verify(ownerRepository).save(any());
    }

    @Test
    void findAll() {

        //when
        when(ownerRepository.findAll()).thenReturn(owners);
        Set<Owner> returnedOwners = ownerSDJpaService.findAll();

        //then
        assertEquals(owners, returnedOwners);
        assertEquals(2, ownerSDJpaService.findAll().size() );


    }

    @Test
    void deleteById() {
        ownerSDJpaService.deleteById(owner2.getId());
        assertNull(ownerSDJpaService.findById(owner2.getId()));
        verify(ownerRepository).deleteById(anyLong());
    }

    @Test
    void delete() {
        ownerSDJpaService.delete(owner2);
        assertNull(ownerSDJpaService.findById(owner2.getId()));
        verify(ownerRepository,times(1)).delete(any());
        verify(ownerRepository).delete(any());
    }
}
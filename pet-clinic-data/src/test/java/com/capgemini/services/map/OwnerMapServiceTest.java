package com.capgemini.services.map;

import com.capgemini.models.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class OwnerMapServiceTest {

    OwnerMapService ownerMapService;
    Owner owner1;

    @BeforeEach
    void setUp() {
        ownerMapService = new OwnerMapService(new PetTypeMapService(), new PetMapService());
        owner1 = new Owner();
        owner1.setId(1L);
        owner1.setFirstName("Tom");
        owner1.setLastName("John");
        ownerMapService.save(owner1);
    }

    @Test
    void findById() {
        Owner owner = ownerMapService.findById(1L);
        assertEquals(1L, owner.getId());
    }

    @Test
    void saveExistingId() {
        Owner owner2 = new Owner();
        owner2.setId(3L);
        Owner savedOwner = ownerMapService.save(owner2);
        assertEquals(owner2.getId(), savedOwner.getId());
    }

    @Test
    void saveNoId() {

        Owner savedOwner = ownerMapService.save(Owner.builder().build());
        assertNotNull(savedOwner);
        assertNotNull(savedOwner.getId());
    }

    @Test
    void findAll() {
        List<Owner> owners = ownerMapService.findAll();
        assertEquals(1, owners.size());
    }

    @Test
    void delete() {
        ownerMapService.delete(ownerMapService.findById(owner1.getId()));
        assertEquals(0,ownerMapService.findAll().size());
    }

    @Test
    void deleteById() {
        ownerMapService.deleteById(owner1.getId());
        assertNull(ownerMapService.findById(owner1.getId()));
    }

    @Test
    void findByLastName() {
        Owner tom = ownerMapService.findByLastName("John");
        assertNotNull(tom);
        assertEquals(owner1.getId(), tom.getId());
    }

    @Test
    void findByLastNameNotFound() {
        Owner tom = ownerMapService.findByLastName("foo");
        assertNull(tom);

    }

}
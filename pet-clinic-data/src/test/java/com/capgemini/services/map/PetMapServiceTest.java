package com.capgemini.services.map;

import com.capgemini.models.Pet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PetMapServiceTest {

     PetMapService petMapService;
     Pet cat;

    @BeforeEach
    void setUp() {
        petMapService = new PetMapService();
        cat = new Pet();
        cat.setId(2L);
        petMapService.save(cat);
    }

    @Test
    void findById() {
        Pet findPet= petMapService.findById(cat.getId());
        assertEquals(cat, findPet);
    }

    @Test
    void saveExistingId() {
        Pet dog = new Pet();
        dog.setId(3L);
        Pet savedPet = petMapService.save(dog);
        assertEquals(dog.getId(), savedPet.getId());
    }

    @Test
    void saveNoId() {
        Pet pet = petMapService.save(Pet.builder().build());
        assertNotNull(pet);
        assertNotNull(pet.getId());
    }

    @Test
    void findAll() {
        Set<Pet> pets = petMapService.findAll();
        assertEquals(1,pets.size());
    }

    @Test
    void delete() {
        petMapService.delete(petMapService.findById(cat.getId()));
        assertEquals(0, petMapService.findAll().size());
    }

    @Test
    void deleteById() {
        petMapService.deleteById(cat.getId());
        assertNull(petMapService.findById(cat.getId()));
    }
}
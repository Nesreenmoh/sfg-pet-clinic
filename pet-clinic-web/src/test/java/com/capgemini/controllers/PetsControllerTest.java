package com.capgemini.controllers;

import com.capgemini.models.Owner;
import com.capgemini.models.Pet;
import com.capgemini.models.PetType;
import com.capgemini.services.OwnerService;
import com.capgemini.services.PetService;
import com.capgemini.services.PetTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class PetsControllerTest {

    @InjectMocks
    PetsController petsController;

    @Mock
    PetTypeService petTypeService;

    @Mock
    OwnerService ownerService;

    @Mock
    PetService petService;

    MockMvc mockMvc;
    Owner owner1;
    List<PetType> petTypes;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(petsController).build();
        owner1 = new Owner();
        owner1.setId(1L);
        petTypes= Arrays.asList(new PetType("dog"),
                                new PetType("cat"));

    }

    @Test
    void initCreationForm() throws Exception {
        when(ownerService.findById(anyLong())).thenReturn(owner1);
        when(petTypeService.findAll()).thenReturn(petTypes);

        mockMvc.perform(get("/owner/1/pets/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("pets/createOrUpdatePetForm"))
                .andExpect(model().attributeExists("pet"));
    }


    @Test
    void addNewPet() throws Exception {

        when(ownerService.findById(anyLong())).thenReturn(owner1);
        when(petTypeService.findAll()).thenReturn(petTypes);

        mockMvc.perform(post("/owner/1/pets/new"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owner/1"));

    }

    @Test
    void updatePetForm() throws Exception {
        Pet pet1=new Pet();
        pet1.setId(2L);
        owner1.getPets().add(pet1);

        when(ownerService.findById(anyLong())).thenReturn(owner1);
        when(petTypeService.findAll()).thenReturn(petTypes);
        when(petService.findById(anyLong())).thenReturn(Pet.builder().id(2L).build());

        mockMvc.perform(get("/owner/1/pets/2/update"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("pet"))
                .andExpect(view().name("PETS_CREATE_OR_UPDATE"));

        verify(petService,times(1)).findById(anyLong());
    }

    @Test
    void updatePet() throws Exception {

        Pet pet1=new Pet();
        pet1.setId(2L);
        owner1.getPets().add(pet1);

        when(ownerService.findById(anyLong())).thenReturn(owner1);
        when(petTypeService.findAll()).thenReturn(petTypes);

        mockMvc.perform(post("/owner/1/pets/2/update"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owner/1"));

        verify(petService,times(1)).save(any());
    }
}
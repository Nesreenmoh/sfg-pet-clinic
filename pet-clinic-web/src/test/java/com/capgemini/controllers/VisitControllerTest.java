package com.capgemini.controllers;

import com.capgemini.models.Owner;
import com.capgemini.models.Pet;
import com.capgemini.models.Visit;
import com.capgemini.services.OwnerService;
import com.capgemini.services.PetService;
import com.capgemini.services.VisitService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class VisitControllerTest {

    @InjectMocks
    VisitController visitController;

    @Mock
    PetService petService;

    @Mock
    VisitService visitService;

    @Mock
    OwnerService ownerService;

    MockMvc mockMvc;

    Owner owner1;
    Pet pet;
    Visit visit;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.initMocks(this);
        visitController = new VisitController(petService, visitService);
        mockMvc = MockMvcBuilders.standaloneSetup(visitController).build();
        owner1 = new Owner();
        owner1.setId(1L);
        pet = new Pet();
        pet.setId(2L);
        visit = new Visit();
        visit.setId(1L);
        owner1.getPets().add(pet);
        pet.getVisits().add(visit);
        visit.setPet(pet);
    }

    @Test
    void initNewVisitForm() throws Exception {
        when(petService.findById(anyLong())).thenReturn(pet);
        when(ownerService.findById(anyLong())).thenReturn(owner1);
        when(visitService.save(any())).thenReturn(visit);

        mockMvc.perform(get("/owner/1/pet/2/visits/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("pets/createOrUpdateVisitForm"))
                .andExpect(model().attributeExists("pet"))
                .andExpect(model().attributeExists("visit"));
        verify(petService, times(1)).findById(anyLong());
    }

    @Test
    void processNewVisitForm() throws Exception {

        when(petService.findById(anyLong())).thenReturn(pet);
        when(ownerService.findById(anyLong())).thenReturn(owner1);
        when(visitService.save(any())).thenReturn(visit);

        mockMvc.perform(post("/owner/1/pet/2/visits/new"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owner/{ownerId}"));

        verify(visitService, times(1)).save(any());
        verify(petService, times(1)).findById(anyLong());


    }
}
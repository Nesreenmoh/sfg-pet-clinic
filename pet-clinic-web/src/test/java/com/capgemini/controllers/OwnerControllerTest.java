package com.capgemini.controllers;

import com.capgemini.models.Owner;
import com.capgemini.services.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

    @Mock
    OwnerService ownerService;

    @InjectMocks
    OwnerController ownerController;

    MockMvc mockMvc;

    List<Owner> owners;

    @BeforeEach
    void setUp() {
        Owner smith = new Owner();
        smith.setId(4L);
        Owner john = new Owner();
        john.setId(5L);
        owners = new ArrayList<>();
        owners.add(smith);
        owners.add(john);

        mockMvc = MockMvcBuilders
                .standaloneSetup(ownerController)
                .build();
    }


    @Test
    void findOwner() throws Exception {
        mockMvc.perform(get("/owner/find"))
                .andExpect(status().isOk())
                .andExpect(view().name("owner/findowner"))
                .andExpect(model().attributeExists("owner"));

        verifyZeroInteractions(ownerService);

    }

    @Test
    void findOwnerReturnMany() throws Exception {
        // the service will return many records
        when(ownerService.findAllByLastNameContaining(anyString())).thenReturn(owners);

        mockMvc.perform(get("/owner"))
                .andExpect(status().isOk())
                .andExpect(view().name("owner/ownerslist"))
                .andExpect(model().attribute("selections", hasSize(2)));
        verify(ownerService,times(1)).findAllByLastNameContaining(anyString());
            }


    @Test
    void findOwnerReturnOne() throws Exception {

        Owner owner1 = new Owner();
        owner1.setId(1L);
        // the service will return one record
        when(ownerService.findAllByLastNameContaining(anyString())).thenReturn(Arrays.asList(owner1));

        mockMvc.perform(get("/owner"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owner/1"));

        verify(ownerService,times(1)).findAllByLastNameContaining(anyString());
    }

    // test if the last name does not find return all list

    @Test
    void findOwnerReturnEmpty() throws Exception {
//        List<Owner> emptyList = new ArrayList<>();
        // the service will return null
        when(ownerService.findAllByLastNameContaining(anyString())).thenReturn(owners);

        mockMvc.perform(get("/owner").param("lastName",""))
                .andExpect(status().isOk())
                .andExpect(view().name("owner/ownerslist"));

        assertEquals(owners.size(), 2);
        verify(ownerService,times(1)).findAllByLastNameContaining(anyString());
    }

    // intialize CreationForm
    @Test
    void initCreationForm() throws Exception {
        mockMvc.perform(get("/owner/new"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("owner"))
                .andExpect(view().name("owner/creatorupdateownerform"));
     verifyZeroInteractions(ownerService);
    }

    // add New Owner
    @Test
    void addNewOwner() throws Exception {
        Owner owner1= new Owner();
        owner1.setId(2L);
        //when
        when(ownerService.save(ArgumentMatchers.any())).thenReturn(owner1);

        mockMvc.perform(post("/owner/new"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owner/2"));
       verify(ownerService,times(1)).save(any());
    }

    // update Get method Owner
    @Test
    void updateOwnerGet() throws Exception {
        Owner owner1= new Owner();
        owner1.setId(2L);
        //when
        when(ownerService.findById(anyLong())).thenReturn(owner1);

        mockMvc.perform(get("/owner/2/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name("owner/creatorupdateownerform"));
        verify(ownerService,times(1)).findById(anyLong());
    }

    // update Get method Owner
    @Test
    void updateOwnerPost() throws Exception {
        Owner owner1= new Owner();
        owner1.setId(2L);
        //when
        when(ownerService.save(ArgumentMatchers.any())).thenReturn(owner1);

        mockMvc.perform(post("/owner/2/edit"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owner/2"));
        verify(ownerService,times(1)).save(any());
    }



    @Test
    void ownerDetails() throws Exception {
        Owner owner = new Owner();
        owner.setId(1L);
        when(ownerService.findById(anyLong())).thenReturn(owner);
        mockMvc.perform(get("/owner/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("owner/ownerdetails"))
                .andExpect(model().attribute("owner", hasProperty("id", is(1L))));
    }
}
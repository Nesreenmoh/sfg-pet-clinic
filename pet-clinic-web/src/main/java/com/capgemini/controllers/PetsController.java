package com.capgemini.controllers;

import com.capgemini.models.Owner;
import com.capgemini.models.PetType;
import com.capgemini.services.OwnerService;
import com.capgemini.services.PetTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/owner/{ownerId}")
public class PetsController {

    private final static String PETS_CREATE_OR_UPDATE="pets/createorupdatepets";
    private final PetTypeService petTypeService;
    private final OwnerService ownerService;


    public PetsController(PetTypeService petTypeService, OwnerService ownerService) {
        this.petTypeService = petTypeService;
        this.ownerService = ownerService;
    }

    @RequestMapping({"","/","index","index.html"})
    public String petsList(){
        return "pets/index";
    }


    @ModelAttribute("types")
    public List<PetType> populatePetTypes(){
        return this.petTypeService.findAll();
    }

    @ModelAttribute("owner")
    public Owner findOwner(@PathVariable("ownerId") Long ownerId){
        return ownerService.findById(ownerId);
    }
}

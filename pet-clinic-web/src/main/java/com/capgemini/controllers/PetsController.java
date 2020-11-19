package com.capgemini.controllers;

import com.capgemini.models.Owner;
import com.capgemini.models.Pet;
import com.capgemini.models.PetType;
import com.capgemini.services.OwnerService;
import com.capgemini.services.PetService;
import com.capgemini.services.PetTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/owner/{ownerId}")
public class PetsController {

    private final static String PETS_CREATE_OR_UPDATE="pets/createOrUpdatePetForm";
    private final PetTypeService petTypeService;
    private final OwnerService ownerService;
    private final PetService petService;


    public PetsController(PetTypeService petTypeService, OwnerService ownerService, PetService petService) {
        this.petTypeService = petTypeService;
        this.ownerService = ownerService;
        this.petService = petService;
    }

    @RequestMapping({"","/","index","index.html"})
    public String petsList(){
        return "pets/index";
    }


    @ModelAttribute("types")
    public List<PetType> populatePetTypes(){
        return this.petTypeService.findAll();
    }

    @GetMapping("/pets/new")
    public String initCreationForm(Model model, Owner owner){
        Pet pet = new Pet();
        owner.getPets().add(pet);
        pet.setOwner(owner);
        model.addAttribute("pet", pet);
        return PETS_CREATE_OR_UPDATE;
    }

    @PostMapping("/pets/new")
    public String addNewPet(@PathVariable("ownerId") Long ownerId,  Owner owner,@ModelAttribute @Validated Pet pet,  Model model, BindingResult result){
        if (StringUtils.hasLength(pet.getName()) && pet.isNew() && owner.getPet(pet.getName(), true) != null){
            result.rejectValue("name", "duplicate", "already exists");
        }
        owner.setId(ownerId);
        owner.getPets().add(pet);
        if(result.hasErrors()){
            return PETS_CREATE_OR_UPDATE;
        }
        //pet.setOwner(owner);
        petService.save(pet);
        return "redirect:/owner/"+owner.getId();

    }

    @GetMapping("/pets/{petId}/update")
    public String updatePetForm(Model model, @PathVariable("petId") Long petId){
        model.addAttribute("pet", petService.findById(petId));
        return "PETS_CREATE_OR_UPDATE";
    }


    @PostMapping("/pets/{petId}/update")
    public String updatePet(Model model,@ModelAttribute @Validated Pet pet, Owner owner, BindingResult result){
        if(result.hasErrors()){
            return "redirect:/pets/new";
        }
        owner.getPets().add(pet);
        petService.save(pet);
        return "redirect:/owner/"+owner.getId();

    }

    @InitBinder
    public void initOwnerBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @ModelAttribute("owner")
    public Owner findOwner(@PathVariable("ownerId") Long ownerId){
        return ownerService.findById(ownerId);
    }
}

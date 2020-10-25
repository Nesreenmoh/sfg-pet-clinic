package com.capgemini.bootstrap;

import com.capgemini.models.Owner;
import com.capgemini.models.Pet;
import com.capgemini.models.PetType;
import com.capgemini.models.Vet;
import com.capgemini.services.OwnerService;
import com.capgemini.services.PetTypeService;
import com.capgemini.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

   private final OwnerService ownerService;
   private final VetService vetService;
   private final PetTypeService petTypeService;


    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {

        // define a pet type
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedPetDogType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedPetcatType = petTypeService.save(cat);


        // define an owner 1
        Owner owner1= new Owner();
        owner1.setFirstName("Michael");
        owner1.setLastName("Weston");
        owner1.setAddress("123 Brickerel");
        owner1.setCity("Miami");
        owner1.setTelephone("1231231234");


        // define a pet 1
        Pet mikesPet = new Pet();
        mikesPet.setPetType(savedPetDogType);
        mikesPet.setOwner(owner1);
        mikesPet.setBirthDate(LocalDate.now());
        mikesPet.setName("Rosco");
        owner1.getPets().add(mikesPet);

        // save owner1
        ownerService.save(owner1);

        // define owner 2 data
        Owner owner2= new Owner();
        owner2.setFirstName("Fiona");
        owner2.setLastName("Gienanne");
        owner2.setAddress("123 Brickerel");
        owner2.setCity("Miami");
        owner2.setTelephone("1231231234");

        // define pet 2 data
        Pet fionasCat = new Pet();
        fionasCat.setName("Just Cat");
        fionasCat.setOwner(owner2);
        fionasCat.setBirthDate(LocalDate.now());
        fionasCat.setPetType(savedPetcatType);
        owner2.getPets().add(fionasCat);


        // save owner2 data
        ownerService.save(owner2);

        System.out.println("Loaded Owners....");

        // define vet1 data
        Vet vet1= new Vet();
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");
        vetService.save(vet1);

        // define vet2 data
        Vet vet2= new Vet();
        vet2.setFirstName("Anis");
        vet2.setLastName("Rashid");
        vetService.save(vet2);

        System.out.println("Loaded Vets....");

    }
}

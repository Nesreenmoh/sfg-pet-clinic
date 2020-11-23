package com.capgemini.controllers;

import com.capgemini.models.Vet;
import com.capgemini.services.VetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/vets")
public class VetController {


    private final VetService vetService;

    public VetController(VetService vetService) {
        this.vetService = vetService;
    }



    @RequestMapping({"","/","index","index.html"})
    public String listVets(Model model)
    {
        model.addAttribute("vets", vetService.findAll());
        return "vets/index";
    }

    // visit this localhost:8080/vets/api/vets to show JSON of vets

    @GetMapping("/api/vets")
    public @ResponseBody  List<Vet> getVetsJSON(){
        return vetService.findAll();
    }
}

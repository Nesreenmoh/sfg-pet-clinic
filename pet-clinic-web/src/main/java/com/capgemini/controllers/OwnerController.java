package com.capgemini.controllers;

import com.capgemini.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/owner")
public class OwnerController {

    // define a variable of an owner service
    private final OwnerService ownerService;

    // constructor


    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    // retrieve all owners data
    @RequestMapping({"","/","index","index.html"})
    public String ownerList(Model model){
        model.addAttribute("owners", ownerService.findAll());
        return "owner/index";
    }
}

package com.capgemini.controllers;

import com.capgemini.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
    @RequestMapping({"", "/", "index", "index.html"})
    public String ownerList(Model model) {
        model.addAttribute("owners", ownerService.findAll());
        return "owner/index";
    }

    @RequestMapping("/find")
    public String findOwner() {
        return "owner/find";
    }

    @GetMapping("/{ownerId}")
    public ModelAndView ownerDetails(@PathVariable("ownerId") Long ownerId) {
        ModelAndView modelAndView = new ModelAndView("owner/ownerdetails");
        modelAndView.addObject(ownerService.findById(ownerId));
        return modelAndView;
    }
}

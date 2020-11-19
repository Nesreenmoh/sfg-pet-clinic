package com.capgemini.controllers;

import com.capgemini.models.Owner;
import com.capgemini.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/owner")
public class OwnerController {

    // define a variable of an owner service
    private final OwnerService ownerService;

    // constructor

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }


    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @RequestMapping("/find")
    public String findOwner(Model model) {
        model.addAttribute("owner", new Owner());
        return "owner/findowner";
    }

    @GetMapping
    public String processFindForm(Model model, Owner owner, BindingResult bindingResult) {
        if (owner.getLastName() == null) {
            owner.setLastName("");
        }
        System.out.println(owner.getLastName());
        List<Owner> results = ownerService.findAllByLastNameContaining(owner.getLastName());

        if (results.isEmpty()) {
            bindingResult.rejectValue("lastName", "Sorry Not Found", "Sorry Not Found");
            return "owner/findowner";

        } else if (results.size() == 1) {
            System.out.println("result "+ results.size());
            owner = results.get(0);
            return "redirect:/owner/" + owner.getId();
        } else {
            model.addAttribute("selections", results);
            return "owner/ownerslist";
        }
    }


    @GetMapping("/{ownerId}")
    public ModelAndView ownerDetails(@PathVariable("ownerId") Long ownerId) {
        ModelAndView modelAndView = new ModelAndView("owner/ownerdetails");
        modelAndView.addObject(ownerService.findById(ownerId));
        return modelAndView;
    }
}

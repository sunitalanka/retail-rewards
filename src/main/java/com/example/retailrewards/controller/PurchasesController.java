package com.example.retailrewards.controller;

import com.example.retailrewards.data.CustomerRepository;
import com.example.retailrewards.data.PurchasesRepository;
import com.example.retailrewards.models.Purchase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("purchases")
public class PurchasesController {

    @Autowired
    private PurchasesRepository purchasesRepository;

    @Autowired
    private CustomerRepository customerRepository;



    @GetMapping("view")
    public String displayAllPurchases(Model model){
        model.addAttribute("customers", customerRepository.findAll());
        model.addAttribute("purchases", purchasesRepository.findAll());
        return "purchases/view";
    }

    @GetMapping("create")
    public String createPurchase(Model model){
        model.addAttribute("title", "Create Purchase");
        model.addAttribute("purchase", new Purchase());
        model.addAttribute("customers", customerRepository.findAll());
        //model.addAttribute("purchases", purchasesRepository.findAll());
        return "purchases/create";
    }

    @PostMapping("create")
    public String processCreateCustomer(Model model, @ModelAttribute Purchase purchase, Errors errors){
        if(errors.hasErrors()) {
            model.addAttribute("title", "Create Purchases");
            model.addAttribute("errorMsg", "Bad data!");
            return "purchases/create";
        }

        purchasesRepository.save(purchase);
        model.addAttribute("Purchase", purchase);
        model.addAttribute("successMessage","Saved Record Successfully!");
        return "redirect:create";
    }
}

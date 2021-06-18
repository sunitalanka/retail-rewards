package com.example.retailrewards.controller;

import com.example.retailrewards.data.CustomerRepository;
import com.example.retailrewards.models.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("customers")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("index")
    public String displayAllCustomer(Model model){
        model.addAttribute("customers", customerRepository.findAll());
        return "customers/index";
    }


    @GetMapping("addCustomer")
    public String createCustomer(Model model){
        model.addAttribute("title", "Add Customer");
        model.addAttribute("customer", new Customer());
        model.addAttribute("customers",customerRepository.findAll());
        return "customers/addCustomer";
    }

    @PostMapping("addCustomer")
    public String processCreateCustomer(Model model, @ModelAttribute Customer customer, Errors errors){
        if(errors.hasErrors()) {
            model.addAttribute("title", "Add Customer");
            model.addAttribute("errorMsg", "Bad data!");
            return "customers/addCustomer";
        }

        customerRepository.save(customer);
        model.addAttribute("Customer", customer);
        return "redirect:addCustomer";
    }



}

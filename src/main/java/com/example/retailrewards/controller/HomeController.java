package com.example.retailrewards.controller;

import com.example.retailrewards.data.CustomerRepository;
import com.example.retailrewards.data.PurchasesRepository;
import com.example.retailrewards.models.Customer;
import com.example.retailrewards.models.Purchase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class HomeController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PurchasesRepository purchasesRepository;





    @GetMapping
    public String index() {
        return "index";
    }

    @GetMapping("rewards/{customerId}")
    public String landingRewards(Model model, ModelMap modelMap, @PathVariable Integer customerId, @ModelAttribute Purchase purchase) throws ParseException {
        model.addAttribute("title", "Rewards");
        model.addAttribute("purchases", purchasesRepository.findAllByCustomerId(customerId));
        List<Purchase> result = purchasesRepository.findAllByCustomerId(customerId);


        int totalRewards = 0;
        Map<String,Integer> monthRewards = new HashMap<>();

        for (Purchase res : result) {
            Integer tempRewards = 0;
            String strDate = res.getDate();
            LocalDate date = LocalDate.parse(strDate);
            String month= date.getMonth().name();
            if(monthRewards.get(month)==null){
                monthRewards.put(month,0);
            }else {
                tempRewards = monthRewards.get(month);
            }

            int rewards = 0;
            int amount = (int) (res.getAmountSpent());
            if (amount > 100) {
                rewards = ((amount - 100) * 2) + (50 * 1);
                totalRewards = totalRewards + rewards;
            }
            if (amount >= 50 && amount <= 100) {
                rewards = (50 * 1);
                totalRewards = totalRewards + rewards;
            }
            tempRewards = tempRewards + rewards;
            monthRewards.put(month, tempRewards);
          }
        modelMap.addAttribute("rewMap",monthRewards);
        model.addAttribute("totalRewards", totalRewards);
        return "rewards";

    }
}


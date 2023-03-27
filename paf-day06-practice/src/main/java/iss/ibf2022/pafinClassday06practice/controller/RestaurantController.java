package iss.ibf2022.pafinClassday06practice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import iss.ibf2022.pafinClassday06practice.Service.RestaurantService;
import iss.ibf2022.pafinClassday06practice.model.Restaurant;

@Controller
@RequestMapping(path={"/restaurant"})
public class RestaurantController {

    @Autowired
    RestaurantService restaurantSvc;

    @GetMapping()
    public String getMainPage(Model model) {

        List<String> foodType = restaurantSvc.getUniqueTypeOfFood();
        model.addAttribute("type", foodType);

        return "main";
    }

    @GetMapping(path={"/type"})
    public String getRestaurantList(@RequestParam String cuisine, Model model) {

        List<Restaurant> restaurantList = restaurantSvc.getRestaurantByFoodType(cuisine);
        model.addAttribute("restaurants", restaurantList);

        return "restaurants";
    }
    
}

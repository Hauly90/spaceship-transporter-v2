package com.greenfox.mockexam.controllers;

import com.greenfox.mockexam.services.ShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PlanetController {

    private ShipService shipService;

    @Autowired
    public PlanetController(ShipService shipService) {
        this.shipService = shipService;
    }

    @DeleteMapping("/planets/{id}")
    public String deletePlanet(@PathVariable long id) {
        shipService.deleteSomePlanet(id);

        return "redirect:/";
    }
}

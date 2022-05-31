package com.greenfox.mockexam.controllers;

import com.greenfox.mockexam.models.Ship;
import com.greenfox.mockexam.services.ShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ShipController {

    private ShipService shipService;

    @Autowired
    public ShipController(ShipService shipService) {
        this.shipService = shipService;
    }

    @GetMapping("/")
    public String getMainPage(Model model) {
        model.addAttribute("listOfShips", shipService.getAllShips());
        model.addAttribute("listOfPlanets", shipService.getAllPlanets());
        return "mainPage";
    }

    @PostMapping("/ships/move")
    public String postMovingShipAround(@RequestParam(required = false) String shipType, @RequestParam(required = false) String planetType) {
        shipService.moveShip(shipType, planetType);

        return "redirect:/";
    }

    @PostMapping("/ships")
    public String postCreateNewShip(@RequestParam(required = false) String shipName, @RequestParam(required = false) String shipMaximumWarp, @RequestParam(required = false) String shipPlanet) {
        Ship newShip = new Ship(shipName, Double.parseDouble(shipMaximumWarp), false, shipService.getShipByName(shipPlanet));
        shipService.addNewShip(newShip);

        return "redirect:/";
    }

    @DeleteMapping("/planets/{id}")
    public String deletePlanet(@PathVariable long id) {
        shipService.deleteSomePlanet(id);

        return "redirect:/";
    }

    @GetMapping("/ships")
    public ResponseEntity<?> getListWithWarpSpeed(@RequestParam Double warpAtLeast) {
        return ResponseEntity.ok().body(shipService.getWarpSpeed(warpAtLeast));
    }

    @GetMapping("/docking")
    public String postDockOrUndock(@RequestParam long id) {
        shipService.changeDockingStatus(id);

        return "redirect:/";
    }

    @GetMapping("/addNewShip")
    public String getAddShipPage(Model model) {
        model.addAttribute("listOfPlanets", shipService.getAllPlanets());
        return "addShip";
    }
}

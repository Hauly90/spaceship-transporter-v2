package com.greenfox.mockexam.services;

import com.greenfox.mockexam.models.Planet;
import com.greenfox.mockexam.models.Ship;
import com.greenfox.mockexam.repositories.PlanetRepository;
import com.greenfox.mockexam.repositories.ShipRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Getter
@Setter
public class ShipService {

    private ShipRepository shipRepository;
    private PlanetRepository planetRepository;

    private Planet planet;

    @Autowired
    public ShipService(ShipRepository shipRepository, PlanetRepository planetRepository) {
        this.shipRepository = shipRepository;
        this.planetRepository = planetRepository;

        Planet earth = new Planet("Earth", 3);
        planetRepository.save(earth);
        Planet titan = new Planet("Titan", 500);
        planetRepository.save(titan);
        Planet vulcan = new Planet("Vulcan", 350);
        planetRepository.save(vulcan);

        Ship voyager = new Ship("Voyager", 9.975, false, earth);
        shipRepository.save(voyager);
        Ship enterprise = new Ship("Enterprise", 9.6, true, titan);
        shipRepository.save(enterprise);
        Ship discovery = new Ship("Discovery", 8, false, vulcan);
        shipRepository.save(discovery);
    }

    public ShipService() {

    }

    public List<Ship> getAllShips() {
        return shipRepository.findAll();
    }

    public List<Planet> getAllPlanets() {
        return planetRepository.findAll();
    }

    public void addNewShip(Ship ship) {
        shipRepository.save(ship);
    }

    public Planet getShipByName(String string) {
        List<Planet> result = planetRepository.findAll().stream().filter(a -> a.getName().equals(string)).findFirst().stream().collect(Collectors.toList());

        return result.get(0);
    }

    public void moveShip(String shipType, String planetType) {
        List<Ship> resultShip = shipRepository.findAll().stream().filter(ship -> ship.getName().equals(shipType)).findFirst().stream().collect(Collectors.toList());
        List<Planet> resultPlanet = planetRepository.findAll().stream().filter(planet -> planet.getName().equals(planetType)).findFirst().stream().collect(Collectors.toList());

        List<Ship> allShips = shipRepository.findAll().stream().filter(allPlanets -> allPlanets.getName().equals(planetType)).collect(Collectors.toList());

        if (!resultShip.get(0).isDocked() && resultPlanet.get(0).getDockingCapacity() > allShips.size()) {
            resultShip.get(0).setPlanet(resultPlanet.get(0));
            Ship someShip = resultShip.get(0);
            shipRepository.save(someShip);
        }
    }

    public void deleteSomePlanet(long id) {
        List<Ship> shipsWithDeletedPlanet = shipRepository.findAll().stream().filter(ship -> ship.getPlanet().getId() == id).collect(Collectors.toList());
        List<Ship> shipWithLivingPlanet = shipRepository.findAll().stream().filter(ship -> ship.getPlanet().getId() != id).findFirst().stream().collect(Collectors.toList());

        for (Ship oneShip : shipsWithDeletedPlanet) {
//            oneShip.setPlanet(planetRepository.findById(1L).get());
            oneShip.setPlanet(shipWithLivingPlanet.get(0).getPlanet());
        }

        planetRepository.deleteById(id);
    }

    public List<Ship> getWarpSpeed(Double warpSpeed) {

        List<Ship> shipsWithSpeed = shipRepository.findAll().stream().filter(ship -> ship.getMaxSpeed() >= warpSpeed)
                .sorted(Comparator.comparing(Ship::getMaxSpeed).reversed()).collect(Collectors.toList());

        return shipsWithSpeed;
    }

    public void changeDockingStatus(long id) {
        Optional<Ship> shipForChanges = shipRepository.findById(id);

        if (shipForChanges.isPresent()) {
            if (shipForChanges.get().isDocked()) {
                shipForChanges.get().setDocked(false);
                shipRepository.save(shipForChanges.get());
            } else {
                shipForChanges.get().setDocked(true);
                shipRepository.save(shipForChanges.get());
            }
        }

    }
}

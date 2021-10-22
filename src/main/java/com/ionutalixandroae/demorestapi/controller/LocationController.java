package com.ionutalixandroae.demorestapi.controller;

import java.util.concurrent.atomic.AtomicLong;

import com.fasterxml.jackson.databind.JsonNode;
import com.ionutalixandroae.demorestapi.model.Location;
import com.ionutalixandroae.demorestapi.model.LocationsFile;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.*;

@RestController
public class LocationController {

    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/location")
    public Location location(@RequestParam(value = "name", defaultValue = "Munich") String name) {
        return new Location(counter.incrementAndGet(), name);
    }

    @PostMapping("/add")
    public JSONObject save(@RequestBody JsonNode body) {
        Location newLocation = new Location(counter.incrementAndGet(), body.get("name").asText());
        LocationsFile locationsFile = new LocationsFile("locations.json");
        return locationsFile.saveToFile(newLocation);
    }
}

package com.ionutalixandroae.demorestapi.controller;

import java.util.concurrent.atomic.AtomicLong;

import com.ionutalixandroae.demorestapi.model.Location;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LocationController {

    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/location")
    public Location location(@RequestParam(value = "name", defaultValue = "Munich") String name) {
        return new Location(counter.incrementAndGet(), name);
    }
}

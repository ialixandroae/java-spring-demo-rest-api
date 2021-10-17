package com.ionutalixandroae.demorestapi.model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Location {

    private final long id;
    private final String name;
    private final JsonNode locationInfo;

    public Location(long id, String name) {
        this.id = id;
        this.name = name;
        this.locationInfo  = this.getLocationInfo(name);
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return this.name;
    }

    public Map<String, Double> getCoordinates() {
        if (this.locationInfo != null) {
            Map<String, Double> coordinates = new HashMap<>();
            Double latitude = this.locationInfo.get("lat").asDouble();
            Double longitude = this.locationInfo.get("lon").asDouble();
            coordinates.put("latidude", latitude);
            coordinates.put("longitude", longitude);
            return coordinates;
        }
        return null;
    }

    public JsonNode getLocationInfo() {
        return this.locationInfo;
    }

    private JsonNode getLocationInfo(String name) {
        String urlString = String.format("https://nominatim.openstreetmap.org/search?city=%s&format=json", name);

        try {
            InputStream stream = new URL(urlString).openStream();
            JsonNode results = new ObjectMapper().readTree(stream);
            JsonNode bestResult = results.get(0);
            return bestResult;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

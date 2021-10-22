package com.ionutalixandroae.demorestapi.model;

import java.io.FileReader;
import java.io.FileWriter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LocationsFile {

    private final String filePath;
    private final String path = "/path_to/Spring/demo-rest-api/";

    public LocationsFile(String filePath) {
        this.filePath = String.format("%s%s", this.path, filePath);
    }

    public JSONObject saveToFile(Location newLocation) {
        // Get locations from file
        JSONArray locations = this.getLocations();

        // Prepare the objects to convert from Java object to string to JSON
        ObjectMapper mapper = new ObjectMapper();
        JSONParser parser = new JSONParser();

        // This object will hold the new and updated file content
        JSONObject fileContent = new JSONObject();

        try (FileWriter writer = new FileWriter(this.filePath)){
            String newLocationAsString = mapper.writeValueAsString(newLocation);
            locations.add(parser.parse(newLocationAsString));
            fileContent.put("locations", locations);
            writer.write(fileContent.toJSONString());
            return fileContent;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private JSONArray getLocations() {
        try (FileReader reader = new FileReader(this.filePath)) {
            JSONParser jsonParser = new JSONParser();
            Object fileContent = jsonParser.parse(reader);
            JSONObject jsonContent = (JSONObject) fileContent;
            return (JSONArray) jsonContent.get("locations");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

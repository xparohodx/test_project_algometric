package utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class JsonHelper {

    String pathToControlFile = "data/world_universities_and_domains.json";

    public JsonHelper() {

    }

    //converts array of JSON object to array of University objects
    public List<University> jsonArrayToUniversityList(String json) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        List<University> universityList = null;
        try {
            universityList = mapper.readValue(json, new TypeReference<List<University>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return universityList;
    }

    //additional function to get the whole control JSON
    public String readFileToString(String pathToFile) {
        Path path = Paths.get(pathToFile);
        String out = null;
        try {
            out = Files.readString(path, StandardCharsets.ISO_8859_1);
            return out.replace("state-province", "state_province");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //getting all information from control JSON file to list of University objects
    public List<University> getAllRecordsFromJson() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        String json = readFileToString(pathToControlFile);
        try {
            List<University> universityList = mapper.readValue(json, new TypeReference<List<University>>() {
            });
            return universityList;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    //check that control JSON file contains all universities from response
    public void compareObjects(List<University> dataBase, List<University> currentList) {
        boolean flag = false;
        String name = "";
        for (University u1: currentList) {
            name = u1.getName();
            for (University u2: dataBase) {
                if (u2.getName().equals(name)) {
                    flag = true;
                }
            }
        }
        assert flag;
    }
}

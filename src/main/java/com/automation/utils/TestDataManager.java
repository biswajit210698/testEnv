package com.automation.utils;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Test Data Manager to handle JSON test data
 */
public class TestDataManager {
    private static JsonObject testDataObject;
    private static final Gson gson = new Gson();

    static {
        loadTestData();
    }

    /**
     * Load test data from JSON file
     */
    private static void loadTestData() {
        String testDataPath = ConfigManager.getTestDataPath() + "testdata.json";
        try (FileReader fileReader = new FileReader(testDataPath)) {
            testDataObject = JsonParser.parseReader(fileReader).getAsJsonObject();
        } catch (IOException e) {
            System.err.println("Failed to load test data file: " + e.getMessage());
        }
    }

    /**
     * Get test data for a specific module and scenario
     */
    public static Map<String, String> getTestData(String module, String scenario) {
        Map<String, String> dataMap = new HashMap<>();
        try {
            JsonObject moduleData = testDataObject.getAsJsonObject(module);
            if (moduleData != null) {
                JsonObject scenarioData = moduleData.getAsJsonObject(scenario);
                if (scenarioData != null) {
                    for (String key : scenarioData.keySet()) {
                        JsonElement element = scenarioData.get(key);
                        if (element.isJsonPrimitive()) {
                            dataMap.put(key, element.getAsString());
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Error retrieving test data: " + e.getMessage());
        }
        return dataMap;
    }

    /**
     * Get a single value from test data
     */
    public static String getTestDataValue(String module, String scenario, String key) {
        Map<String, String> data = getTestData(module, scenario);
        return data.getOrDefault(key, "");
    }

    /**
     * Get all data for a module
     */
    public static Map<String, Object> getModuleData(String module) {
        Map<String, Object> dataMap = new HashMap<>();
        try {
            JsonObject moduleData = testDataObject.getAsJsonObject(module);
            if (moduleData != null) {
                for (String key : moduleData.keySet()) {
                    dataMap.put(key, moduleData.get(key));
                }
            }
        } catch (Exception e) {
            System.err.println("Error retrieving module data: " + e.getMessage());
        }
        return dataMap;
    }
}
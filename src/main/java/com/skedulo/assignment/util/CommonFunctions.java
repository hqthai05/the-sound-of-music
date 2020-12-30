package com.skedulo.assignment.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.skedulo.assignment.model.Performance;
import com.skedulo.assignment.model.RawPerformance;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * All common functions were defined in this class, it's easy to handle
 */
public class CommonFunctions {

    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static List<Performance> readFile(String fileName) {
        List<Performance> performances = new ArrayList<>();
        try {
            Reader reader = Files.newBufferedReader(Paths.get(fileName));
            performances = gson.fromJson(reader, new TypeToken<List<Performance>>() {}.getType());
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return performances;
    }

    public static List<RawPerformance> readRawFile(String fileName) {
        List<RawPerformance> performances = new ArrayList<>();
        try {
            Reader reader = Files.newBufferedReader(Paths.get(fileName));
            performances = gson.fromJson(reader, new TypeToken<List<RawPerformance>>() {}.getType());
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return performances;
    }

    public static void writeFile(String fileName, List<RawPerformance> result) {
        try{
            Writer writer = new FileWriter(fileName.substring(0,fileName.indexOf(".json")) + ".optimal.json");
            gson.toJson(result, writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

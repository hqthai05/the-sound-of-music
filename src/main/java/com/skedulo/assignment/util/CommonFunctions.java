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
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * All common functions were defined in this class, it's easy to handle
 */
public class CommonFunctions {

    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static List<String> listFilesUnderDirectory(String dir) {
        List<String> result = new ArrayList<>();
        try (Stream<Path> walk = Files.walk(Paths.get(dir.replaceAll("/", "\\\\")))) {
            result = walk.filter(Files::isRegularFile)
                    .map(x -> x.toString()).collect(Collectors.toList());
        } catch (IOException e) {
            System.out.println("=======================================================");
            System.out.println("Invalid path, please make sure the path is correct.");
            System.out.println("More detail:");
            e.printStackTrace();
            System.out.println("=======================================================");
        }
        if(result.isEmpty()) {
            System.out.println("There are no existing files in given path. Please try again.");
        }
        return result;
    }

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

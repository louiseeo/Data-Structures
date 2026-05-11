package com.louiseeo.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.louiseeo.model.GameResult;
import com.louiseeo.model.Player;
import com.louiseeo.model.WordPair;

public class FileService {

    /**
     * Loads the word bank from the specified file.
     *
     * @param filename : the path to the file containing pairs
     * @param out      : the output stream to send error messages to the client
     * @return a list of pairs loaded from the file
     */
    public static List<WordPair> loadWordbank(String filename, PrintWriter out) {
        List<WordPair> pairs = new ArrayList<>();
        File file = new File(filename);
        if (!file.exists()) {
            System.out.println("Error: File not found -> " + filename);
            return pairs; // return empty list safely
        }

        try (FileReader fr = new FileReader(file)) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<WordPair>>() {
            }.getType();
            List<WordPair> loaded = gson.fromJson(fr, type);
            if (loaded != null) {
                pairs = loaded;
            }
        } catch (IOException e) {
            System.out.println("Error loading file: " + filename + " -> " + e.getMessage());
        }
        return pairs;
    }

    public static void saveGameHistory(String filename, GameResult result) {
        FileWriter fw = new FileWriter(filename)
        // hint: use GsonBuilder with pretty printing
        // hint: use FileWriter with append mode -> new FileWriter(filename, true)
        // just toJson the result and write it
    }

}
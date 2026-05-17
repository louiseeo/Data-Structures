package com.louiseeo.service;

import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.louiseeo.model.Accounts;

/**
 * Handles account registration and login validation.
 * Loads and saves player account data from JSON files.
 *
 * @author louiseeo
 */
public class AccountService {

    private static final String FILE = "data/accounts.json";

    /**
     * Loads all registered accounts from the JSON file.
     *
     * @return list of registered user accounts
     */
    public static List<Accounts> loadUsers() {
        try (FileReader reader = new FileReader(FILE)) {

            Type type = new TypeToken<List<Accounts>>() {
            }.getType();

            List<Accounts> users = new Gson().fromJson(reader, type);

            if (users != null) {
                return users;
            } else {
                return new ArrayList<>();
            }

        } catch (Exception e) {
            System.out.println("No accounts found. Creating new account list...");
            return new ArrayList<>();
        }
    }

    /**
     * Registers a new account if the username
     * does not already exist.
     *
     * @param username : entered username
     * @param password : entered password
     * @return true if signup is successful
     */
    public static boolean signup(
            String username,
            String password) {

        List<Accounts> users = loadUsers();

        for (Accounts user : users) {
            if (user.getUsername()
                    .equalsIgnoreCase(username)) {
                return false;
            }
        }

        users.add(new Accounts(username, password));
        saveAccounts(users);
        return true;
    }

    /**
     * Validates login credentials against saved accounts.
     *
     * @param username : entered username
     * @param password : entered password
     * @return true if login is successful
     */
    public static boolean login(
            String username,
            String password) {

        List<Accounts> users = loadUsers();

        for (Accounts user : users) {
            if (user.getUsername().equals(username)
                    &&
                    user.getPassword().equals(password)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Saves all account data into the JSON file.
     *
     * @param users : list of user accounts
     */
    private static void saveAccounts(
            List<Accounts> users) {

        try (FileWriter writer = new FileWriter(FILE)) {

            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .create();

            gson.toJson(users, writer);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
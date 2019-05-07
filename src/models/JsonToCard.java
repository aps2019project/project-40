package models;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class JsonToCard {
    public static void collectibleToCollection(Collection collection){
        Gson gson = new Gson();
        saveCollectibleItemCards(gson,collection);
    }
    public static void moveToCollection(Collection collection) {
        Gson gson = new Gson();
        saveSpellCards(gson, collection);
        saveHeroCards(gson,collection);
        saveMinionCards(gson, collection);
        saveUsableItemCards(gson,collection);
        saveFlag(gson,collection);
    }

    public static void saveFlag(Gson gson, Collection collection) {
        File folder = new File("./json/item/flag");
        saveCards(gson, collection, folder);
    }

    public static void saveSpellCards(Gson gson, Collection collection) {
        File folder = new File("./json/spell/");
        saveCards(gson, collection, folder);
    }

    public static void saveHeroCards(Gson gson, Collection collection) {
        File folder = new File("./json/hero/");
        saveCards(gson, collection, folder);
    }

    public static void saveMinionCards(Gson gson, Collection collection) {
        File folder = new File("./json/minion/");
        saveCards(gson, collection, folder);
    }

    public static void saveUsableItemCards(Gson gson, Collection collection) {
        File folder = new File("./json/item/usable_item");
        saveCards(gson, collection, folder);
    }

    public static void saveCollectibleItemCards(Gson gson, Collection collection) {
        File folder = new File("./json/item/collectible_item");
        saveCards(gson, collection, folder);
    }

    private static void saveCards(Gson gson, Collection collection, File folder) {
        File[] listOfFiles = folder.listFiles();
        for (File listOfFile : listOfFiles) {
            try (Reader reader = new FileReader(listOfFile)) {

                Card card = gson.fromJson(reader, Card.class);

                collection.addCardToCollection(card);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

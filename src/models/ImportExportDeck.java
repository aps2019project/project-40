package models;

import com.google.gson.Gson;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ImportExportDeck {
    public static void main(String[] args) throws IOException {
        Deck deck = new Deck();
        deck.setDeckName("ali");
        Collection a = new Collection();
        JsonToCard.moveToCollection(a);
        deck.setCards(a.getCards());
        exportDeck(deck);
        Deck d = importDeck("ali.json");
        System.out.println(d.getDeckName());
        System.out.println(d.getCards().size());
    }

    public static void exportDeck(Deck deck) throws IOException {
        Gson gson = new Gson();
        String fileName = "./Decks/" + deck.getDeckName() + ".json"; // change here for file name
        File file = new File(fileName);
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(deck.getDeckName());
        fileWriter.write("-------");
        for (Card card : deck.getCards()) {
            if (card.getType().equals(CardType.MINION) || card.getType().equals(CardType.HERO)) {
                String jsonCard = gson.toJson(card);
                fileWriter.write(jsonCard);
                fileWriter.write("-------");
            } else if (card.getType().equals(CardType.SPELL)) {
                String jsonCard = gson.toJson(card);
                fileWriter.write(jsonCard);
                fileWriter.write("-------");
            } else if (card.getType().equals(CardType.COLLECTIBLE_ITEM)) {
                String jsonCard = gson.toJson(card);
                fileWriter.write(jsonCard);
                fileWriter.write("-------");
            }
        }
    }

    public static Deck importDeck(String deckName) throws IOException {
        File file = new File(deckName);
        Gson gson = new Gson();
        Deck deck = new Deck();
        String deckString = FileUtils.readFileToString(file);
        String[] cardStrings = deckString.split("-------");
        for (String cardString : cardStrings) {
            System.out.println(cardString);
            if (cardString.startsWith("{")) {
                if (cardString.contains("\"type\":\"HERO\"") || cardString.contains("\"type\":\"MINION\"")) {
                    Unit card = gson.fromJson(cardString, Unit.class);
                    deck.addCard(card);
                } else if (cardString.contains("\"type\":\"SPELL\"")) {
                    Spell card = gson.fromJson(cardString, Spell.class);
                    deck.addCard(card);

                } else if (cardString.contains("\"type\":\"USABLE_ITEM\"") || cardString.contains("\"type\":\"COLLECTIBLE_ITEM\"")) {
                    Card card = gson.fromJson(cardString, Card.class);
                    deck.addCard(card);
                }
            } else {
                deck.setDeckName(cardString);
            }
        }
        return deck;
    }


}


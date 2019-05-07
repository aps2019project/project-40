package request.collectionMenuRequest;

import request.Request;
import request.collectionMenuRequest.collectionRequestChilds.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CollectionRequest extends Request {
    private static CollectionRequest collectionRequest;

    public static CollectionRequest getInstance() {
        if (collectionRequest == null)
            collectionRequest = new CollectionRequest();
        return collectionRequest;
    }

    public CollectionRequest getCollectionOptionList() {
        String command = scanner.nextLine().trim().toLowerCase();

        if (command.matches("show"))
            return simpleCommand(CollectionOptionList.SHOW);

        if (command.matches("show all decks"))
            return simpleCommand(CollectionOptionList.SHOW_ALL_DECKS);

        if (command.matches("save"))
            return simpleCommand(CollectionOptionList.SAVE);

        if (command.matches("help"))
            return simpleCommand(CollectionOptionList.HELP);

        if (command.matches("exit"))
            return simpleCommand(CollectionOptionList.EXIT);

        Pattern pattern = Pattern.compile("delete deck (\\w+)");
        Matcher matcher = pattern.matcher(command);
        if (matcher.find())
            return handelDeck(CollectionOptionList.DELETE_DECK, matcher.group(1));

        pattern = Pattern.compile("select deck (\\w+)");
        matcher = pattern.matcher(command);
        if (matcher.find())
            return handelDeck(CollectionOptionList.SELECT_DECK, matcher.group(1));

        pattern = Pattern.compile("validate deck (\\w+)");
        matcher = pattern.matcher(command);
        if (matcher.find())
            return handelDeck(CollectionOptionList.VALIDATE_DECK, matcher.group(1));

        pattern = Pattern.compile("show deck (\\w+)");
        matcher = pattern.matcher(command);
        if (matcher.find())
            return handelDeck(CollectionOptionList.SHOW_DECK, matcher.group(1));

        pattern = Pattern.compile("create deck (\\w+)");
        matcher = pattern.matcher(command);
        if (matcher.find())
            return handelDeck(CollectionOptionList.CREATE_DECK, matcher.group(1));

        pattern = Pattern.compile("remove (\\w+) from deck (\\w+)");
        matcher = pattern.matcher(command);
        if (matcher.find())
            return remove(matcher.group(1), matcher.group(2));

        pattern = Pattern.compile("add (\\w+) to deck (\\w+)");
        matcher = pattern.matcher(command);
        if (matcher.find())
            return add(matcher.group(1), matcher.group(2));

        return null;
    }

    public CollectionRequest handelDeck(CollectionOptionList collectionOptionList, String name) {
        DeckCommand deckCommand = new DeckCommand();
        deckCommand.setDeckName(name);
        deckCommand.setType(collectionOptionList);

        return deckCommand;
    }

    public CollectionRequest remove(String cardID, String name) {
        Remove remove = new Remove();
        remove.setCardID(cardID);
        remove.setDecknNme(name);

        return remove;
    }

    public CollectionRequest add(String cardID, String name) {
        Add add = new Add();
        add.setCardID(cardID);
        add.setDecknNme(name);

        return add;
    }

    public CollectionRequest simpleCommand(CollectionOptionList collectionOptionList) {
        SimpleRequest simpleRequest = new SimpleRequest();
        simpleRequest.setMessage(collectionOptionList);

        return simpleRequest;
    }

}

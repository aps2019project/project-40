package view.collectionMenuView;

import view.View;

public class CollectionMenuView extends View {
    private static CollectionMenuView collectionMenuView;

    public static CollectionMenuView getInstance() {

        if (collectionMenuView == null)
            collectionMenuView = new CollectionMenuView();

        return collectionMenuView;
    }

    public void showHelp(){
        System.out.println("exit");
        System.out.println("show");
        System.out.println("search [card name|item name]");
        System.out.println("save");
        System.out.println("create deck [deck name]");
        System.out.println("delete deck [deck name]");
        System.out.println("add [card id | item id | hero id] to deck [deck name]");
        System.out.println("remove [card id | item id | hero id] from deck [deck name]");
        System.out.println("validate deck [deck name]");
        System.out.println("select deck [deck name]");
        System.out.println("show all decks");
        System.out.println("show deck [deck name]");
        System.out.println("help");
    }
}

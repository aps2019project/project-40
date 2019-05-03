package controller;

public class CollectionController {
    private static CollectionController collectionController;

    public static CollectionController getInstance() {

        if (collectionController == null) {

            collectionController = new CollectionController();
        }

        return collectionController;
    }

    public void collectionControllerMain(){

    }
}

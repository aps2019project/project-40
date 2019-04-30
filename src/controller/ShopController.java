package controller;

public class ShopController {
    private static ShopController shopController;

    public static ShopController getInstance() {

        if (shopController == null) {

            shopController = new ShopController();
        }

        return shopController;
    }
    public void shopControllerMain(){

    }
}

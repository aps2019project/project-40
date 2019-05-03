package view.shopMenuView;

public class ShopMenuView {
    private static ShopMenuView shopMenuView;

    public static ShopMenuView getInstance() {

        if (shopMenuView == null)
            shopMenuView = new ShopMenuView();

        return shopMenuView;
    }

    public void showHelp(){
        System.out.println("exit");
        System.out.println("show");
        System.out.println("show collection");
        System.out.println("search [card name|item name]");
        System.out.println("buy [card name|item name]");
        System.out.println("sell [card name|item name]");
        System.out.println("search collection [card name|item name]");
        System.out.println("help");
    }
}

package ui.battleUI;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import models.GamePlay.Match;
import models.Unit;
import ui.ImageLibrary;

public class BattleUI {

    private Match match;
    public TableBuilder tableBuilder = new TableBuilder();
    public HandBuilder handBuilder = new HandBuilder();

    private ImageView[] player1Mana = new ImageView[9];
    private ImageView[] player2Mana = new ImageView[9];
    private Label labelPlayer1HeroHP = new Label();
    private Label labelPlayer2HeroHP = new Label();

    public BattleUI(Match match) {
        this.match = match;
    }


    public void battleUI(Stage mainStage) {

        Pane pane = new Pane();
        ImageView background = new ImageView(ImageLibrary.Background.getImage());
        background.fitHeightProperty().bind(mainStage.heightProperty());
        background.fitWidthProperty().bind(mainStage.widthProperty());


        pane.getChildren().addAll(background, header(), endTurnButton(),
                tableBuilder.getPolygons(), tableBuilder.getTable(match), handBuilder.getHand(match));


        Scene scene = new Scene(pane, 1280, 720);
        scene.getStylesheets().add(getClass().getResource("/ui/style/style.css").toExternalForm());
        scene.setCursor(new ImageCursor(ImageLibrary.CursorImage.getImage()));
        mainStage.setScene(scene);
        mainStage.setResizable(false);
        mainStage.show();
    }


    private Group header() {

        Group group = new Group();
        group.getChildren().addAll(imagesHeader(), headerGameInfo());
        return group;
    }

    private AnchorPane imagesHeader() {

        AnchorPane headerImage = new AnchorPane();

        ImageView leftImageHeader = new ImageView(ImageLibrary.LeftImageHeader.getImage());
        ImageView rightImageHeader = new ImageView(ImageLibrary.RightImageHeader.getImage());

        leftImageHeader.setScaleX(.4);
        leftImageHeader.setScaleY(.4);
        rightImageHeader.setScaleX(.4);
        rightImageHeader.setScaleY(.4);

        leftImageHeader.setLayoutX(-150);
        leftImageHeader.setLayoutY(-78);
        rightImageHeader.setLayoutX(798);
        rightImageHeader.setLayoutY(-53);

        updateHeroHP();

        labelPlayer1HeroHP.relocate(180, 75);
        labelPlayer2HeroHP.relocate(1090, 75);

        labelPlayer1HeroHP.setScaleX(1.5);
        labelPlayer1HeroHP.setScaleY(1.5);
        labelPlayer2HeroHP.setScaleX(1.5);
        labelPlayer2HeroHP.setScaleY(1.5);

        labelPlayer1HeroHP.setTextFill(Color.WHITE);
        labelPlayer2HeroHP.setTextFill(Color.WHITE);

        headerImage.getChildren().addAll(leftImageHeader, rightImageHeader, labelPlayer1HeroHP, labelPlayer2HeroHP);
        return headerImage;
    }

    public void updateHeroHP() {

        int player1HeroHp = ((Unit) (match.getPlayer1().getHand().getHero())).getHP();
        int player2HeroHp = ((Unit) (match.getPlayer2().getHand().getHero())).getHP();
        labelPlayer1HeroHP.setText(player1HeroHp + "");
        labelPlayer2HeroHP.setText(player2HeroHp + "");
    }


    private AnchorPane headerGameInfo() {

        AnchorPane headerGameInfo = new AnchorPane();
        headerGameInfo.getChildren().addAll(nameOfPlayersInHeader(), manasOfPlayersHeader());
        return headerGameInfo;

    }

    private AnchorPane nameOfPlayersInHeader() {

        AnchorPane headerLeftGameInfo = new AnchorPane();
        AnchorPane headerRightGameInfo = new AnchorPane();

        Label player1Name = new Label(match.getPlayer1().getUserName());
        Label player2Name = new Label(match.getPlayer2().getUserName());

        player1Name.setScaleX(2);
        player1Name.setScaleY(2);
        player2Name.setScaleX(2);
        player2Name.setScaleY(2);

        player1Name.setTextFill(Color.WHITE);
        player2Name.setTextFill(Color.WHITE);

        headerLeftGameInfo.getChildren().add(player2Name);
        headerRightGameInfo.getChildren().add(player1Name);

        headerLeftGameInfo.relocate(400, 50);
        headerRightGameInfo.relocate(850, 50);

        player1Name.setAlignment(Pos.CENTER);
        player2Name.setAlignment(Pos.CENTER);

        headerLeftGameInfo.setRotate(4);
        headerRightGameInfo.setRotate(-4);

        AnchorPane headerGameInfo = new AnchorPane();
        headerGameInfo.getChildren().addAll(headerLeftGameInfo, headerRightGameInfo);
        return headerGameInfo;
    }

    private AnchorPane manasOfPlayersHeader() {

        HBox player1ManaBox = new HBox();
        HBox player2ManaBox = new HBox();

        for (int i = 0; i < 9 /*numberOfAllMana*/; i++) {
            player1Mana[i] = new ImageView();
            player2Mana[i] = new ImageView();
        }

        updatePlayersMana();

        for (int i = 8; i >= 0; i--) player1ManaBox.getChildren().add(player1Mana[i]);
        for (int i = 0; i < 9 /*numberOfAllMana*/; i++) player2ManaBox.getChildren().add(player2Mana[i]);


        player2ManaBox.relocate(320, 70);
        player1ManaBox.relocate(710, 70);
        player2ManaBox.setSpacing(-35);
        player1ManaBox.setSpacing(-35);

        player1ManaBox.setRotate(3);
        player2ManaBox.setRotate(-3);

        AnchorPane manasPane = new AnchorPane();
        manasPane.getChildren().addAll(player1ManaBox, player2ManaBox);
        return manasPane;
    }

    public void updatePlayersMana() {

        int numberOfPlayer1Mana = match.getPlayer1Mana();
        int numberOfPlayer2Mana = match.getPlayer2Mana();

        for (int i = 0; i < numberOfPlayer1Mana; i++) {

            player1Mana[i].setImage(ImageLibrary.FillMana.getImage());
            player1Mana[i].setScaleX(0.4);
            player1Mana[i].setScaleY(0.4);
            player1Mana[i].getStyleClass().add("mana-bar-item");
        }

        for (int i = numberOfPlayer1Mana; i < 9 /*numberOfAllMana*/; i++) {

            player1Mana[i].setImage(ImageLibrary.EmptyMana.getImage());
            player1Mana[i].setScaleX(0.4);
            player1Mana[i].setScaleY(0.4);
            player1Mana[i].getStyleClass().add("mana-bar-item");
        }

        for (int i = numberOfPlayer2Mana; i < 9 /*numberOfAllMana*/; i++) {

            player2Mana[i].setImage(ImageLibrary.EmptyMana.getImage());
            player2Mana[i].setScaleX(0.4);
            player2Mana[i].setScaleY(0.4);
            player2Mana[i].getStyleClass().add("mana-bar-item");
        }

        for (int i = 0; i < numberOfPlayer2Mana; i++) {

            player2Mana[i].setImage(ImageLibrary.FillMana.getImage());
            player2Mana[i].setScaleX(0.4);
            player2Mana[i].setScaleY(0.4);
            player2Mana[i].getStyleClass().add("mana-bar-item");
        }
    }


    private AnchorPane endTurnButton() {

        ImageView endTurnButtonImage = new ImageView(ImageLibrary.EndTurnButton.getImage());
        Label endTurnLabel = new Label("END TURN");
        endTurnLabel.setTextFill(Color.WHITE);
        endTurnLabel.setFont(Font.font(20));

        StackPane endTurnButton = new StackPane();
        endTurnButton.getChildren().addAll(endTurnButtonImage, endTurnLabel);
        endTurnButton.relocate(1000, 570);
        endTurnButton.getStyleClass().add("enterMouseOnEndTurnButton");

        AnchorPane pane = new AnchorPane();
        pane.getChildren().add(endTurnButton);

        endTurnButton.setOnMouseClicked(event -> {

            //TODO
            /*ImageView endTurnPopup = new ImageView(ImageLibrary.EndTurnPopup.getImage());
            Label endTurnPopupLabel = new Label("END TURN");
            endTurnPopupLabel.setFont(Font.font(40));
            endTurnPopupLabel.setTextFill(Color.WHITE);

            StackPane popup = new StackPane();
            popup.getChildren().addAll(endTurnPopup, endTurnPopupLabel);
            popup.relocate(0, 0);
            popup.setScaleX(0.3);
            popup.setScaleY(0.3);

            ScaleTransition scaleTransition = new ScaleTransition();
            scaleTransition.setDuration(Duration.millis(500));
            scaleTransition.setNode(popup);
            scaleTransition.setByX(2);
            scaleTransition.setByY(2);
            scaleTransition.setCycleCount(1);
            scaleTransition.setAutoReverse(false);
            scaleTransition.play();

            FadeTransition fadeTransition = new FadeTransition(Duration.millis(500));
            fadeTransition.setFromValue(1);
            fadeTransition.setToValue(0);
            fadeTransition.setCycleCount(1);
            fadeTransition.setAutoReverse(false);
            fadeTransition.play();

            pane.getChildren().add(popup);*/
        });

        return pane;
    }
}

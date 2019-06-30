package ui.battleUI;

import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import models.Card;
import models.GamePlay.Match;
import ui.ImageLibrary;

import java.util.ArrayList;


public class HandBuilder {

    private HBox hand = new HBox();
    private StackPane[] handCard = new StackPane[6];

    HBox getHand(Match match) {

        for (int i = 0; i < 6; i++) {
            handCard[i] = new StackPane();
            hand.getChildren().add(handCard[i]);
        }

        updateHand(match);

        hand.relocate(330, 570);
        hand.setSpacing(-10);
        return hand;
    }

    public void updateHand(Match match) {

        ArrayList<Card> handsCard = match.getPlayer1().getHand().getHandCards();
        Card reserveCard = match.getPlayer1().getHand().getReserveCard();

        for (int i = 0; i < 5; i++) {

            handCard[i].getChildren().removeAll(handCard[i].getChildren());
            handCard[i].getChildren().add(new ImageView(ImageLibrary.HandCardAround.getImage()));
            handCard[i].setScaleX(0.7);
            handCard[i].setScaleY(0.7);
            handCard[i].getStyleClass().add("enterMouseOnHandCard");


            if (handsCard.size() > i) {
                try {
                    handCard[i].getChildren().add(new ImageView("resources/battle/units/" +
                            handsCard.get(i).getCardName() + "/stand"));
                } catch (Exception e) {
                    handCard[i].getChildren().add(new ImageView("resources/battle/units/default/stand"));
                }
            }
        }

        handCard[5].getChildren().removeAll(handCard[5].getChildren());
        handCard[5].getChildren().add(new ImageView(ImageLibrary.HandCardAround.getImage()));
        handCard[5].setScaleX(0.7);
        handCard[5].setScaleY(0.7);
        handCard[5].getStyleClass().add("enterMouseOnHandCard");

        if (reserveCard != null) {

            try {
                handCard[5].getChildren().add(new ImageView("resources/battle/units/" +
                        reserveCard.getCardName() + "/stand"));
            } catch (Exception e) {
                handCard[5].getChildren().add(new ImageView("resources/battle/units/default/stand"));
            }
        }
    }
}


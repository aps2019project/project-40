package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import models.Card;
import models.Unit;


public class CardController {
    @FXML
    private ImageView cardImage;

    @FXML
    private Label cardInfo;

    @FXML
    private Label cardName;

    @FXML
    private Label defensePoint;

    @FXML
    private Label attackPoint;

    @FXML
    private Label manaPoint;

    void setInformation(Card card) {
        String manaP = String.valueOf(card.getManaCost());
        switch (card.getType()) {
            case SPELL:
                cardInfo.setText(card.getDescription());
                cardName.setText(card.getCardName());
                manaPoint.setText(String.valueOf(card.getManaCost()));
                break;
            case USABLE_ITEM:
                cardInfo.setText(card.getDescription());
                cardName.setText(card.getCardName());
                break;
            case MINION:
                cardInfo.setText(card.getDescription());
                cardName.setText(card.getCardName());
                defensePoint.setText(String.valueOf(((Unit) card).getHealthPoint()));
                attackPoint.setText(String.valueOf(((Unit) card).getAttackPoint()));
                manaPoint.setText(manaP);
                break;
            case HERO:
                cardInfo.setText(card.getDescription());
                cardName.setText(card.getCardName());
                defensePoint.setText(String.valueOf(((Unit) card).getHealthPoint()));
                attackPoint.setText(String.valueOf(((Unit) card).getAttackPoint()));
                break;
        }
    }

}

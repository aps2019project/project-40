package controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.Account;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StartMenuController implements Initializable {
    private double x, y;
    @FXML
    private Button btnSave;

    @FXML
    private Label money;

    @FXML
    private Button btnPlay;

    @FXML
    private Button btnShop;

    @FXML
    private Button btnCollection;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        money.setText(String.valueOf(Controller.getInstance().getAccount().getMoney()));
    }

    @FXML
    void mouseIn(MouseEvent event) {
        if (event.getSource() instanceof Button) {
            Button button = (Button) event.getSource();
            if (button.getText().trim().isEmpty())
                button.setOpacity(1);
            else
                button.setStyle("-fx-font-size: 24 ;-fx-background-color: transparent;-fx-font-style:italic;-fx-font-weight:bold ");
        }
    }

    @FXML
    void mouseOut(MouseEvent event) {
        if (event.getSource() instanceof Button) {
            Button button = (Button) event.getSource();
            if (button.getText().trim().isEmpty())
                button.setOpacity(0.6);
            else
                button.setStyle("-fx-font-size: 20 ;-fx-background-color: transparent;-fx-font-style:italic;-fx-font-weight:bold");
        }
    }

    @FXML
    void gotoMatchHistory(ActionEvent event) {
        openPage("../view/MatchHistoryView.fxml");
    }

    @FXML
    void gotoLeaderboard() {
        openPage("../view/LeaderBoardView.fxml");

    }

    @FXML
    void gotoSettings() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("../view/cardBackground/CustomCard.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            scene.setOnMousePressed(event -> {
                x = event.getSceneX();
                y = event.getSceneY();
            });

            scene.setOnMouseDragged(event -> {

                Controller.stage.setX(event.getScreenX() - x);
                Controller.stage.setY(event.getScreenY() - y);

            });
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (IOException e) {
        }
    }

    @FXML
    void gotoBattleMenu() {
        openPage("../view/battleMenuView/BattleMenuView.fxml");
    }

    @FXML
    void gotoCollectionMenu() {
        openPage("../view/collectionMenuView/CollectionMenuView.fxml");
    }

    @FXML
    void gotoShopMenu() {
        openPage("../view/shopMenuView/ShopMenuView.fxml");
    }

    @FXML
    void save(ActionEvent event) {
        Account.save(Controller.getInstance().getAccount());
    }

    @FXML
    void exit(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    public void openPage(String path) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource(path));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            scene.setOnMousePressed(event -> {
                x = event.getSceneX();
                y = event.getSceneY();
            });

            scene.setOnMouseDragged(event -> {

                Controller.stage.setX(event.getScreenX() - x);
                Controller.stage.setY(event.getScreenY() - y);

            });
            Controller.stage.setScene(scene);
        } catch (IOException e) {
        }
    }

}

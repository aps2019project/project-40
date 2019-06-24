package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Account;
import models.LoginMenu;
import request.accountMenuRequest.AccountError;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;

public class AccountMenuController {
    private double x, y;
    private Account account;
    @FXML
    private TextField txtUsername;

    @FXML
    private Label loginError;

    @FXML
    private PasswordField txtPassword;

    @FXML
    void signIn(ActionEvent event) {
        String userName = txtUsername.getText();
        String password = txtPassword.getText();
        if (userName.trim().isEmpty() || password.trim().isEmpty()) {
            loginError.setText(AccountError.FIELDS_CAN_NOT_BE_EMPTY.toString());
            return;
        }
        if (LoginMenu.getInstance().checkIfAccountExist(userName)) {
            account = LoginMenu.getInstance().login(userName, password);

            if (account == null)
                loginError.setText(AccountError.PASSWORD_IS_INCORRECT.toString());
            else {
                Controller.getInstance().setAccount(account);
                gotoStartMenu();
            }

        } else
            loginError.setText(AccountError.USERNAME_DOESENT_EXIST.toString());
    }

    @FXML
    void signUp(ActionEvent event) {
        String userName = txtUsername.getText();
        String password = txtPassword.getText();
        if (userName.trim().isEmpty() || password.trim().isEmpty()) {
            loginError.setText(AccountError.FIELDS_CAN_NOT_BE_EMPTY.toString());
            return;
        }
        if (LoginMenu.getInstance().checkIfAccountExist(userName)) {
            loginError.setText(AccountError.USERNAME_ALREADY_EXIST.toString());
            return;
        }
        Controller.getInstance().setAccount(account);
        account = LoginMenu.getInstance().createAccount(userName, password);
        Controller.getInstance().setAccount(account);
        Account.save(account);
        gotoStartMenu();
    }

    @FXML
    void exit(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    public void gotoStartMenu() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../view/StartMenuView.fxml"));
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

    private void showLeaderboard() {
        if (LoginMenu.getUsers().size() > 0)
            Collections.sort(LoginMenu.getUsers(), new Comparator<Account>() {
                public int compare(Account account1, Account account2) {
                    return account1.getWinsNumber() - account2.getWinsNumber();
                }
            });
        //AccountMenuView.getInstance().showLeaderBoard(LoginMenu.getUsers());
    }
}

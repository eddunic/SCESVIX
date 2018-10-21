package scesvi.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class LoginScreenController {

    @FXML
    private JFXTextField tName;

    @FXML
    private JFXPasswordField pPassword;

    @FXML
    private JFXButton bEnter;

    @FXML
    private Label lblCount;

    @FXML
    private Label lblRegister;

    public void btOpenRegisterAction() {
    	MainApp.changeScreen("UserRegister");
    }
    
}


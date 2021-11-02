package com.company.controllers;

import com.company.handlers.MainHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController {
    private Stage stage;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button automaticComparisonBrowseButton1;

    @FXML
    private Button automaticComparisonBrowseButton2;

    @FXML
    private Button automaticComparisonCompareButton;

    @FXML
    private TextField automaticComparisonInputField1;

    @FXML
    private TextField automaticComparisonInputField2;

    @FXML
    private Button getHashBrowseButton;

    @FXML
    private Button getHashCalculateButton;

    @FXML
    private TextField getHashInputField;

    @FXML
    private TextField getHashOutputField;

    @FXML
    private Button manualComparisonCompareButton;

    @FXML
    private TextField manualComparisonInputField1;

    @FXML
    private TextField manualComparisonInputField2;


    @FXML
    void initialize() {
        MainHandler mainHandler = new MainHandler();

        getHashBrowseButton.setOnAction(actionEvent -> mainHandler.setFilePathOfUserToTextField(getHashInputField,stage));
        getHashCalculateButton.setOnAction(actionEvent -> mainHandler.setFileHashToField(getHashInputField.getText(),getHashOutputField));

        automaticComparisonBrowseButton1.setOnAction(actionEvent -> mainHandler.setFilePathOfUserToTextField(automaticComparisonInputField1, stage));
        automaticComparisonBrowseButton2.setOnAction(actionEvent -> mainHandler.setFilePathOfUserToTextField(automaticComparisonInputField2, stage));
        automaticComparisonCompareButton.setOnAction(actionEvent -> mainHandler.compareHashes(automaticComparisonInputField1, automaticComparisonInputField2));

        manualComparisonCompareButton.setOnAction(actionEvent -> mainHandler.compareFieldsValues(manualComparisonInputField1, manualComparisonInputField2));
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}

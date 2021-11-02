package com.company;

import com.company.controllers.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/sample.fxml"));
        Parent root = (Parent)loader.load();

        MainController mainController = loader.getController();
        mainController.setStage(stage);

        stage.getIcons().add(new Image(Main.class.getResourceAsStream("/images/icon.png")));
        stage.setTitle("MDer5");
        stage.setScene(new Scene(root));
        stage.show();
    }
}

package ru.sapteh;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class MainFx extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/StartMenu.fxml"));
        stage.setTitle("Autoservice program");
        stage.setScene(new Scene(root));
        stage.getIcons().add(new Image("/image/service_logo.png"));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

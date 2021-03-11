package ru.sapteh.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.sapteh.dao.DAO;
import ru.sapteh.entity.User;
import ru.sapteh.service.UserDaoImpl;

import java.io.*;

public class MainController {
    private final SessionFactory factory;
    public static String role;
    public MainController() {
        factory = new Configuration().configure().buildSessionFactory();
    }

    ObservableList<User> listUsers = FXCollections.observableArrayList();
    @FXML
    private ImageView mainImage;

    @FXML
    private Button buttonExit;
    @FXML
    private TextField txtLogin;
    @FXML
    private PasswordField txtPassword;

    @FXML
    private Button buttonOpenProgram;
    @FXML
    private Button buttonCloseRegistration;
    @FXML
    private Label lblInfo;

    @FXML
    public void onActionExit(ActionEvent event) {

    }

    @FXML
    public void initialize() {
        getMainImage();
        initUsers();
    }

    public void initUsers() {
        DAO<User, Integer> daoUser = new UserDaoImpl(factory);
        listUsers.addAll(daoUser.findByAll());
    }
    @FXML
    void onActionOpenProgram(ActionEvent event) throws IOException {
        for (User user: listUsers) {

            if (user.getLogin().equals(txtLogin.getText()) && user.getPassword().equals(txtPassword.getText())){
                buttonOpenProgram.getScene().getWindow().hide();
                lblInfo.setText("Вход...");
                role = user.getRole();
                Parent parent = FXMLLoader.load(getClass().getResource("/view/Main.fxml"));
                Stage stage = new Stage();
                stage.setTitle("Auto service");
                stage.setScene(new Scene(parent));
                stage.getIcons().add(new Image("/image/AvtoServ.png"));
                stage.show();
            } else lblInfo.setText("Неправильный логин или пароль");
        }
    }




    private void getMainImage(){
        try {
            InputStream pathImage = new FileInputStream("./src/main/resources/image/service_logo.png");
            Image image = new Image(pathImage);
            mainImage.setImage(image);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
    @FXML
    void buttonRegistration(ActionEvent event) throws IOException {
        buttonCloseRegistration.getScene().getWindow().hide();
        Parent parent = FXMLLoader.load(getClass().getResource("/view/RegUser.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Auto service");
        stage.setScene(new Scene(parent));
        stage.getIcons().add(new Image("/image/Reg.png"));
        stage.show();
    }

}

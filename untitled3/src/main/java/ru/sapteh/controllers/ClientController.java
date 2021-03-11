package ru.sapteh.controllers;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.sapteh.dao.DAO;
import ru.sapteh.entity.Client;
import ru.sapteh.entity.ClientService;
import ru.sapteh.service.ClientDaoImpl;
import ru.sapteh.service.ClientServiceDaoImpl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;


public class ClientController {

    private final SessionFactory factory;

    public ClientController(){
        factory = new Configuration().configure().buildSessionFactory();
    }

    private final ObservableList<Client> clientObservableList = FXCollections.observableArrayList();

    @FXML
    private TableView<Client> tableViewClient;
    @FXML
    private TableColumn <Client, Integer> id;
    @FXML
    private TableColumn <Client, Character> gender;
    @FXML
    private TableColumn <Client, String> firstName;
    @FXML
    private TableColumn <Client, String> lastName;
    @FXML
    private TableColumn <Client, String> patronymic;
    @FXML
    private TableColumn <Client, Date> birthday;
    @FXML
    private TableColumn <Client, String> phone;
    @FXML
    private TableColumn <Client, String> email;
    @FXML
    private TableColumn <Client, Date> registrationDate;
    @FXML
    private TableColumn <Client, String> lastVisitDate;
    @FXML
    private TableColumn <Client, Integer> countVisit;
    @FXML
    private TableColumn <Client, String> tags;

    @FXML
    private Button buttonRegNewClient;
    @FXML
    private ComboBox<Integer> sizeComboBox;

    @FXML
    private Pagination sizePagination;

    private int valuesFromDatabaseSize;
    private int totalPage;

    private void initDataFromDatabase(){
        DAO<Client, Integer> clientDaoImpl = new ClientDaoImpl(factory);
        DAO<ClientService, Integer> clientServiceDaoImpl = new ClientServiceDaoImpl(factory);
        clientServiceDaoImpl.findByAll();
        List<Client> listClientsFromDatabase = clientDaoImpl.findByAll();
        clientObservableList.addAll(listClientsFromDatabase);
    }
    private void initTableView() {

        tableViewClient.setItems(clientObservableList);
        id.setCellValueFactory(c -> new SimpleObjectProperty<>(c.getValue().getId()));
        gender.setCellValueFactory(c -> new SimpleObjectProperty<>(c.getValue().getGender().getCode()));
        firstName.setCellValueFactory(c -> new SimpleObjectProperty<>(c.getValue().getFirstName()));
        lastName.setCellValueFactory(c -> new SimpleObjectProperty<>(c.getValue().getLastName()));
        patronymic.setCellValueFactory(c -> new SimpleObjectProperty<>(c.getValue().getPatronymic()));
        birthday.setCellValueFactory(c -> new SimpleObjectProperty<>(c.getValue().getBirthday()));
        phone.setCellValueFactory(c -> new SimpleObjectProperty<>(c.getValue().getPhone()));
        email.setCellValueFactory(c -> new SimpleObjectProperty<>(c.getValue().getEmail()));
        registrationDate.setCellValueFactory(c -> new SimpleObjectProperty<>(c.getValue().getRegistrationDate()));
        lastVisitDate.setCellValueFactory(c -> {
            Set<ClientService> clientServiceSet = c.getValue().getClientServiceSet();
            if (clientServiceSet.size() != 0) {
                Date startTime = clientServiceSet.stream()
                        .max(Comparator.comparing(ClientService::getStartTime))
                        .get().getStartTime();
                return new SimpleObjectProperty<>(
                        new SimpleDateFormat("dd.MM.yyyy").format(startTime)
                );
            }
            return new SimpleObjectProperty<>("");
        });
        countVisit.setCellValueFactory(c -> new SimpleObjectProperty<>(c.getValue().getClientServiceSet().size()));

//        tags.setCellValueFactory(c -> new SimpleObjectProperty<>(c.getValue().getTags().iterator().next().getColor()));
//        tags.setCellFactory(column -> new TableCell<>() {
//            @Override
//            protected void updateItem(String item, boolean empty) {
//                if(item != null || !empty){
//                    if(item.equalsIgnoreCase("green"))
//                        setStyle("-fx-background-color: #80ee80");
//                    if(item.equalsIgnoreCase("red"))
//                        setStyle("-fx-background-color: #db9898");
//                }
//            }
//        });
    }
        @FXML
        public void initialize(){
            initDataFromDatabase();
            initTableView();

            //ComboBox
            valuesFromDatabaseSize = clientObservableList.size();
            ObservableList<Integer> options = FXCollections.observableArrayList( 10, 20, 50, valuesFromDatabaseSize);
            sizeComboBox.setItems(options);
            sizeComboBox.setValue(options.get(0));
            sizeComboBox.valueProperty().addListener(
                    (obj, oldValue, newValue) -> {
                        int valueComboBox = sizeComboBox.getValue();

                        totalPage = (int) (Math.ceil(valuesFromDatabaseSize * 1.0 / valueComboBox));

                        //Pagination pages
                        sizePagination.setPageCount(totalPage);
                        sizePagination.setCurrentPageIndex(0);
                        tableViewClient.setItems(
                                FXCollections.observableArrayList(
                                        clientObservableList.subList(sizePagination.getCurrentPageIndex(), newValue))

                        );

                        //pagination
                        sizePagination.currentPageIndexProperty().addListener((obj1, oldValue1, newValue1) ->{
                                    try {
                                        tableViewClient.setItems(FXCollections.observableArrayList(clientObservableList.subList
                                                (valueComboBox * (newValue1.intValue() + 1) - valueComboBox, valueComboBox
                                                        * (newValue1.intValue() + 1))));
                                    }catch (IndexOutOfBoundsException e){
                                        tableViewClient.setItems(FXCollections.observableArrayList(clientObservableList.subList
                                                (valueComboBox * (newValue1.intValue() + 1) - valueComboBox, valueComboBox
                                                        * (newValue1.intValue() + 1))));
                                    }
                                }
                        );
                    });
        }

    public void buttonRegNewClient() throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/view/RegClient.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Registration new client");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/image/service_logo.png")));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.setScene(new Scene(parent));
        stage.getIcons().add(new Image("/image/RegClient.png"));
        stage.showAndWait();
    }
}


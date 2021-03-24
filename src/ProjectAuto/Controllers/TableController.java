package ProjectAuto.Controllers;

import ProjectAuto.Main;
import ProjectAuto.Objects.Database;
import ProjectAuto.Objects.Order;
import com.sun.jdi.connect.spi.Connection;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;
import java.util.ResourceBundle;

public class TableController implements Initializable {

    @FXML
    private TableView<Order> table_orders;

    @FXML
    private TableColumn<Order, Integer> col_id;

    @FXML
    private TableColumn<Order, String> col_name;

    @FXML
    private TableColumn<Order, String> col_mail;

    @FXML
    private TableColumn<Order, String> col_spz;

    @FXML
    private TableColumn<Order, String> col_brand;

    @FXML
    private TableColumn<Order, String> col_model;

    @FXML
    private TableColumn<Order, String> col_date;

    @FXML
    private TableColumn<Order, String> col_time;

    @FXML
    private TableColumn<Order, String> col_note;

    @FXML
    private Button AddOrderButton;

    @FXML
    private Button EditOrderButton;

    @FXML
    private Button DeleteOrderButton;

    @FXML
    private Button updateButton;

    @FXML
    private Button exitButton;

    @FXML
    private void AddOrderButtonClick(ActionEvent event) throws IOException {

        System.out.println("Add order button pressed");
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Přidat objednávku");
        alert.setHeaderText("Chystáte se vložit novou objednávku do systému?");
        alert.setContentText("Pokud ano, klikněte OK");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.CANCEL){
            System.out.println("Adding order canceled");
        }
        else{
            System.out.println("Adding order scene activate");

            Parent tableViewParent = FXMLLoader.load(Main.class.getResource("Scenes/AddOrderViewEx.fxml"));
            Scene tableViewScene = new Scene(tableViewParent);

            Stage addOrderWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
            addOrderWindow.setScene(tableViewScene);
            addOrderWindow.show();
        }

    }

    @FXML
    void DeleteOrderButtonPressed(ActionEvent event) throws IOException{

        System.out.println("Delete order button pressed");
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Odstranit objednávku");
        alert.setHeaderText("Chystáte se dstranit objednávku ze systému?");
        alert.setContentText("Pokud ano, klikněte OK");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.CANCEL){
            System.out.println("Deleting order canceled");
        }
        else{
            System.out.println("Deleting order activated");
        }

    }

    @FXML
    void EditOrderButtonPressed(ActionEvent event) throws IOException{

        System.out.println("Edit order button pressed");
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Upravit objednávku");
        alert.setHeaderText("Chystáte se upravit objednávku v systému?");
        alert.setContentText("Pokud ano, klikněte OK");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.CANCEL){
            System.out.println("Editin order canceled");
        }
        else{
            System.out.println("Editing order scene activate");

            //TODO: metoda switchScene na EditOrder scénu
        }
    }

    public void exitButtonPressed(ActionEvent actionEvent) {
        System.out.println("Exit button pressed");

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Ukončení programu");
        alert.setHeaderText("Opravdu chcete ukončit program ?");
        alert.setContentText("Pokud ano, klikněte OK");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.CANCEL){
            System.out.println("Exiting canceled");
        }
        else{
            System.out.println("Exiting program");

            Stage stage = (Stage) exitButton.getScene().getWindow();
            stage.close();
        }
    }

    ObservableList<Order> listM;

    int index = -1;

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;


    @Override
    public void initialize(URL url, ResourceBundle rb){
        col_id.setCellValueFactory(new PropertyValueFactory<Order, Integer>("orderID"));
        col_name.setCellValueFactory(new PropertyValueFactory<Order, String>("name"));
        col_mail.setCellValueFactory(new PropertyValueFactory<Order, String>("email"));
        col_spz.setCellValueFactory(new PropertyValueFactory<Order, String>("spz"));
        col_brand.setCellValueFactory(new PropertyValueFactory<Order, String>("brand"));
        col_model.setCellValueFactory(new PropertyValueFactory<Order, String>("model"));
        col_date.setCellValueFactory(new PropertyValueFactory<Order, String>("date"));
        col_time.setCellValueFactory(new PropertyValueFactory<Order, String>("time"));
        col_note.setCellValueFactory(new PropertyValueFactory<Order, String>("note"));

        listM = Database.getDataOrders();
        table_orders.setItems(listM);
    }
}

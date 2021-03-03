package sample;

import com.sun.jdi.connect.spi.Connection;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.print.DocFlavor;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class OrderController implements Initializable {

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
    void exitButton(ActionEvent event) {
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

        listM = Database.getDataOrders();
        table_orders.setItems(listM);
    }

}

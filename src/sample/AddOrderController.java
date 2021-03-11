package sample;

import com.sun.jdi.connect.spi.Connection;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

public class AddOrderController {

    @FXML
    private TextField timeTextField;

    @FXML
    private DatePicker dateTextField;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField emailTextFieald;

    @FXML
    private TextField spzTextField;

    @FXML
    private TextField brandTextField;

    @FXML
    private TextField modelTextField;

    @FXML
    private TextArea noteTextField;

    @FXML
    private Button AddOrderButton;

    @FXML
    private Button AddOrderExitButton;

    @FXML
    public void AddOrderExitButtonPressed(ActionEvent event) throws IOException {
        System.out.println("Cancel button pressed");
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Ukončit přidávání objednávky");
        alert.setHeaderText("Ukončením se nepřidá objednávka a informace se neuloží?");
        alert.setContentText("Pro pokračování klikněte na OK");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.CANCEL){
            System.out.println("Cancel exiting from addOrderView");
        }
        else{
            System.out.println("Adding order canceled and returning to TableView");

            Parent tableViewParent = FXMLLoader.load(getClass().getResource("TableViewEx.fxml"));
            Scene tableViewScene = new Scene(tableViewParent);

            Stage addOrderWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
            addOrderWindow.setScene(tableViewScene);
            addOrderWindow.show();
        }
    }


    ObservableList<Order> listM;

    int index = -1;

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;


    public void AddOrderPressed(ActionEvent actionEvent) throws IOException{

        /*
        String query = null;
        query = String.format("INSERT INTO sampleGuiTableView(name, email, spz, brand, model, date, time) VALUES('%s','%s','%s','%s','%s','%s','%s')");


                /*
        String sql = "INSERT INTO sampleGuiTableView (name, email, spz, brand, model, date, time) value (?, ?, ?, ?, ?, ?, ?)";
        try {
            pst.setString(1, nameTextField.getText());
            pst.setString(2, emailTextFieald.getText());
            pst.setString(3, spzTextField.getText());
            pst.setString(4, brandTextField.getText());
            pst.setString(5, modelTextField.getText());
            pst.setString(6, dateTextField.getValue().toString());
            pst.setString(7, timeTextField.getText());
            pst.execute();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Proces Objednávky");
            alert.setHeaderText("Objednávka přidána");
        }
        catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Proces Objednávky");
            alert.setHeaderText(e.toString());
        }*/
    }
}

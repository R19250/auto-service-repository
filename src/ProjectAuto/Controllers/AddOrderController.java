package ProjectAuto.Controllers;

import ProjectAuto.Main;
import ProjectAuto.Objects.Database;
import javafx.scene.control.Alert;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    private TextField noteTextField;

    @FXML
    private Button AddOrderButton;

    @FXML
    private Button AddOrderExitButton;

    @FXML
    private ComboBox<String> timeField;

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

            Parent tableViewParent = FXMLLoader.load(Main.class.getResource("Scenes/TableViewEx.fxml"));
            Scene tableViewScene = new Scene(tableViewParent);

            Stage addOrderWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
            addOrderWindow.setScene(tableViewScene);
            addOrderWindow.show();
        }
    }

/*
    ObservableList<ProjectAuto.Objects.Order> listM;
    int index = -1;
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
*/

    public void AddOrderPressed(ActionEvent event) throws IOException{
        String query = null;
        Alert alertConfirm = new Alert(Alert.AlertType.CONFIRMATION);
        Alert alertError = new Alert(Alert.AlertType.ERROR);

        if(!timeField.getValue().equals("") && dateTextField.getValue() != null && !nameTextField.getText().equals("") &&
                !emailTextFieald.getText().equals("") && !spzTextField.getText().equals("") && !brandTextField.getText().equals("") &&
                !modelTextField.getText().equals("")){
            if (!timeField.getValue().equals("Termín plně obsazen")){

                //TODO: další oetření

                //if (!nameTextField.contains()) {
                    if (spzTextField.getText().length() == 7) {
                        if (noteTextField.getText().equals("")) {
                            alertConfirm.setTitle("Proces Objednávky");
                            alertConfirm.setHeaderText("Nezadal jste poznámku, chce přesto pokračovat?");
                            alertConfirm.setContentText("Pokud ano, klikněte OK");

                            Optional<ButtonType> result = alertConfirm.showAndWait();
                            if (result.get() != ButtonType.CANCEL) {
                                System.out.println("Adding order process");
                                AddOrderMethod(event);
                            } else {
                                System.out.println("Waiting for note to be add");
                            }
                        } else {
                            AddOrderMethod(event);
                        }
                    } else {
                        System.out.println("SPZ is wrong");
                        alertError.setTitle("Proces Objednávky");
                        alertError.setHeaderText("SPZ je nesprávně zadána");
                        alertError.setContentText("Zadejte SPZ ve správném formátu");
                        alertError.showAndWait();
                    }

                //TODO: další oetření
                //}else{}
            }else{
                System.out.println("Select date is unavailable");
                alertError.setTitle("Proces Objednávky");
                alertError.setHeaderText("Vybraný termín je plný");
                alertError.setContentText("Zadejte prosim jiný den");
                alertError.showAndWait();
            }
        }else{
            System.out.println("Key information missing for the order");
            alertError.setTitle("Proces Objednávky");
            alertError.setHeaderText("Nebyly vyplneny potrebne informace");
            alertError.setContentText("Zadejte prosim požadované údaje");
            alertError.showAndWait();
        }
    }

    public void initialize(){
        timeField.getItems().removeAll(timeField.getItems());
        timeField.getItems().addAll("10:00:00", "11:00:00","13:00:00","14:00:00","15:00:00","16:00:00","17:00:00");
    }

    public void dateSelected(ActionEvent actionEvent) {
        ResultSet result = Database.getUsedTimeFromDatabase(dateTextField.getValue().toString());
        timeField.getItems().removeAll(timeField.getItems());
        timeField.getItems().addAll("10:00:00", "11:00:00","13:00:00","14:00:00","15:00:00","16:00:00","17:00:00");

        try {
            result.last();
            int size = result.getRow();
            result.beforeFirst();
            if (size < 2) {
                while (result.next()) {
                    timeField.getItems().remove(result.getString(1));
                }
            } else {
                timeField.getItems().removeAll(timeField.getItems());
                timeField.getItems().add("Termín plně obsazen");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void AddOrderMethod(ActionEvent event) throws IOException{
        String query = null;

        query = String.format("INSERT INTO sampleGuiTableView(name, email, spz, brand, model, date, time, note)" +
                        " VALUES('%s','%s','%s','%s','%s','%s','%s','%s')",
                nameTextField.getText(), emailTextFieald.getText(), spzTextField.getText(), brandTextField.getText(),
                modelTextField.getText(), dateTextField.getValue(), timeField.getValue(), noteTextField.getText());
        try {
            Database.insertData(query);
            System.out.println("ProjectAuto.Objects.Order has been added");

            Parent tableViewParent = FXMLLoader.load(Main.class.getResource("Scenes/TableViewEx.fxml"));
            Scene tableViewScene = new Scene(tableViewParent);

            Stage addOrderWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
            addOrderWindow.setScene(tableViewScene);
            addOrderWindow.show();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("ProjectAuto.Objects.Order can not be added");
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Proces Objednávky");
            alert1.setHeaderText("Objednavku se nepodarilo pridat");
            alert1.setContentText(e.toString());
            alert1.showAndWait();}
    }
}

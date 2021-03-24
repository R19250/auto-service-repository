package ProjectAuto;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Scenes/TableViewEx.fxml"));
        primaryStage.setTitle("Objednávky autodílny");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
        //ProjectAuto.Objects.Database db = new ProjectAuto.Objects.Database();
        //db.selectData("SELECT * FROM testTableYolo");
        //db.selectDataArg("SELECT * FROM sampleGuiTableView");
    }
}

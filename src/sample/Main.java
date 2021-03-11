package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("TableViewEx.fxml"));
        primaryStage.setTitle("SampleTableView");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {

        launch(args);

        //Database db = new Database();

        //db.selectData("SELECT * FROM testTableYolo");
        //db.selectDataArg("SELECT * FROM sampleGuiTableView");
    }
}

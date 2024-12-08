import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws SQLException {
        DatabaseOperations.createNewTable();
        DatabaseOperations.insert("John Doe", "john@example.com");
        DatabaseOperations.insert("Jane Doe", "jane@example.com");
        DatabaseOperations.shiftIds();
        DatabaseOperations.selectAll();
        StringTokenizer tokenizer = DatabaseOperations.getAll();
        TableView<Info> tableView = new TableView<>();
        while (tokenizer.hasMoreTokens()) {
            TableColumn<Info, String> tableColumn = new TableColumn<Info, String>(tokenizer.nextToken());
            tableView.getColumns().add(tableColumn);
        }
        Pane root = new Pane();
        root.getChildren().add(tableView);
        primaryStage.setTitle("database");
        Scene scene = new Scene(root, 350, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
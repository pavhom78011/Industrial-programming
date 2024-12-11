import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main extends Application {

    String addedNames = "";
    String addedAges = "";
    ArrayList<String> allInfo = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) throws SQLException {
        DatabaseOperations.createNewTable();
        allInfo.addAll(DatabaseOperations.GetAllInfo());
        StringTokenizer tokenizer = DatabaseOperations.getAllNamesOfColumns();
        TableView<Info> tableView = new TableView<>(AddAll(allInfo));
        tableView.setMinWidth(300);
        tableView.setMinHeight(500);
        Button sortNames = new Button("sort names");
        Button sortAges = new Button("sort ages");
        Button addNew = new Button("add");
        Button deleteAll = new Button("delete all");
        Button delete = new Button("delete");
        Button exit = new Button("exit");
        TextField textName = new TextField();
        TextField textAge = new TextField();
        sortNames.setLayoutX(300);
        sortNames.setPrefWidth(100);
        sortAges.setLayoutX(300);
        sortAges.setLayoutY(50);
        sortAges.setPrefWidth(100);
        addNew.setLayoutX(300);
        addNew.setLayoutY(100);
        addNew.setPrefWidth(100);
        textName.setLayoutX(300);
        textName.setLayoutY(140);
        textName.setPrefWidth(100);
        textAge.setLayoutX(300);
        textAge.setLayoutY(180);
        textAge.setPrefWidth(100);
        deleteAll.setLayoutX(300);
        deleteAll.setLayoutY(230);
        deleteAll.setPrefWidth(100);
        delete.setLayoutX(300);
        delete.setLayoutY(280);
        delete.setPrefWidth(100);
        exit.setLayoutX(300);
        exit.setLayoutY(450);
        exit.setPrefWidth(100);
        TableColumn<Info, String> tableColumn = new TableColumn<Info, String>(tokenizer.nextToken());
        TableColumn<Info, String> tableColumn1 = new TableColumn<Info, String>(tokenizer.nextToken());
        TableColumn<Info, String> tableColumn2 = new TableColumn<Info, String>(tokenizer.nextToken());
        tableColumn.setCellValueFactory(new PropertyValueFactory<Info, String>("id"));
        tableColumn1.setCellValueFactory(new PropertyValueFactory<Info, String>("name"));
        tableColumn2.setCellValueFactory(new PropertyValueFactory<Info, String>("age"));
        tableView.getColumns().add(tableColumn);
        tableView.getColumns().add(tableColumn1);
        tableView.getColumns().add(tableColumn2);
        Pane root = new Pane();
        root.getChildren().add(tableView);
        root.getChildren().add(sortNames);
        root.getChildren().add(sortAges);
        root.getChildren().add(addNew);
        root.getChildren().add(textName);
        root.getChildren().add(textAge);
        root.getChildren().add(deleteAll);
        root.getChildren().add(delete);
        root.getChildren().add(exit);
        primaryStage.setTitle("database");
        Scene scene = new Scene(root, 400, 500);
        primaryStage.setScene(scene);
        sortNames.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                tableView.getSortOrder().clear();
                tableView.getSortOrder().add(tableColumn1);
            }
        });
        sortAges.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                tableView.getSortOrder().clear();
                tableView.getSortOrder().add(tableColumn2);
            }
        });
        addNew.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (!textName.getText().isEmpty() && !textAge.getText().isEmpty()) {
                    String name = textName.getText();
                    int age = Integer.parseInt(textAge.getText());
                    try {
                        DatabaseOperations.insert(name, age);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    allInfo.clear();
                    try {
                        allInfo.addAll(DatabaseOperations.GetAllInfo());
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    tableView.setItems(AddAll(allInfo));
                    textName.clear();
                    textAge.clear();
                }
            }
        });
        deleteAll.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    DatabaseOperations.clearDatabase();
                    DatabaseOperations.createNewTable();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                allInfo.clear();
                try {
                    allInfo.addAll(DatabaseOperations.GetAllInfo());
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                tableView.setItems(AddAll(allInfo));
            }
        });
        delete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Info info = tableView.getSelectionModel().getSelectedItem();
                if (info != null) {
                    try {
                        DatabaseOperations.delete(info.getId());
                        DatabaseOperations.shiftIds();
                        tableView.getItems().remove(info);
                        allInfo.clear();
                        try {
                            allInfo.addAll(DatabaseOperations.GetAllInfo());
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                        tableView.setItems(AddAll(allInfo));
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.exit(0);
            }
        });
        primaryStage.show();
    }

    static ObservableList<Info> AddAll (ArrayList<String> allInfo) {
        ObservableList<Info> data = FXCollections.observableArrayList();
        StringTokenizer stringTokenizer = new StringTokenizer(allInfo.get(0));
        StringTokenizer stringTokenizer1 = new StringTokenizer(allInfo.get(1));
        StringTokenizer stringTokenizer2 = new StringTokenizer(allInfo.get(2));
        int n = stringTokenizer.countTokens();
        for (int i = 0; i < n; ++i) {
            data.add(new Info(Integer.parseInt(stringTokenizer.nextToken()),stringTokenizer1.nextToken(), Integer.parseInt(stringTokenizer2.nextToken())));
        }
        return data;
    }

    public static void main(String[] args) throws SQLException {
        launch(args);
    }

}
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {
    static String str = "";
    @Override
    public void start(Stage primaryStage) throws IOException{
        final int[] i = {0};
        try {
            str = ReadWrite.ReadFile();
        } catch (Exception e) {
            System.out.println("file opening error");
        }
        Button button = new Button("Do");
        Button button1 = new Button("GoToNextString");
        Button button2 = new Button("Exit");
        button.setLayoutX(250);
        button.setLayoutY(150);
        button1.setLayoutX(250);
        button1.setLayoutY(100);
        button2.setLayoutX(250);
        button2.setLayoutY(50);
        TextField textField1 = new TextField("");
        TextArea textArea1 = new TextArea("");
        textArea1.setLayoutY(50);
        textArea1.setPrefHeight(150);
        textArea1.setPrefWidth(200);
        textArea1.setText(str);
        Pane root = new Pane();
        root.getChildren().add(textField1);
        root.getChildren().add(button);
        root.getChildren().add(textArea1);
        root.getChildren().add(button1);
        root.getChildren().add(button2);
        Scene scene = new Scene(root, 400, 250);
        primaryStage.setTitle("Example");
        primaryStage.setScene(scene);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                str += textField1.getText();
                textArea1.setText(str);
            }
        });
        textField1.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.ENTER) {
                    str += textField1.getText();
                    textArea1.setText(str);
                }
            }
        });
        button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                str += '\n';
                textArea1.setText(str);
            }
        });
        button2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    ReadWrite.WriteFile(str);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                System.exit(0);
            }
        });
        primaryStage.show();
    }

    public static void main(String[] args) throws IOException {
        launch(args);
    }
}
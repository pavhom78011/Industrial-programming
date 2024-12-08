import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main extends Application {
    ArrayList<MyStr> list = new ArrayList<>();
    ArrayList<CheckBox> companyCheckboxes = new ArrayList<>();
    ArrayList<VBox> presents = new ArrayList<>();
    int previousCost = 0;
    double sum = 0;
    int concertcost = 0;
    boolean concertSelected = false, discountUsed = false;
    @Override
    public void init() throws IOException {
        list = MyStr.ReadFile();
        concertcost = MyStr.ReadConcertCost();
    }
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Gift");
        Pane root = new Pane();
        VBox vBox = new VBox(list.size());
        Label label = new Label("0");
        Label concert = new Label("Do you need a concert?");
        Label regular_or_not = new Label("Regular customer?(if yes 10% discount)");
        Button button = new Button("continue");
        button.setLayoutY(450);
        button.setLayoutX(230);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                primaryStage.hide();
                openSecondWindow();
            }
        });
        label.setLayoutY(450);
        label.setLayoutX(10);
        concert.setLayoutY(210);
        concert.setLayoutX(80);
        regular_or_not.setLayoutX(40);
        regular_or_not.setLayoutY(300);
        root.getChildren().add(label);
        root.getChildren().add(concert);
        root.getChildren().add(regular_or_not);
        root.getChildren().add(button);
        for (int i = 0; i < list.size(); ++i) {
            CheckBox checkbox = new CheckBox(list.get(i).company);
            vBox.getChildren().add(checkbox);
            companyCheckboxes.add(checkbox);
            VBox vBoxPresents = new VBox();
            vBoxPresents.setLayoutX(160);
            for (int k = 0; k < list.get(i).presents.size(); ++k) {
                CheckBox checkBoxPresent = new CheckBox(list.get(i).presents.get(k) + " " + list.get(i).costs.get(k));
                vBoxPresents.getChildren().add(checkBoxPresent);
                checkBoxPresent.setOnAction(event -> CheckBoxPresents(checkBoxPresent, label, presents));
            }
            presents.add(vBoxPresents);
            checkbox.setOnAction(event -> handleCheckBoxSelection(checkbox, root));
        }
        CheckBox yes = new CheckBox("yes");
        yes.setLayoutX(110);
        yes.setLayoutY(240);
        CheckBox no = new CheckBox("no");
        no.setLayoutX(110);
        no.setLayoutY(270);
        CheckBox regular_customer = new CheckBox("yes");
        CheckBox not_a_regular_customer = new CheckBox("no");
        regular_customer.setLayoutX(110);
        regular_customer.setLayoutY(330);
        not_a_regular_customer.setLayoutX(110);
        not_a_regular_customer.setLayoutY(360);
        root.getChildren().add(yes);
        root.getChildren().add(no);
        root.getChildren().add(regular_customer);
        root.getChildren().add(not_a_regular_customer);
        yes.setOnAction(event -> YesOrNo(yes, yes, no, label));
        no.setOnAction(event -> YesOrNo(no, yes, no, label));
        regular_customer.setOnAction(event -> RegularOrNot(regular_customer, regular_customer, not_a_regular_customer, label));
        not_a_regular_customer.setOnAction(event -> RegularOrNot(not_a_regular_customer, regular_customer, not_a_regular_customer, label));
        root.getChildren().add(vBox);
        Scene scene = new Scene(root, 300, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    private void handleCheckBoxSelection(CheckBox selectedBox, Pane root) {
        int ind = 0;
        for (CheckBox checkBox : companyCheckboxes) {
            if (checkBox != selectedBox) {
                checkBox.setSelected(false);
                clearAllCheckBoxes(presents.get(ind));
            }
            ++ind;
        }
        for (VBox vBox : presents) {
            root.getChildren().remove(vBox);
        }
        ind = companyCheckboxes.indexOf(selectedBox);
        root.getChildren().add(presents.get(ind));
    }
    private void clearAllCheckBoxes(VBox vBox) {
        for (int i = 0; i < vBox.getChildren().size(); i++) {
            CheckBox checkBox = (CheckBox) vBox.getChildren().get(i);
            checkBox.setSelected(false);
        }
    }
    private void CheckBoxPresents (CheckBox selectedBox, Label label, ArrayList<VBox> presents) {
        StringTokenizer tokenizer = new StringTokenizer(selectedBox.getText());
        tokenizer.nextToken();
        sum -= previousCost;
        previousCost = Integer.parseInt(tokenizer.nextToken());
        sum += previousCost;
        label.setText(String.valueOf(sum));
        for (int i = 0; i < presents.size(); ++i) {
            for (int k = 0; k < presents.get(i).getChildren().size(); ++k) {
                CheckBox checkBox = (CheckBox) presents.get(i).getChildren().get(k);
                if (checkBox != selectedBox) {
                    checkBox.setSelected(false);
                }
            }
        }
    }
    private void YesOrNo (CheckBox selectedBox, CheckBox yes, CheckBox no, Label label) {
        if (selectedBox == yes) {
            no.setSelected(false);
            yes.setDisable(true);
            no.setDisable(false);
            concertSelected = true;
            sum += concertcost;
        }
        else {
            yes.setSelected(false);
            no.setDisable(true);
            yes.setDisable(false);
            if (concertSelected) {
                sum -= concertcost;
            }
            concertSelected = false;
        }
        label.setText(String.valueOf(sum));
    }
    private void RegularOrNot (CheckBox selectedBox, CheckBox yes, CheckBox no, Label label) {
        if (selectedBox == yes) {
            no.setSelected(false);
            yes.setDisable(true);
            no.setDisable(false);
            discountUsed = true;
            sum = sum / 10 * 9;
        }
        else {
            yes.setSelected(false);
            no.setDisable(true);
            yes.setDisable(false);
            if (discountUsed) {
                sum = sum * 10 / 9;
            }
            discountUsed = false;
        }
        label.setText(String.valueOf(sum));
    }
    private void openSecondWindow() {
        Stage secondStage = new Stage();
        Pane root = new Pane();
        secondStage.setTitle("Result");
        Label label = new Label("Total sum: " + String.valueOf(sum));
        Button button = new Button("finish");
        button.setLayoutY(90);
        button.setLayoutX(40);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    MyStr.Write(sum);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                System.exit(0);
            }
        });
        label.setLayoutY(50);
        label.setLayoutX(40);
        root.getChildren().add(label);
        root.getChildren().add(button);
        Scene secondScene = new Scene(root, 150, 150);
        secondStage.setScene(secondScene);
        secondStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
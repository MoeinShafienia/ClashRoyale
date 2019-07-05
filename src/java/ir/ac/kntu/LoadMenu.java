package ir.ac.kntu;

import javafx.beans.binding.BooleanBinding;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoadMenu {
    VBox root;
    Text text;
    TextField answer;
    Button next,back;

    public LoadMenu() {
        root = new VBox(10);
        root.setAlignment(Pos.CENTER);
        root.getStyleClass().add("card-picker-image");
    }

    public Scene createScene(Stage stage) {
        addTextField();
        addButton();

        handleText();
        Scene scene = new Scene(root);
        buttonHandler(stage);
        return scene;
    }

    private void addTextField() {
        answer = new TextField("Moji99");
        root.getChildren().add(answer);
    }
    private void addButton() {
        next = new Button("Next");
        back = new Button("Back");
        next.getStyleClass().add("custom-button");
        back.getStyleClass().add("custom-button");
        root.getChildren().addAll(next,back);
    }

    private void handleText() {
        BooleanBinding bb = new BooleanBinding(){
            { 
                super.bind(answer.textProperty());   
            }
            @Override
            protected boolean computeValue() {
                if(answer.getText().equals(null)) {
                    return true;
                }
                return false;
            }
        };
        next.disableProperty().bind(bb);
    }

    public void buttonHandler(Stage stage) {
        next.setOnAction(e -> {

        });
        back.setOnAction(e -> {
            stage.setScene(MainMenu.getScene());
        });
    }
}
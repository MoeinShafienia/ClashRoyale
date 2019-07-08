package ir.ac.kntu;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class ServerMenu {
    VBox root;
    Button button;

    public ServerMenu() {
        root = new VBox(20);
        root.setAlignment(Pos.CENTER);
        root.getStyleClass().add("card-picker-image");
    }

    public Scene createScene() {
        addButton();

        Scene scene = new Scene(root,Constants.WIDTH,Constants.HEIGHT);
        scene.getStylesheets().add("ir/ac/kntu/assets/Viper.css");
        buttonHandler();
        return scene;
    }

    private void addButton() {
        button = new Button("Create Server");
        button.getStyleClass().add("custom-button");
        root.getChildren().add(button);
    }

    public void buttonHandler() {
        button.setOnAction(e -> {
            new Server(5000);
        });
    }
}
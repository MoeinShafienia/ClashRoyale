package ir.ac.kntu;

import ir.ac.kntu.Constants;
import ir.ac.kntu.Player;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class GameOverMenu {
    private VBox root;
    private Text text;

    public GameOverMenu() {
        root = new VBox(20);
        root.setAlignment(Pos.CENTER);
    }

    public Scene createScene(Player player) {
        text = new Text();
        text.getStyleClass().add("label-custom");
        text.setText(player.getName() + " Won!");
        root.getChildren().add(text);
        Scene scene = new Scene(root,Constants.WIDTH,Constants.HEIGHT);
        return scene;
    }
}
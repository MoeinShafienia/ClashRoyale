import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PlayerNameMenu {
    private TextField nameField;
    private VBox childPane;
    private Pane root;
    private Button button;

    public PlayerNameMenu() {
        childPane = new VBox(20);
        root = new Pane();
        childPane.setTranslateX(Constants.WIDTH/2 -150);
        childPane.setTranslateY(Constants.HEIGHT/3 -10);
        childPane.setAlignment(Pos.CENTER);
        root.getStyleClass().add("card-picker-image");
    }

    public Scene createScene(String label) {
        addLable(label);
        addTextField();
        addButton("Next");

        root.getChildren().add(childPane);
        Scene scene = new Scene(root,Constants.WIDTH,Constants.HEIGHT);
        return scene;
    }

    private void addLable(String name) {
        Label label = new Label(name);
        childPane.getChildren().add(label);
    }
    private void addButton(String text) {
        button = new Button(text);
        button.getStyleClass().add("custom-button");
        childPane.getChildren().add(button);
    }
    private void addTextField() {
        nameField = new TextField("Moji99");
        childPane.getChildren().add(nameField);
    }

    public void buttonHandler(Stage stage, Scene targetScene) {
        button.setOnAction(e -> {
            String player1Name = nameField.getText();
            System.out.println(player1Name);
            stage.setScene(targetScene);
        });
    }
}
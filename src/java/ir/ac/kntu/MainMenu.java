import java.util.ArrayList;
import java.util.List;

import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainMenu {
    private Pane pane;
    private HBox root;
    private VBox leftPane;
    private VBox rightPane;
    private Line line;
    private List<Button> buttons;

    public MainMenu() {
        pane = new Pane();
        root = new HBox(10);
        leftPane = new VBox(10);
        rightPane = new VBox(10);
        root.setTranslateX(Constants.WIDTH/2 -140);
        root.setTranslateY(Constants.HEIGHT/3 -50);
        leftPane.setAlignment(Pos.CENTER_RIGHT);
        rightPane.setAlignment(Pos.CENTER_LEFT);
        pane.getStyleClass().add("card-picker-image");
        buttons = new ArrayList<>();
    }
    public Scene createScene() {
        //line
        addLine();
        root.getChildren().add(leftPane);
        //label
        addLable();
        //buttons
        addButton("Single Player");
        addButton("Multi Player");
        addButton("Load Game");
        addButton("Create Map");
        addButton("Exit");
        root.getChildren().add(rightPane);

        pane.getChildren().add(root);
        startAnimation();
        Scene scene = new Scene(pane,Constants.WIDTH,Constants.HEIGHT);

        return scene;
    }

    private void addLine() {
        line = new Line(Constants.WIDTH/2 ,Constants.HEIGHT/3 + 5,
            Constants.WIDTH/2 ,Constants.HEIGHT/3 + 340);
        line.setStrokeWidth(3);
        line.setStroke(Color.color(1, 1, 1, 0.75));
        line.setEffect(new DropShadow(5, Color.BLACK));
        line.setScaleY(0);
        leftPane.getChildren().add(line);  
    }
    private void addLable() {
        Label label = new Label("CLASH ROYALE");
        rightPane.getChildren().add(label);
    }
    private void addButton(String text) {
        Button button = new Button(text);
        button.getStyleClass().add("custom-button");
        buttons.add(button);
        rightPane.getChildren().add(button);
    }   

    private void startAnimation() {
        ScaleTransition st = new ScaleTransition(Duration.seconds(1.5), line);
        st.setToY(1);
        st.setOnFinished(e -> {
            for (int i = 0; i < rightPane.getChildren().size(); i++) {
                Node n = rightPane.getChildren().get(i);

                TranslateTransition tt = new TranslateTransition(Duration.seconds(1.5 + i * 0.15), n);
                tt.setToX(0);
                tt.setOnFinished(e2 -> n.setClip(null));
                tt.play();
            }
        });
        st.play();
    }

    public List<Button> getButtons() {
        return buttons;
    }

    public void buttonHandler(Stage stage,Scene scene) {
        buttons.get(0).setOnAction(e -> {
            stage.setScene(scene);
        });
        buttons.get(4).setOnAction(e -> {
            Platform.exit();
        });

    }

}
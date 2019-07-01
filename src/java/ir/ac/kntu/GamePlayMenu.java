import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;

public class GamePlayMenu {
    private BorderPane root;
    private FlowPane top,bottom;
    private ProgressBar bar1,bar2;

    public GamePlayMenu() {
        root = new BorderPane();
        top = new FlowPane();
        bottom = new FlowPane();
        root.getStyleClass().add("card-picker-image");
        top.setAlignment(Pos.TOP_CENTER);
        top.setVgap(8);
        top.setHgap(4);
        top.setPrefWrapLength(900);

    }

    public Scene creatScene() {
        addLabel("Player 2");
        addCards();
        // addCards();
        addLabel("Player 1");

        Scene scene = new Scene(root,Constants.WIDTH,Constants.HEIGHT);
        return scene;
    }

    private void addLabel(String text) {
        Label label = new Label(text);
        root.getChildren().add(label);
    }

    private void addBar() {
        bar1 = new ProgressBar();
        root.getChildren().add(bar1);
    }

    private void addCards() {
        Label label;
        for(int i = 0; i < 4; i++) {
            label = new Label();
            label.getStyleClass().add("card-select-archer");
            top.getChildren().add(label);
        }
        root.getChildren().add(top);
    }
}
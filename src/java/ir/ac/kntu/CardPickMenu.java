import java.util.ArrayList;

import javafx.beans.binding.BooleanBinding;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class CardPickMenu{
    private ArrayList<ToggleButton> buttons;
    private VBox root;
    private FlowPane middle;
    private Button nextButton;

    public CardPickMenu() {
        buttons = new ArrayList<>();
        root = new VBox(30);
        root.setAlignment(Pos.CENTER);
        root.getStyleClass().add("card-picker-image");
        middle = new FlowPane();
        middle.setAlignment(Pos.CENTER);
        middle.setVgap(8);
        middle.setHgap(4);
        middle.setPrefWrapLength(900); 
    }
    public Scene createScene(String text) {
        addLable(text);
        addButton();
        root.getChildren().add(middle);
        nextButton = new Button("Next");
        nextButton.getStyleClass().add("custom-button");
        root.getChildren().add(nextButton);
        Scene scene = new Scene(root,Constants.WIDTH,Constants.HEIGHT);
        handlePictures();
        return scene;
    }

    private void addLable(String text) {
        Label label = new Label(text);
        root.getChildren().add(label);
    }

    private void addButton() {
        ToggleButton button;
        for(int i = 0; i < 6; i++) {
            button = new ToggleButton();
            button.getStyleClass().add("custom-button");
            buttons.add(button);
            middle.getChildren().add(button);
        }
        buttons.get(0).getStyleClass().add("card-select-archer");
        buttons.get(1).getStyleClass().add("card-select-dragon");
        buttons.get(2).getStyleClass().add("card-select-knight");
        buttons.get(3).getStyleClass().add("card-select-goblin");
        buttons.get(4).getStyleClass().add("card-select-shield");
        buttons.get(5).getStyleClass().add("card-select-swordsman");
    } 

    //binding 
    private void handlePictures() {
        BooleanBinding bb = new BooleanBinding(){
            {
                for(ToggleButton button : buttons) {
                    super.bind(button.selectedProperty());
                }
            }
            @Override
            protected boolean computeValue() {
                int numOfCards = 0;
                for(ToggleButton button : buttons) {
                    if(button.isSelected()) {
                        numOfCards++;
                    }
                }
                return (numOfCards == 4)? false:true;
            }
        };
        nextButton.disableProperty().bind(bb);
    }

    public void buttonHandler(Stage stage, Scene targetScene) {
        nextButton.setOnAction(e -> {
            stage.setScene(targetScene);
        });
    }

}
package ir.ac.kntu;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;

public class TowerPickMenu {

    private int player;
    private Scene scene;
    private Pane pane;
    private Button nextButton;
    private Label label;
    private ArrayList<Tower> towers = new ArrayList<>();

    public TowerPickMenu(int player) {
        this.player = player;
    }


    public Scene createScene() {
        char[][] map = Map.readMap();
        pane = new Pane();
        drawMap(pane, map);
        makeLabel();
        makeButton();
        scene = new Scene(pane,1000,800);
        pane.getChildren().addAll(label,nextButton);
        return scene;
    }

    public void drawMap(Pane pane,char[][] map){
        for (int i = 0; i < 800; i += 40) {
            for (int j = 0; j < 800; j += 40) {
                Rectangle rectangle = new Rectangle(i, j, 40, 40);
                Map.setColor(rectangle, map, i, j);
                rectangle.setStroke(Color.BLACK);
                EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e) {
                        if(towers.size() < 3){
                            BlackTower tower = new BlackTower();
                            tower.setPositionX((int)rectangle.getX()/40);
                            tower.setPositionY((int)rectangle.getY()/40);
                            towers.add(tower);
                        }else if (towers.size() < 6){
                            ElectricTower tower = new ElectricTower();
                            tower.setPositionX((int)rectangle.getX()/40);
                            tower.setPositionY((int)rectangle.getY()/40);
                            towers.add(tower);
                        }else if(towers.size() < 9){
                            HospitalTower tower = new HospitalTower();
                            tower.setPositionX((int)rectangle.getX()/40);
                            tower.setPositionY((int)rectangle.getY()/40);
                            towers.add(tower);
                        }
                    }
                };
                rectangle.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
                pane.getChildren().add(rectangle);
            }
        }
    }

    public void makeButton() {
        nextButton = new Button("next");
        nextButton.setTranslateX(801);
        nextButton.setTranslateY(100);
    }

    public void makeLabel(){
        label = new Label();
        if(player ==1){
            label.setText("player 1 : place your towers");
        }else{
            label.setText("player 2 : place your towers");
        }
        label.setTranslateX(801);
    }

    public void buttonHandler(Stage stage, Scene targetScene) {
        nextButton.setOnAction(e -> {
            if(player == 1){
                Player.getPlayer(1).setTowers(towers);
                System.out.println("towers set" + towers.size() + " size");
                stage.setScene(targetScene);
            }else{
                Player.getPlayer(2).setTowers(towers);
                handleGameScene(stage);
            }
        });
    }

    public void handleGameScene(Stage stage) {
        nextButton.setOnAction(e -> {
            GamePlayMenu game = new GamePlayMenu();
            Scene scene6 = game.createScene();
            scene6.getStylesheets().add("ir/ac/kntu/assets/Viper.css");
            stage.setScene(scene6);
            //starting the game
            Map.startgame();
            handleGameOver(stage);
        });
    }

    public void handleGameOver(Stage stage) {
        for(Player player : Player.getPlayers()) {
            if(player.getHealth() <= 0) {
                stage.setScene(new GameOverMenu().createScene(player));
            }
        }
    }
}

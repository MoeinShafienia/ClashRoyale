package ir.ac.kntu;

import javafx.application.Application;
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
import java.util.List;

public class CreateMap extends Application {

    public void createScene(Stage stage) {
        Pane pane = new Pane();
        Scene scene = new Scene(pane, 1000, 800);
        char[][] map = new char[20][20];
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                map[i][j] = 'k';
            }
        }
        List<Position> chosen = new ArrayList<>();


        getSpawn(map, pane, stage, scene, chosen, Color.RED, "spawn location");
    }

    private void getSpawn(char[][] map, Pane pane, Stage stage, Scene scene,
                          List<Position> chosen, Color color, String messege) {
        readMap(map, pane, chosen, color);
        Label label = new Label(messege);
        label.setTranslateX(801);
        pane.getChildren().add(label);

        Button button = new Button("next");
        button.setTranslateX(801);
        button.setTranslateY(50);
        button.setOnAction((e) -> {
            if (color.equals(Color.RED)) {
                pane.getChildren().remove(label);
                getSpawn(map, pane, stage, scene, chosen, Color.YELLOW, "path " +
                        "locaton");
            } else if (color.equals(Color.YELLOW)) {
                pane.getChildren().remove(label);
                getSpawn(map, pane, stage, scene, chosen, Color.BLUE, "tower " +
                        "location");
            } else if(color.equals((Color.BLUE))){
                pane.getChildren().remove(label);
                getSpawn(map, pane, stage, scene, chosen, Color.GREEN,
                        "buildersHut location");
            }else{
                saveMap(map);
            }
        });
        pane.getChildren().add(button);
        stage.setScene(scene);
        stage.show();
    }

    private void saveMap(char[][] map) {
        //TODO
    }

    private void readMap(char[][] map, Pane pane, List<Position> chosen,
                         Color color) {
        for (int i = 0; i < 800; i += 40) {
            for (int j = 0; j < 800; j += 40) {
                Rectangle rectangle = new Rectangle(i, j, 40, 40);
                Map.setColor(rectangle, map, i, j);
                rectangle.setStroke(Color.BLACK);
                EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e) {
                        if (rectangle.getFill().equals(Color.WHITE)) {
                            rectangle.setFill(color);
                            chosen.add(new Position((int) rectangle.getX() / 40, (int) rectangle.getY() / 40));
                            setColor(map,color,(int) rectangle.getX() / 40,
                                    (int) rectangle.getY() / 40);
                        } else {
                            rectangle.setFill(Color.WHITE);
                            chosen.remove(new Position((int) rectangle.getX() / 40,
                                    (int) rectangle.getY() / 40));
                            map[(int) rectangle.getX() / 40][(int) rectangle.getY() / 40] = 'k';
                        }
                    }
                };
                //Adding event Filter
                rectangle.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
                pane.getChildren().add(rectangle);
            }
        }
    }

    private void setColor(char[][] map,Color color,int x,int y){
        if (color.equals(Color.RED)) {
            map[x][y] = 'r';
        } else if (color.equals(Color.YELLOW)) {
            map[x][y] = 'y';
        } else if(color.equals(Color.BLUE)){
            map[x][y] = 'b';
        }else{
            map[x][y] = 'g';
        }
    }

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        createScene(primaryStage);
    }
}

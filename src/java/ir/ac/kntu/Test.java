package ir.ac.kntu;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


public class Test extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane pane = new Pane();
        Rectangle r = new Rectangle(100,100,100,100);
        Button b = new Button("next");
        b.setOnAction(e ->{
            Rectangle r1 = new Rectangle(200,200,100,100);
            pane.getChildren().add(r1);
        });

        pane.getChildren().addAll(r,b);

        Scene scene = new Scene(pane,800,800);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    //move
    /*public int movementNumber(char[][] map) {
        int movementNumbers = 0;
        if (map[getPositionY() / 40 ][getPositionX() / 40+ 1] == 'y') {
            if (!enemyInfront(40)) {
                if (teamateInfront(40)) {
                    movementNumbers++;
                }
            }
        }
        if (map[getPositionY() / 40 ][getPositionX() / 40- 1] == 'y') {
            if (!enemyInfront(-40)) {
                if (teamateInfront(-40)) {
                    movementNumbers++;
                }
            }
        }
        return movementNumbers;
    }

    public boolean canMoveUp(int NUMBER, char[][] map, int speed) {

        if (map[getPositionY() / 40 + NUMBER / 40][getPositionX() / 40] == 'y') {
            System.out.println("got into if");
            //enemy in front
            for (Player player : Player.getPlayers()) {
                if (!this.getPlayer().equals(player)) {
                    for (Soldier soldier : player.getSoldiers()) {
                        if (soldier.getPositionY() == this.getPositionY() + NUMBER) {
                            return false;
                        }
                    }
                }
            }
            //soldier of the same team in front
            for (Soldier soldier : this.getPlayer().getSoldiers()) {
                if (soldier.getPositionY() == this.getPositionY() + NUMBER) {
                    return speed >= 2;
                }
            }
            return true;
        }
        System.out.println("do not got into if");
        return false;
    }*/
}

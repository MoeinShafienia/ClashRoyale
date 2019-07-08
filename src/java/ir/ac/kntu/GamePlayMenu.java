package ir.ac.kntu;

import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GamePlayMenu {
    private static Scene scene;
    private static BorderPane root;
    private static Pane pane;
    private VBox topChart,bottomChart;
    private HBox topUnits,bottomUnits,topKeys,bottomKeys;
    private static Label mana1,mana2;
    private Button save;

    public GamePlayMenu() {
        root = new BorderPane();
        topChart = new VBox(20);
        bottomChart = new VBox(20);
        topUnits = new HBox(5);
        bottomUnits = new HBox(5);
        topKeys = new HBox(90);
        bottomKeys = new HBox(90);
    }

    public Scene createScene() {
        addStyle();

        addName(Player.getPlayer(1).getName(),bottomChart);
        for(Soldier soldier : Player.getPlayer(1).getSelectedSoldiers()) {
            addLabel(soldier.getImage(), bottomUnits);
        }
        bottomChart.getChildren().add(bottomUnits);
        addKeys(1);
        addManaBar(bottomChart,1);
        save = new Button("Save");
        bottomChart.getChildren().add(save);
        save.getStyleClass().add("custom-button");
        saveHandler();
        root.setLeft(bottomChart);

        pane = Map.drawMap(Map.getMap());
        root.setCenter(pane);

        addName(Player.getPlayer(2).getName(),topChart);
        for(Soldier soldier : Player.getPlayer(2).getSelectedSoldiers()) {
            addLabel(soldier.getImage(), topUnits);
        }
        topChart.getChildren().add(topUnits);
        addKeys(2);
        addManaBar(topChart,2);
        root.setRight(topChart);

        scene = new Scene(root,Constants.WIDTH + 600,Constants.HEIGHT+80);
        handleKeyPressed(scene);
        return scene;
    }

    private void addStyle() {
        root.getStyleClass().add("background-game");
        topChart.setAlignment(Pos.CENTER);
        topUnits.setAlignment(Pos.CENTER);
        topKeys.setAlignment(Pos.CENTER);
        bottomChart.setAlignment(Pos.CENTER);
        bottomUnits.setAlignment(Pos.CENTER);
        bottomKeys.setAlignment(Pos.CENTER);
    }

    private void addLabel(String style,HBox top) {
        Label label = new Label();
        label.getStyleClass().add(style);
        top.getChildren().add(label);
    }

    private void addManaBar(VBox box, int player) {
        Label mana = new Label("Mana");
        mana.getStyleClass().add("label-custom");
        box.getChildren().add(mana);
        if(player == 1) {
            mana1 = mana;
        } else {
            mana2 = mana;
        }
    }

    private void addName(String name, VBox chart){
        Label label = new Label(name);
        label.getStyleClass().add("label-custom");
        chart.getChildren().add(label);
    }

    private void addKeys(int player) {
        if(player == 1) {
            Label label,label2,label3,label4;
            label = new Label("A");
            label2 = new Label("S");
            label3 = new Label("D");
            label4 = new Label("F");
            bottomKeys.getChildren().addAll(label,label2,label3,label4);
            bottomChart.getChildren().add(bottomKeys);
        } else {
            Label label,label2,label3,label4;
            label = new Label("H");
            label2 = new Label("J");
            label3 = new Label("K");
            label4 = new Label("L");
            topKeys.getChildren().addAll(label,label2,label3,label4);
            topChart.getChildren().add(topKeys);
        }
    }

    public static void updateMana() {
        Platform.runLater(() -> {
            mana1.setText(String.valueOf(Player.getPlayer(1).getMana()));
            mana2.setText(String.valueOf(Player.getPlayer(2).getMana()));
        });
    }

    public void handleKeyPressed(Scene scene) {
        scene.addEventHandler(KeyEvent.KEY_PRESSED, key -> {
            if(key.getCode() == KeyCode.A) {
                System.out.println("spawning notice " + Player.getPlayer(1).getSelectedSoldiers().get(0));
                Player.getPlayer(1).spawn(Player.getPlayer(1).getSelectedSoldiers().get(0).newObject());
            } else if (key.getCode() == KeyCode.S) {
                System.out.println("spawning notice " + Player.getPlayer(1).getSelectedSoldiers().get(0));
                Player.getPlayer(1).spawn(Player.getPlayer(1).getSelectedSoldiers().get(1).newObject());
            } else if (key.getCode() == KeyCode.D) {
                System.out.println("spawning notice " + Player.getPlayer(1).getSelectedSoldiers().get(0));
                Player.getPlayer(1).spawn(Player.getPlayer(1).getSelectedSoldiers().get(2).newObject());
            } else if (key.getCode() == KeyCode.F) {
                System.out.println("spawning notice " + Player.getPlayer(1).getSelectedSoldiers().get(0));
                Player.getPlayer(1).spawn(Player.getPlayer(1).getSelectedSoldiers().get(3).newObject());
            } else if (key.getCode() == KeyCode.H) {
                System.out.println("spawning notice " + Player.getPlayer(2).getSelectedSoldiers().get(0));
                Player.getPlayer(2).spawn(Player.getPlayer(2).getSelectedSoldiers().get(0).newObject());
            } else if (key.getCode() == KeyCode.J) {
                System.out.println("spawning notice " + Player.getPlayer(2).getSelectedSoldiers().get(0));
                Player.getPlayer(2).spawn(Player.getPlayer(2).getSelectedSoldiers().get(1).newObject());
            } else if (key.getCode() == KeyCode.K) {
                System.out.println("spawning notice " + Player.getPlayer(2).getSelectedSoldiers().get(0));
                Player.getPlayer(2).spawn(Player.getPlayer(2).getSelectedSoldiers().get(2).newObject());
            } else if (key.getCode() == KeyCode.L) {
                System.out.println("spawning notice " + Player.getPlayer(2).getSelectedSoldiers().get(0));
                Player.getPlayer(2).spawn(Player.getPlayer(2).getSelectedSoldiers().get(3).newObject());
            }
        });
    }

    public static Scene getScene() {
        return scene;
    }

    public static void setScene(Scene scene1) {
        scene = scene1;
    }

    public static void spawn(Unit unit) {
        System.out.println("spawning graphic");
        ImageView image = new ImageView(new Image(unit.getUnitImageURL()));
        image.setTranslateX(unit.getPositionX() * 40);
        image.setTranslateY(unit.getPositionY() * 40);
        Platform.runLater(() ->{
            pane.getChildren().add(image);
        });
    }

    public static void update() {
        System.out.println("updating graphic");
        Platform.runLater(() -> {
            pane.getChildren().clear();
            pane = Map.drawMap(Map.getMap());
            root.setCenter(pane);
        });
        for(Soldier soldier : Player.getPlayer(1).getSoldiers()) {
            spawn(soldier);
        }
        for(Tower tower : Player.getPlayer(1).getTowers()) {
            spawn(tower);
            System.out.println("Tower is spawned");
        }
        for(Soldier soldier : Player.getPlayer(2).getSoldiers()) {
            spawn(soldier);
        }
        for(Tower tower : Player.getPlayer(2).getTowers()) {
            spawn(tower);
        }
    }

    public static void remove(Unit unit) {
        System.out.println("removing graphic");
        Rectangle rectangle = new Rectangle(unit.getPositionX()*40,
                unit.getPositionY()*40, 40, 40);
        rectangle.setFill(Color.YELLOW);
        rectangle.setStroke(Color.BLACK);
        Platform.runLater(() -> {
            pane.getChildren().add(rectangle);
        });
    } 

    private void saveHandler() {
        save.setOnAction(e -> {
            //handle save
        });
    }
}
package ir.ac.kntu;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    static Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        Player player1 = new Player();
        player1.setHealth(3);
        player1.setMana(100);
        player1.setPlayerId(1);
        Player.getPlayers().add(player1);
        Player player2 = new Player();
        player2.setHealth(3);
        player2.setMana(100);
        player1.setPlayerId(2);
        Player.getPlayers().add(player2);


        MainMenu mainMenu = new MainMenu();
        PlayerNameMenu pMenu = new PlayerNameMenu();
        PlayerNameMenu qMenu = new PlayerNameMenu();
        CardPickMenu card1 = new CardPickMenu();
        CardPickMenu card2 = new CardPickMenu();
        TowerPickMenu towerPickMenu1 = new TowerPickMenu(1);
        TowerPickMenu towerPickMenu2 = new TowerPickMenu(2);

        //create scenes
        Scene scene1 = mainMenu.createScene();
        Scene scene2 = pMenu.createScene("Player 1 Name:");
        Scene scene3 = qMenu.createScene("Player 2 Name:");
        Scene scene4 = card1.createScene("Player 1 Choose 4 Cards:");
        Scene scene5 = card2.createScene("Player 2 Choose 4 Cards:");
        Scene towerScene1 = towerPickMenu1.createScene();
        Scene towerScene2 = towerPickMenu2.createScene();

        //primary scene
        primaryStage.setScene(scene1);
        //handle buttons
        mainMenu.buttonHandler(primaryStage, scene2);
        pMenu.buttonHandler(primaryStage,scene3,1);
        qMenu.buttonHandler(primaryStage, scene4,2);
        card1.buttonHandler(primaryStage, scene5,1);
        card2.buttonHandler(primaryStage,towerScene1,2);
        towerPickMenu1.buttonHandler(primaryStage,towerScene2);
        // card2.handleGameScene(primaryStage);
        towerPickMenu2.buttonHandler(primaryStage,towerScene2);


        //add styles
        scene1.getStylesheets().add("ir/ac/kntu/assets/Viper.css");
        scene2.getStylesheets().add("ir/ac/kntu/assets/Viper.css");
        scene3.getStylesheets().add("ir/ac/kntu/assets/Viper.css");
        scene4.getStylesheets().add("ir/ac/kntu/assets/Viper.css");
        scene5.getStylesheets().add("ir/ac/kntu/assets/Viper.css");

       

        primaryStage.show();
    }

    public static Stage getStage(){
        return stage;
    }


    public static void main(String[] args) {
        launch(args);
    }
}
package ir.ac.kntu;

import javafx.application.Platform;
import javafx.stage.Stage;

public class GameCycle implements Runnable {
    @Override
    public void run(){

        System.out.println("spawning towers");
        for(Player player : Player.getPlayers()){
            for(Tower t : player.getTowers()){
                System.out.println("hey im spawning");
                player.spawn(t);
            }
        }

        while (!Player.isGameOver()) {
            System.out.println("im in");
            Tower.handleAttack();
            //Soldier.handle(Soldier::attack);
            Soldier.handleMove();
            for(Soldier soldier : Soldier.getRemovableList()){
                Player.removeUnit(soldier);
            }
            Soldier.getRemovableList().clear();
            Soldier.handleAttack();

            Player.handleMana();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        Platform.runLater(() ->{
            Main.getStage().setScene(new GameOverMenu().createScene(Player.getPlayer(1)));
        });

    }
}

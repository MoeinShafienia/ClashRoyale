package ir.ac.kntu;

import javafx.application.Platform;

public class GameCycle implements Runnable {
    @Override
    public void run() {

        while (!Player.isGameOver()) {
            //Tower.handleAttack();
            //Soldier.handle(Soldier::attack);
            Soldier.handle(soldier -> soldier.move(soldier.getSpeed()));
            //Player.handleMana();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}

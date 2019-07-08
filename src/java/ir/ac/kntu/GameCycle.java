package ir.ac.kntu;

import javafx.application.Platform;

public class GameCycle implements Runnable {
    @Override
    public void run() {

        System.out.println("spawning towers");
        for(Player player : Player.getPlayers()){
            for(Tower t : player.getTowers()){
                System.out.println("hey im spawning");
                player.spawn(t);
            }
        }

        while (true) {
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

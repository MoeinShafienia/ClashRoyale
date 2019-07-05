package ir.ac.kntu;

public class GameCycle implements Runnable {
    @Override
    public void run() {
        while (!Player.isGameOver()) {
            Tower.handleAttack();
            Soldier.handle(Soldier::attack);
            Soldier.handle(soldier -> soldier.move(soldier.getSpeed(),"nothing"));
            Player.handleMana();
        }
    }
}

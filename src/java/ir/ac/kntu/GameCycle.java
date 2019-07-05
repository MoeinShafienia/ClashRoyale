package ir.ac.kntu;

public class GameCycle implements Runnable {
    @Override
    public void run() {
        try {
            while (!Player.isGameOver()) {
                System.out.println("1");
                Tower.handleAttack();
                System.out.println("2");
                Soldier.handle(Soldier::attack);
                System.out.println("3");
                Soldier.handle(soldier -> soldier.move(soldier.getSpeed(),"nothing"));
                System.out.println("4");
                Player.handleMana();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

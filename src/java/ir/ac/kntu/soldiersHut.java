package ir.ac.kntu;

public class soldiersHut extends Unit implements Runnable {

    private int cycle;
    private Player player;
    private Player targetPlayer;

    public soldiersHut(Player player, Player targetPlayer) {
        this.cycle = 0;
        this.player = player;
        this.targetPlayer = targetPlayer;
    }

    @Override
    public void attack() {

    }

    private void spawnUnit(){
        int randomNumber = RandomHelper.nextInt(4);
        switch (randomNumber){
            case 0:
                player.spawn(targetPlayer.getSelectedSoldiers().get(0).newObject());
                break;
            case 1:
                player.spawn(targetPlayer.getSelectedSoldiers().get(1).newObject());
                break;
            case 2:
                player.spawn(targetPlayer.getSelectedSoldiers().get(2).newObject());
                break;
            default:
                player.spawn(targetPlayer.getSelectedSoldiers().get(3).newObject());
                break;
        }
    }

    @Override
    public void run() {
        while (true){
            if(cycle ==5){
                spawnUnit();
                cycle =0;
            }
            if(this.getHealth() <= 0){
                //stop the thread
                //player.setBuildersHut(false);
            }
        }
    }

    public void addCycle(){
        cycle++;
    }
}

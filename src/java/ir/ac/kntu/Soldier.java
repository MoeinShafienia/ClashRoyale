package ir.ac.kntu;


public abstract class Soldier extends Unit {

    private int speed;
    private char[][] map = Map.readMap();
    private String lastMove = "nothing";

    public static void handle(Handlerer handlerer) {
        for (int i = 1; i <= 2; i++) {
            Player player = Player.getPlayer(i);
            player.getSoldiers().stream()
                    .filter(Soldier::isSoldierAtHisBase)
                    .forEach(handlerer::apply);
        }
        for (int i = 1; i <= 2; i++) {
            Player player = Player.getPlayer(i);
            player.getSoldiers().stream()
                    .filter(Soldier::isSoldierAtEnemyBase)
                    .forEach(handlerer::apply);
        }
    }

    public boolean isSoldierAtEnemyBase() {
        if (this.getPlayer().getPlayerId() == 1 && this.getPositionX() <= 11*40) {
            return true;
        }
        if (this.getPlayer().getPlayerId() == 2 && this.getPositionX() > 11*40) {
            return true;
        }
        return false;
    }

    public boolean isSoldierAtHisBase() {
        if (this.getPlayer().getPlayerId() == 1 && this.getPositionX() > 11 * 40) {
            return true;
        }
        if (this.getPlayer().getPlayerId() == 2 && this.getPositionX() <= 10 * 40) {
            return true;
        }
        return false;

    }


    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }


    public abstract Soldier newObject();


    public String getLastMove(){
        return lastMove;
    }

    public void move(int speed){
        System.out.println(this.getPlayer().getSoldiers().size());
        if(speed <= 0){
            System.out.println(this.getPositionX() +" " +getPositionY()+  "my" +
                    " speed is zero");
            return;
        }
        if(canMoveUp()){
            System.out.println("up");
            setPositionY(getPositionY() - 40);
            move(speed-1);
        }else if(canMoveRight()){
            System.out.println("right");
            setPositionX(getPositionX() + 40);
            move(speed-1);
        }else if(canMoveLeft()){
            System.out.println("left");
            setPositionX(getPositionX() - 40);
            move(speed-1);
        }else {
            System.out.println("hey im trapped");
        }
    }

    public boolean canMoveUp(){
        if(map[getPositionY()/40 - 1][getPositionX()/40] == 'y'){
            return true;
        }
        return false;
    }

    public boolean canMoveRight(){
        if(map[getPositionY()/40][getPositionX()/40 + 1] == 'y'){
            return true;
        }
        return false;
    }

    public boolean canMoveLeft(){
        if(map[getPositionY()/40][getPositionX()/40 - 1] == 'y'){
            return true;
        }
        return false;
    }
}

package ir.ac.kntu;


public abstract class Soldier extends Unit {

    private int speed;
    private char[][] map = Map.readMap();
    private String lastMove = "nothing";

    public static void handle(Handlerer handlerer) {
        for (int i = 1; i <= 2; i++) {
            Player player = Player.getPlayer(i);
            player.getSoldiers().stream()
                    //.filter(Soldier::isSoldierAtHisBase)
                    .forEach(handlerer::apply);
        }
        /*for (int i = 1; i <= 2; i++) {
            Player player = Player.getPlayer(i);
            player.getSoldiers().stream()
                    //.filter(Soldier::isSoldierAtEnemyBase)
                    .forEach(handlerer::apply);
        }*/
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

        int number;
        if(this.getPlayer().equals(Player.getPlayer(1))){
            number = 1;
        }else{
            number = -1;
        }
        System.out.println("that fucking number is " + number);

        System.out.println(this.getPlayer().getSoldiers().size());
        if(speed <= 0){
            System.out.println(this.getPositionX() +" " +getPositionY()+  "my" +
                    " speed is zero");
            return;
        }
        if(canMoveRight(number)){
            System.out.println("right");
            setPositionX(getPositionX() + number);
            lastMove = "right";
            move(speed-1);
            return;
        }
        if(canMoveUp()){
            if (!lastMove.equals("down")) {
                System.out.println("up");
                setPositionY(getPositionY() - 1);
                lastMove = "up";
                move(speed-1);
                return;
            }
        }
        if(canMoveDown()){
            if (!lastMove.equals("up")) {
                System.out.println("left");
                setPositionY(getPositionY() + 1);
                lastMove = "down";
                move(speed-1);
                return;
            }
        }
    }

    public boolean canMoveUp(){
        if(map[getPositionX()][getPositionY() - 1] == 'y'){
            return true;
        }
        return false;
    }

    public boolean canMoveRight(int number){
        if(map[getPositionX() + number][getPositionY()] == 'y'){
            return true;
        }
        return false;
    }

    public boolean canMoveDown(){
        if(map[getPositionX()][getPositionY() + 1] == 'y'){
            return true;
        }
        return false;
    }
}

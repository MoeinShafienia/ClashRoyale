package ir.ac.kntu;


public abstract class Soldier extends Unit{

    private int speed;

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
        if (this.getPlayer().getPlayerId() == 1 && this.getPositionX() <= 10) {
            return true;
        }
        if (this.getPlayer().getPlayerId() == 2 && this.getPositionX() > 10) {
            return true;
        }
        return false;
    }

    public boolean isSoldierAtHisBase() {
        if (this.getPlayer().getPlayerId() == 1 && this.getPositionX() > 10) {
            return true;
        }
        if (this.getPlayer().getPlayerId() == 2 && this.getPositionX() <= 10) {
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

    public void move(int speed, String lastMove) {
        final int NUMBER;
        char[][] map = Map.getMap();

        if (getPlayer().getPlayerId() == 1) {
            NUMBER = 1;
        } else {
            NUMBER = -1;
        }

        if (speed == 0) {
            return;
        }
        if (canMoveUp(NUMBER, map)) {
            setPositionY(getPositionY() + NUMBER);
            lastMove = "nothing";
            move(speed - 1, lastMove);
        } else {
            lastMove = moveHorizontal(lastMove, map);
            move(speed - 1, lastMove);
        }
    }

    public String moveHorizontal(String lastMove, char[][] map) {
        if (lastMove.equals("right")) {
            if(map[getPositionX()][getPositionY() + 1] == 'y') {
                if(enemyInfront(1)){
                    return "right";
                }else if(teamateInfront(1)){
                    setPositionX(getPositionX() +1);
                    return "right";
                }
            }
            return "right";
        } else if (lastMove.equals("left")) {
            if(map[getPositionX()][getPositionY() - 1] == 'y') {
                if(enemyInfront(-1)){
                    return "left";
                }else if(teamateInfront(-1)){
                    setPositionX(getPositionX() -1);
                    return "left";
                }
            }
            return "left";
        } else {
            int movementNumbers = movementNumber(map);
            if (movementNumbers > 1) {
                int randomNumber = RandomHelper.nextInt(2);
                if (randomNumber == 1) {
                    setPositionX(getPositionX() + 1);
                    return "right";
                } else {
                    setPositionX(getPositionX() - 1);
                    return "left";
                }
            } else {
                if (map[getPositionX() + 1][getPositionY()] == 'y') {
                    if(!enemyInfront(1)){
                        if(teamateInfront(1)){
                            setPositionX(getPositionX() + 1);
                            return "right";
                        }
                    }
                }else{
                    setPositionX(getPositionX() - 1);
                    return "left";
                }
            }
        }
        return lastMove;
    }

    public int movementNumber(char[][] map) {
        int movementNumbers = 0;
        if (map[getPositionX() + 1][getPositionY()] == 'y') {
            if(!enemyInfront(1)){
                if(teamateInfront(1)){
                    movementNumbers++;
                }
            }
        }
        if (map[getPositionX() - 1][getPositionY()] == 'y') {
            if(!enemyInfront(-1)){
                if(teamateInfront(-1)){
                    movementNumbers++;
                }
            }
        }
        return movementNumbers;
    }

    public boolean canMoveUp(int NUMBER, char[][] map) {

        if(map[getPositionY() + NUMBER][getPositionY()] == 'y') {
            //enemy in front
            for (Player player : Player.getPlayers()) {
                if (!this.getPlayer().equals(player)) {
                    for (Soldier soldier : player.getSoldiers()) {
                        if (soldier.getPositionY() == this.getPositionY() + NUMBER) {
                            return false;
                        }
                    }
                }
            }
            //soldier of the same team in front
            for (Soldier soldier : this.getPlayer().getSoldiers()) {
                if (soldier.getPositionY() == this.getPositionY() + NUMBER) {
                    if (this.getSpeed() - soldier.getSpeed() > 1) {
                        return true;
                    }
                }
            }
            return true;
        }
        return false;
    }

    public boolean enemyInfront(int number){
        //enemy in front
        //true means he cant move
        for(Player player : Player.getPlayers()){
            if(!this.getPlayer().equals(player)){
                for(Soldier soldier : player.getSoldiers()){
                    if(soldier.getPositionX() == this.getPositionX() + number){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean teamateInfront(int number){
        // true means he can move
        for(Soldier soldier : this.getPlayer().getSoldiers()){
            if(soldier.getPositionX() == this.getPositionX() + number){
                if(this.getSpeed() - soldier.getSpeed() <= 1){
                    return false;
                }
            }
        }
        return true;
    }

    public abstract Soldier newObject();


}

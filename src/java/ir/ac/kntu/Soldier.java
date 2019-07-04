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
            setPositionX(getPositionX() + NUMBER);
            lastMove = "nothing";
            move(speed - 1, lastMove);
        } else {
            lastMove = moveHorizontal(lastMove, map);
            move(speed - 1, lastMove);
        }
    }

    public String moveHorizontal(String lastMove, char[][] map) {
        if (lastMove.equals("right")) {
            if (map[getPositionX()][getPositionY() + 1] == 'y') {
                setPositionY(getPositionY() + 1);
                return "right";
            }
        } else if (lastMove.equals("left")) {
            if (map[getPositionX()][getPositionY() - 1] =='y'){
                setPositionY(getPositionY() - 1);
                return "left";
            }
        } else {
            int movementNumbers = movementNumber(map);
            if (movementNumbers > 1) {
                int randomNumber = RandomHelper.nextInt(2);
                if (randomNumber == 1) {
                    setPositionY(getPositionY() + 1);
                    return "right";
                } else {
                    setPositionY(getPositionY() - 1);
                    return "left";
                }
            } else {
                if (map[getPositionX()][getPositionY() + 1] == 'y') {
                    setPositionY(getPositionY() + 1);
                    return "right";
                }
                if (map[getPositionX()][getPositionY() - 1] == 'y') {
                    setPositionY(getPositionY() - 1);
                    return "left";
                }
            }
        }
        return "nothing";
    }

    public int movementNumber(char[][] map) {
        int movementNumbers = 0;
        if (map[getPositionX()][getPositionY() + 1] == 'y') {
            movementNumbers++;
        }
        if (map[getPositionX()][getPositionY() - 1] == 'y') {
            movementNumbers++;
        }
        return movementNumbers;
    }

    public boolean canMoveUp(int NUMBER, char[][] map) {
        return map[getPositionX() + NUMBER][getPositionY()] == 'y';
    }

    public Soldier newObject(){
    }


}

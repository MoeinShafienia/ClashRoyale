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

    public void move(int speed) {
        final int NUMBER;

        if (getPlayer().getPlayerId() == 1) {
            NUMBER = +40;
        } else {
            NUMBER = -40;
        }
        System.out.println("number is " + NUMBER);

        if (speed == 0) {
            return;
        }
        if (canMoveUp(NUMBER, map, speed)) {
            setPositionY(getPositionY() + NUMBER);
            System.out.println("move up" + NUMBER);
            this.lastMove = "nothing";
            move(speed - 1);
        } else {
            this.lastMove = moveHorizontal(lastMove, map);
            move(speed - 1);
        }
    }

    public String moveHorizontal(String lastMove, char[][] map) {
        System.out.println("last move is " + lastMove);
        if (lastMove.equals("right")) {
            if (map[getPositionY() / 40][getPositionX() / 40 + 1] == 'y') {
                if (enemyInfront(40)) {
                    return "right";
                } else if (teamateInfront(40)) {
                    setPositionX(getPositionX() + 40);
                    System.out.println("moving right");
                    return "right";
                }
            }
            return "right";
        } else if (lastMove.equals("left")) {
            if (map[getPositionY() / 40][getPositionX() / 40 - 1] == 'y') {
                if (enemyInfront(-40)) {
                    return "left";
                } else if (teamateInfront(-40)) {
                    setPositionX(getPositionX() - 40);
                    System.out.println("moving left");
                    return "left";
                }
            }
            return "left";
        } else {
            int movementNumbers = movementNumber(map);
            if (movementNumbers > 1) {
                int randomNumber = RandomHelper.nextInt(2);
                if (randomNumber == 1) {
                    setPositionX(getPositionX() + 40);
                    System.out.println("not moving up");
                    return "right";
                } else {
                    setPositionX(getPositionX() - 40);
                    System.out.println("not moving up");
                    return "left";
                }
            } else {
                System.out.println("got here moein");
                if (map[getPositionY() / 40][getPositionX() / 40 + 1] == 'y') {
                    if (!enemyInfront(40)) {
                        if (teamateInfront(40)) {
                            setPositionX(getPositionX() + 40);
                            System.out.println("moving right");
                            return "right";
                        }
                    }
                } else {
                    if (!enemyInfront(-40)) {
                        if (teamateInfront(-40)) {
                            setPositionX(getPositionX() - 40);
                            System.out.println("moving left");
                            return "left";
                        }
                    }
                }
            }
        }
        System.out.println("bug");
        return lastMove;
    }

    public int movementNumber(char[][] map) {
        int movementNumbers = 0;
        if (map[getPositionY() / 40 ][getPositionX() / 40+ 1] == 'y') {
            if (!enemyInfront(40)) {
                if (teamateInfront(40)) {
                    movementNumbers++;
                }
            }
        }
        if (map[getPositionY() / 40 ][getPositionX() / 40- 1] == 'y') {
            if (!enemyInfront(-40)) {
                if (teamateInfront(-40)) {
                    movementNumbers++;
                }
            }
        }
        return movementNumbers;
    }

    public boolean canMoveUp(int NUMBER, char[][] map, int speed) {

        if (map[getPositionY() / 40 + NUMBER / 40][getPositionX() / 40] == 'y') {
            System.out.println("got into if");
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
                    return speed >= 2;
                }
            }
            return true;
        }
        System.out.println("do not got into if");
        return false;
    }

    public boolean enemyInfront(int number) {
        //enemy in front
        //true means he cant move
        for (Player player : Player.getPlayers()) {
            if (!this.getPlayer().equals(player)) {
                for (Soldier soldier : player.getSoldiers()) {
                    if (soldier.getPositionY() == this.getPositionY() + number) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean teamateInfront(int number) {
        // true means he can move
        for (Soldier soldier : this.getPlayer().getSoldiers()) {
            if (soldier.getPositionY() == this.getPositionY() + number) {
                if (this.getSpeed() - soldier.getSpeed() <= 1) {
                    return false;
                }
            }
        }
        return true;
    }

    public abstract Soldier newObject();


    public String getLastMove(){
        return lastMove;
    }
}

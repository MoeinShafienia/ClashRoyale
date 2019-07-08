package ir.ac.kntu;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Tower extends Unit {

    private int health;
    private int damage;
    private int mana;
    private int range;
    private int positionX;
    private int positionY;
    private Player player;

    public static void handleAttack() {
        for(Player player : Player.getPlayers()){
            player.getTowers().forEach(Tower::attack);
        }
    }

    public static boolean isThereTowerInThisPosition(int x, int y,
                                                     Player player) {
        for (Player player1 : Player.getPlayers()) {
            if (!player1.equals(player)) {
                for (Tower unit : player1.getTowers()) {
                    if (unit.getPositionX() == x && unit.getPositionY() == y) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static Unit getTower(int i, int j, Player player) {
        for (Player player1 : Player.getPlayers()) {
            if (!player1.equals(player)) {
                for (Tower unit : player1.getTowers()) {
                    if (unit.getPositionX() == i && unit.getPositionY() == j) {
                        return unit;
                    }
                }
            }
        }
        return null;
    }


    public int getHealth() {
        return health;
    }
    public void setHealth(int health) {
        this.health = health;
    }
    public int getDamage() {
        return damage;
    }
    public void setDamage(int damage) {
        this.damage = damage;
    }
    public int getRequiredMana() {
        return mana;
    }
    public void setRequiredMana(int requiredMana) {
        this.mana = requiredMana;
    }
    public int getRange() {
        return range;
    }
    public void setRange(int range) {
        this.range = range;
    }
    public int getPositionX() {
        return positionX;
    }
    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }
    public int getPositionY() {
        return positionY;
    }
    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }
    public Player getPlayer() {
        return player;
    }
    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public void attack(){
        List<Unit> targets = new ArrayList<>();
        for(int i = positionX - range ; i < positionX + range ; i++){
            for(int j = positionY - range ; j < positionY + range ; j++){
                if(Unit.isThereUnitInThisPosition(i,j,player)){
                    targets.add(Soldier.getUnit(i,j,this.player));
                }
            }
        }
        if(targets.size() != 0) {
            int randomNumber = RandomHelper.nextInt(targets.size());
            targets.get(randomNumber).reduceHealth(this.damage);
        }
    }

}

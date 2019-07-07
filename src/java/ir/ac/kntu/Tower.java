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
        for(int i = positionX - 40*range ; i < positionX + 40*range ; i+=40){
            for(int j = positionY - 40*range ; j < positionY +40* range ; j+=40){
                if(Unit.isThereUnitInThisPosition(i,j,player)){
                    targets.add(Soldier.getUnit(i,j,this.player));
                }
            }
        }
        targets = targets.stream().filter(unit -> unit instanceof Soldier).collect(Collectors.toList());
        if(targets.size() != 0) {
            int randomNumber = RandomHelper.nextInt(targets.size());
            targets.get(randomNumber).reduceHealth(this.damage);
        }
    }

}

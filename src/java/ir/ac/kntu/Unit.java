package ir.ac.kntu;

import java.util.ArrayList;
import java.util.List;

public abstract class Unit {
    private int requiredMana;
    private int health;
    private int damage;
    private int range;
    private int positionX;
    private int positionY;
    private String image;
    private String unitImageURL;
    private Player player;

    public String getUnitImageURL() {
        return unitImageURL;
    }
    public void setUnitImageURL(String url) {
        unitImageURL = url;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public int getRequiredMana() {
        return requiredMana;
    }
    public void setRequiredMana(int requiredMana) {
        this.requiredMana = requiredMana;
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
        int previosX = this.getPositionX();
        this.positionX = positionX;
        GamePlayMenu.update(previosX,getPositionY(),this);
    }
    public int getPositionY() {
        return positionY;
    }
    public void setPositionY(int positionY) {
        int previosY = this.getPositionY();
        this.positionY = positionY;
        GamePlayMenu.update(getPositionX(),previosY,this);
    }
    public Player getPlayer() {
        return player;
    }
    public void setPlayer(Player player) {
        this.player = player;
    }

    public abstract void attack();

    public static Unit getUnit(int x, int y,Player player) {
        for(Unit unit : player.getUnits()){
            if(unit.getPositionX() == x && unit.getPositionY() == y){
                return unit;
            }
        }
        return null;
    }


    public static boolean isThereUnitInThisPosition(int x,int y,Player player){
        for(Unit unit : player.getUnits()){
            if(unit.getPositionX() == x && unit.getPositionY() == y){
                return true;
            }
        }
        return false;
    }

    public void reduceHealth(int damage){
        setHealth(getHealth()-damage);
        if(getHealth() <= 0){
            Player.removeUnit(this);
            GamePlayMenu.remove(this);
        }
    }

}

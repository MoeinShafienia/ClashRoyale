package ir.ac.kntu;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class Player implements Serializable {

    private String name;
    private int health;
    private int mana;
    private int playerId;
    private ArrayList<Soldier> selectedSoldiers;

    private List<Soldier> soldiers;
    private List<Tower> towers;

    public void setTowers(List<Tower> towers) {
        this.towers = towers;
    }

    private List<Unit> units;
    private static List<Player> players = new ArrayList<>();

    public Player() {
        soldiers = new ArrayList<>();
        towers = new ArrayList<>();
        selectedSoldiers = new ArrayList<>();
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public ArrayList<Soldier> getSelectedSoldiers() {
        return selectedSoldiers;
    }

    public void setSelectedSoldiers(ArrayList<Soldier> selecteSoldiers) {
        this.selectedSoldiers = selecteSoldiers;
    }

    public int getPlayerId() {
        return playerId;
    }
    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public static List<Player> getPlayers() {
        return players;
    }

    public static boolean isGameOver() {
        for(Player player : players){
            if(player.getHealth() <= 0){
                return true;
            }
        }
        return false;
    }

    public static Player getPlayer(int i) {
        return players.get(i-1);
    }


    public int getHealth() {
        return health;
    }
    public void setHealth(int health) {
        this.health = health;
    }
    public int getMana() {
        return mana;
    }
    public void setMana(int mana) {
        this.mana = mana;
    }

    public static void removeUnit(Unit unit) {
        unit.getPlayer().getUnits().remove(unit);
    }

    public static void increaseMana() {
        for(Player player : players){
            player.setMana(player.getMana() + 2);
        }
    }


    public List<Soldier> getSoldiers() {
        return soldiers;
    }
    public List<Tower> getTowers(){
        return towers;
    }
    public List<Unit> getUnits(){
        return units;
    }

    public void spawn(Soldier soldier){
        soldiers.add(soldier);
        //graphic notify
    }

}

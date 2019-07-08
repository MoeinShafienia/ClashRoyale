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
    private List<Unit> units;
    private static List<Player> players = new ArrayList<>();

    public Player() {
        soldiers = new ArrayList<>();
        towers = new ArrayList<>();
        selectedSoldiers = new ArrayList<>();
        units = new ArrayList<>();
    }

    public static void handleMana() {
        players.get(0).setMana(players.get(0).getMana() + 2);
        players.get(1).setMana(players.get(1).getMana() + 2);
        GamePlayMenu.updateMana();
    }

    public void setTowers(List<Tower> towers) {
        this.towers = towers;
        units.addAll(towers);
    }

    public List<Soldier> getSoldiers() {
        return soldiers;
    }

    public List<Tower> getTowers() {
        return towers;
    }

    public List<Unit> getUnits() {
        return units;
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
        for (Player player : players) {
            if (player.getHealth() <= 0) {
                return true;
            }
        }
        return false;
    }

    public static Player getPlayer(int i) {
        return players.get(i - 1);
    }

    public static void removeUnit(Unit unit) {
        unit.getPlayer().getUnits().remove(unit);
    }

    public static void increaseMana() {
        for (Player player : players) {
            player.setMana(player.getMana() + 2);
        }
    }


    public void spawn(Unit unit) {
        if (unit instanceof Soldier) {
            Soldier soldier = (Soldier) unit;
            soldier.setPlayer(this);
            soldiers.add(soldier);
            units.add(soldier);
            chooseSpawnLocation(soldier, this);
            GamePlayMenu.spawn(soldier);
        } else if(unit instanceof Tower) {
            GamePlayMenu.spawn(unit);
        }
    }

    private void chooseSpawnLocation(Soldier soldier, Player player) {
        List<Position> spawnLocation = new ArrayList<>();
        char[][] map = Map.readMap();
        final int X;
        if (Player.getPlayer(1).equals(player)) {
            X = 1;
        } else {
            X = 20;
        }

        for (int j = 1; j <= 20; j++) {
            if (map[X][j] == 'r') {
                System.out.println("spawn location found" + X + " " + j);
                //if(!Unit.isThereUnitInThisPosition(i*40,J*40,this)) {
                    spawnLocation.add(new Position(X, j));
                //}
            }
        }

        if(spawnLocation.size() != 0) {
            int randomNumber = RandomHelper.nextInt(spawnLocation.size());
            soldier.setPositionX(spawnLocation.get(randomNumber).getX());
            soldier.setPositionY(spawnLocation.get(randomNumber).getY());
            System.out.println("soldiers x :" + soldier.getPositionX() +
                    " " + soldier.getPositionY());
        }

    }

}

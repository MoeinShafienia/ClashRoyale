package ir.ac.kntu;

public class ElectricTower extends Tower {

    public ElectricTower() {
        setHealth(1500);
        setDamage(250);
        setRequiredMana(45);
        setRange(3);
        setUnitImageURL("ir/ac/kntu/assets/electric_tower_icon.png");
    }
}

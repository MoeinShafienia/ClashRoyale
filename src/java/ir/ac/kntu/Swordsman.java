package ir.ac.kntu;

public class Swordsman extends Melee {

    public Swordsman() {
        setRequiredMana(20);
        setHealth(500);
        setDamage(350);
        setSpeed(1);
        setRange(1);
        setImage("card-select-swordsman");
    }

    @Override
    public Swordsman newObject(){
        return new Swordsman();
    }
}

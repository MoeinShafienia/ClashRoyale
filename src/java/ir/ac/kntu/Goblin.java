package ir.ac.kntu;

public class Goblin extends Melee {

    public Goblin() {
        setRequiredMana(10);
        setHealth(200);
        setDamage(250);
        setSpeed(3);
        setRange(1);
        setImage("card-select-goblin");
    }

    @Override
    public Goblin newObject(){
        return new Goblin();
    }
}

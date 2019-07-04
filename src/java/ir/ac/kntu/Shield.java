package ir.ac.kntu;

public class Shield extends Melee {

    public Shield() {
        setRequiredMana(10);
        setHealth(1000);
        setDamage(150);
        setSpeed(1);
        setRange(1);
    }

    @Override
    public Shield newObject(){
        return new Shield();
    }
}

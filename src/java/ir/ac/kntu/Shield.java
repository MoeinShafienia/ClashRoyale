package ir.ac.kntu;

public class Shield extends Melee {

    public Shield() {
        setRequiredMana(10);
        setHealth(1000);
        setDamage(150);
        setSpeed(1);
        setRange(1);
        setImage("card-select-shield");
        setUnitImageURL("ir/ac/kntu/assets/shield_icon.png");
    }

    @Override
    public Shield newObject(){
        return new Shield();
    }
}

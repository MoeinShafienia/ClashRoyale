package ir.ac.kntu;
public class Archer extends Ranged {
    public Archer() {
        setRequiredMana(15);
        setHealth(300);
        setDamage(200);
        setSpeed(1);
        setRange(2);
        setImage("card-select-archer");
        setUnitImageURL("ir/ac/kntu/assets/archer_icon.png");
    }

    @Override
    public Archer newObject(){
        return new Archer();
    }
}
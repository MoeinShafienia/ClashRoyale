package ir.ac.kntu;
public class Archer extends Ranged {
    public Archer() {
        setRequiredMana(15);
        setHealth(300);
        setDamage(200);
        setSpeed(1);
        setRange(2);
    }

    @Override
    public Archer newObject(){
        return new Archer();
    }
}
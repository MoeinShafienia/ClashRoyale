package ir.ac.kntu;
public class Healer extends Ranged {
    public Healer() {
        setRequiredMana(30);
        setHealth(300);
        setDamage(-200);
        setSpeed(1);
        setRange(3);
    }

    @Override
    public Healer newObject(){
        return new Healer();
    }
}
package ir.ac.kntu;
public class Dragon extends Ranged {
    public Dragon() {
        setRequiredMana(35);
        setHealth(500);
        setDamage(350);
        setSpeed(2);
        setRange(3);
        setImage("card-select-dragon");
    }

    @Override
    public Dragon newObject(){
        return new Dragon();
    }
}
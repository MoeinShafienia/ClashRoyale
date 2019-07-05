package ir.ac.kntu;
public class Dragon extends Ranged {
    public Dragon() {
        setRequiredMana(35);
        setHealth(500);
        setDamage(350);
        setSpeed(2);
        setRange(3);
        setImage("card-select-dragon");
        setUnitImageURL("ir/ac/kntu/assets/dragon_icon.png");
    }

    @Override
    public Dragon newObject(){
        return new Dragon();
    }
}
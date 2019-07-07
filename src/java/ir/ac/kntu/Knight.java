package ir.ac.kntu;

public class Knight extends Melee {

    public Knight() {
        setRequiredMana(30);
        setHealth(600);
        setDamage(400);
        setSpeed(2);
        setRange(1);
        setImage("card-select-knight");
        setUnitImageURL("ir/ac/kntu/assets/knight_icon.jpg");
    }

    @Override
    public Knight newObject(){
        return new Knight();
    }
}

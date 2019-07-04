package ir.ac.kntu;

import java.util.ArrayList;
import java.util.List;

public abstract class Melee extends Soldier {
    @Override
    public void attack() {
        List<Unit> targets = new ArrayList<>();

        if(!Unit.isThereUnitInThisPosition(getPositionX(),getPositionY()-1,getPlayer())){
            targets.add(Unit.getUnit(getPositionX(),getPositionY()-1,
                    getPlayer()));
        }
        if(!Unit.isThereUnitInThisPosition(getPositionX(),getPositionY()+1,getPlayer())){
            targets.add(Unit.getUnit(getPositionX(),getPositionY()+1,
                    getPlayer()));
        }
        if(!Unit.isThereUnitInThisPosition(getPositionX()-1,getPositionY(),getPlayer())){
            targets.add(Unit.getUnit(getPositionX()-1,getPositionY(),
                    getPlayer()));
        }
        if(!Unit.isThereUnitInThisPosition(getPositionX()+1,getPositionY(),getPlayer())){
            targets.add(Unit.getUnit(getPositionX()+1,getPositionY(),
                    getPlayer()));
        }

        int randomNumber = RandomHelper.nextInt(targets.size());
        targets.get(randomNumber).reduceHealth(this.getDamage());

    }


}

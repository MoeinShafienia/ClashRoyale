package ir.ac.kntu;

import java.util.ArrayList;
import java.util.List;

public abstract class Melee extends Soldier {
    @Override
    public void attack() {
        List<Unit> targets = new ArrayList<>();

        if(Unit.isThereUnitInThisPosition(getPositionX(),getPositionY()-40,
                getPlayer())){
            targets.add(Unit.getUnit(getPositionX(),getPositionY()-40,
                    getPlayer()));
        }
        if(Unit.isThereUnitInThisPosition(getPositionX(),getPositionY()+40,
                getPlayer())){
            targets.add(Unit.getUnit(getPositionX(),getPositionY()+40,
                    getPlayer()));
        }
        if(Unit.isThereUnitInThisPosition(getPositionX()-40,getPositionY(),
                getPlayer())){
            targets.add(Unit.getUnit(getPositionX()-40,getPositionY(),
                    getPlayer()));
        }
        if(Unit.isThereUnitInThisPosition(getPositionX()+40,getPositionY(),
                getPlayer())){
            targets.add(Unit.getUnit(getPositionX()+40,getPositionY(),
                    getPlayer()));
        }

        if(targets.size() != 0) {
            int randomNumber = RandomHelper.nextInt(targets.size());
            targets.get(randomNumber).reduceHealth(this.getDamage());
        }

    }


}


package ir.ac.kntu;

import java.util.ArrayList;
import java.util.List;

public abstract class Ranged extends Soldier {
    @Override
    public void attack() {
        List<Unit> targets = new ArrayList<>();
        for(int i = getPositionX() - getRange()*40 ; i < getPositionX() + getRange()*40 ; i+=40){
            for(int j = getPositionY() - getRange()*40 ; j < getPositionY() + getRange()*40 ; j+=40){
                if(Unit.isThereUnitInThisPosition(i,j,getPlayer())){
                    targets.add(Unit.getUnit(i,j,getPlayer()));
                }
                
            }
        }
        if (targets.size() != 0) {
            int randomNumber = RandomHelper.nextInt(targets.size());
            targets.get(randomNumber).reduceHealth(getDamage());
        }
    }
}



package ir.ac.kntu;

import java.util.ArrayList;
import java.util.List;

public abstract class Ranged extends Soldier {
    @Override
    public void attack() {
        List<Unit> targets = new ArrayList<>();
        for(int i = getPositionX() - getRange() ; i < getPositionX() + getRange() ; i++){
            for(int j = getPositionY() - getRange() ; j < getPositionY() + getRange() ; j++){
                if(!Unit.isThereUnitInThisPosition(i,j,getPlayer())){
                    targets.add(Unit.getUnit(i,j,getPlayer()));
                }
                
            }
        }
        int randomNumber = RandomHelper.nextInt(targets.size());
        targets.get(randomNumber).reduceHealth(getDamage());
    }
}


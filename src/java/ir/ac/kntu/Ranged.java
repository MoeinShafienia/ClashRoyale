
package ir.ac.kntu;

import java.util.ArrayList;
import java.util.List;

public abstract class Ranged extends Soldier {
    @Override
    public void attack() {
        List<Unit> targets = new ArrayList<>();
        for(int i = getPositionX() - getRange() ; i <= getPositionX() + getRange() ; i++){
            for(int j = getPositionY() - getRange() ; j <= getPositionY() + getRange() ; j++){
                if(Unit.isThereUnitInThisPosition(i,j,getPlayer())){
                    targets.add(Unit.getUnit(i,j,getPlayer()));
                }
                if(Tower.isThereTowerInThisPosition(i,j,this.getPlayer())){
                    targets.add(Tower.getTower(i,j,this.getPlayer()));
                }
            }
        }
        System.out.println("target size :" + targets.size());
        if (targets.size() != 0) {
            int randomNumber = RandomHelper.nextInt(targets.size());
            System.out.println("oh i fucked someone");
            targets.get(randomNumber).reduceHealth(getDamage());
        }
    }
}


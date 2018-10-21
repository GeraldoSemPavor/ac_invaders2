package game.collisions;

import game.hipster.Hipster;
import game.player.Player;
import game.shootables.Shuriken;

import java.util.ArrayList;

/**
 * Created by codecadet on 18/10/2018.
 */
public class CollisionDetector {

    private int killCount = 0;

    /**
     * Iterates the ArrayList<Shuriken> inside the ArrayList<Hipster> and compares values with equal() bellow
     */
    public void checkCollisions(ArrayList<Hipster> hipsters, ArrayList<Shuriken> shurikens, Player player) {

        for (Hipster hipster : hipsters) {
            for (Shuriken shuriken : shurikens) {
                if (equals(hipster, shuriken)) {
                    hipster.killHipster();
                    killCount++;
                    System.out.println(killCount);
                    shuriken.killShuriken();
                }
            }
            if (canHitPlayer(hipster, player)){
                hipster.killHipster();
                player.addCollision();
                player.changeClothes();
            }
        }
    }

    /**
     * Compares shuriken and hipster col and row.
     * Because the hipster picture ocupies 2x2 cells we check for the hipster row and row + 1
     */
    public boolean equals(Hipster hipster, Shuriken shuriken) {
        if (hipster.getCol() == shuriken.getCol() && ((hipster.getRow() == shuriken.getRow()) || (hipster.getRow() + 1 == shuriken.getRow()))) {
            return true;
        }
        return false;
    }

    public boolean canHitPlayer(Hipster hipster, Player player){
        if ((hipster.getCol() == ((player.getCol() + 1))) && ((hipster.getRow() == player.getRow()) ||
                ((hipster.getRow() + 1) == player.getRow()) ||
                ((hipster.getRow() ) == (player.getRow() + 1)))){
            return true;
        }
        return false;
    }


    public int getKillCount() {
        return killCount;
    }
}


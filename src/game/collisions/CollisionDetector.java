package game.collisions;

import game.hipster.Hipster;
import game.shootables.Shuriken;

import java.util.ArrayList;

/**
 * Created by codecadet on 18/10/2018.
 */
public class CollisionDetector {

    /**
     * Iterates the ArrayList<Shuriken> inside the ArrayList<Hipster> and compares values with equal() bellow
     */
    public void checkCollisions(ArrayList<Hipster> hipsters, ArrayList<Shuriken> shurikens) {

        for (Hipster hipster : hipsters) {
            for (Shuriken shuriken : shurikens) {
                if (equals(hipster, shuriken)) {
                    hipster.killHipster();
                    shuriken.killShuriken();
                }
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


}


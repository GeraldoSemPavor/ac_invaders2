package game.factories;

import game.Game;
import game.hipster.Hipster;

public class HipsterFactory {

    /** Creates hipster wave in a specific pattern */

    /** SYNTAX OF PLACEMENT ( COL , ROW )
     * COL = Total cols - horizontal distance.
     * ROW = ( x * ( i + y ) + z)
     * ---legend---
     * x = vertical distance between hipsters
     * y = not sure but think it has something to do with vertical placement
     * z = vertical distance from top of map*/
    public static Hipster makeHipster(int i){

        if (i < 5) {
            return new Hipster(Game.COLUMNS - 5, (4 * (i + 1)) + 12);
        }
        if (i < 9){
            return new Hipster(Game.COLUMNS - 3,  (4 * ((i - 5) + 1)) + 14);
        }
        if (i < 14){
            return new Hipster(Game.COLUMNS - 1,  (4 * ((i - 9) + 1)) + 12);
        }
        return null;
    }
}

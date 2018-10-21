package game.shootables;

import game.Game;
import game.hipster.Hipster;
import game.map.Cell;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.util.ArrayList;

public class Shuriken {

    /**
     * Properties
     */

    /**
     * Coordinates and Graphic Representation
     */
    private int col;
    private int row;
    private Picture shurikenImage;

    /**
     * Constructor
     */

    public Shuriken(int col, int row) {

        /** Coordinates. */
        this.col = col;
        this.row = row;

        /** Instantiating graphic representation */
        shurikenImage = new Picture(col * Cell.CELLSIZE, row * Cell.CELLSIZE, "shuriken-30px-s1.png");
        shurikenImage.draw();

    }

    /**
     * Methods
     */

    private boolean animationCounter = false;

    public void move(ArrayList<Shuriken> bullets, ArrayList<Hipster> hipsters) {

        /** Moves until the end of Map border */
        if (this.col < Game.COLUMNS) {

            if (bullets.size() != 0) {

                col++;
                shurikenImage.translate(Cell.CELLSIZE, 0);

                if (animationCounter == false) {
                    shurikenImage.load("shuriken-30px-s2.png");
                }
                if (animationCounter == true) {
                    shurikenImage.load("shuriken-30px-s1.png");
                }
                animationCounter = !animationCounter;
            }
            return;
        }

        /** Deletes hipster from ArrayList<Shuriken> and his graphic representation */
        shurikenImage.delete();
        bullets.remove(this);
    }

    /**
     * Put hipster outside map coordinates and deletes graphic representation
     */
    public void killShuriken() {
        this.col = -1;
        this.row = -1;
        shurikenImage.delete();
    }

    /**
     * Getters
     */

    public int getCol() {
        return this.col;
    }

    public int getRow() {
        return this.row;
    }
}

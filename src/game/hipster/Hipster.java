package game.hipster;

import game.map.Cell;
import game.shootables.Shuriken;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by codecadet on 19/10/2018.
 */
public class Hipster {

    /**
     * Properties
     */

    /**
     * Coordinates and Graphic Representation
     */
    private int col;
    private int row;
    private Picture hipsterImage;

    /**
     * This is used to change the hipster speed
     */
    private int moveCounter;

    /**
     * Constructor
     */

    public Hipster(int col, int row) {

        /** Coordinates and start move counter at 0. */
        this.col = col;
        this.row = row;
        moveCounter = 0;

        /** Instantiating graphic representation */
        hipsterImage = new Picture(col * Cell.CELLSIZE, row * Cell.CELLSIZE, "hipster.png");
        hipsterImage.draw();
    }

    /**
     * Methods
     */


    public void move(ArrayList<Hipster> hipsters, ArrayList<Shuriken> bullets) {


        /** Moves until the end of Map border */
        if (this.col > 0) {


            if (hipsters.size() != 0) {

                if (moveCounter == 3) {

                    col--;
                    hipsterImage.translate(-Cell.CELLSIZE, 0);
                    System.out.println("col: " + col + " row: " + row);
                    moveCounter = 0;

                }
                moveCounter++;
            }
            return;
        }

        /** Deletes hipster from ArrayList<Hipster> and his graphic representation */
        hipsterImage.delete();
        hipsters.remove(this);
    }

    /**
     * Getterts
     */

    public int getCol() {
        return this.col;
    }

    public int getRow() {
        return this.row;
    }

    /**
     * Put hipster outside map coordinates and deletes graphic representation
     */
    public void killHipster() {
        this.col = -1;
        this.row = -1;
        if (Math.random() * 30 == 15){
        hipsterImage.load("pow-gabriel.png");
        }
        hipsterImage.load("pow.png");
        try {
            Thread.sleep(90);
        }catch (InterruptedException ie){
            ie.getStackTrace();
            System.out.println("POW");
        }
        hipsterImage.delete();
    }

    /**
     * Uses coordinates as the measure parameters
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hipster hipster = (Hipster) o;
        return col == hipster.col &&
                row == hipster.row; // &&
        //!hasCollided &&
        //!hipster.hasCollided;
    }
}

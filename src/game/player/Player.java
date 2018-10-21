package game.player;

import game.Game;
import game.map.Cell;
import org.academiadecodigo.simplegraphics.graphics.*;
import org.academiadecodigo.simplegraphics.pictures.Picture;

/**
 * Created by codecadet on 18/10/2018.
 */
public class Player {

    /**
     * Properties
     */

    /**
     * Coordinates and Graphic Representation
     */
    private int col;
    private int row;
    private Picture playerImage;

    /**
     * Constructor
     */

    public Player(int col, int row) {

        /** Coordinates and instantiating graphic representation */
        this.col = col;
        this.row = row;
        playerImage = new Picture(0, 16 * Cell.CELLSIZE, "Ninja1medium.png");
        playerImage.draw();
    }

    /**
     * Methods
     */


    /**
     * Movement and Collision Logic.
     */

    /** UP */
    public void moveUp() {

        if (this.row - 1 < 16) {
            return;
        }
        this.row -= 1;

        playerImage.translate(0, -Cell.CELLSIZE);
        playerImage.draw();
    }

    /** DOWN */
    public void moveDown() {

        if (this.row + 1 == Game.ROWS) {
            return;
        }
        this.row += 1;

        playerImage.translate(0, Cell.CELLSIZE);
        playerImage.draw();
    }

    /** LEFT */
    public void moveLeft() {

        if (this.col - 1 < 0) {
            return;
        }
        this.col -= 1;
        /*rectangle.translate(- Cell.CELLSIZE, 0);
        rectangle.fill();*/
        //System.out.println("x: " + col + " y: " + row );
        playerImage.translate(-Cell.CELLSIZE, 0);
        playerImage.draw();
    }

    /** RIGHT */
    public void moveRight() {

        if (this.col + 1 == Game.COLUMNS) {
            return;
        }
        this.col += 1;
        /*rectangle.translate(Cell.CELLSIZE, 0);
        rectangle.fill();*/
        //System.out.println("x: " + col + " y: " + row );
        playerImage.translate(Cell.CELLSIZE, 0);
        playerImage.draw();
    }

    /**
     * Getters
     */

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }
}

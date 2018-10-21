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
     * Coordinates, collision counter and Graphic Representation
     */
    private int col;
    private int row;
    private int collisionCounter;
    private Picture playerImage;

    /**
     * Constructor
     */

    public Player(int col, int row) {

        /** Coordinates, collision counter and instantiating graphic representation */
        this.col = col;
        this.row = row;
        collisionCounter = 0;
        playerImage = new Picture(col * Cell.CELLSIZE, row * Cell.CELLSIZE, "ninja-s1.png");
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

    /** Collision and Change clothes method */
    public void addCollision(){
        if (collisionCounter == 4){
            return;
        }
        this.collisionCounter++;
    }

    public void changeClothes(){

        switch (collisionCounter) {
            case 1:
                playerImage.load("ninja-s2.png");
                break;
            case 2:
                playerImage.load("ninja-s3.png");
                break;
            case 3:
                playerImage.load("ninja-s4.png");
                break;
            case 4:
                playerImage.load("ninja-final.png");
            default:
                System.out.println("Change clothes error");
                break;
        }
    }

    public void killPlayer(){
        this.playerImage.load("gg.png");
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

    public int getCollisionCounter(){
        return this.collisionCounter;
    }
}

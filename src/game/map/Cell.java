package game.map;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

/**
 * Created by codecadet on 18/10/2018.
 */
public class Cell {

    /**
     * Properties
     */

    /**
     * Cellsize in pixels
     */
    public static final int CELLSIZE = 30;

    /**
     * Coordinates
     */
    private int col;
    private int row;
    private Rectangle cell;

    /**
     * Constructor
     */
    public Cell(int col, int row) {
        this.col = col;
        this.row = row;
        cell = new Rectangle(col * CELLSIZE, row * CELLSIZE, CELLSIZE, CELLSIZE);
        cell.setColor(Color.BLACK);

        /** Comment in or out to show grid or no grid */
        //cell.draw();
    }

    /**
     * Getters & TOS
     */

    public Cell getCell() {
        return this;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "col=" + col +
                ", row=" + row +
                '}';
    }
}


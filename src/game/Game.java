package game;

import game.collisions.CollisionDetector;
import game.factories.HipsterFactory;
import game.hipster.Hipster;
import game.map.Cell;
import game.player.Player;
import game.shootables.Shuriken;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;


public class Game implements KeyboardHandler {

    /**
     * Properties
     */

    /**
     * These static values set the size of the game grid, use only this to set dimensions
     */
    public static final int COLUMNS = 64;
    public static final int ROWS = 33;

    /**
     * Cell Bi-dimensional array that creates the map grid. Uses columns and rows static values
     */
    private Cell[][] cells;
    private int columns;
    private int rows;

    /**
     * These ArrayLists for shootables, enemies and a action stack for the shoot() method
     */
    private ArrayList<Shuriken> shurikens;
    private ArrayList<Hipster> hipsters;
    private ArrayList<Integer> actionStack;

    /**
     * Collision detector
     */
    private CollisionDetector collisionDetector;

    /**
     * Player and Keyboard
     */
    private Player player;
    private Keyboard keyboard;


    /**
     * Constructor
     */


    public Game(int columns, int rows) {

        /** Rows and Columns are used to create the Cell[][] */
        this.rows = rows;
        this.columns = columns;
        cells = new Cell[rows][columns];

        /** ArrayList for shurikens, hipsters and shoot() actioStack */
        shurikens = new ArrayList<Shuriken>();
        hipsters = new ArrayList<Hipster>();
        actionStack = new ArrayList<Integer>();

        /** Collision Detector */
        collisionDetector = new CollisionDetector();

        /** Keyboard */

        keyboard = new Keyboard(this);

        /** Movement */

        /** UP */
        KeyboardEvent upPressed = new KeyboardEvent();
        upPressed.setKey(KeyboardEvent.KEY_UP);
        upPressed.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(upPressed);

        /** DOWN */
        KeyboardEvent downPressed = new KeyboardEvent();
        downPressed.setKey(KeyboardEvent.KEY_DOWN);
        downPressed.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(downPressed);

        /** LEFT */
        KeyboardEvent leftPressed = new KeyboardEvent();
        leftPressed.setKey(KeyboardEvent.KEY_LEFT);
        leftPressed.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(leftPressed);

        /** RIGHT */
        KeyboardEvent rightPressed = new KeyboardEvent();
        rightPressed.setKey(KeyboardEvent.KEY_RIGHT);
        rightPressed.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(rightPressed);

        /** SHOOT */
        KeyboardEvent shoot = new KeyboardEvent();
        shoot.setKey(KeyboardEvent.KEY_SPACE);
        shoot.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(shoot);

        /** NOT IN USE */
        KeyboardEvent spaceReleased = new KeyboardEvent();
        spaceReleased.setKey(KeyboardEvent.KEY_SPACE);
        spaceReleased.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        keyboard.addEventListener(spaceReleased);

    }

    /**
     * Methods
     */

    /**
     * Initializes the game.
     * Sets background, populates the Cell][][] with coordinates.
     * Asks the Factory to make enemies.
     * Instantiates the player
     */
    public void init() {

        Picture background = new Picture(0, 0, "wall1920x1080.png");
        background.draw();

        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < columns; x++) {
                cells[y][x] = new Cell(x, y);
            }
        }

        for (int i = 0; i < 14; i++) {
            hipsters.add(HipsterFactory.makeHipster(i));
        }

        player = new Player(0, 16);

        /** See getMap() near the end */
        getMap();
    }

    /**
     * Starts the game.
     * Checks actionStack for shoot orders.
     * Proceeds to move shurikens, check for collisions, moves hipsters and checks collisions again.
     * Pauses thread for "x" milliseconds
     */
    public void start() throws InterruptedException {

        while (true) {

            if (!actionStack.isEmpty()) {
                actionStack.remove(0);
                shoot();
            }
            moveBullets(shurikens, hipsters);
            collisionDetector.checkCollisions(hipsters, shurikens);
            moveHipsters(hipsters, shurikens);
            collisionDetector.checkCollisions(hipsters, shurikens);
            Thread.sleep(60);
            if (hipsters.size() == 0) {
                for (int i = 0; i < 14; i++) {
                    hipsters.add(HipsterFactory.makeHipster(i));
                }
            }
        }
    }

    /**
     * Moves all Hipsters
     */
    private void moveHipsters(ArrayList<Hipster> hipsters, ArrayList<Shuriken> bullets) {

        try {

            for (Hipster hipster : hipsters) {

                if (hipsters.size() == 0) {
                    return;
                }
                try {

                    hipster.move(hipsters, bullets);
                } catch (NullPointerException npe) {
                    npe.getStackTrace();
                    System.out.println("Aquele erro chato...");
                }

            }
        } catch (ConcurrentModificationException c) {
            System.out.println("Concurrent exception");
        }
    }

    /**
     * Moves all Shurikens
     */
    private void moveBullets(ArrayList<Shuriken> bullets, ArrayList<Hipster> hipsters) {

        try {

            for (Shuriken shuriken : bullets) {

                if (bullets.size() == 0) {
                    return;
                }
                try {

                    shuriken.move(bullets, hipsters);
                } catch (NullPointerException npe) {
                    npe.getStackTrace();
                    System.out.println("Aquele erro chato...");
                }

            }
        } catch (ConcurrentModificationException c) {
            System.out.println("Concurrent exception");
        }
    }

    /**
     * Shoot method. Adds a shuriken to the ArrayList<Shuriken>.
     */
    private void shoot() {
        shurikens.add(new Shuriken(player.getCol() + 1, player.getRow()));
    }

    /**
     * Method for debugging and getting the cells
     */
    public void getMap() {
        int counter = 0;
        for (Cell[] row : cells) {
            for (Cell cell : row) {

                //System.out.println(cell);
                counter++;
            }
        }
        System.out.println("There are this many cells: " + counter);
    }

    /**
     * Keyboard KeyPRESSED and keyRELEASED Events
     */
    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {

        switch (keyboardEvent.getKey()) {
            case KeyboardEvent.KEY_UP:
                player.moveUp();
                break;
            case KeyboardEvent.KEY_DOWN:
                player.moveDown();
                break;
            case KeyboardEvent.KEY_LEFT:
                player.moveLeft();
                break;
            case KeyboardEvent.KEY_RIGHT:
                player.moveRight();
                break;
            case KeyboardEvent.KEY_SPACE:
                actionStack.add(1);
                break;
            default:
                System.out.println("Keyboard uncooperative, fix it. Pressed");
        }
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

        switch (keyboardEvent.getKey()) {
            case KeyboardEvent.KEY_SPACE:
                break;
            default:
                System.out.println("Keyboard uncooperative, fix it. Released");
        }

    }

}


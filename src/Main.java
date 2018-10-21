import game.Game;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        Game paint = new Game(Game.COLUMNS, Game.ROWS);

        paint.init();
        paint.start();
    }
}
public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        game.fillBoard();


        while (true) {
            game.printBoard();
            game.move();
        }
    }
}
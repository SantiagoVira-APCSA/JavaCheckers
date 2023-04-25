public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        for (int y = 0; y < 3; y++) {
            for (int x = (y + 1) % 2; x < 8; x += 2) {
                Tile t = game.getTile(x, y);
                t.setPiece(new Piece(y == 2));
            }
        }
        for (int y = 7; y > 4; y--) {
            for (int x = (y + 1) % 2; x < 8; x += 2) {
                Tile t = game.getTile(x, y);
                t.setPiece(new Piece(y == 5));
            }
        }
        game.printBoard();
        System.out.println("●");
        System.out.println("○");
    }
}
public class Game {
    private Tile[][] board;

    public Game() {
        board = new Tile[8][8];
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                board[y][x] = new Tile(x, y);
            }
        }
        board[0][1].setPiece(new Piece(false));
    }

    public void printBoard() {
        System.out.print("\033[H\033[2J");
        System.out.flush();

        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                board[y][x].print();
            }
            System.out.println();
        }
    }

    public Tile getTile(int x, int y) {
        return board[y][x];
    }
}

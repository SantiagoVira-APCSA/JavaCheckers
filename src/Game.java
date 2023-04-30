import java.util.Scanner;

public class Game {
    private Tile[][] board;
    private int turn;

    public Game() {
        board = new Tile[8][8];
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                board[y][x] = new Tile(x, y);
            }
        }
        turn = 0;
    }

    public void fillBoard() {
        for (int y = 0; y < 3; y++) {
            for (int x = (y + 1) % 2; x < 8; x += 2) {
                Tile t = getTile(x, y);
                t.setPiece(new Piece(true, y == 2));
            }
        }
        for (int y = 7; y > 4; y--) {
            for (int x = (y + 1) % 2; x < 8; x += 2) {
                Tile t = getTile(x, y);
                t.setPiece(new Piece(false, y == 5));
            }
        }
    }

    public void printBoard() {
        System.out.print("\033[H\033[2J");
        System.out.flush();

        for (int y = -1; y < 8; y++) {
            for (int x = -1; x < 8; x++) {
                if (y == -1 && x == -1) System.out.print(Color.WHITE_BACKGROUND + Color.BLACK + "   " + Color.RESET);
                else if (y == -1) {
                    String letter = "ABCDEFGH".substring(x, x + 1);
                    System.out.print(Color.WHITE_BACKGROUND + Color.BLACK + " " + letter + " " + Color.RESET);
                } else if (x == -1)
                    System.out.print(Color.WHITE_BACKGROUND + Color.BLACK + " " + (y + 1) + " " + Color.RESET);
                else board[y][x].print();
            }
            System.out.println();
        }

        move();
    }

    public void move() {
        int[] move = getCoordsFromInput();
        int x = move[0];
        int y = move[1];

        // Check if the piece can move immediately up in either direction
        boolean canMoveLeft = isAvailable(x - 1, y + 1);
        boolean canMoveRight = isAvailable(x + 1, y + 1);

        // If the squares immediately above are taken, check if the piece can jump
        if (!canMoveLeft) canMoveLeft = isAvailable(x - 2, y + 2);
        if (!canMoveRight) canMoveRight = isAvailable(x + 2, y + 2);
    }

    public boolean isAvailable(int x, int y) {
//        Tile currentTile = getTile(x, y);
        return inRange(x) && inRange(y) && getTile(x, y).isEmpty();
    }

    public int[] getCoordsFromInput() {
        Scanner s = new Scanner(System.in);
        String xLetter = "";
        int x, y;
        String letter = "ABCDEFGH";
        do {
            do {
                System.out.println("Which horizontal position do you want to move? (A-H): ");
                xLetter = s.nextLine().trim().toUpperCase();
            } while (!letter.contains(xLetter) || xLetter.length() != 1);
            x = letter.indexOf(xLetter);

            do {
                System.out.println("Which vertical position do you want to move? (1-8): ");
                y = s.nextInt() - 1;
            } while (!inRange(y));
        } while (!getTile(x, y).pieceCanMove());


        s.close();
        return new int[]{x, y};
    }

    public Tile getTile(int x, int y) {
        return board[y][x];
    }

    public boolean isWhiteMove() {
        return turn % 2 == 0;
    }

    public boolean inRange(int num) {
        return (0 <= num && num < 8);
    }
}

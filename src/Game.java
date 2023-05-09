import java.util.Scanner;

public class Game {
    private final Tile[][] board;
    private int turn;
    private final Scanner scan;

    public Game() {
        board = new Tile[8][8];
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                board[y][x] = new Tile(x, y);
            }
        }
        turn = 0;
        scan = new Scanner(System.in);
    }

    public void fillBoard() {
        for (int y = 0; y < 3; y++) {
            for (int x = (y + 1) % 2; x < 8; x += 2) {
                Tile t = getTile(x, y);
                t.setPiece(new Piece(true));
            }
        }
        for (int y = 7; y > 4; y--) {
            for (int x = (y + 1) % 2; x < 8; x += 2) {
                Tile t = getTile(x, y);
                t.setPiece(new Piece(false));
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

    }

    public void move() {
        int[] toMove = getCoordsFromInput();
        int x = toMove[0], y = toMove[1];
        int row1 = nRowsAhead(y, 1), row2 = nRowsAhead(y, 2);

        // Check if the piece can move immediately up in either direction
        boolean canMoveLeft = isAvailable(x - 1, row1);
        boolean canMoveRight = isAvailable(x + 1, row1);
        boolean canJumpLeft = false, canJumpRight = false;

        // If the squares immediately above are taken, check if the piece can jump
        if (!canMoveLeft) canJumpLeft = isAvailable(x - 2, row2) && tileIsOpponent(x - 1, row1);
        if (!canMoveRight) canJumpRight = isAvailable(x + 2, row2) && tileIsOpponent(x + 1, row1);


        // Check if choice
        if ((canMoveLeft || canJumpLeft) && (canMoveRight || canJumpRight)) {
            String choice;
            do {
                System.out.print("Which direction do you want to move? (L or R): ");
                choice = scan.nextLine().trim().toUpperCase();
            } while (!(choice.equals("L") || choice.equals("R")));

            if (choice.equals("L")) {
                canJumpRight = false;
                canMoveRight = false;
            } else {
                canJumpLeft = false;
                canMoveLeft = false;
            }
        }

        Tile newTile = null;
        if (canMoveLeft) newTile = getTile(x - 1, row1);
        else if (canMoveRight) newTile = getTile(x + 1, row1);
        else if (canJumpLeft) {
            newTile = getTile(x - 2, row2);
            if (getTile(x - 1, row1).isWhite() != getTile(x, y).isWhite()) getTile(x - 1, row1).movePiece();
        } else if (canJumpRight) {
            newTile = getTile(x + 2, row2);
            if (getTile(x + 1, row1).isWhite() != getTile(x, y).isWhite()) getTile(x + 1, row1).movePiece();
        }

        if (newTile != null) {
            newTile.setPiece(getTile(x, y).movePiece());
            turn++;
        }
    }

    private int nRowsAhead(int y, int n) {
        // Return the current row + or - n, depending on whose turn it is
        // Helper function for move()
        return isWhiteMove() ? y + n : y - n;
    }

    public boolean isAvailable(int x, int y) {
//        Tile currentTile = getTile(x, y);
        return inRange(x) && inRange(y) && getTile(x, y).isEmpty();
    }

    public int[] getCoordsFromInput() {
        String xLetter;
        int x, y;
        String letter = "ABCDEFGH";
        boolean hasMoves;
        System.out.println("Player " + (isWhiteMove() ? Color.WHITE_BOLD + "White" : Color.RED_BOLD + "Red") + Color.RESET + "'s turn!");
        do {
            do {
                System.out.print("Which horizontal position do you want to move? (A-H): ");
                xLetter = scan.nextLine().trim().toUpperCase();
            } while (!letter.contains(xLetter) || xLetter.length() != 1);
            x = letter.indexOf(xLetter);

            do {
                System.out.print("Which vertical position do you want to move? (1-8): ");
                y = scan.nextInt() - 1;
                scan.nextLine();
            } while (!inRange(y));

            int row1 = nRowsAhead(y, 1), row2 = nRowsAhead(y, 2);
            hasMoves = isAvailable(x - 1, row1) || isAvailable(x + 1, row1) ||
                    (isAvailable(x - 2, row2) && tileIsOpponent(x - 1, row1)) ||
                    (isAvailable(x + 2, row2) && tileIsOpponent(x + 1, row1));
        } while (!(getTile(x, y).pieceCanMove(isWhiteMove()) && hasMoves));


        return new int[]{x, y};
    }

    private boolean tileIsOpponent(int x, int y) {
        // Helper function to check if a tile is holding an opponent piece
        return isWhiteMove() != getTile(x, y).isWhite();
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

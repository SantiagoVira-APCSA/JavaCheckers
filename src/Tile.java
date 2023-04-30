public class Tile {
    private int x, y;
    private String background;
    private Piece piece;

    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
        this.background = (x + y) % 2 == 0 ? Color.RED_BACKGROUND : Color.BLACK_BACKGROUND;
    }

    public void print() {
        String symbol = piece == null ? " " : piece.getSymbol();
        System.out.print(background + " " + symbol + " " + Color.RESET);
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public boolean pieceCanMove() {
        return piece != null && piece.canMove();
    }

    public boolean isEmpty() {
        return piece == null;
    }
}

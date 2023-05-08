public class Tile {
    private final int x, y;
    private final String background;
    private Piece piece;

    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
        this.piece = null;
        this.background = (x + y) % 2 == 0 ? Color.RED_BACKGROUND : Color.BLACK_BACKGROUND;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void print() {
        String symbol = piece == null ? " " : piece.getSymbol();
        System.out.print(background + " " + symbol + " " + Color.RESET);
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public boolean pieceCanMove(boolean isWhiteMove) {
        return piece != null && isWhiteMove == piece.isWhite();
    }

    public boolean isEmpty() {
        return piece == null;
    }

    public boolean isWhite() {
        return piece.isWhite();
    }

    public Piece movePiece() {
        Piece temp = piece;
        piece = null;
        return temp;
    }
}

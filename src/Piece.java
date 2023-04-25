public class Piece {
    private boolean canMove;
    private boolean isKing;
    private String symbol;

    public Piece(boolean canMove) {
        this.canMove = canMove;
        isKing = false;
        symbol = isKing ? "O" : "0";
    }

    public String getSymbol() {
        return symbol;
    }
}

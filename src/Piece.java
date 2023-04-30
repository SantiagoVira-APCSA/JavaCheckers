public class Piece {
    private boolean canMove, isKing, isWhite;
    private String symbol, color;

    public Piece(boolean isWhite, boolean canMove) {
        this.canMove = canMove;
        this.isWhite = isWhite;
        isKing = false;
        symbol = isKing ? "O" : "o";
        color = isWhite ? Color.WHITE : Color.RED;
    }

    public String getSymbol() {
        return color + symbol;
    }

    public boolean canMove() {
        return canMove;
    }
}

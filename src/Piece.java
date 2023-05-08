public class Piece {
    private final boolean isKing, isWhite;
    private final String symbol, color;

    public Piece(boolean isWhite) {
        this.isWhite = isWhite;
        isKing = false;
        symbol = "o";//isKing ? "O" : "o";
        color = isWhite ? Color.WHITE : Color.RED;
    }

    public String getSymbol() {
        return color + symbol;
    }

    public boolean isKing() {
        return isKing;
    }

    public boolean isWhite() {
        return isWhite;
    }

}

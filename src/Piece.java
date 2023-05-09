public class Piece {
    private final boolean isWhite;
    private boolean isKing;
    private String symbol;
    private final String color;

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

    public void makeKing() {
        isKing = true;
        symbol = "0";
    }

    public boolean isWhite() {
        return isWhite;
    }

}

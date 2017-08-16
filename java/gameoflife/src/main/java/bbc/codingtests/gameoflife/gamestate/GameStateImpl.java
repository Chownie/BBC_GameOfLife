package bbc.codingtests.gameoflife.gamestate;

public class GameStateImpl implements GameState {
    private final char[][] gameGrid;
    private final int rowCount;
    private final int colCount;

    // Convert back to a string, trying to match the same given syntax.
    // Newlines between rows, no newline at the end
    // Live cells denoted by an asterisk(*) dead cells denoted by a period(.)
    @Override
    public String toString() {
        // Concatenating strings in a loop copies the string every iteration, more efficient to use a StringBuilder.
        StringBuilder output = new StringBuilder();

        for(int y = 0; y < this.rowCount; y++) {
            for(int x = 0; x < this.colCount; x++) {
                output.append(this.gameGrid[x][y]);
            }

            // Only add newlines between rows, not after each one
            if(y != this.rowCount-1) {
                output.append("\n");
            }
        }

        return output.toString();
    }

    // Convert given string into a 2D array of characters representing live and dead cells
    public GameStateImpl(String input) {
        String[] gameRows = input.split("\n");

        // Take the length of the first string to find the number of columns, take the length of the array to find rows
        // (assume that it's a perfect grid and no rows or columns differ in size)
        this.rowCount = gameRows.length;
        this.colCount = gameRows[0].length();

        this.gameGrid = new char[this.colCount][this.rowCount];

        //
        for(int y = 0; y < this.rowCount; y++) {
            char[] line = gameRows[y].toCharArray();
            for(int x = 0; x < this.colCount; x++) {
                this.gameGrid[x][y] = line[x];
            }
        }
    }

    // A cell is live if the character representing it is an asterisk
    public boolean isCellAliveAt(int row, int col) {
        return this.gameGrid[row][col] == '*';
    }

    @Override
    public int getRows() {
        return this.rowCount;
    }

    @Override
    public int getCols() {
        return this.colCount;
    }
}

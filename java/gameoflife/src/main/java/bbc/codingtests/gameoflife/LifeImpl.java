package bbc.codingtests.gameoflife;

import bbc.codingtests.gameoflife.gamestate.GameState;
import bbc.codingtests.gameoflife.gamestate.GameStateImpl;

public class LifeImpl implements Life
{
    //
    private static final String ALIVE = "*";
    private static final String DEAD = ".";

	public GameState evolve(GameState currentState) {
	    StringBuilder newGameInput = new StringBuilder();

        for(int y = 0; y < currentState.getRows(); y++) {
            for (int x = 0; x < currentState.getCols(); x++) {
                int liveNeighbours = AliveNeighbourCount(currentState, y, x);
                boolean alive = currentState.isCellAliveAt(x, y);
                newGameInput.append(LifeBehaviour(alive, liveNeighbours));
            }
            if(y != currentState.getRows()) { // Avoid superfluous newlines at the end, match the input style
                newGameInput.append("\n");
            }
        }

        return new GameStateImpl(newGameInput.toString());
	}

	// For live cells:
    //          Less than 2 neighbours means death
    //          More than 3 neighbours means death
    // For dead cells:
    //          Exactly 3 neighbouring cells creates a live cell in this position
	private String LifeBehaviour(boolean alive, int neighbours) {
        if(alive) {
            if(neighbours == 2 || neighbours == 3) {
                return ALIVE;
            } else {
                return DEAD;
            }
        } else {
            if(neighbours == 3) {
                return ALIVE;
            } else {
                return DEAD;
            }
        }
    }

	// Find the number of live neighbours by
	private int AliveNeighbourCount(GameState currentState, int row, int column) {
        int width = currentState.getCols() - 1;
        int height = currentState.getRows() - 1;

	    // Find the appropriate upper-left and bottom-right corners for checking neighbours
        int startPositionX = (column - 1 < 0 )      ? column : column - 1;
        int endPositionX   = (column + 1 > width)   ? column : column + 1;
        int startPositionY = (row    - 1 < 0 )      ? row : row - 1;
        int endPositionY   = (row    + 1 > height)  ? row : row + 1;

        int liveCount = 0;

        for(int x = startPositionX; x <= endPositionX; x++) {
            for(int y = startPositionY; y <= endPositionY; y++) {
                if(y == row && x == column) {
                    continue;
                }
                if(currentState.isCellAliveAt(x, y)) {
                    liveCount++;
                }
            }
        }

        return liveCount;
    }
}

package bbc.codingtests.gameoflife;

import bbc.codingtests.gameoflife.gamestate.GameState;
import bbc.codingtests.gameoflife.gamestate.GameStateImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class LifeTest {
	@Test
	public void testEmptyGrid() {
		String emptyStateInput = "...\n...\n...";

		Life testLife = new LifeImpl();
		GameState emptyState = new GameStateImpl(emptyStateInput);
		assertEquals("An empty grid should stay the same", emptyStateInput, testLife.evolve(emptyState).toString());
	}

	@Test
	public void testUnderpopulatedGrid() {
		String underpopStateInput = "*..\n...\n..*";
		String expectedStateInput = "...\n...\n...";

		Life testLife = new LifeImpl();
		GameState underPopState = new GameStateImpl(underpopStateInput);
		assertEquals("Live cells with fewer than two neighbours should die", expectedStateInput, testLife.evolve(underPopState).toString());
	}

	@Test
	public void testOverpopulatedGrid() {
		String overPopStateInput = "**.\n**.\n**.";
		String expectedStateInput = "**.\n..*\n**.";

		Life testLife = new LifeImpl();
		GameState overPopState = new GameStateImpl(overPopStateInput);
		assertEquals("Live cells with more than three neighbours should die", expectedStateInput, testLife.evolve(overPopState).toString());
	}

	@Test
	public void testSurvival() {
		String survivalStateInput = ".*.\n.*.\n*.*";
		String expectedStateInput = "...\n***\n.*.";

		Life testLife = new LifeImpl();
		GameState survivalState = new GameStateImpl(survivalStateInput);
		assertEquals("Live cells with two or three neighbours should live", expectedStateInput, testLife.evolve(survivalState).toString());
	}

	@Test
	public void testCreation() {
		String creationStateInput = ".*.\n...\n*.*";
		String expectedStateInput = "...\n.*.\n...";

		Life testLife = new LifeImpl();
		GameState survivalState = new GameStateImpl(creationStateInput);
		assertEquals("Dead cells with three live neighbours should become live", expectedStateInput, testLife.evolve(survivalState).toString());
	}

	@Test public void testSeededGrid() {
		String initialStateInput = ".....\n.....\n.***.\n.....\n.....";
		String alternateStateInput = ".....\n..*..\n..*..\n..*..\n.....";

		Life testLife = new LifeImpl();
		GameState seededState = new GameStateImpl(initialStateInput);

		GameState turnTwo = testLife.evolve(seededState);
		GameState turnThree = testLife.evolve(turnTwo);

		assertEquals("A blinker should match the initial state every odd turn", initialStateInput, turnThree.toString());
		assertEquals("A blinker should rotate 90 degrees from the initial state every even turn", alternateStateInput, turnTwo.toString());
	}
}

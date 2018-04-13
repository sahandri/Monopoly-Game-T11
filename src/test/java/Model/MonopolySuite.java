package Model;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	TestBoard.class,
	TestCard.class,
	TestCorner.class,
	TestDice.class,
	TestMonopoly.class,
	TestPlayer.class,
	TestRailroad.class,
	TestSquare.class,
	TestStreet.class,
	TestTax.class,
	TestToken.class,
	TestUtilities.class
})
public class MonopolySuite{
	
}

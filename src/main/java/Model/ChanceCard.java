package Model;

import java.util.Random;

public class ChanceCard extends Card{
	
	private Random rand = new Random();

	private int randomNumber = rand.nextInt(16);
	
	private String message = new String();
	
	public ChanceCard(int price, int position) {
		super(price, position);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void perform(Player player, Board board) {
		// Follow instructions 
		switch(randomNumber) {
		case 0:
			 message = "Advance to Go (Collect $200) (Mr. M hops on both feet.)";
			 player.getMoney().addMoney(200);
			 board.move(player, 0);
			 break;
		case 1:
			if(player.getToken().getPosition() > 24) {
				player.getMoney().addMoney(200);
			}
			board.move(player, 24);
			message =  "Advance to Illinois Ave. - If you pass Go, collect $200 (Mr. M has tied a cloth bundle onto his cane to make a bindle, carried over his right shoulder, and is smoking a cigar)";
			break;
		case 2:
			if(player.getToken().getPosition() > 11) {
				player.getMoney().addMoney(200);
			}
			board.move(player, 11);
			message =  "Advance to St. Charles Place – If you pass Go, collect $200 (Mr. M hurries along, hauling a little boy by the hand)";
			break;
		case 3:
			// If difference from player's current location to utility1 less than that of utility2, move to utility1.
			int utility1 = Math.abs(player.getToken().getPosition() - 12);
			int utility2 = Math.abs(player.getToken().getPosition() - 28);
			if(utility1 < utility2) {
				board.move(player, 12); // Electric Company
			}
			else {
				board.move(player, 28); // Water Works	
			}
			message =  "Advance token to nearest Utility. If unowned, you may buy it from the Bank. If owned, throw dice and pay owner a total ten times the amount thrown. (Mr. M trudges along with a huge battleship token on his back)";
			break;
		case 4:
			int railroad1 = Math.abs(player.getToken().getPosition() - 5);
			int railroad2 = Math.abs(player.getToken().getPosition() - 15);
			int railroad3 = Math.abs(player.getToken().getPosition() -25);
			int railroad4 = Math.abs(player.getToken().getPosition() - 35);
			if((railroad1 < railroad2) && (railroad1 < railroad3) && (railroad1 < railroad4)) {
				// Pays twice the amount, what if moved player there twice?
				board.move(player, 5);
				board.move(player, 5);
			}
			else if((railroad2 < railroad1) && (railroad2 < railroad3) && (railroad2 < railroad4)) {
				board.move(player, 15);
				board.move(player, 15);
			}
			else if((railroad3 < railroad1) && (railroad3 < railroad2) && (railroad3 < railroad4)) {
				board.move(player, 25);
				board.move(player, 25);
			}
			else {
				board.move(player, 35);
				board.move(player, 35);
			}
 			message =  "Advance token to the nearest Railroad and pay owner twice the rental to which he/she {he} is otherwise entitled. If Railroad is unowned, you may buy it from the Bank. (There are two of these.) (At lower left, Mr. M carries a large flatiron token with two hands; at upper right a railroad crossing is seen)";
			break;
		case 5:
			player.getMoney().addMoney(50);
			message =  "Bank pays you dividend of $50 (With his feet up on a telephone-bearing desk, Mr. M leans back in an overstuffed chair, blowing cigar smoke rings)";
			break;
		case 6:
			player.setGetOutOfJail(true);
			message =  "Get out of Jail Free – This card may be kept until needed, or traded/sold (Mr. M, in close-fitting one-piece prison stripes, is literally kicked out)";
			break;
		case 7:
			int threeBack = player.getToken().getPosition()-3;
			board.move(player, threeBack);
			message =  "Go Back 3 Spaces (Mr. M is hauled back by a cane hooked around his neck)";
			break;
		case 8:
			player.getToken().imprison();
			board.move(player, 10);
			message =  "Go to Jail – Go directly to Jail – Do not pass Go, do not collect $200 (A truncheon-carrying policeman in a dark-colored uniform hauls a surprised Mr M along by the feet)";
			break;
		case 9:
			int houses = 0;
			int hotels = 0;
			for(int i = 0; i < board.getStreetArray().length; i++) {
				for(int j = 0; j < board.getStreetArray()[i].length; j++) {
					if(player.equals(board.getPropertyOwner(board.getStreetArray()[i][j]))){
					//TODO	
					}
				}
				
			}
			message =  "Make general repairs on all your property – For each house pay $25 – For each hotel $100 (Consulting a \"How to Fix It\" brochure, a hammer-wielding Mr. M sits astride a house not much larger than he is; it buckles under his weight)";
			break;
		case 10:
			player.getMoney().sbustractMoney(15);
			message =  "Pay poor tax of $15 (His trouser pockets pulled out to show them empty, Mr. M spreads his hands) (The video game version replaces this with Speeding fine $15, reportedly also in the UK version.)";
			break;
		case 11:
			if(player.getToken().getPosition() > 5) {
				player.getMoney().addMoney(200);
			}
			board.move(player, 5);
			message =  "Take a trip to Reading Railroad – If you pass Go, collect $200 (Mr. M rides astride the TOOTing engine of a train)";
			break;
		case 12:
			board.move(player, 39);
			message =  "Take a walk on the Boardwalk – Advance token to Boardwalk. (Mr. M, a smallish dog hung over one arm, with the other pushes a squalling baby in a small pram; behind them, birds fly in the sky above a low fence)";
			break;
		case 13:
			//TODO
			message = "You have been elected Chairman of the Board – Pay each player $50 (A newsboy shouts an Extra with Mr. M's headshot on its front page)";
			break;
		case 14:
			player.getMoney().addMoney(150);
			message =  "Your building loan matures – Collect $150 (Mr. M joyfully embraces an apparent wife)";
			break;
		case 15:
			player.getMoney().addMoney(150);
			message =  "You have won a crossword competition - Collect $100";
			break;
		default:
			message = "Card not in deck!";
			break;
		}
		this.randomNumber = rand.nextInt(16);
	}

	public String toString() {
				return " Chance! \n";
			}
	
	public String getMessage() {
		return message;
	}

}
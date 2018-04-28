package Model;

import java.util.Random;

public class ChanceCard extends Card{
	
	private Random rand = new Random();

	private int randomNumber;
	
	private String message = new String();
	
	public ChanceCard(int price, int position) {
		super(price, position);
		try {
			setChance(position);
		}catch(IllegalArgumentException e){
			System.err.println("invalid position "+e.getMessage());
		}
	}

	public void setChance(int position) {
		if(position == 7 || position == 22 || position == 36) {
			randomNumber = rand.nextInt(16);
		}
		else {
			throw new IllegalArgumentException("Unknown position");
		}
	}

	@Override
	public void perform(Player player, Board board) {
		// Follow instructions 
		switch(randomNumber) {
		case 0:
			 
			 player.getMoney().addMoney(200);
			 board.move(player, 0);
			 message = "Advance to Go (Collect $200)";
			 break;
		case 1:
			if(player.getToken().getPosition() > 24) {
				player.getMoney().addMoney(200);
			}
			message =  "Advance to Illinois Ave. - If you pass Go, collect $200";
			board.move(player, 24);
			break;
		case 2:
			if(player.getToken().getPosition() > 11) {
				player.getMoney().addMoney(200);
			}
			board.move(player, 11);
			message =  "Advance to St. Charles Place – If you pass Go, collect $200";
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
			message =  "Advance token to nearest Utility. If unowned, you may buy it from the Bank. If owned, throw dice and pay owner a total ten times the amount thrown.";
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
 			message =  "Advance token to the nearest Railroad and pay owner twice the rental to which he/she is otherwise entitled. If Railroad is unowned, you may buy it from the Bank.";
			break;
		case 5:
			player.getMoney().addMoney(50);
			message =  "Bank pays you dividend of $50";
			break;
		case 6:
			player.setGetOutOfJail(true);
			message =  "Get out of Jail Free – This card may be kept until needed, or traded/sold";
			break;
		case 7:
			int threeBack = player.getToken().getPosition()-3;
			if(threeBack < 0) {
				threeBack += 39;
			}
			board.move(player, threeBack);
			message =  "Go Back 3 Spaces";
			break;
		case 8:
			player.getToken().imprison();
			board.move(player, 10);
			message =  "Go to Jail – Go directly to Jail – Do not pass Go, do not collect $200";
			break;
		case 9:
			int houses = board.getOwnedHouses(player).size();
			int hotels = board.getOwnedHotels(player).size();
			player.getMoney().sbustractMoney(25*houses + 100*hotels);
			message = "Make general repairs on all your property – For each house pay $25 – For each hotel $100";
			break;
		case 10:
			player.getMoney().sbustractMoney(15);
			message =  "Pay poor tax of $15";
			break;
		case 11:
			if(player.getToken().getPosition() > 5) {
				player.getMoney().addMoney(200);
			}
			board.move(player, 5);
			message =  "Take a trip to Reading Railroad – If you pass Go, collect $200";
			break;
		case 12:
			board.move(player, 39);
			message =  "Take a walk on the Boardwalk – Advance token to Boardwalk.";
			break;
		case 13:

			int numPlayers = board.getPlayers().size()-1;
			for(int i = 0; i <= numPlayers; i ++) {
				if(!(i == player.getID())) {
					board.getPlayers().get(i).getMoney().addMoney(50);
				}

			}
			player.getMoney().sbustractMoney(50*numPlayers);
			message = "You have been elected Chairman of the Board – Pay each player $50";
			break;
		case 14:
			player.getMoney().addMoney(150);
			message =  "Your building loan matures – Collect $150";
			break;
		case 15:

			player.getMoney().addMoney(100);

			message =  "You have won a crossword competition - Collect $100";
			break;
		default:
			message = "Card not in deck!";
			break;
		}
		this.randomNumber = rand.nextInt(16);
	}

	public String toString() {
				return " Chance! \n" + getMessage() + "\n";
			}
	
	public String getMessage() {
		return message;
	}
	
	public void setCard(int card) {
		this.randomNumber = card;
	}

	
	public int getCard() {
		return this.randomNumber;
	}


}
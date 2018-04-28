package Model;

import java.util.Random;

public class CommunityChest extends Card{
	private Random rand = new Random();

	private int randomNumber;
	
	private String message = new String();
	
	public CommunityChest(int price, int position) {
		super(price, position);
		try {
			setCommunityChest(position);
		}catch(IllegalArgumentException e){
			System.err.println("invalid position "+e.getMessage());
		}
	}

	public void setCommunityChest(int position) {
		if(position == 2 || position == 17 || position == 33) {
			randomNumber = rand.nextInt(17);
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
					player.getMoney().addMoney(200);
					message =  "Bank error in your favor – Collect $200";
					break;
				case 2:
					player.getMoney().sbustractMoney(50);
					message =  "Doctor's fees – Pay $50 ";
					break;
				case 3:
					player.getMoney().addMoney(50);
					message =  "From sale of stock you get $50";
					break;
				case 4:
					player.setGetOutOfJail(true);
					message =  "Get out of Jail Free – This card may be kept until needed, or traded/sold";
					break;
				case 5:
					player.getToken().imprison();
					board.move(player, 10);
					message =  "Go to Jail – Go directly to Jail – Do not pass Go, do not collect $200";
					break;
				case 6:
					int numPlayers = board.getPlayers().size()-1;
					for(int i = 0; i <= numPlayers; i ++) {
						if(!(i == player.getID())) {
							board.getPlayers().get(i).getMoney().sbustractMoney(50);
						}
					}
					player.getMoney().addMoney(50*numPlayers);
					message =  "Grand Opera Night – Collect $50 from every player for opening night seats";
					break;
				case 7:
					player.getMoney().addMoney(100);
					message =  "Holiday Fund matures - Receive $100";
					break;
				case 8:
					player.getMoney().addMoney(20);
					message =  "Income tax refund – Collect $20";
					break;
				case 9:
					numPlayers = board.getPlayers().size()-1;
					for(int i = 0; i <= numPlayers; i ++) {
						if(!(i == player.getID())) {
							board.getPlayers().get(i).getMoney().sbustractMoney(10);
						}

					}
					player.getMoney().addMoney(10*numPlayers);
					message = "It is your birthday - Collect $10 from each player";
					break;
				case 10:
					player.getMoney().addMoney(100);
					message =  "Life insurance matures – Collect $100";
					break;
				case 11:
					player.getMoney().sbustractMoney(100);
					message =  "Pay hospital fees of $100";
					break;
				case 12:
					player.getMoney().sbustractMoney(150);
					message =  "Pay school fees of $150";
					break;
				case 13:
					player.getMoney().addMoney(25);
					message = "Receive $25 consultancy fee";
					break;
				case 14:
					int houses = board.getOwnedHouses(player).size();
					int hotels = board.getOwnedHotels(player).size();
					player.getMoney().sbustractMoney(40*houses + 115*hotels);
					message =  "You are assessed for street repairs – $40 per house – $115 per hotel";
					break;
				case 15:
					player.getMoney().addMoney(10);
					message =  "You have won second prize in a beauty contest – Collect $10";
					break;
				case 16:
					player.getMoney().addMoney(100);

					message =  "You inherit $100";
					break;
				default:
					message = "Card not in deck!";
					break;
				}
				this.randomNumber = rand.nextInt(17);
	}

	public String toString() {
				return " Community Chest! \n";
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

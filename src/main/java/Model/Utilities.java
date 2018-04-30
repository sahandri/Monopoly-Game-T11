package Model;

//import src.main.java.Model.Tax.taxes;

public class Utilities extends Square {

    /*
     2 utilities in Monopoly include water works and electricity company.
     Example:
     Mary's token is on Reading Railroad; on her turn, she rolls the dice and they come up with 7. Counting the
     spaces, she lands her token on Electric Company, which is owned by Sarah. The dice roll (7) is multiplied by
     4, for a total of 28; Mary now owes Sarah $28 rent.
    */
	
	
	public enum utilities{
		WATER_WORKS,
		ELECTRICITY_COMPANY
	}
	
	private utilities utility;
	private String message = "";
	
    public Utilities(int price, int position) {
    	super(price, position);
		try {
			setUtility(position);
		}catch(IllegalArgumentException e){
			System.err.println("invalid position"+e.getMessage());
		}
    }

    private void setUtility(int position) {
    	if(position == 12) {
			utility = utilities.ELECTRICITY_COMPANY;
		}
		else if(position == 28) {
			utility = utilities.WATER_WORKS;
		}
		else {
			throw new IllegalArgumentException("Unknown position");
		}
		
	}
    
    public String toString(){
		switch(this.utility){
			case ELECTRICITY_COMPANY: return " at Electricity Company. \n" + message;
			case WATER_WORKS: return " at Water Works. \n" + message;
			default: return "Type not defined.";
		}
	}


	public void perform(Player player, Board board) {
		if(this.getOwner() == -1 && player.getDecision()){ // if nobody owns the street and player wants to buy it
			//player pays price and buy the property
			board.purchaseProperty(player, this.getPosition());
			this.setOwner(player);
			message = player.getName() + " -$" + getPrice() +"\n";
		}
		else if(this.getOwner() != -1 && this.getOwner()!=player.getID()){
			if(player.getDecision() && board.getPropertyOwner(getPosition()).getDecision()) {
				board.getPropertyOwner(getPosition()).getMoney().addMoney(getPrice());
				board.purchaseProperty(player, this.getPosition());
				this.setOwner(player);
				message = player.getName() + " -$" + getPrice() + "\n";
			}
		}
		else {
			  payRent(player, board);
		}
    }
	
	
	public void payRent(Player player, Board board) {
		int rent = 4 * board.roll();
		player.getMoney().sbustractMoney(rent);
		if(this.getOwner() != -1) {
			board.getPropertyOwner(getPosition()).getMoney().addMoney(rent);
		}
		message  = player.getName() + " -$" + rent + "\n";
	}
}

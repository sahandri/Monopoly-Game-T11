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
			case ELECTRICITY_COMPANY: return "Electricity Company";
			case WATER_WORKS: return "Water Works";
			default: return "Type not defined.";
		}
	}


	public void perform(Player player, Board board) {
		switch(this.utility){
		case ELECTRICITY_COMPANY:
			if(player.getDecision() == true){
				player.getMoney().sbustractMoney(150);
			}
			/**else if(this.utility is owned){
			 * payRent(player, board);
			 * }
			 */
			break;
		case WATER_WORKS:
			if(player.getDecision() == true){
				player.getMoney().sbustractMoney(150);
			}
			/**else if(this.utility is owned){
			 * payRent(player, board);
			 * }
			 */
			break;
		}
    }
	
	
	public void payRent(Player player, Board board) {
		player.getMoney().sbustractMoney(4 * board.roll());
	}
}

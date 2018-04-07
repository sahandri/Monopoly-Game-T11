package Model;
import java.util.HashMap;
import java.util.Map;

public class Corner extends Square{
	public enum corners{
		START,
		JAIL,
		FREE_PARKING,
		GO_TO_JAIL
	}
	private corners type;
	private boolean beginning;
	
	/* constructor for Corner object that takes the position of the corner (0|10|20|30) as argument*/
	public Corner(int price, int position){
		super(price,position);
		beginning = true;
		//assign corner type according to position
		switch(position){
			case 0: type=corners.START;
				break;
			case 10: type=corners.JAIL;
				break;
			case 20: type=corners.FREE_PARKING;
				break;
			case 30: type=corners.GO_TO_JAIL;
				break;
			default: type=corners.START;
		}
	}
	
	public String toString(){
		switch(this.type){
			case START: return "GO";
			case JAIL: return "Jail";
			case FREE_PARKING: return "Free Parking";
			case GO_TO_JAIL: return "Go To Jail";
			default: return "corner type not assigned";
		}
	}
	
	public corners getType(){
		return this.type;
	}

	@Override
	public void perform(Player player, Board board) {
		switch(this.type){
			case START:
				//the payment isn't collected on START at the beginning of the game.
				break;
			case JAIL:
				/*
				 * If an ordinary dice roll ends with the player's token on Jail, 
				 * they are "Just Visiting" and can move ahead on their next turn 
				 * WITHOUT incurring any penalty.
				 * 
				 * If a player is in jail, they do not take a normal turn and must 
				 * roll doubles on the dice or pay a fine of $50 to be released
				 */
				if(board.getToken(player).inJail()){
					int roll_1 = board.roll();
					int roll_2 = board.roll();
					if(roll_1 == roll_2){ board.getToken(player).release(); }								//release the token from jail state if the 2 numbers thrown are equal
					else{if(player.getMoney().money-50 >= 0){player.getMoney().sbustractMoney(50);}}		//if the player has enough money to pay for bail, subtract the money and release him
				}
				break;
			case FREE_PARKING:
				//do nothing
				break;
			case GO_TO_JAIL:
				board.getToken(player).move(10);		//move player's token to position 10 = jail
				board.getToken(player).imprison();	//set the token to jail state
				break;
		}
	}
}

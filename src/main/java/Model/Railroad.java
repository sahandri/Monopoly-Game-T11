/**
 * Four railroads, players collect $25 rent if they own one railroad;
 * $50 for two; $100 for three; $200 for all four.
 * the price of a railroad is $200
 *
 */
package Model;

public class Railroad extends Square{

	private String owner;

	public Railroad(int price, int position) {
		super(price, position);
		owner = "";
		if(!(position ==5 || position ==15 ||
				position ==25 || position ==35)){
			throw new IllegalArgumentException("Unknown position");
		}
	}


	@Override
	public void perform(Player player, Board board) {
		if(board.getPropertyOwner(this.getPosition())==player){ //if pLayer is the owner of the railroad
			// checks the number of railroads owned by owner
			//player collects rent
		}else if(player.getDecision()){//if player wants to buy property
			setOwner(player);
		}
	}

	//sets property owner and collects money from player
	//returns true if nobody ownes the property
	public boolean setOwner(Player player){
		if(owner==""){ // if nobody owes the railroad
			//player pays $200 and buy the property
			owner = player.getName();
			return true; //property successfully sold
		}
		return false; //the property is owned by somebody else and can't sell property
	}

	//returns the owner of railroad
	public String getOwner(){
		return owner;
	}

	public String toString(){
		return "Rail Road";
	}

}

/**
 * Four railroads, players collect $25 rent if they own one railroad;
 * $50 for two; $100 for three; $200 for all four.
 * the price of a railroad is $200
 *
 */
package Model;

public class Railroad extends Square{

	private int ID;

	public enum railroads{
		READING_RAILROAD,
		PENNSYLVANIA_RAILROAD,
		B_O_RAILROAD,
		SHORT_LINE
	}

	private railroads railroad;

	public Railroad(int price, int position) {
		super(price, position);
		try {
			setRailroad(position);
		}catch(IllegalArgumentException e){
			System.err.println("invalid position"+e.getMessage());
		}
	}

	public void setRailroad(int position) {
		if(position == 5) {
			railroad = railroads.READING_RAILROAD;
		}
		else if(position == 15) {
			railroad = railroads.PENNSYLVANIA_RAILROAD;
		}
		else if(position == 25) {
			railroad = railroads.B_O_RAILROAD;
		}
		else if(position == 35) {
			railroad = railroads.SHORT_LINE;
		}
		else {
			throw new IllegalArgumentException("Unknown position");
		}
	}
	
	public int getID(){
		return ID;
	}

	@Override
	public void perform(Player player, Board board) {
		if(this.getOwner() == -1 && player.getDecision()){ // if nobody owns the street and player wants to buy it
			//player pays price and buy the property
			board.purchaseProperty(player, this.getPosition());
			this.setOwner(player);
		}else if(this.getOwner() != -1 && this.getOwner()!=player.getID()){ //if street owns by somebody else
			int counter = numOfColorGroup(board); //gets the number of Railroads owned by a the owner
			switch (counter){
				case 1: player.getMoney().sbustractMoney(25);
					board.getPropertyOwner(getPosition()).getMoney().addMoney(25);
					break;
				case 2: player.getMoney().sbustractMoney(50);
					board.getPropertyOwner(getPosition()).getMoney().addMoney(50);
					break;
				case 3: player.getMoney().sbustractMoney(100);
					board.getPropertyOwner(getPosition()).getMoney().addMoney(100);
					break;
				case 4: player.getMoney().sbustractMoney(200);
					board.getPropertyOwner(getPosition()).getMoney().addMoney(200);
					break;
			}
			if(player.getDecision() && board.getPropertyOwner(getPosition()).getDecision()) {
				board.purchaseProperty(player, this.getPosition());
				this.setOwner(player);
			}
		}
	}
	
	//returns number of railroad owned by the same player(owner of this street)
	private int numOfColorGroup(Board board){
		int counter = 0;
		int[][] street = board.getStreetArray();
		for(int i=0; i<4; i++){
			//if(this.getOwner() == board.getPropertyOwner(street[this.getID()][i]).getID()){
			if(this.getOwner() == board.getSquares()[street[8][i]].getOwner()){
				counter++;
			}
		}
		return counter;
	}
	

	public String toString(){
		switch(this.railroad){
			case READING_RAILROAD: return " at Reading Railroad. \n";
			case PENNSYLVANIA_RAILROAD: return " at Pennsylvania Railroad. \n";
			case B_O_RAILROAD: return " at B. & O. Railroad. \n";
			case SHORT_LINE: return " at Short Line. \n";
			default: return "Type not defined.";
		}
	}
}

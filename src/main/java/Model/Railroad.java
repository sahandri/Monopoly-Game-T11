/**
 * Four railroads, players collect $25 rent if they own one railroad;
 * $50 for two; $100 for three; $200 for all four.
 * the price of a railroad is $200
 *
 */
package Model;

public class Railroad extends Square{

	private int ID;

	public Railroad(int price, int position) {
		super(price, position);
		ID = 8;
		if(!(position ==5 || position ==15 ||
				position ==25 || position ==35)){
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
					break;
				case 2: player.getMoney().sbustractMoney(50);
					break;
				case 3: player.getMoney().sbustractMoney(100);
					break;
				case 4: player.getMoney().sbustractMoney(200);
					break;
			}
		}
	}

	//returns number of railroad owned by the same player(owner of this street)
	public int numOfColorGroup(Board board){
		int counter = 0;
		int[][] street = board.getStreetArray();
		for(int i=0; i<street[this.getID()].length; i++){
			//if(this.getOwner() == board.getPropertyOwner(street[this.getID()][i]).getID()){
			if(this.getOwner() == board.getSquares()[street[this.getID()][i]].getOwner()){
				counter++;
			}
		}
		return counter;
	}

	public String toString(){
		return "Rail Road";
	}
}

/**
 * Four railroads, players collect $25 rent if they own one railroad;
 * $50 for two; $100 for three; $200 for all four.
 *
 */
package Model;

public class Railroad extends Square{

	public Railroad(int price, int position) {
		super(price, position);
		if(!(position ==5 || position ==15 ||
				position ==25 || position ==35)){
			throw new IllegalArgumentException("Unknown position");
		}
	}


	@Override
	public void perform(Player player, Board board) {
		if(board.getPropertyOwner(this.getPosition())==player){
			//player collects rent
		}else if(player.getDecision()){
			//player pays rent
		}else{
			
		}
		
	}

}

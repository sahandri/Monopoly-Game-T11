package Model;

public class Corner extends Square{
	public enum corners{
		START,
		JAIL,
		FREE_PARKING,
		GO_TO_JAIL
	}
	private corners type;
	
	/* constructor for Corner object that takes the position of the corner (0|10|20|30) as argument*/
	public Corner(int price, int position){
		super(price,position);
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
		// TODO Auto-generated method stub
		
	}
}

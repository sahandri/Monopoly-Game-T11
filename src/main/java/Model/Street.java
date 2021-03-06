/**
 * The Monopoly game-board consists twenty-two streets (grouped into eight color groups)
 * Twenty-two streets, divided into eight color groups of two or three streets;
 * a player must own all of a color group in order to build houses or hotels.
 * Once achieved, color group properties must be improved or "broken down" evenly.
 */
package Model;

public class Street extends Square{

    //different color groups of the streets
    public enum colors{BROWN, LIGHT_BLUE, PINK, ORANGE, RED,
                        YELLOW, GREEN, DARK_BLUE}

    private colors color;
    private int ID; //every color group has and ID
    private int rent;
    private int house; //number of houses on the street
    private int hotel; //number of hotels on the street
    private boolean mortgage;
    private String message = "";

	public Street(int price, int position, int rent) {
		super(price, position);
		house = 0;
		hotel = 0;
		mortgage = false;
		this.rent = rent;
		try {
            setColor(position);
        }catch(IllegalArgumentException e){
		    System.err.println("invalid position"+e.getMessage());
		}
	}

	//a helper method to initialize the streets with their group colors
	private void setColor(int position) {
        switch(position) {
            case 1:
            case 3:
                color = colors.BROWN;
                ID = 0;
                break;
            case 6:
            case 8:
            case 9:
                color = colors.LIGHT_BLUE;
                ID = 1;
                break;
            case 11:
            case 13:
            case 14:
                color = colors.PINK;
                ID = 2;
                break;
            case 16:
            case 18:
            case 19:
                color = colors.ORANGE;
                ID = 3;
                break;
            case 21:
            case 23:
            case 24:
                color = colors.RED;
                ID = 4;
                break;
            case 26:
            case 27:
            case 29:
                color = colors.YELLOW;
                ID = 5;
                break;
            case 31:
            case 32:
            case 34:
                color = colors.GREEN;
                ID = 6;
                break;
            case 37:
            case 39:
                color = colors.DARK_BLUE;
                ID = 7;
                break;
            default:// throw error if wrong position is specified
                throw new IllegalArgumentException("Unknown position");
        }
    }

    //returns the group color of the street
	public colors getColor(){
        return color;
    }

    //returns the group ID of the street
    public int getID(){
        return ID;
    }
    public int getHouse() {
    	return house;
    }
    
    public int getHotel() {
    	return hotel;
    }
    
    //if player owns all the color groups can buy a house
    public boolean buyHouse(Player player, Board board) {
    	if(house <=3) {
	    	if(checkColorOwnership(player, board)) {
	    		player.getMoney().sbustractMoney(200);
	    		house++;
	    		return true;
	    	}
    	}
    	return false;
    }
    
    
    //if player owns 4 houses can buy a hotel
    //------------????all properties or just this one
    public boolean buyHotel(Player player) {
    	if(hotel==0 && house==4) {
    		player.getMoney().sbustractMoney(200);
    		hotel++;
    		house = 0;
    		return true;
    	}
    	return false;
    }
    
    public boolean sellHouse(Player player) {
    	if(house > 0) {
    		player.getMoney().addMoney(100);
    		house--;
    		return true;
    	}
    	return false;
    }
    
    public boolean sellHotel(Player player) {
    	if(hotel > 0) {
    		player.getMoney().addMoney(100);
    		hotel--;
    		return true;
    	}
    	return false;
    }
    
    
    public boolean mortgage(Player player) {
    	if(this.getOwner()==player.getID() && house==0 && hotel==0 && mortgage==false) {
    		player.getMoney().addMoney(this.getPrice()/2);
    		mortgage=true;
    		return true;
    	}
    	return false;
    }
    
    public boolean unmortgage(Player player) {
    	if(this.getOwner()==player.getID() && mortgage==true) {
    		player.getMoney().sbustractMoney((this.getPrice()/2)+(this.getPrice()/10));
    		mortgage=false;
    		return true;
    	}
    	return false;
    }
    
    //if property has houses or hotels we call this method to calculate
    //the amount of rent
    private int calculateRent(Board board) {
    	int r =0;
    	if(mortgage==false) {
	    	if(house > 0) { //player owns house
	    		switch(house) {
	    		case 1: r=rent*5;
	    			break;
	    		case 2: r=rent*15;
	    			break;
	    		case 3: r=rent*45;
	    			break;
	    		case 4: r=rent*65;
	    			break;
	    		}
	    	}else if(hotel>0) { //player owns hotel
	    		r=rent*85;	
	    	}
	    	else {//player owns the street without house or hotel
	    		int counter = numOfColorGroup(board); //gets the number of streets in the same color owned by a the owner
		        switch (counter){
	                case 1: r=rent;
	                    break;
	                case 2: r=rent*2;
	                	break;
	                case 3: r=rent*4;
	                    break;
	            }
	    	}
    	}
    	return r;
    }
    

	@Override
	public void perform(Player player, Board board) {
	    if(this.getOwner() == -1 && player.getDecision()){ // if nobody owns the street and player wants to buy it
            //player pays price and buy the property
            board.purchaseProperty(player, this.getPosition());
            this.setOwner(player);
            message = player.getName() + " -$" + getPrice() + " \n"; // to be printed in history
        }else if(this.getOwner() != -1 && this.getOwner()!=player.getID()){ //if street owns by somebody else
            player.getMoney().sbustractMoney(calculateRent(board));//subtract the rent amount
            board.getPropertyOwner(getPosition()).getMoney().addMoney(calculateRent(board));//adds the rent amount to the owner
            message = player.getName() + " pays rent -$" + calculateRent(board) + " \n"; // to be printed in history 
	        if(player.getDecision() && board.getPropertyOwner(getPosition()).getDecision()) {//player buys the property from other player
				board.purchaseProperty(player, this.getPosition());
				this.setOwner(player);
				message = player.getName() + " -$" + getPrice() + " \n"; // to be printed in history
			}
        }
	}
	
	
	//checks if player owns all the color groups
	private boolean checkColorOwnership(Player player, Board board) {
		boolean allColors = true;
    	int[][] street = board.getStreetArray();
        for(int i=0; i<street[this.getID()].length; i++){
        	if(board.getSquares()[street[this.getID()][i]].getOwner()!=player.getID()){
                allColors = false;
            }
        }
        return allColors;
	}

	//returns number of same color groups owned by the same player(owner of this street)
	private int numOfColorGroup(Board board){
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
    	switch(getPosition()) {
        case 1: return " at Mediterranian Avenue. \n" + message;
        case 3: return " at Baltic Avenue. \n" + message;
        case 6: return " at Oriental Avenue. \n" + message;
        case 8: return " at Vermont Avenue. \n" + message;
        case 9: return " at Connecticut Avenue. \n" + message;
        case 11:return " at St. Charles Place. \n" + message;
        case 13:return " at States Avenue. \n" + message;
        case 14: return " at Virginia Avenue. \n" + message;
        case 16:return " at St. James Place. \n" + message;
        case 18:return " at Tennessee Avenue. \n" + message;
        case 19:return " at New York Avenue. \n" + message;
        case 21:return " at Kentucky Avenue. \n" + message;
        case 23:return " at Indiana Avenue. \n" + message;
        case 24:return " at Illinois Avenue. \n" + message;
        case 26:return " at Atlantic Avenue. \n" + message;
        case 27:return " at Ventnor Avenue. \n" + message;
        case 29:return " at Marvin Gardens. \n" + message;
        case 31:return " at Pacific Avenue. \n" + message;
        case 32:return " at North Carolina Avenue. \n" + message;
        case 34:return " at Pennsylvania Avenue. \n" + message;
        case 37:return " at Park Place. \n" + message;
        case 39:return " at Boardwalk. \n" + message;
        default:// throw error if wrong position is specified
            throw new IllegalArgumentException("Unknown position");
    }
    }
}
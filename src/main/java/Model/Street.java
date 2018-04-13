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
    //private int owner;

	public Street(int price, int position) {
		super(price, position);
		//owner = -1;
		try {
            setColor(position);
        }catch(IllegalArgumentException e){
		    System.err.println("invalid position"+e.getMessage());
		}
	}

	//a helper method to initialize the streets with their group colors
	public void setColor(int position) {
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

	@Override
	public void perform(Player player, Board board) {
	    if(this.getOwner() == -1 && player.getDecision()){ // if nobody owns the street and player wants to buy it
            //player pays price and buy the property
            board.purchaseProperty(player, this.getPosition());
            this.setOwner(player);
        }else if(this.getOwner() != -1 && this.getOwner()!=player.getID()){ //if street owns by somebody else
	        int counter = numOfColorGroup(board); //gets the number of streets in the same color owned by a the owner
	        switch (counter){
                case 1: //subtract the rent amount
                    break;
                case 2: //subtract the 2*rent amount
                    break;
                case 3: // subtract the 4*rent amount
                    break;
            }
        }
	}

	//returns number of same color groups owned by the same player(owner of this street)
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
        switch(this.color){
            case BROWN: return "Brown";
            case LIGHT_BLUE: return "Light Blue";
            case PINK: return "Pink";
            case ORANGE: return "Orange";
            case RED: return "Red";
            case YELLOW: return "Yellow";
            case GREEN: return "Green";
            case DARK_BLUE: return "Dark Blue";
            default: return "Type not defined.";
        }
    }
}
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
    private int owner;

	public Street(int price, int position) {
		super(price, position);
		owner = -1;
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
	    if(owner == -1 && player.getDecision()){ // if nobody owns the street and player wants to buy it
            //player pays price and buy the property
            player.getMoney().sbustractMoney(this.getPrice());
            setOwner(player);
        }else if(owner == -1 && owner!=player.getID()){ //if street owns by somebody else
	        //player pays rent
        }
	}

    //sets property owner
    private void setOwner(Player player){
	    owner = player.getID();
	}

    //returns owner of the street
    public int getOwner(){
	    return owner;
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
/*
    public static void main(String[] args){
	    Board board = new Board();
	    int[][] street = board.getStreet();
	    for(int i = 0; i<10; i++){
	        for(int j=0;j<street[i].length;j++){
	            System.out.print(street[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }
    */
}
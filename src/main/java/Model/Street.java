/**
 * The Monopoly game-board consists twenty-two streets (grouped into eight color groups)
 */
package Model;

import java.rmi.UnexpectedException;

public class Street extends Square{

    //different color groups of the streets
    public enum colors{BROWN, LIGHT_BLUE, PINK, ORANGE, RED,
                        YELLOW, GREEN, DARK_BLUE}

    private colors color;

	public Street(int price, int position) {
		super(price, position);
		try {
            initialize(position);
        }catch(IllegalArgumentException e){
		    System.err.println("invalid position"+e.getMessage());
		}
	}

	public void initialize(int position) {
        switch(position) {
            case 1:
            case 3:
                color = colors.BROWN;
                break;
            case 6:
            case 8:
            case 9:
                color = colors.LIGHT_BLUE;
                break;
            case 11:
            case 13:
            case 14:
                color = colors.PINK;
                break;
            case 16:
            case 18:
            case 19:
                color = colors.ORANGE;
                break;
            case 21:
            case 23:
            case 24:
                color = colors.RED;
                break;
            case 26:
            case 27:
            case 29:
                color = colors.YELLOW;
                break;
            case 31:
            case 32:
            case 34:
                color = colors.GREEN;
                break;
            case 37:
            case 39:
                color = colors.DARK_BLUE;
                break;
            default:
                throw new IllegalArgumentException("Unknown position");
        }
    }

	public colors getType(){
        return color;
    }

	@Override
	public void perform(Player player) {
		// TODO 
	}
	public static void main(String[] args){
		System.out.println("hello");
	}
}

package Model;

/**BUFFET TAX If you land here you have two options: You may
 estimate your tax at $200 and pay the Bank, or you may pay 10% of
 your total worth to the Bank. Your total worth is all your cash on
 hand, printed prices of mortgaged and unmortgaged properties and
 cost price of all buildings you own.
 You must decide which option you will take before you add up
 your total worth.**/
public class Tax extends Square{

	/** BUFFET Tax position on board 4, price is 200. 
	 * CORPORATE Tax position on board 38, price is 75.
	 */

	public enum taxes{
		BUFFET_TAX,
		CORPORATE_TAX
	}

	private taxes tax;

	public Tax(int price, int position) {
		super(price, position);
		try {
			setTax(position);
		}catch(IllegalArgumentException e){
			System.err.println("invalid position"+e.getMessage());
		}
	}

	public void setTax(int position) {
		if(position == 4) {
			tax = taxes.BUFFET_TAX;
		}
		else if(position == 38) {
			tax = taxes.CORPORATE_TAX;
		}
		else {
			throw new IllegalArgumentException("Unknown position");
		}
	}


	public String toString(){
		switch(this.tax){
			case BUFFET_TAX: return " at Income Tax Pay $200. \n";
			case CORPORATE_TAX: return " at Luxury Tax Pay $75. \n";
			default: return "Type not defined.";
		}
	}


	public void perform(Player player, Board board){
		switch(this.tax){
			case BUFFET_TAX:
				// Pay 200 or 10 percent of total worth.
				if(player.getDecision() != true){
					player.getMoney().sbustractMoney(200);
				}
				else{
					player.getMoney().sbustractMoney((int)tenPercent(player.getMoney().getMoney()));
				}
				break;
			case CORPORATE_TAX:
				// Pay 75 
				player.getMoney().sbustractMoney(75);
				break;
		}
	}

	public double tenPercent(double totalWorth){
		return totalWorth * 0.1;
	}

}

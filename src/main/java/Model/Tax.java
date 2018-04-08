package Model;

/**INCOME TAX If you land here you have two options: You may
estimate your tax at $200 and pay the Bank, or you may pay 10% of
your total worth to the Bank. Your total worth is all your cash on
hand, printed prices of mortgaged and unmortgaged properties and
cost price of all buildings you own.
You must decide which option you will take before you add up
your total worth.**/
public class Tax extends Square{

	/** Income Tax position on board 4, price is 200. 
	 * Luxury Tax position on board 38, price is 100.
	 * @param price
	 * @param position
	 */

	public enum taxes{
		INCOME_TAX,
		LUXURY_TAX
	}

	private taxes type;

	public Tax(int price, int position) {
		super(price, position);
		if(!(position == 4 || position == 38)) {
			throw new IllegalArgumentException("Incorrect Position");
		}
	}

	public String toString(){
		switch(this.type){
			case INCOME_TAX: return "Income Tax";
			case LUXURY_TAX: return "Luxury Tax";
			default: return "Type not defined.";
		}
	}


	public void perform(Player player, Board board){
		switch(this.type){
			case INCOME_TAX:
				// Pay 200 or 10 percent of total worth.
				if(player.decision == true){
					// Pay 200
				}
				else{
					//Pay 10% of total worth
				}
				break;
			case LUXURY_TAX:
				// Pay 100 or 10 percent of total worth.
				if(player.decision == true){
					// Pay 100
				}
				else{
					// Pay 10% of total worth.
				}
				break;
		}
	}

	public double tenPercent(double totalWorth){
		return totalWorth * 0.1;
	}

}

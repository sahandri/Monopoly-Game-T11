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
	 * @param price
	 * @param position
	 */

	public enum taxes{
		BUFFET_TAX,
		CORPORATE_TAX
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
			case BUFFET_TAX: return "Buffet Tax";
			case CORPORATE_TAX: return "Corporate Tax";
			default: return "Type not defined.";
		}
	}


	public void perform(Player player, Board board){
		switch(this.type){
			case BUFFET_TAX:
				// Pay 200 or 10 percent of total worth.
				if(player.getDecision() == true){
					player.getMoney().sbustractMoney(200);
				}
				else{
					player.getMoney().sbustractMoney((int)tenPercent(player.getMoney().money));
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

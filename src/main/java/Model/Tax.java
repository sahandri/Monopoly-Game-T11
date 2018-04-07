package Model;
/*
/**�INCOME TAX�� If you land here you have two options: You may
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
	Tax(int price, int position) {
		super(price, position);
		// TODO Auto-generated constructor stub
	}

	public void perform(Player player, Board board) {

	}

}

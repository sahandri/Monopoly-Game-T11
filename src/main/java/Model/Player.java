package Model;

public class Player {
	private String name;
	private int id;
	private Money money;
	/** The variable decision is a boolean value the player can set whenever a simple choice must be made,
	 * such as to pay the whole tax or 10% of total worth, to buy a property or not, etc. **/
	private boolean decision;
	private Token token;


	/**
	 * constructor that takes integer ID as argument,
	 * each player is given $1500 to start.
	 */
	public Player(int id){
		this.id = id;
		this.money = new Money(1500);
		this.token = new Token();
	}

	public Player(int id, Token token){
		this.id = id;
		this.money = new Money(1500);
		this.token = token;
	}

	public Money getMoney() {
		return this.money;
	}

	public int getID(){
		return this.id;
	}

	public boolean getDecision(){
		return this.decision;
	}

	public void setDecision(boolean choice){
		this.decision = choice;
	}

	public String getName(){
		return this.name;
	}

	public void setName(String playerName){
		this.name = playerName;
	}

	public Token getToken(){
		return this.token;
	}

	public void setToken(String name){
		this.token.setName(name);
	}

}

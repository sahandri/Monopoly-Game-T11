package Model;

// square is an abstract class which represents each individual square on the board. The class is extended by
// streets, utilities, railroads, cards(including chance card and community chest), taxes, and 4 corners. The
// class is marked as package-private

public abstract class Square{
    private int price;
    // position starts from 0 to 39. For instance, 0 represents to Go, 1 can represent street, etc
    private int position;
    private int owner;

    Square(int price, int position){
        this.price = price;
        this.position = position;
        this.owner = -1;
    }

    public int getPrice() {
        return price;
    }

    public int getPosition() {
        return position;
    }

    //sets property owner
    public boolean setOwner(Player player){
        if(owner==-1) {     //if nobody owns the street sets owner and returns true
            owner = player.getID();
            return true;
        }
        return false;
    }

    //returns owner of the street
    public int getOwner(){
        return owner;
    }
    
    public void sellProperty(Player buyer, Board board) {
		Player seller = board.getPropertyOwner(getPosition());
		if(seller.getDecision()) {
			buyer.getMoney().sbustractMoney(getPrice());
			seller.getMoney().addMoney(getPrice());
			// seller no longer owns property
			this.setOwner(buyer);
			board.purchaseProperty(buyer, getPosition());	
		}
		
	}
    
    public abstract void perform(Player player, Board board);
}

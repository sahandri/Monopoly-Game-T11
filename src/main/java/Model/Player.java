package Model;

public class Player {
    private String name;
    private int id;
    private Money money;
    
    /**
      * constructor that takes integer ID as argument
    */
    public Player(int id, Token tok){
        this.id = id;
    }

    public Money getMoney() {
        return money;
    }
    
    public int getID(){
    		return this.id;
    }
}

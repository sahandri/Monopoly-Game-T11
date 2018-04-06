package Model;

public class Token{
    private String Name;    //represents to the correspond img file name
    private int position =0;

//    The img file name is set at the beginning of the game, passed by user choice or randomly
//    selected if user does not choose token
    public void setName(String name) {
        Name = name;
    }
    
    /* this method is called when the token is moved and it updates the token position on the board*/
    public void move(int newPosition){
    		this.position = newPosition;
    }
    
    public int getPosition(){
    		return this.position;
    }

    public String getName() {
        return Name;
    }
}

package Model;

public class Token{
    // As each player owns a token, I don't think that position is necessary for token
//    The Name represents to the correspond img file name
    private String Name;

//    The img file name is set at the beginning of the game, passed by user choice or randomly
//    selected if user does not choose token
    public void setName(String name) {
        Name = name;
    }

    public String getName() {
        return Name;
    }
}

package Model;

// square is an abstract class which represents each individual square on the board. The class is extended by
// streets, utilities, railroads, cards(including chance card and community chest), taxes, and 4 corners. The
// class is marked as package-private

abstract class Square{
    private int price;
    // position starts from 0 to 39. For instance, 0 represents to Go, 1 can represent street, etc
    private int position;

    Square(int price, int position){
        this.price = price;
        this.position = position;
    }

    public int getPrice() {
        return price;
    }

    public int getPosition() {
        return position;
    }
    
    public abstract void perform(Player player);
}

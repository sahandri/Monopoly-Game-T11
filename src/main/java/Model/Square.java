package Model;

public abstract class Square{
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
}
package Model;

public class Money {
    int money;

    public Money(){
        this.money = 0;
    }

    Money(int money){
        this.money = money;
    }

    public void addMoney(int money){
        this.money += money;
    }

    public void sbustractMoney(int money){
        this.money -= money;
    }

    public int getMoney() {
        return money;
    }
}

package Model;

public class Money {
    private int money;

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
    	if((this.money-money) >=0)
    		this.money -= money;
    	else this.money = 0;
    }

    public int getMoney() {
        return money;
    }
}

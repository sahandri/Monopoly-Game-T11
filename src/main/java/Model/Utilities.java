package Model;

public class Utilities extends Square {

    /*
     2 utilities in Monopoly include water works and electricity company.
     Example:
     Mary's token is on Reading Railroad; on her turn, she rolls the dice and they come up with 7. Counting the
     spaces, she lands her token on Electric Company, which is owned by Sarah. The dice roll (7) is multiplied by
     4, for a total of 28; Mary now owes Sarah $28 rent.
    */
    public Utilities(int price, int position) {
        super(price, position);
    }
}

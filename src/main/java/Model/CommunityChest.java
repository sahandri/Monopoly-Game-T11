package Model;

public class CommunityChest extends Card{

	public CommunityChest(int price, int position) {
		super(price, position);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void perform(Player player, Board board) {

	}

	public String toString() {
				return " Community Chest! \n";
			}
}

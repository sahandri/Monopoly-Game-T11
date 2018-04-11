import View.MonopolyPanel;

import javax.swing.*;
import java.awt.*;

public class Monopoly {
    // monopoly is the Main class for this assignment, used to initialize a monopoly game.


    public static void main(String[] args) {
        // 2 long variables are used to check the game time(i.e., 15 mins or 20 mins), if time exceeded the
        // requirement, then game automatically ends, and whoever has the highest amount of money wins
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e){
            e.printStackTrace();
        }

        MonopolyPanel monopolyPanel = new MonopolyPanel();
        monopolyPanel.setVisible(true);
        monopolyPanel.setExtendedState(Frame.MAXIMIZED_BOTH);
    }
}

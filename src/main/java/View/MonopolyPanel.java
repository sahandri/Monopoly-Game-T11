package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MonopolyPanel extends JFrame implements ActionListener {
    private Container container = getContentPane();
    private JPanel jPanel[] = new JPanel[5];
    private BoardPanel boardPanel;

    public MonopolyPanel(){
        //        As menu is not needed for now, so the menu setup is left out
//        set up the layout for the container
        container.setLayout(new BorderLayout());

//        initialize JPanel with 4 sections of area
        for (int i = 0; i < 5; i++) {
            jPanel[i] = new JPanel();
        }

        boardPanel = new BoardPanel();
        jPanel[2].setLayout(new FlowLayout(FlowLayout.LEFT));
        jPanel[2].add(boardPanel);

        jPanel[0].setLayout(new BorderLayout());

    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}

package UI.main_interface.component;

import javax.swing.*;

public class Room extends JPanel {
    private JLabel backgroundLabel;

    public Room(){
        setLayout(null);
        setBounds(0, 0, 1175, 770);

        //phan viet code



        //
        ImageIcon backgroundImage = new ImageIcon(getClass().getResource("/images/background.png"));
        backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, getWidth(), getHeight());
        add(backgroundLabel);
    }
}

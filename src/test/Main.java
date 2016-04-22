package test;

import javax.swing.*;
import java.awt.*;

public class Main extends JPanel {
    public static void main(String[] args) {
        Game game = new Game();
        JFrame frame = game.getFrame();
        KeyListening keyListening = game.getKeyListening();
        Render render = game.getRender();

/*******************************стандарт******************************/
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setBounds(0, 0, 800, 600);
        frame.setResizable(false);
/*********************************************************************/

        frame.addKeyListener(keyListening);
        frame.setFocusable(true);
//        keyListening.setFrame(frame);

        render.readTxt();
        game.run();
    }
}

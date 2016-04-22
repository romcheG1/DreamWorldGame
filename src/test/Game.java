package test;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Game extends JPanel implements Runnable {
    private boolean running;
    protected int timeDelay = 17;
    protected JFrame frame;
    protected Render render;
    protected Thread thread;
    protected KeyListening keyListening;


    Game() {
        running = true;
        keyListening = new KeyListening();
        frame = new JFrame();
        render = new Render();
        thread = new Thread(this);
        new Thread(thread).start();
        frame.getContentPane().add(render);
    }

    @Override
    public void run() {
        while (running) {
            try {
                thread.sleep(timeDelay);

                update1();
            } catch (InterruptedException ex) {
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    protected void update1() {
        frame.repaint();
        keyListening.move();
        render.falling();

        render.correctPlayer();
    }

    public void stop() {
        running = false;
    }


    public JFrame getFrame() {
        return frame;
    }

    public Render getRender() {
        return render;
    }

    public KeyListening getKeyListening() {
        return keyListening;
    }
}




//
//package test;
//
//        import javax.swing.*;
//        import java.util.concurrent.TimeUnit;
//        import java.util.logging.Level;
//        import java.util.logging.Logger;
//
//public class Game extends JPanel implements Runnable {
//    private boolean running;
//    protected int timeDelay = 100;
//    private JFrame frame;
//
//    public void start() {
//        running = true;
//        new Thread(this).start();
//        JFrame frame = new JFrame();
//    }
//
//    public void stop() {
//        running = false;
//    }
//
//    @Override
//    public void run() {
//        while (running) {
//            try {
//                TimeUnit.MILLISECONDS.sleep(timeDelay);
//            } catch (InterruptedException ex) {
//                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null,ex);
//            }
//            update();
//        }
//
//    }
//
//    private void update() {
//        frame.repaint();
//
//    }
//
//    public JFrame getFrame() {
//        return frame;
//    }
//}

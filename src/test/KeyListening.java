package test;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyListening extends JFrame implements KeyListener {
    int dx, dy;                       //зміна координат плеєра по осям х і у
    int speed = 1;
    Render render = new Render();


    private boolean isUpPressed;
    private boolean isLeftPressed;
    private boolean isDownPressed;
    private boolean isRightPressed;
    protected boolean isPressed;
    private  boolean isSpacePressed;

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        isPressed = true;
        switch (e.getKeyChar()) {
            case 'w':
                isUpPressed = true;
                break;
            case 'a':
                isLeftPressed = true;
                break;
            case 's':
                isDownPressed = true;
                break;
            case 'd':
                isRightPressed = true;
                break;
            case ' ':
                isSpacePressed=true;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyChar()) {
            case 'w':
                isUpPressed = false;
                break;
            case 'a':
                isLeftPressed = false;
                break;
            case 's':
                isDownPressed = false;
                break;
            case 'd':
                isRightPressed = false;
                break;
            case ' ':
                isSpacePressed=false;
                break;
        }
        if(!isUpPressed&&!isLeftPressed&&!isDownPressed&&!isRightPressed&&!isSpacePressed)
            isPressed = false;
    }

    public void move() {
        dx = dy = 0;

        if (isUpPressed) dy = -speed;
        if (isDownPressed) dy = speed;
        if (isLeftPressed) dx = -speed;
        if (isRightPressed) dx = speed;



        if(isSpacePressed)

        {
            for(int i = 0 ; i<=2;i++)
            {
                render.isOnGround = false;
                dy=-(speed+i);

            }
        }

        render.changePlayerPos(dx, dy);






        }
    }



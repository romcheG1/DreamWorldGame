package test;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * Created by romcheG1 on 12.04.2016.
 */
public class Tile {
    // BufferedImage image = new BufferedImage(tileXleft,tileYup,);
    int tileXleft, tileYup;
    int tileXright, tileYdown;

    Image grass = new ImageIcon("assets\\tile-grass.png").getImage();

    Image ground2 = new ImageIcon("assets\\tile-earth2.png").getImage();
    Image ground = new ImageIcon("assets\\tile-ground.png").getImage();
    int SizeOfTile = 16;

    Image tileType;

    public Image getGround() {
        Random random = new Random();
        int k = random.nextInt(5);

        if (k > 1)
            return ground;
        else
            return ground2;
    }

    public int getTileXright(){return tileXleft+16;}
    public int getTileXleft(){return tileXleft;}
    public int getTileYup(){return tileYup;}
    public int getTileYdown(){return tileYup+16;}

}

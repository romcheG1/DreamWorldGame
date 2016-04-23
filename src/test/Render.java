package test;
import javax.swing.*;
import java.awt.*;
import java.io.FileReader;
import java.util.ArrayList;

public class Render extends JPanel {
    Physics physics = new Physics();
    Image sky = new ImageIcon("assets\\sky.png").getImage();
    Image player = new ImageIcon("assets\\player.png").getImage();
     ArrayList<Tile> tileArray = new ArrayList<>();
    int tileArrayIndex = 0;
    static int x = 0, y = 180;  //позиція плеєра
    int fallingSpeed =3 ;
    static boolean isOnGround = false;
    public void falling() {
        if (isOnGround) {
            fallingSpeed = 3;
        }else{
            y += fallingSpeed / 5;
            fallingSpeed++;
        }
    }

    public void changePlayerPos(int dx, int dy) {
        x += dx;
        y += dy;
    }

    public void readTxt() {
        try {
//          tileArray=new ArrayList<>();
            FileReader reader = new FileReader("assets\\map.txt");
            // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            Tile tile = new Tile();
//            tileArrayIndex = 0;
            int t;

            int StepX = 0, StepY = 0;

            while ((t = reader.read()) != -1) {
                if (tileArrayIndex > 1000)             //ліміт 1000 плит!!!!!!!!!!!!!!!_________
                    break;

                if (t == 52)//4    //alya enter
                {
                    StepY = StepY + tile.SizeOfTile;
                    StepX = 0;
                }

                if (t == 49)//1
                {

                    StepX = StepX + tile.SizeOfTile;

                }
                if (t == 50)//2
                {

                    Tile bufferTile = new Tile();
                    bufferTile.tileType = bufferTile.grass;
                    bufferTile.tileXleft = StepX;
                    bufferTile.tileYup = StepY;

                    tileArray.add(tileArrayIndex, bufferTile);

                    StepX = StepX + tile.SizeOfTile;
                    tileArrayIndex++;
                }
                if (t == 51)//3
                {
                    Tile bufferTile = new Tile();
                    bufferTile.tileType = bufferTile.getGround();
                    bufferTile.tileXleft = StepX;
                    bufferTile.tileYup = StepY;

                    tileArray.add(tileArrayIndex, bufferTile);

                    StepX = StepX + tile.SizeOfTile;
                    tileArrayIndex++;
                }
            }
        } catch (Exception ex) {
        }
    }


    /*koku tut rabotal =)))*/

    public ArrayList<Tile> getTileArray(){return  tileArray;} //poleznaya vesh

    public Image getPlayer()
    {
        return player;
    }

public void correctPlayer() {

    for (int i = 0; i < getTileArray().size(); i++) {
        if (getPlayerYdown() > getTileArray().get(i).getTileYup() && getPlayerYdown() < getTileArray().get(i).getTileYdown() + 1 &&
                getPlayerXleft() < getTileArray().get(i).getTileXright() && getPlayerXright() > getTileArray().get(i).getTileXleft()) {
            y -= getPlayerYdown() - getTileArray().get(i).getTileYup();
            isOnGround = true;
            break;
        } else {
            isOnGround = false;
        }
        if (getPlayerXright() > getTileArray().get(i).getTileXleft() && getPlayerXleft() + 20 < getTileArray().get(i).getTileXleft() + 16 &&      //перевірка стєнки зправа
                getPlayerYup() > getTileArray().get(i).getTileYup() - 2 && getPlayerYup() < getTileArray().get(i).getTileYup() + 16) {
            x = x - (20 - (getTileArray().get(i).getTileXleft() - getPlayerXleft()));
        }
        if (getPlayerXleft() < getTileArray().get(i).getTileXleft() + 16 && getPlayerXleft() > getTileArray().get(i).getTileXleft() &&  //зліва
                getPlayerYup() + 1 < getTileArray().get(i).getTileYup() + 16 && getPlayerYup() + 1 > getTileArray().get(i).getTileYup()) {
            x = x + (getTileArray().get(i).getTileXleft() + 16 - getPlayerXleft());
        }
    }
}
    public int getPlayerXleft(){return x;}
    public int getPlayerXright() {
        return x + player.getWidth(this);
    }
    public int getPlayerYup(){return y;}
    public int getPlayerYdown(){return y + player.getHeight(this);}

     /*koku tut zakonchil =)))*/

    public void paintComponent(Graphics g) {
        g.drawImage(sky, 0, 0, this);

        for (int i = 0; i < tileArray.size(); i++) {
            g.drawImage(tileArray.get(i).tileType, tileArray.get(i).tileXleft, tileArray.get(i).tileYup, null);
        }

        g.drawImage(player, x, y, null);

        g.drawString("Xl " + getPlayerXleft(), 10, 10);
        g.drawString("Yu " + getPlayerYup(), 10, 20);
    }

}


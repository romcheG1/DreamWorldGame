package test;
import javax.swing.*;
import java.awt.*;
import java.io.FileReader;
import java.util.ArrayList;

public class Render extends JPanel {
    Image sky = new ImageIcon("assets\\sky.png").getImage();
    Image player = new ImageIcon("assets\\kek.png").getImage();
     ArrayList<Tile> tileArray = new ArrayList<>();
    int tileArrayIndex = 0;
    static int x = 0, y = 200;  //позиція плеєра
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




//        for (int i = 0; tileArray.get(i).tileX < 800; i++) {
//            if (x >= tileArray.get(i).tileX && x <= tileArray.get(i).tileX + 15)
//                System.out.println(i);
////            for (int j = 0; j < tileArray.size(); i++) {
////                if(y>=tileArray.get(i).tileX&&x<=tileArray.get(i).tileX+15)
////
////            }
//        }
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
                    bufferTile.tileX = StepX;
                    bufferTile.tileY = StepY;

                    tileArray.add(tileArrayIndex, bufferTile);

                    StepX = StepX + tile.SizeOfTile;
                    tileArrayIndex++;
                }
                if (t == 51)//3
                {
                    Tile buffer = new Tile();
                    buffer.tileType = buffer.getGround();
                    buffer.tileX = StepX;
                    buffer.tileY = StepY;

                    tileArray.add(tileArrayIndex, buffer);

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
        if (getTileArray().get(i).tileY - getPlayerY() < 370 && getTileArray().get(i).tileY - getPlayerY() > 1 &&
                getTileArray().get(i).tileX > x - 16 && getTileArray().get(i).tileX < x + 20) {
            y = y - (370 - (getTileArray().get(i).tileY - getPlayerY()));
            isOnGround = true;
            //System.out.println("igrokY:" + getPlayerY() + "      " + "TileY:" + getTileArray().get(i).tileY);
            break;
        } else {
            isOnGround = false;
        }
        if (getPlayerX() + 20 > getTileArray().get(i).tileX && getPlayerX() + 20 < getTileArray().get(i).tileX + 16 &&      //перевірка стєнки зправа
                getPlayerY() > getTileArray().get(i).tileY - 2 && getPlayerY() < getTileArray().get(i).tileY + 16) {
            System.out.println(x);
            x = x -(20-(getTileArray().get(i).tileX - getPlayerX()));
        }
        if(getPlayerX() < getTileArray().get(i).tileX +16 && getPlayerX() > getTileArray().get(i).tileX + 16 &&  //зліва
                        getPlayerY() > getTileArray().get(i).tileY - 2 && getPlayerY() < getTileArray().get(i).tileY + 16){

        }
    }
}
    public int getPlayerX(){return x;}
    public int getPlayerY(){return y;}

     /*koku tut zakonchil =)))*/

    public void paintComponent(Graphics g) {
        g.drawImage(sky, 0, 0, this);

        for (int i = 0; i < tileArray.size(); i++) {
            g.drawImage(tileArray.get(i).tileType, tileArray.get(i).tileX, tileArray.get(i).tileY, null);
        }

        g.drawImage(player, x, y, null);
    }
}
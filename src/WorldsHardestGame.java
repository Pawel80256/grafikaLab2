import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
;

public class WorldsHardestGame extends JPanel implements KeyListener, ActionListener{

    Timer timer;
    private float timePassed=0;
    int deaths=0;
    int level=0;
    int score;
    Player player;
    ArrayList<BlueBall> blueBallArrayList = new ArrayList<BlueBall>();
    ArrayList<Integer> mapCoordinatesX = new ArrayList<Integer>();
    ArrayList<Integer> mapCoordinatesY = new ArrayList<Integer>();
    //level 2
    private Coin[] lvl2Coins = new Coin[3];
    boolean lvl2CoinsCollected=false;
    Line2D lvl2Line = new Line2D.Double(600,370,600,670);
    int rotation=0;
    Point lvl2lineCenter = new Point(), lvl2lineEdge, rotateLineX = new Point() ;
    //level3
    private Coin[] lvl3Coins = new Coin[7];
    boolean lvl3CoinsCollected=false;
    Rectangle lvl3Rectangle = new Rectangle();
    //level4
    private Coin[] lvl4CoinArray = new Coin[4];
    boolean lvl4CoinsCollected = false;
    private final Image image = new ImageIcon("logo.png").getImage();

    boolean borderCollision;
    boolean upCollision,rightCollision,downCollision,leftCollision;
    boolean chckRCollision =true;
    boolean chckLCollision =true;
    boolean chckUCollision =true;
    boolean chckDCollision =true;
    Rectangle2D upBorder,rightBorder,downBorder,leftBorder;


    public WorldsHardestGame() throws IOException {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(8,this);
        timer.start();
        loadLvl1();
        upBorder = new Rectangle();
        rightBorder = new Rectangle();
        downBorder = new Rectangle();
        leftBorder = new Rectangle();
        borderCollision=false;

    }

    public void printDeathsAndTime(Graphics g){
        g.setColor(Color.black);
        g.fillRect(0,700,1200,100);
        g.setColor(Color.white);
        g.setFont(new Font("Comic Sans MS",Font.BOLD,50));
        g.drawString("Deaths: "+deaths,20,750);
        g.drawString("Time: "+(int)timePassed,930,750);

    }

    public void loadLvl1()
    {
        mapCoordinatesX.clear();
        mapCoordinatesY.clear();
        blueBallArrayList.clear();

        player = new Player(230,300,20,20);
        player.setX(230); player.setY(300);
        blueBallArrayList.add( new BlueBall(370,245,30,30,6,1));
        blueBallArrayList.add( new BlueBall(780,290,30,30,6,-1));
        blueBallArrayList.add( new BlueBall(370,330,30,30,6,1));
        blueBallArrayList.add( new BlueBall(780,375,30,30,6,-1));

        Collections.addAll(mapCoordinatesX,200,200,430,430,830,830,860,860,980,980,780,780,350,350,320,320,200);
        Collections.addAll(mapCoordinatesY,200,450,450,420,420,240,240,460,460,210,210,240,240,420,420,200,200);


    }
    public void loadlvl2(){
        mapCoordinatesX.clear();
        mapCoordinatesY.clear();
        Collections.addAll(mapCoordinatesX,300,900,900,300,300,270,270,300,600,300,270,270,300,300);
        Collections.addAll(mapCoordinatesY,70,70,670,670,450,450,420,420,370,320,320,290,290,70);
        player = new Player(275,295,20,20);
        lvl2Coins[0] = new Coin(600,220,10,10);
        lvl2Coins[1] = new Coin(750,370,10,10);
        lvl2Coins[2] = new Coin(600,520,10,10);
        player.setX(275);
        player.setY(295);
    }
    public void loadlvl3(){
        mapCoordinatesX.clear();
        mapCoordinatesY.clear();
        Collections.addAll(mapCoordinatesX,100,100,140,140,840,840,880,880,840,840,140,140,100);
        Collections.addAll(mapCoordinatesY,380,420,420,520,520,420,420,380,380,280,280,380,380);
        player = new Player(110,390,20,20);
        lvl3Coins[0] = new Coin(160,300,10,10);
        lvl3Coins[1] = new Coin(260,490,10,10);
        lvl3Coins[2] = new Coin(360,300,10,10);
        lvl3Coins[3] = new Coin(460,500,10,10);
        lvl3Coins[4] = new Coin(560,300,10,10);
        lvl3Coins[5] = new Coin(660,500,10,10);
        lvl3Coins[6] = new Coin(760,300,10,10);
        lvl3Rectangle.setBounds(60,280,40,240);
    }
    public void loadlvl4(){
        mapCoordinatesX.clear();
        mapCoordinatesY.clear();
        Collections.addAll(mapCoordinatesX,240,560,560,620,620,940,940,620,620,560,560,240,240);
        Collections.addAll(mapCoordinatesY,520,520,560,560,520,520,280,280,240,240,280,280,520);
        player = new Player(570,530,20,20);
        blueBallArrayList.clear();
        blueBallArrayList.add(new BlueBall(260,290,35,35,5,1));
        blueBallArrayList.add(new BlueBall(300,480,35,35,5,-1));
        blueBallArrayList.add(new BlueBall(340,290,35,35,5,1));
        blueBallArrayList.add(new BlueBall(380,480,35,35,5,-1));
        blueBallArrayList.add(new BlueBall(420,290,35,35,5,1));
        blueBallArrayList.add(new BlueBall(460,480,35,35,5,-1));
        blueBallArrayList.add(new BlueBall(500,290,35,35,5,1));
        blueBallArrayList.add(new BlueBall(640,480,35,35,5,-1));
        blueBallArrayList.add(new BlueBall(680,290,35,35,5,1));
        blueBallArrayList.add(new BlueBall(720,480,35,35,5,-1));
        blueBallArrayList.add(new BlueBall(760,290,35,35,5,1));
        blueBallArrayList.add(new BlueBall(800,480,35,35,5,-1));
        blueBallArrayList.add(new BlueBall(840,290,35,35,5,1));
        blueBallArrayList.add(new BlueBall(890,480,35,35,5,-1));

        lvl4CoinArray[0] = new Coin(250,290,15,15);
        lvl4CoinArray[1] = new Coin(920,290,15,15);
        lvl4CoinArray[2] = new Coin(250,500,15,15);
        lvl4CoinArray[3] = new Coin(920,500,15,15);

    }

    public void renderPlayer(Graphics g){
        //czujniki parkowania
        upBorder = new Rectangle(player.getX()+3,player.getY(),15,2);
        rightBorder = new Rectangle(player.getX()+18,player.getY()+3,2,15);
        downBorder = new Rectangle(player.getX()+3,player.getY()+18,15,2);
        leftBorder = new Rectangle(player.getX(),player.getY()+3,2,15);
        //player rectangle
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.red);
        g2d.fillRect(player.getX(),player.getY(), player.getWidth(), player.getHeight());
        g2d.setColor(Color.BLUE);
        g2d.fillRect((int)upBorder.getX(),(int)upBorder.getY(),(int)upBorder.getWidth(),(int)upBorder.getHeight());
        g2d.fillRect((int)rightBorder.getX(),(int)rightBorder.getY(),(int)rightBorder.getWidth(),(int)rightBorder.getHeight());
        g2d.fillRect((int)downBorder.getX(),(int)downBorder.getY(),(int)downBorder.getWidth(),(int)downBorder.getHeight());
        g2d.fillRect((int)leftBorder.getX(),(int)leftBorder.getY(),(int)leftBorder.getWidth(),(int)leftBorder.getHeight());
    }

    public void renderBorders(){

        for(int i=0; i<mapCoordinatesX.size()-1;i++){
            Line2D mapBorder = new Line2D.Double(mapCoordinatesX.get(i),mapCoordinatesY.get(i),mapCoordinatesX.get(i+1),mapCoordinatesY.get(i+1));
            if(chckRCollision) rightCollision = mapBorder.intersects(rightBorder);
            if(chckUCollision) upCollision=mapBorder.intersects(upBorder);
            if(chckDCollision) downCollision=mapBorder.intersects(downBorder);
            if(chckLCollision) leftCollision=mapBorder.intersects(leftBorder);
            if(upCollision){ chckUCollision=false; }
            if(rightCollision){ chckRCollision=false; }
            if(downCollision){ chckDCollision=false; }
            if(leftCollision){ chckLCollision=false; }
            if(upCollision||rightCollision||downCollision||leftCollision){
                borderCollision=true;
                break;
            }
        }
    }


    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        int[] mapx = mapCoordinatesX.stream().mapToInt(i->i).toArray();
        int[] mapy = mapCoordinatesY.stream().mapToInt(i->i).toArray();
        //////////////////////////////////////////////////////// LEVEL -1 /////////////////////////////////////////////////////////////////////////////////////////////////////
        if(level==-1){
            g2d.setColor(new Color(0,204,204));
            g2d.fillRect(0,0,1200,800);
            g2d.setColor(Color.red);
            g2d.setFont(new Font("Comic Sans MS",Font.BOLD,100));
            g2d.drawString("Thanks for playing!", 100,120);
            g2d.drawString("Your score: "+score, 200,300);
            g2d.drawString("Deaths:"+ deaths,50,500);
            g2d.drawString("Time:"+ (int)timePassed,600,500);
            g2d.setFont(new Font("Comic Sans MS",Font.BOLD,90));
            g2d.drawString("Press enter to try again!",30,700);

        }
//////////////////////////////////////////////////////// LEVEL 0 /////////////////////////////////////////////////////////////////////////////////////////////////////
        if(level==0){
            g2d.setColor(new Color(0,204,204));
            g2d.fillRect(0,0,1200,800);
            g2d.setColor(Color.red);
            g2d.setFont(new Font("Comic Sans MS",Font.BOLD,100));
            g2d.drawString("World's hardest game", 50,120);
            g.drawImage(image,30,200,null);
            g2d.setFont(new Font("Comic Sans MS",Font.BOLD,60));
            g2d.drawString("Press enter to start",550,500);
            g2d.setColor(Color.black);
            g2d.setFont(new Font("Arial",Font.BOLD,25));
            g2d.drawString("Paweł Nowacki 80256",20,750);

        }
        ////////////////////////////////////////////////////////////////////////////////// LEVEL 1 ////////////////////////////////////////////////////////////////////
        if(level==1){
            g2d.setColor(new Color(77,77,77));g2d.setColor(new Color(102, 179, 255));
            g2d.fillRect(0,0,1200,800);

            //wypelnienie mapy
            Polygon map = new Polygon(mapx,mapy,mapx.length);
            g2d.setColor(new Color(51,51,51));
            g2d.fillPolygon(map);

            //spawn and meta
            g2d.setColor(new Color(0,153,51));
            Rectangle spawn = new Rectangle(200,200,120,220);
            g2d.fillRect(spawn.x,spawn.y,spawn.width,spawn.height);
            Rectangle meta = new Rectangle(860,240,120,220);
            g2d.fillRect(meta.x,meta.y,meta.width,meta.height);
            if(new Rectangle(player.getX(),player.getY(),player.getWidth(),player.getHeight()).intersects(meta)){
                loadlvl2();
                this.level=2;
                System.out.println("Meta!");
            }
            //player
            this.renderPlayer(g);
            //map borders
            g2d.setColor(Color.black);
            g2d.setStroke(new BasicStroke(3));
            g2d.drawPolyline(mapx,mapy,mapx.length);

            // reset stroke
            g2d.setStroke(new BasicStroke(2));

            //Blue balls
            g2d.setColor(new Color(0,102,204));
            for(BlueBall blueBall: blueBallArrayList){
                g2d.fillOval(blueBall.getX(),blueBall.getY(),blueBall.getWidth(),blueBall.getHeight());
            }

            g2d.setColor(new Color(0,26,128));
            for (BlueBall blueBall: blueBallArrayList){
                g2d.drawOval(blueBall.getX(),blueBall.getY(),blueBall.getWidth(),blueBall.getHeight());
            }

            //blue balls movement
            for(int i=0; i<mapCoordinatesX.size()-1;i++){
                Shape tempBorder = new Line2D.Double(mapCoordinatesX.get(i), mapCoordinatesY.get(i), mapCoordinatesX.get(i+1), mapCoordinatesY.get(i+1));
                Shape tempPlayer = new Rectangle2D.Double(player.getX(),player.getY(),player.getWidth(),player.getHeight());
                for(BlueBall blueBall: blueBallArrayList){
                    Shape tempBall = new Ellipse2D.Double(blueBall.getX(),blueBall.getY(),blueBall.getWidth(),blueBall.getHeight());
                    if(tempBorder.intersects(tempBall.getBounds())){
                        blueBall.setX(blueBall.getX()+((-blueBall.getDirection())*15));
                        blueBall.reverseDirection();
                    }
                    if(tempPlayer.intersects(tempBall.getBounds())){
                        player.setX(230); player.setY(300);
                        deaths++;
                    }
                }
            }
            //kontrola kolizji z granicami mapy
           this.renderBorders();
            this.printDeathsAndTime(g);

        }
        ////////////////////////////////////////////////////////////////////////////////// LEVEL 2 ////////////////////////////////////////////////////////////////////

        if(level==2){

            //tlo
            g2d.setColor(new Color(77,77,77));g2d.setColor(new Color(0,204,204));
            g2d.setStroke(new BasicStroke(3));
            g2d.fillRect(0,0,1200,800);

            //wypelnienie mapy
            Polygon map = new Polygon(mapx,mapy,mapx.length);
            g2d.setColor(new Color(51,51,51));
            g2d.fillPolygon(map);

            //kreska
            g2d.setColor(new Color(0,102,204));
            lvl2Line.setLine(lvl2lineCenter.getX(),lvl2lineCenter.getY(),rotateLineX.getX(),rotateLineX.getY());
            if(rotation==0) lvl2Line.setLine(600,370,600,670);
            g2d.draw(lvl2Line);

            //korekta
           g2d.setColor(new Color(0,204,204));
            g2d.fillRect(300,0,600,70);
            g2d.fillRect(900,100,100,600);

            //spawn
            g2d.setColor(new Color(0,153,51));
            Rectangle spawn = new Rectangle(270,290,30,30);
            g2d.fillRect(spawn.x,spawn.y, spawn.width, spawn.height);

            //meta
            Rectangle meta = new Rectangle(270,420,30,30);
            g2d.fillRect(meta.x,meta.y,meta.width,meta.height);
            if(new Rectangle(player.getX(),player.getY(),player.getWidth(),player.getHeight()).intersects(meta) && lvl2CoinsCollected){
                System.out.println("Meta!");
                loadlvl3();
                level=3;
            }
            //coins
            g2d.setColor(new Color(250, 250, 45, 255));
            for (Coin coin:lvl2Coins) {
                if(!coin.isCollected()){
                    g2d.fillOval(coin.getX(), coin.getY(),coin.getWidth(),coin.getHeight());
                }
            }
//
            //player
            this.renderPlayer(g);
            //granice mapy
            g2d.setColor(Color.black);
            g2d.drawPolyline(mapx,mapy,mapx.length);

            this.renderBorders();
            this.printDeathsAndTime(g);
        }
        ////////////////////////////////////////////////////////////////////////////////// LEVEL 3 ////////////////////////////////////////////////////////////////////
        if(level==3){
            //tlo
            g2d.setColor(new Color(77,77,77));g2d.setColor(new Color(0,204,204));
            g2d.setStroke(new BasicStroke(3));
            g2d.fillRect(0,0,1200,800);

            Polygon map = new Polygon(mapx,mapy,mapx.length);
            g2d.setColor(new Color(51,51,51));
            g2d.fillPolygon(map);

            //spawn i meta
            Rectangle spawn = new Rectangle(100,380,40,40);
            Rectangle meta = new Rectangle(840,380,40,40);
            g2d.setColor(new Color(0,153,51));
            g2d.fillRect(spawn.x,spawn.y, spawn.width, spawn.height);
            g2d.fillRect(meta.x,meta.y,meta.width,meta.height);
            //finish
            if(new Rectangle(player.getX(),player.getY(),player.getWidth(),player.getHeight()).intersects(meta) && lvl3CoinsCollected){
                loadlvl4();
                System.out.println("Meta!");
                level=4;
            }
            //blue rectangle
            g2d.setColor(new Color(0,102,204));
            g2d.fillRect(lvl3Rectangle.x,lvl3Rectangle.y,lvl3Rectangle.width, lvl3Rectangle.height);

            //player
            this.renderPlayer(g);


            //map borders
            g2d.setColor(Color.black);
            g2d.setStroke(new BasicStroke(3));
            g2d.drawPolyline(mapx,mapy,mapx.length);

            g2d.setColor(new Color(250, 250, 45, 255));
            for (Coin coin:lvl3Coins) {
                if(!coin.isCollected()){
                    g2d.fillOval(coin.getX(), coin.getY(),coin.getWidth(),coin.getHeight());
                }
            }
            this.renderBorders();
            this.printDeathsAndTime(g);
        }
        //////////////////////////////////////////////////////// LEVEL 4 /////////////////////////////////////////////////////////////////////////////////////////////////////
        if(level==4){
            //tlo
            g2d.setColor(new Color(77,77,77));g2d.setColor(new Color(0,204,204));
            g2d.setStroke(new BasicStroke(3));
            g2d.fillRect(0,0,1200,800);

            Polygon map = new Polygon(mapx,mapy,mapx.length);
            g2d.setColor(new Color(51,51,51));
            g2d.fillPolygon(map);

            //spawn i meta
            g2d.setColor(new Color(0,153,51));
            Rectangle spawn = new Rectangle(560,520,60,40);
            Rectangle meta = new Rectangle(560,240,60,40);
            g2d.fillRect(spawn.x,spawn.y,spawn.width,spawn.height);
            g2d.fillRect(meta.x, meta.y, meta.width, meta.height);
            if(new Rectangle(player.getX(),player.getY(),player.getWidth(),player.getHeight()).intersects(meta) && lvl4CoinsCollected){
                System.out.println("Zwycięstwo!");
                score=(int)(100000/(timePassed+(10*deaths)));
                level=-1;
            }

            //blue balls
            g2d.setColor(new Color(0,102,204));
            for(BlueBall blueball: blueBallArrayList){
                g2d.fillOval(blueball.getX(),blueball.getY(),blueball.getWidth(),blueball.getHeight());
            }
            g2d.setColor(new Color(0,26,128));
            for(BlueBall blueBall: blueBallArrayList){
                g2d.drawOval(blueBall.getX(),blueBall.getY(),blueBall.getWidth(),blueBall.getHeight());
            }
            //blue balls movement
            for(int i=0;i<mapCoordinatesX.size() - 1;i++){
                Shape tempBorder = new Line2D.Double(mapCoordinatesX.get(i), mapCoordinatesY.get(i), mapCoordinatesX.get(i+1), mapCoordinatesY.get(i+1));
                Shape tempPlayer = new Rectangle2D.Double(player.getX(),player.getY(),player.getWidth(),player.getHeight());
                for(BlueBall blueBall: blueBallArrayList){
                    Shape tempBall = new Ellipse2D.Double(blueBall.getX(),blueBall.getY(),blueBall.getWidth(),blueBall.getHeight());
                    if(tempBorder.intersects(tempBall.getBounds())){
                        blueBall.setY(blueBall.getY()+((-blueBall.getDirection())*15));
                        blueBall.reverseDirection();
                    }
                    if(tempPlayer.intersects(tempBall.getBounds())){
                        player.setX(570);
                        player.setY(530);
                        for(Coin coin: lvl4CoinArray) coin.setCollected(false);
                        deaths++;
                    }
                }



            }

            g2d.setColor(Color.BLACK);
            g2d.drawPolyline(mapx,mapy,mapx.length);

            g2d.setColor(new Color(250, 250, 45, 255));
            for (Coin coin:lvl4CoinArray) {
                if(!coin.isCollected()){
                    g2d.fillOval(coin.getX(), coin.getY(),coin.getWidth(),coin.getHeight());
                }
            }
            this.renderPlayer(g);
            this.renderBorders();
            this.printDeathsAndTime(g);

        }
    }





    @Override
    public void actionPerformed(ActionEvent e) {

        if(level!=0 && level!=-1){
            timePassed+=0.016;
        }
        if(!leftCollision&&!rightCollision&&!upCollision&&!downCollision)
        {
            player.tick();
        }

        if(level==1){

            for(BlueBall blueBall: blueBallArrayList){
                blueBall.tick();
                if(blueBallArrayList.indexOf(blueBall)%2==0){
                    blueBall.moveRight();
                }
                else blueBall.moveLeft();
            }
        }

        if(level==2){

        if(player.getX()>305){
          rotation=1;
        }

            lvl2lineCenter = new Point((int)lvl2Line.getX1(),(int)lvl2Line.getY1());
            lvl2lineEdge = new Point((int)lvl2Line.getX2(),(int)lvl2Line.getY2());
            rotateLineX = rotateLineClockWise(lvl2lineCenter,lvl2lineEdge,rotation);

            if(lvl2Line.intersects(new Rectangle(player.getX(),player.getY(),player.getWidth(),player.getHeight()))){
                player.setX(275);
                player.setY(295);
                rotation=0;
                for(Coin coin: lvl2Coins) coin.setCollected(false);
                deaths++;
            }
            lvl2CoinsCollected=true;
            for (Coin coin:lvl2Coins) {
                Rectangle coinTemp = new Rectangle(coin.getX(),coin.getY(),coin.getWidth(),coin.getHeight());
                Rectangle playerRect = new Rectangle(player.getX(),player.getY(),player.getWidth(),player.getHeight());
                if(coinTemp.intersects(playerRect)) coin.setCollected(true);
                if(!coin.isCollected()) lvl2CoinsCollected=false;
            }
            if(player.getX()<=295 && player.getY()<=320){
                rotation=0;
            }
        }

        if(level==3){
            lvl3CoinsCollected=true;
            for (Coin coin:lvl3Coins) {
                Rectangle coinTemp = new Rectangle(coin.getX(),coin.getY(),coin.getWidth(),coin.getHeight());
                Rectangle playerRect = new Rectangle(player.getX(),player.getY(),player.getWidth(),player.getHeight());
                if(coinTemp.intersects(playerRect)) coin.setCollected(true);
                if(!coin.isCollected()) lvl3CoinsCollected=false;
            }

            if(player.getX()> 140)
            lvl3Rectangle.setBounds(lvl3Rectangle.x+1,lvl3Rectangle.y,lvl3Rectangle.width,lvl3Rectangle.height);
            if(lvl3Rectangle.intersects(new Rectangle(player.getX(),player.getY(),player.getWidth(),player.getHeight()))){
                for(Coin coin: lvl3Coins) coin.setCollected(false);
                player.setX(110);
                player.setY(390);
                lvl3Rectangle.setBounds(30,280,40,240);
                deaths++;
            }
        }
        if(level==4){

            for(BlueBall blueBall: blueBallArrayList){
                blueBall.tick();
                if(blueBallArrayList.indexOf(blueBall)%2==0){
                    blueBall.moveUp();
                }
                else blueBall.moveDown();
            }
            lvl4CoinsCollected=true;
            for (Coin coin:lvl4CoinArray) {
                Rectangle coinTemp = new Rectangle(coin.getX(),coin.getY(),coin.getWidth(),coin.getHeight());
                Rectangle playerRect = new Rectangle(player.getX(),player.getY(),player.getWidth(),player.getHeight());
                if(coinTemp.intersects(playerRect)) coin.setCollected(true);
                if(!coin.isCollected()) lvl4CoinsCollected=false;
            }
        }


        repaint();

    }


    @Override
    public void keyTyped(KeyEvent e) {


    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (level == 0) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) level = 1;
        }
        if (level == -1) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                deaths=0;
                score = 0;
                timePassed=0;
                loadLvl1();
                level = 1;
            }
        }

        if (level != 0 && level != -1) {
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                if (!rightCollision) player.moveRight();
                if (leftCollision) {
                    leftCollision = false;
                    chckLCollision = true;
                    player.moveRight();
                    //timer.start();
                }
            }


            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                if ((!leftCollision && !downCollision) || (!leftCollision && !upCollision)) player.moveLeft();
                if (rightCollision) {
                    rightCollision = false;
                    chckRCollision = true;
                    player.moveLeft();
                    // timer.start();
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                if (!upCollision) player.moveUp();
                if (downCollision) {
                    downCollision = false;
                    chckDCollision = true;
                    player.moveUp();
                    //  timer.start();
                }
            }


            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                if (!downCollision) player.moveDown();
                if (upCollision) {
                    upCollision = false;
                    chckUCollision = true;
                    player.moveDown();
                    // timer.start();
                }
            }
        }
    }






    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            player.setVelX(0);
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            player.setVelX(0);
        }
        if(e.getKeyCode() == KeyEvent.VK_UP){
            player.setVelY(0);
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN){
            player.setVelY(0);
        }
    }

    static Point rotateLineClockWise(Point center, Point edge, int angle) {
        double xRot = (int) center.x + Math.cos(Math.toRadians(angle)) * (edge.x - center.x) - Math.sin(Math.toRadians(angle)) * (edge.y - center.y);
        double yRot = (int) center.y + Math.sin(Math.toRadians(angle)) * (edge.x - center.x) + Math.cos(Math.toRadians(angle)) * (edge.y - center.y);
        return new Point((int) xRot, (int) yRot);
    }
}

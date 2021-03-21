public class Player {
    private int width =20;
    private int height =20;
    private int x;
    private int y;
    private double velX=0;
    private double velY=0;


    public Player(int playerPosX, int playerPosY, int playerWidth, int playerHeight){
        this.x =playerPosX;
        this.y =playerPosY;
        this.width =playerWidth;
        this.height =playerHeight;


    }

    public int getWidth() { return width; }
    public void setWidth(int width) { this.width = width; }
    public int getHeight() { return height; }
    public void setHeight(int height) { this.height = height; }
    public int getX() { return x; }
    public void setX(int x) { this.x = x; }
    public int getY() { return y; }
    public void setY(int y) { this.y = y; }
    public double getVelX() { return velX; }
    public void setVelX(double velX) { this.velX = velX; }
    public double getVelY() { return velY; }
    public void setVelY(double velY) { this.velY = velY; }

    public void tick(){
      x +=velX;
      y +=velY;
    }

    public void moveRight(){

        setVelX(2.5);

    }
    public void moveLeft(){
        setVelX(-2.5);

    }
    public void moveUp(){
        setVelY(-2.5);

    }
    public void moveDown(){
        setVelY(2.5);

    }
}

public class BlueBall {
    int width;
    int height;
    int x;
    int y;
    int direction;
    double speed;
    private double velX=0;
    private double velY=0;


    public BlueBall(int blueBallX, int blueBallY, int blueBallWidth, int blueBallHeight,double speed,int direction) {
        this.width = blueBallWidth;
        this.height = blueBallHeight;
        this.x = blueBallX;
        this.y = blueBallY;
        this.speed=speed;
        this.direction=direction;
    }
    public int getDirection() { return direction; }
    public void setDirection(int direction) { this.direction = direction; }
    public double getSpeed() { return speed; }
    public void setSpeed(int speed) { this.speed = speed; }
    public int getWidth() {
        return width;
    }
    public void setWidth(int width) {
        this.width = width;
    }
    public int getHeight() {
        return height;
    }
    public void setHeight(int height) {
        this.height = height;
    }
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }

    public double getVelX() { return velX; }
    public void setVelX(double velX) { this.velX = velX; }

    public double getVelY() {
        return velY;
    }

    public void setVelY(double velY) {
        this.velY = velY;
    }

    public void tick(){
       x+=velX;
       y+=velY;
    }
    public void reverseDirection(){
        this.direction = -this.direction;
    }
    public void moveRight(){
        setVelX(direction*speed);
    }
    public void moveLeft(){
        setVelX(direction*speed);
    }
    public void moveUp(){
        setVelY(direction*speed);
    }
    public void moveDown(){
        setVelY(direction*speed);
    }
}

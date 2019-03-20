public class Step {
    private int x;
    private int y;
    private boolean isMark;

    public Step(int x, int y){
        this.x = x;
        this.y = y;
        isMark = false;
    }

    //Add constructor with matrice

    public void mark(){
        isMark = true;
    }

    public boolean isMarked(){
        return isMark;
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
}

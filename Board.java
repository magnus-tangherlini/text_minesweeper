public class Board{

    int bombsAround;
    boolean isItBomb;
    boolean showing;

    public Board(int bombsAround, boolean isItBomb, boolean showing){
        this.bombsAround = bombsAround;
        this.isItBomb = isItBomb;
        this.showing = showing;
    }

    public int getBombsAround(){
        return bombsAround;
    }

    public boolean getIsItBomb(){
        return isItBomb;
    }   

    public boolean isItShowing(){
        return showing;
    }

    public void changeShowing(boolean newShowing){
        this.showing = newShowing;
        
    }


}
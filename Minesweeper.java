import java.util.ArrayList;
public class Minesweeper{
  
  
  int boardSize = 10;
  Board[][] field = new Board[boardSize][boardSize];
  int totalBombs = 20;
  boolean beginning = false;
  
  
  public Minesweeper(int r, int c){
    initializeBoard(r, c);
  }
  
  public boolean winCondition(){
    int remainingTiles = 0;
    for (int row = 0; row < field.length; row++){
      for (int col = 0; col < field[row].length; col++){
        if (field[row][col].isItShowing() == false){
          remainingTiles++;
        }
      }
    }
    return remainingTiles == totalBombs;
  }
  
  public void hack(){
    for (int row = 0; row < field.length; row++){
      for (int col = 0; col < field[row].length; col++){
        if (field[row][col].getBombsAround() > -1){
          field[row][col].changeShowing(true);
        }
      }
    }
  }
  
  public boolean getIfBomb(int r, int c){
    return field[r][c].getIsItBomb();
  }
  
  public void changeBeginning(boolean b){
    this.beginning = b;
  }
  
  public void initializeBoard(int pointRow, int pointCol){
    for (int r = 0; r < field.length; r++){
      for (int c = 0; c < field[r].length; c++){
        field[r][c] = new Board(0, false, beginning);
      }
    }

    int replicateBombs = totalBombs;
    ArrayList<Point> radius = collectRadius(pointRow, pointCol);
    while (replicateBombs != 0){
      int randomOne = (int)(Math.random() * boardSize);
      int randomTwo = (int)(Math.random() * boardSize);
      boolean isItTouching = isThisInRadius(radius, randomOne, randomTwo);

      
      while (field[randomOne][randomTwo].getIsItBomb() == true || isItTouching == true|| (randomOne == pointRow && randomTwo == pointCol)){
        randomOne = (int)(Math.random() * boardSize);
        randomTwo = (int)(Math.random() * boardSize);
        isItTouching = isThisInRadius(radius, randomOne, randomTwo);
      }
      
      field[randomOne][randomTwo] = new Board(-1, true, beginning);
      replicateBombs--;
    }
    
    for (int row = 0; row < field.length; row++){
      for (int col = 0; col < field[row].length; col++){
        if (field[row][col].getIsItBomb() == false){
          field[row][col] = new Board(getBombs(row, col), false, beginning);
        }
      }
    }
    
  }

  public boolean isThisInRadius(ArrayList<Point> radius, int r, int c){
    boolean isItTouching = false;

    for (int x = 0; x < radius.size();x++){
      isItTouching = doesThisContainRadius(r, c, radius.get(x));
      if(isItTouching){
        return true;
      }
    }
    return false;
  }

  public boolean doesThisContainRadius(int r, int c, Point p){
    if (p.getRow() == r && p.getCol() == c){
      return true;
    }
    return false;
  }

  
  public void showTiles(int r, int c){
    
    
    field[r][c].changeShowing(true);
    if (field[r][c].getBombsAround()==0){
      ArrayList<Point> reveal = collectRadius(r, c);
      for (Point p : reveal){
        int gRow = p.getRow();
        int gCol = p.getCol();
        if (field[gRow][gCol].isItShowing() != true){
          showTiles(gRow, gCol);
          
        }
      }
      
    }
    else if (field[r][c].getIsItBomb()){
      for (int row = 0; row < field.length; row++){
        for (int col = 0; col < field[row].length; col++){
          field[row][col].changeShowing(true);
        }
      }
    }
    
    
    
  }
  
  public int getBombs(int r, int c){
    
    ArrayList<Point> bombs = collectRadius(r, c);
    int counter = 0;
    for (int x = 0; x < bombs.size(); x++){
      int row = bombs.get(x).getRow();
      int col = bombs.get(x).getCol();
      if (field[row][col].getIsItBomb() == true){
        counter++;
      }
    }
    return counter;
  }
  
  public ArrayList<Point> collectRadius(int r, int c){
    ArrayList<Point> pointsAround = new ArrayList<Point>();
    
    if (r!= boardSize-1){
      pointsAround.add(new Point(r+1, c));
    }
    if (c!= boardSize-1){
      pointsAround.add(new Point(r, c+1)) ;
      if(r!= boardSize -1){
        pointsAround.add(new Point(r+1, c+1));
        
      }
    }
    if (r != 0){
      
      pointsAround.add(new Point(r-1, c));
    }
    if (c!=0){
      pointsAround.add(new Point(r, c-1));
      if (r!= 0){
        pointsAround.add(new Point(r-1, c-1));
      }
    }
    
    if (r!= 0 && c!= boardSize -1){
      pointsAround.add(new Point(r-1, c+1));
      
    }
    if (c!= 0 && r!= boardSize -1){
      
      pointsAround.add(new Point(r+1, c-1));
    }
    return pointsAround;
  }
  
  public void printBoard(){
    for (int row = 0; row < field.length; row++){
      System.out.print("|");
      for (int col = 0; col < field[row].length; col++){
        if (field[row][col].isItShowing() == true){
          if (field[row][col].getBombsAround()==-1){
            System.out.print(field[row][col].getBombsAround() + "|");
          }
          else{
            System.out.print(" "+field[row][col].getBombsAround() + "|");
          }
        }
        else{
          System.out.print("  " + "|");
        }
        
      }
      System.out.println("");
    }
  }

  
  
}

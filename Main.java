import java.util.Scanner;
public class Main{

    public static void main(String[] args){

        Scanner firstTurnRow = new Scanner(System.in);
        Scanner firstTurnCol = new Scanner(System.in);

        
        System.out.println("enter row");
        int firstRow = firstTurnRow.nextInt()-1;

        System.out.println("enter column");
        int firstCol = firstTurnCol.nextInt()-1;
        Minesweeper game = new Minesweeper(firstRow, firstCol);

        game.showTiles(firstRow, firstCol);
        game.printBoard();
        System.out.println("");

        boolean isItWon = game.winCondition();
        boolean endGame = false;

        while (!endGame && !isItWon){
            Scanner myObj = new Scanner(System.in);
            Scanner newObj = new Scanner(System.in);

            System.out.println("enter row");
            int row = myObj.nextInt()-1;

            System.out.println("enter column");
            int col = newObj.nextInt()-1;
            //game.hack();
            
            //if you are going to use hack, make sure you comment out the system out prints above, the showTiles and the endgame.
            game.showTiles(row, col);
            game.printBoard();
            endGame = game.getIfBomb(row, col);
            isItWon = game.winCondition();
        }
        if (endGame){
            System.out.println("You blew up");
        }
        else{
            System.out.println("You won!");
        }
        
        
        
        
    }
}
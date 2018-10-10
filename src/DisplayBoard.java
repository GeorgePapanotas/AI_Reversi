import java.util.ArrayList;
import java.util.Scanner;

public class DisplayBoard implements Moves{

    static int scoreW,scoreB;
    private static Board b;
    private static final int ALLTILES = 64;
    private static ArrayList<MoveCoord> listOfMoves;

    public static void main(String [] args){
        b = new Board();
        ScoreCounter(b);
        int a = 1;
        int player = 1;

        displayAvailableMoves(player);
        while(scoreB + scoreW <= ALLTILES){

            MoveCoord move = getMoves(player);

            if(RayTest(move,b,player,true)){
//                b.updateBoard(player,move.getRow(),move.getCol());
//                b.display();
                listOfMoves = displayAvailableMoves(player);
                ScoreCounter(b);
                System.out.println("White: "+scoreW+"   Black: "+scoreB);
                if(player == 1){
                    player = 2;
                }else{
                    player = 1;
                }
            }else{
                System.out.println("Select valid");
            }
        }
        b.display();
    }

    private static Moves.MoveCoord getMoves(int player){
        Scanner s = new Scanner(System.in);
        System.out.println("Player "+player+" make your move ( "+(player == 1?"W ).":"B )."));
        int row, col;
        String in = s.next();

        while(!in.matches("[ABCDEFGH][12345678]") && !in.matches("[abcedfgh][12345678]")){
            System.out.println("Dude make a valid input (A1)");
            in = s.next();
        }
        col = (int) (Character.toUpperCase(in.charAt(0)) - 'A');
        row = Integer.parseInt(String.valueOf(in.charAt(1))) - 1;

        return new Moves.MoveCoord(row,col);
    }

    private static boolean RayTest(Moves.MoveCoord m,Board b,int player,boolean flip){
        char opponent;
        char self;
        if(player == 1){
            opponent = 'B';
            self = 'W';
        }else{
            opponent = 'W';
            self = 'B';
        }

        int x = m.getRow();
        int y = m.getCol();
        char token = b.goToCell(x,y);
        if(token!='-'&&token!='O'){
            return false;
        }
        boolean found = false;
        for(int i = -1; i <= 1; i++){
            for(int j = -1; j <= 1; j++){
                if(!(j==0 && i==0)){
                    x=m.getRow()+i;
                    y=m.getCol()+j;
                    char current = b.goToCell(x,y);
                    if(current == opponent){
                        while(true) {
                            x=x+i;
                            y=y+j;
                            current = b.goToCell(x,y);
                            if(current == self){
                                found = true;
                                if(flip) flip(i,j,x,y,m,player);
                                break;
                            }else if(current != opponent) break;
                        }
                    }
                }
            }
        }
        return found;
    }

    private static void flip(int i, int j, int xPos, int yPos, MoveCoord moveCoord, int player){
        while(xPos != moveCoord.getRow() || yPos != moveCoord.getCol()){
            xPos = xPos - i;
            yPos = yPos - j;
            b.updateBoard(player,xPos,yPos);
        }
    }

    private static ArrayList<MoveCoord> displayAvailableMoves(int player){
//        char playerToken = (player==1)? 'W':'B';
        ArrayList<MoveCoord> listOfMoves = new ArrayList<>();
        for(int i=1;i<8;i++){
            for(int j=1;j<8;j++){
                char current = b.goToCell(i,j);
                if(!(current=='W'||current=='B')){
                    if(RayTest(new MoveCoord(i,j),b,player,false)){
                        if(current != 'O') b.updateEmpty(i,j,'O');
                        listOfMoves.add(new MoveCoord(i,j));
                    }else{
                        if(current != '-') b.updateEmpty(i,j,'-');
                    }
                }
            }
        }
        b.display();
        return listOfMoves;
    }

    //east                  j++
    //west                  j--
    //north                 i--
    //south                 i++
    //south east            i++ j++
    //south west            i++ j--
    //north east            i-- j++
    //north west            i-- j--

    public static void ScoreCounter(Board b){
        scoreW =0;
        scoreB = 0;
        for(int i = 0;i<=7;i++){
            for(int j = 0;j<=7;j++){
                if(b.goToCell(i,j) == 'B'){
                    scoreB++;
                }else if(b.goToCell(i,j) == 'W'){
                    scoreW++;
                }

            }
        }
    }
}

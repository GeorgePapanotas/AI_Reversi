import java.util.ArrayList;
import java.util.Scanner;

//test game D3 E3 F4 G3 F3 C5 H3 F2 C4 C3 E2 E1 B3 H4 H5 A3
//test game E6F4C3C4D3D6C5C6D7B5B6F7A6A5B4A7F3C8E8C7F6E7G8G6F8F5G4E3D2H3G5G3H4H5H7D8B8A4B3D1C1B1C2E1E2A3B7F2F1G1B2A2G2H6H2G7
//test game F5D6C5F6C4F4E6D7E7C6F7D8C8E8G5B8E3F8B6B5A6A4A5A7C7B4G6H6H7B3D3C3C2C1H5D2E2F1B1D1B2A3E1H4H3G4G3F3G1F2B7H2H1G7
public class DisplayBoard implements Moves{

    static int scoreW,scoreB;
    private static Board b;
    private static final int ALLTILES = 64;
    private static ArrayList<MoveCoord> listOfMoves;
    private static int player,user;
    private static String transcript = "E6F4C3C4D3D6C5C6D7B5B6F7A6A5B4A7F3C8E8C7F6E7G8G6F8F5G4E3D2H3G5G3H4H5H7D8B8A4B3D1C1B1C2E1E2A3B7F2F1G1B2A2G2H6H2G7 ";
    private static int letter = 0,number=2;
    public static void main(String [] args){
        b = new Board();
        ScoreCounter(b);
        Scanner s = new Scanner(System.in);
        System.out.print("Please select your turn (1,2): ");

        int k;

        player = s.nextInt();
        while(player!=1 && player!=2){
            System.out.print("Please select 1 or 2 as turns: r");
            player = s.nextInt();
        }
        user = player;

        listOfMoves = displayAvailableMoves(player);
        while(scoreB + scoreW <= ALLTILES){
            if(player == user){
                k = playerTurn();
                if(k == -1){
                    break;
                }
            }else{
                k = CPUturn();
                if(k == -1){
                    break;
                }
            }

        }
        b.display();
        if(scoreB>scoreW){
            System.out.println("Player 1 (B) is the winner with "+(64 - scoreW)+" over "+scoreW);
        }else if(scoreB<scoreW){
            System.out.println("Player 2 (W) is the winner with "+(64 - scoreB)+" over "+scoreB);
        }else{
            System.out.println("The game is draw! 32 - 32");
        }
    }

    private static Moves.MoveCoord getMoves(int player){
        Scanner s = new Scanner(System.in);
        System.out.println("Player "+player+" make your move ( "+(player == 1?"B ).":"W )."));
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

    private static Moves.MoveCoord getTranscript(){
        String s = transcript.substring(letter,number);
        letter+=2;
        number+=2;
        System.out.println("Transcript move for player "+player+" : "+s);
        int row, col;

        col = (int) (Character.toUpperCase(s.charAt(0)) - 'A');
        row = Integer.parseInt(String.valueOf(s.charAt(1))) - 1;

        return new Moves.MoveCoord(row,col);

    }


    private static ArrayList<MoveCoord> displayAvailableMoves(int player){
//        char playerToken = (player==1)? 'W':'B';
        ArrayList<MoveCoord> listOfMoves = new ArrayList<>();
        for(int i=1;i<8;i++){
            for(int j=1;j<8;j++){
                char current = b.goToCell(i,j);
                if(!(current=='W'||current=='B')){
                    if(b.RayTest(new MoveCoord(i,j),b,player,false)){
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

    //public input board + move  return board



//    public static Board executeMove(int player, Board c,MoveCoord m){
//        Board newBoard = c;
//        char opponent;
//        char self;
//
//        if(player == 1){
//            opponent = 'W';
//            self = 'B';
//        }else{
//            opponent = 'B';
//            self = 'W';
//        }
//
//        int x = m.getRow();
//        int y = m.getCol();
//        char token = newBoard.goToCell(x,y);
//        if(token!='-'&&token!='O'){
//            return false;
//        }
//        boolean found = false;
//        for(int i = -1; i <= 1; i++){
//            for(int j = -1; j <= 1; j++){
//                if(!(j==0 && i==0)){
//                    x=m.getRow()+i;
//                    y=m.getCol()+j;
//                    char current = newBoard.goToCell(x,y);
//                    if(current == opponent){
//                        while(true) {
//                            x += i;
//                            y += j;
//                            current = newBoard.goToCell(x,y);
//                            if(current == self){
//                                found = true;
//                                if(flip) flip(i,j,x,y,m,player);
//                                break;
//                            }else if(current != opponent) break;
//                        }
//                    }
//                }
//            }
//        }
//
//        return current;
//    }

    //east                  j++
    //west                  j--
    //north                 i--
    //south                 i++
    //south east            i++ j++
    //south west            i++ j--
    //north east            i-- j++
    //north west            i-- j--

    private static void ScoreCounter(Board b){
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

    private static int playerTurn(){
//        b.findAvailableMoves(player);
        if(listOfMoves.isEmpty()){
            System.out.println("Player "+player+" has no available moves. Skipped Turn.");
            player = (player == 1)? 2:1;
            listOfMoves = displayAvailableMoves(player);
            if(listOfMoves.isEmpty()){
                System.out.println("Player "+player+" has no available moves. Game concluded.");
                return -1;
            }
        }

       // MoveCoord move = getMoves(player);
        MoveCoord move = getTranscript();
        if(b.RayTest(move,b,player,true)){
//                b.updateBoard(player,move.getRow(),move.getCol());
//                b.display();

            player = (player == 1)? 2:1;
        }else{
            System.out.println("Select valid");
        }
        listOfMoves = displayAvailableMoves(player);
        ScoreCounter(b);
        System.out.println("White: "+scoreW+"   Black: "+scoreB);

        return 0;
    }

    private static int CPUturn(){
        //TODO: Implement AI
        return playerTurn();
    }

}

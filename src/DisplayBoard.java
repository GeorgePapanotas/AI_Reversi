import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

//test game D3E3F4G3F3C5H3F2C4C3E2E1B3H4H5A3
//test game E6F4C3C4D3D6C5C6D7B5B6F7A6A5B4A7F3C8E8C7F6E7G8G6F8F5G4E3D2H3G5G3H4H5H7D8B8A4B3D1C1B1C2E1E2A3B7F2F1G1B2A2G2H6H2G7
//test game F5D6C5F6C4F4E6D7E7C6F7D8C8E8G5B8E3F8B6B5A6A4A5A7C7B4G6H6H7B3D3C3C2C1H5D2E2F1B1D1B2A3E1H4H3G4G3F3G1F2B7H2H1G7
//www.ffothello.org/livres/othello-book-Brian-Rose.pdf

public class DisplayBoard implements Moves{

    private int scoreW,scoreB;
    private Board b;
    private final int ALLTILES = 64;
    private ArrayList<MoveCoord> listOfMoves;
    private int player,user;
    private String transcript = "D3E3F4G3F3C5H3F2C4C3E2E1B3H4H5A3 ";
    private int letter = 0,number=2;
    private MinMax minMax;

    public DisplayBoard() {

    }

    public void play() throws IOException {
        b = new Board();
        ScoreCounter(b);
        Scanner s = new Scanner(System.in);
        System.out.print("Please select your turn (1,2): ");

        int k;

        player = s.nextInt();
        while(player!=1 && player!=2){
            System.out.print("Please select 1 or 2 as turns: ");
            player = s.nextInt();
        }
        user = player;
        int cpu = player%2+1;

        minMax = new MinMax(6,cpu);

        if(player == 2){
            player = 1;
        }

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

    private Moves.MoveCoord getMoves(int player){
        Scanner s = new Scanner(System.in);
        System.out.println("Player "+player+" make your move ( "+(player == 1?"B ).":"W )."));
        b.clearAvailableMarker();
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

    private Moves.MoveCoord getTranscript(){
        String s = transcript.substring(letter,number);
        letter+=2;
        number+=2;
        System.out.println("Transcript move for player "+player+" : "+s);
        int row, col;

        col = (int) (Character.toUpperCase(s.charAt(0)) - 'A');
        row = Integer.parseInt(String.valueOf(s.charAt(1))) - 1;

        return new Moves.MoveCoord(row,col);

    }


    private ArrayList<MoveCoord> displayAvailableMoves(int player){
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


    private void ScoreCounter(Board b){
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

    private int playerTurn(){
//        b.findAvailableMoves(player);
        listOfMoves = b.findAvailableMoves(user);

        b.displayAvailableMoves(listOfMoves);
        if(listOfMoves.isEmpty()){
            System.out.println("Player "+player+" has no available moves. Skipped Turn.");
            player = (player == 1)? 2:1;
            listOfMoves = b.findAvailableMoves(player);
            //TODO: Check if CPU is able to make move
            b.displayAvailableMoves(listOfMoves);
            if(listOfMoves.isEmpty()){
                System.out.println("Player "+player+" has no available moves. Game concluded.");
                return -1;
            }
            //TODO: Should return ?
        }

        MoveCoord move = getMoves(player);
        //MoveCoord move = getTranscript();
        if(b.RayTest(move,b,player,true)){
//                b.updateBoard(player,move.getRow(),move.getCol());
//                b.display();

            player = (player == 1)? 2:1;
        }else{
            System.out.println("Select valid");
        }
        listOfMoves = b.findAvailableMoves(player);
        b.displayAvailableMoves(listOfMoves);
        ScoreCounter(b);
        System.out.println("White: "+scoreW+"   Black: "+scoreB);

        return 0;
    }

    private int CPUturn(){
        minMax.takeTurn(b);
        ScoreCounter(b);
        System.out.println("White: "+scoreW+"   Black: "+scoreB);
        player = (player == 1)? 2:1;
        return 0;
    }

}

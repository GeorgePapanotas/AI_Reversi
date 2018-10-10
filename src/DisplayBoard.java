import java.util.Scanner;

public class DisplayBoard implements Moves{


    public static void main(String [] args){
        Board b = new Board();

        b.display();
        System.out.println();

        int a = 1;
        int player = 1;

        while(a != -1){

            MoveCoord move = getMoves(player);

            RayTest(move,b);
            b.updateBoard(player,move.getCol(),move.getRow());
            b.display();

            if(player == 1){
                player = 2;
            }else{
                player = 1;
            }
        }


        b.display();
    }

    private static Moves.MoveCoord getMoves(int player){
        Scanner s = new Scanner(System.in);
        System.out.println("Player "+player+" make your move");
        int row, col;
        String in = s.next();

        while(!in.matches("[ABCDEFGH][12345678]")){
            System.out.println("Dude make a valid input (A1)");
            in = s.next();
        }
        row = (int) (in.charAt(0) - 'A');
        col = Integer.parseInt(String.valueOf(in.charAt(1))) - 1;
//        int row = s.nextInt();
//        int col = s.nextInt();
//        while((row<0 || row >7) || (col<0 || col>7)){
//            System.out.println("Dude make a valid move (>=0, <=7)");
////            row = s.nextInt();
////            col = s.nextInt();
//        }
        return new Moves.MoveCoord(row,col);
    }

    private static void RayTest(Moves.MoveCoord m,Board b){
        int j=m.getCol();
        int x=m.getRow();

        for(int i = 0;i<=7;i++){
            switch(i){
                case 1:
                    while(j!=6){
                        //estw W
                        if(b.goToCell(i,j++) == '-' || b.goToCell(i,j) == 'W'){
                            System.out.println("non valid");
                            break;
                        }
                    }
                    break;
            }
        }
    }

    //east                  j++
    //west                  j--
    //north                 i--
    //south                 i++
    //south east            i++ j++
    //south west            i++ j--
    //north east            i-- j++
    //north west            i-- j--
}

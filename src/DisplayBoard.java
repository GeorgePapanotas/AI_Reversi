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
            b.updateBoard(player,move.getRow(),move.getCol());
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
        col = (int) (in.charAt(0) - 'A');
        row = Integer.parseInt(String.valueOf(in.charAt(1))) - 1;
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
//        int j=m.getCol();
//        int x=m.getRow();
//
//        for(int i = 0;i<=7;i++){
//            switch(i){
//                case 1:
//                    while(j!=6){
//                        //estw W
//                        if(b.goToCell(i,j++) == '-' || b.goToCell(i,j) == 'W'){
//                            System.out.println("non valid");
//                            break;
//                        }
//                    }
//                    break;
//            }
//        }
        int x = m.getRow();
        int y = m.getCol();
        boolean found = false;
        for(int i = -1; i <= 1; i++){
            for(int j = -1; j <= 1; j++){
                if(!(j==0 && i==0)){
                    char current = b.goToCell(x+i,y+j);
                    if(current=='-'||current=='W') break;
                    if(current == 'B'){
                        while(!found) {
                            current = b.goToCell(x+i,y+j);
                            if(current == 'W'){
                                found = true;
                                break;
                            }else if(current != 'B') break;
                        }
                    }
                }
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

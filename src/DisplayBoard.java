import java.util.Scanner;

public class DisplayBoard implements Moves{
    private static Board b;

    public static void main(String [] args){
        b = new Board();

        b.display();
        System.out.println();

        int a = 1;
        int player = 1;

        while(a != -1){

            MoveCoord move = getMoves(player);

            if(RayTest(move,b,player,true)){
                b.updateBoard(player,move.getRow(),move.getCol());
                b.display();
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
        if(b.goToCell(x,y)!='-'){
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

    //east                  j++
    //west                  j--
    //north                 i--
    //south                 i++
    //south east            i++ j++
    //south west            i++ j--
    //north east            i-- j++
    //north west            i-- j--
}

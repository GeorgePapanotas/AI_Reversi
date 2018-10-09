import java.util.Scanner;

public class DisplayBoard {

    static int xpos = -1,ypos = -1;
    public static void main(String [] args){
        Board b = new Board();

        b.display();
        System.out.println();
        Scanner s = new Scanner(System.in);
        int a = s.nextInt();
        int player = 1;

        while(a != -1){

            getMoves(player);
            b.updateBoard(player,xpos-1,ypos-1);
            b.display();

            if(player == 1){
                player = 2;
            }else{
                player = 1;
            }
        }


        b.display();
    }

    private static void getMoves(int player){
        Scanner s = new Scanner(System.in);
        System.out.println("Player "+player+" make your move");
        xpos = s.nextInt();
        ypos = s.nextInt();
        while((xpos<0 || xpos >7) || (ypos<0 || ypos>7)){
            System.out.println("Dude make a valid move (>=0, <=7)");
            xpos = s.nextInt();
            ypos = s.nextInt();
        }
    }
}

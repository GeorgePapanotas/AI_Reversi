public class Board {
    char a[][] = new char[8][8];

    public Board(){

        for(int i  = 0;i<8;i++){
            for(int j = 0;j<8;j++){
                a[i][j] = '-';
            }
        }

        a[3][3] = 'W';
        a[4][4] = 'W';
        a[3][4] = 'B';
        a[4][3] = 'B';


    }

    public void display(){
        System.out.println("  A B C D E F G H");
        for(int i = 0; i < 8; i++){
            System.out.print(i + 1+" ");
            for(int j = 0; j<8;j++){
                System.out.print(this.a[i][j]+" ");
            }
            System.out.println();
        }
    }

    public void updateBoard(int player,int xpos,int ypos){
        char s = '0';

        switch (player){
            case 1: s = 'W';break;
            case 2: s = 'B';break;
        }
        this.a[xpos][ypos] = s;
    }

    public char goToCell(int xpos,int ypos){
        if(xpos < 0 || xpos > 7 || ypos < 0 || ypos > 7){
            return '-';
        }
        return this.a[xpos][ypos];
    }



}

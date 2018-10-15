import java.util.ArrayList;

public class Board {
    private char a[][] = new char[8][8];

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

    public Board(char[][] table){
        this.a = table;
    }

    public Board(Board board){
        this.a = board.a;
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
            case 1: s = 'B';break;
            case 2: s = 'W';break;
        }
        this.a[xpos][ypos] = s;
    }

    public char goToCell(int xpos,int ypos){
        if(xpos < 0 || xpos > 7 || ypos < 0 || ypos > 7){
            return '-';
        }
        return this.a[xpos][ypos];
    }

    public void updateEmpty(int xPos, int yPos, char symbol){
        this.a[xPos][yPos] = symbol;
    }

    private void flip(int i, int j, int xPos, int yPos, Moves.MoveCoord moveCoord, int player){
        while(xPos != moveCoord.getRow() || yPos != moveCoord.getCol()){
            xPos = xPos - i;
            yPos = yPos - j;
            this.updateBoard(player,xPos,yPos);
        }
    }

    public boolean RayTest(Moves.MoveCoord m,Board b,int player,boolean flip){
        char opponent;
        char self;
        if(player == 1){
            opponent = 'W';
            self = 'B';
        }else{
            opponent = 'B';
            self = 'W';
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
                            x += i;
                            y += j;
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

    public ArrayList<Moves.MoveCoord> findAvailableMoves(int player){
                ArrayList<Moves.MoveCoord> listOfMoves = new ArrayList<>();
                for(int i=1;i<8;i++){
                    for(int j=1;j<8;j++){
                        char current = this.goToCell(i,j);
                        if(!(current=='W'||current=='B')){
                            if(this.RayTest(new Moves.MoveCoord(i,j),this,player,false)){
//                                if(current != 'O') this.updateEmpty(i,j,'O');
                                listOfMoves.add(new Moves.MoveCoord(i,j));
                            }else{
                                if(current != '-') this.updateEmpty(i,j,'-');
                            }
                        }
                    }
                }
//                this.display();
        return listOfMoves;
    }
    public void clearAvailableMarker () {
        char current;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                current = this.goToCell(i, j);
                if (current == 'O') {
                    this.updateEmpty(i,j,'-');
                }
            }
        }
    }

    public void displayAvailableMoves(ArrayList<Moves.MoveCoord> listOfMoves){
        for(Moves.MoveCoord move : listOfMoves){
            this.updateEmpty(move.getRow(),move.getCol(),'O');
        }
        this.display();
    }

    public void execute(Moves.MoveCoord m,int player){
        char opponent;
        char self;

        if(player == 1){
            opponent = 'W';
            self = 'B';
        }else{
            opponent = 'B';
            self = 'W';
        }

        int x = m.getRow();
        int y = m.getCol();
        char token = this.goToCell(x,y);
        if(token!='-'&&token!='O'){
            return;
        }
        for(int i = -1; i <= 1; i++){
            for(int j = -1; j <= 1; j++){
                if(!(j==0 && i==0)){
                    x=m.getRow()+i;
                    y=m.getCol()+j;
                    char current = this.goToCell(x,y);
                    if(current == opponent){
                        while(true) {
                            x += i;
                            y += j;
                            current = this.goToCell(x,y);
                            if(current == self){
                                flip(i,j,x,y,m,player);
                                break;
                            }else if(current != opponent) break;
                        }
                    }
                }
            }
        }
    }
}

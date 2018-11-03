public class Evaluation {

    public Evaluation(){

    }
    /*
        Available Evaluators:

          Coin parity
          Mobility
          CornersCaptured
          Stability
     */
    private int[][] board_value =
            {
                    {100, -1, 5, 2, 2, 5, -1, 100},
                    {-1, -20,1, 1, 1, 1,-20, -1},
                    {5 , 1,  1, 1, 1, 1,  1,  5},
                    {2 , 1,  1, 0, 0, 1,  1,  2},
                    {2 , 1,  1, 0, 0, 1,  1,  2},
                    {5 , 1,  1, 1, 1, 1,  1,  5},
                    {-1,-20, 1, 1, 1, 1,-20, -1},
                    {100, -1, 5, 2, 2, 5, -1, 100}
            };


    public int evaluate(Board b,int player){
        int score = 0;


        for(int i = 0;i<8;i++){
            for (int j = 0; j < 8; j++) {
                if(b.goToCell(i,j) == 'B'){
                    score += board_value[i][j];
                }else if(b.goToCell(i,j) == 'W'){
                    score -= board_value[i][j];
                }
            }
        }
        if(player == 1){
            return score;
        }else{
            return score * (-1);
        }
    }

    public int Mobility(){
        int mobilityScore;

        return mobilityScore;
    }



    public int coinParity(int maxPlayerTiles,int minPlayerTiles){
        return 100*(maxPlayerTiles - minPlayerTiles) / (maxPlayerTiles + minPlayerTiles);
    }

    public int mobility(int maxPlayerMoves,int minPlayerMoves){
        if(maxPlayerMoves + minPlayerMoves != 0){
            return  100 * (maxPlayerMoves - minPlayerMoves) / (maxPlayerMoves + minPlayerMoves);
        }else{
            return 0;
        }
    }

    public int cornersCaptured(int maxPlayerCorners,int minPlayerCorners){
        if(maxPlayerCorners + minPlayerCorners != 0){
            return 100*(maxPlayerCorners - minPlayerCorners)/(maxPlayerCorners-minPlayerCorners);
        }else{
            return 0;
        }
    }


}

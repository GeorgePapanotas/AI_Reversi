public class Evaluation {
    /*
        Available Evaluators:

          Coin parity
          Mobility
          CornersCaptured
          Stability
     */
    private static int[][] board_value =
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

    public int coinParity(int maxPlayerTiles,int minPlayerTiles){
        return 100*(maxPlayerTiles - minPlayerTiles) / (maxPlayerTiles + minPlayerTiles);
    }

    public int mobility(int maxPlayerMoves,int minPlayerMoves){
        if(maxPlayerMoves + minPlayerMoves != 0){
            return  100 * (maxPlayerMoves - minPlayerMoves) / (maxPlayerMoves + minPlayerMoves)
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

import java.util.ArrayList;

public class MinMax {

    private int maxDepth, alpha, beta;

    public MinMax(int maxDepth){
        this.maxDepth = maxDepth;
        alpha = Integer.MIN_VALUE;
        beta = Integer.MAX_VALUE;
    }

    public Moves.MoveCoord getBestMove(Board board ){
        node<Moves.GameState> root = new node<Moves.GameState>(new Moves.GameState(board,null,0));
        return alphaBeta(root,true).getMove();
    }

    private Moves.GameState alphaBeta(node<Moves.GameState> root, boolean maximizer){
        int value;
        if(root.depth==maxDepth || root.isLeaf()){
            //TODO: Plug in eval
            return root.getData();
        }
        if(maximizer){
            value = Integer.MIN_VALUE;
            for (node<Moves.GameState> child:root.getChildren()){
                value = Math.max(value, alphaBeta(child,false).getScore());
                alpha = Math.max(alpha,value);
                if(alpha >= beta) break;
            }
            root.getData().setScore(value);
            return new Moves.GameState();
        }else{
            value = Integer.MAX_VALUE;
            for (node<Moves.GameState> child :
                    root.getChildren()) {
                value = Math.min(value, alphaBeta(child, true).getScore());
                beta = Math.min(beta,value);
                if(alpha >= beta) break;
            }
            //TODO: Plug in eval
            root.getData().setScore(value);
            return new Moves.GameState();
        }
    }

    private void generateChildren(node<Moves.GameState> root, int depth){

    }
}

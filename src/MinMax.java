import java.util.ArrayList;

public class MinMax {

    private int maxDepth, alpha, beta, player;

    public MinMax(int maxDepth, int player){
        this.maxDepth = maxDepth;
        this.player = player;
        alpha = Integer.MIN_VALUE;
        beta = Integer.MAX_VALUE;
    }

    public Moves.MoveCoord getBestMove(Board board ){
        node<Moves.GameState> root = new node<Moves.GameState>(new Moves.GameState(board,null,0));
        generateChildren(root);
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
            root.getData().setScore(value);
            return new Moves.GameState();
        }
    }

    private void generateChildren(node<Moves.GameState> root){
        ArrayList<Moves.MoveCoord> listOfMoves = root.getData().getBoard().findAvailableMoves(player);
        for (Moves.MoveCoord move :
                listOfMoves) {
            Moves.GameState gameState = new Moves.GameState(root.getData().getBoard(), move, 0);
            //TODO: Implement executing method and uncomment
//            gameState.getBoard().execute(move);
            node<Moves.GameState> child = new node<>(gameState);
            child.setDepth(root.getDepth() - 1);
            if(root.getDepth() >= maxDepth) generateChildren(child);
            root.addChild(child);
        }
    }
}

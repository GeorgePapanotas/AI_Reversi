import java.util.ArrayList;

public class MinMax {

    private int maxDepth, alpha, beta, player;

    public MinMax(int maxDepth, int player){
        this.maxDepth = maxDepth;
        this.player = player;
        alpha = Integer.MIN_VALUE;
        beta = Integer.MAX_VALUE;
    }

    public void takeTurn(Board board){
        board.clearAvailableMarker();
        board.execute(getBestMove(board),player);
    }

    public Moves.MoveCoord getBestMove(Board board){
        node<Moves.GameState> root = new node<Moves.GameState>(new Moves.GameState(board,null,0));
        generateChildren(root,player);
        return alphaBeta(root,true).getMove();
    }

    private Moves.GameState alphaBeta(node<Moves.GameState> root, boolean maximizer){
        int value;
        Moves.GameState retState = null;
        if(root.getDepth()==maxDepth || root.isLeaf()){
            //TODO: Plug in eval
            retState = root.getData();
            retState.setScore(-100 + (int)(Math.random()*200));
            return retState;
        }
        if(maximizer){
            value = Integer.MIN_VALUE;

            for (node<Moves.GameState> child:root.getChildren()){
                value = Math.max(value, alphaBeta(child,false).getScore());
                alpha = Math.max(alpha,value);
                if(alpha >= beta){
                    retState = child.getData();
                    break;
                }
            }
//            retState.setMove();
//            return root.getData();
            if(retState != null) return retState;
            else return root.getChildren().sort();
        }else{
            value = Integer.MAX_VALUE;
            for (node<Moves.GameState> child :
                    root.getChildren()) {
                value = Math.min(value, alphaBeta(child, true).getScore());
                beta = Math.min(beta,value);
                if(alpha >= beta){
                    retState = child.getData();
                    break;
                }
            }
            root.getData().setScore(value);
//            return root.getData();
            return retState;
        }
    }

    private void generateChildren(node<Moves.GameState> root,int currentPlayer){

        ArrayList<Moves.MoveCoord> listOfMoves = root.getData().getBoard().findAvailableMoves(currentPlayer);
        for (Moves.MoveCoord move :
                listOfMoves) {
            Moves.GameState gameState = new Moves.GameState(root.getData().getBoard(), move, 0);
            //TODO: Implement executing method and uncomment
            gameState.getBoard().execute(move,player);
            node<Moves.GameState> child = new node<>(gameState);
            child.setDepth(root.getDepth() + 1);
            if(root.getDepth() <= maxDepth) generateChildren(child,currentPlayer % 2 + 1);
            root.addChild(child);
        }
    }
}

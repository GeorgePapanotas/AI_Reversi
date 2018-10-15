import java.util.ArrayList;
import java.util.Collections;

public class MinMax {

    private int maxDepth, player;

    public MinMax(int maxDepth, int player){
        this.maxDepth = maxDepth;
        this.player = player;
//        alpha = Integer.MIN_VALUE;
//        beta = Integer.MAX_VALUE;
    }

    public void takeTurn(Board board){
        board.clearAvailableMarker();
        Board newBoard = new Board(board);
        Moves.MoveCoord bestMove = getBestMove(newBoard);
        board.execute(bestMove,player);
    }

    public Moves.MoveCoord getBestMove(Board board){
        node<Moves.GameState> root = new node<Moves.GameState>(new Moves.GameState(board,null,0));
        generateChildren(root,player);
        int  l = alphaBeta(root, Integer.MIN_VALUE, Integer.MAX_VALUE, true);
        for(node<Moves.GameState> node : root.getChildren()){
            if(l == node.getData().getScore()){
                return node.getData().getMove();
            }
        }
        return null;
    }

    private int alphaBeta(node<Moves.GameState> root, int alpha, int beta, boolean maximizer){
        int value;
        int cAlpha = alpha;
        int cBeta = beta;
        Moves.GameState retState = null;
        if(root.getDepth()==maxDepth || root.isLeaf()){
            //TODO: Plug in eval
            retState = root.getData();
            retState.setScore(-100 + (int)(Math.random()*200));
            return retState.getScore();
        }
        if(maximizer){
            value = Integer.MIN_VALUE;

            for (node<Moves.GameState> child:root.getChildren()){
                value = Math.max(value, alphaBeta(child,cAlpha,cBeta,false));
                cAlpha = Math.max(cAlpha,value);
                if(cAlpha >= cBeta){
                    retState = child.getData();
                    break;
                }
            }
//            retState.setMove();
//            return root.getData();
            if(retState != null) return retState.getScore();
            else{
                Collections.sort(root.getChildren());
                return root.getChildren().get(0).getData().getScore();
            }
        }else{
            value = Integer.MAX_VALUE;
            for (node<Moves.GameState> child :
                    root.getChildren()) {
                value = Math.min(value, alphaBeta(child, cAlpha, cBeta, true));
                cBeta = Math.min(cBeta,value);
                if(cAlpha >= cBeta){
                    retState = child.getData();
                    break;
                }
            }
            root.getData().setScore(value);
//            return root.getData();
            if(retState != null) return retState.getScore();
            else{
                Collections.sort(root.getChildren());
                return root.getChildren().get(root.getChildren().size()-1).getData().getScore();
            }
        }
    }

    private void generateChildren(node<Moves.GameState> root,int currentPlayer){
        ArrayList<Moves.MoveCoord> listOfMoves = root.getData().getBoard().findAvailableMoves(currentPlayer);
        Board cpy = new Board(root.getData().getBoard());
        for (Moves.MoveCoord move :
                listOfMoves) {
            Board boardCopy = new Board(cpy);
            Moves.GameState gameState = new Moves.GameState(boardCopy, move, 0);
            gameState.getBoard().execute(move,currentPlayer);
            gameState.getBoard().display();
            System.out.println(root.getDepth());
            node<Moves.GameState> child = new node<>(gameState);
            child.setDepth(root.getDepth() + 1);
            if(root.getDepth() <= maxDepth) generateChildren(child,currentPlayer % 2 + 1);
            root.addChild(child);
        }
    }
}

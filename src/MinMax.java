import java.util.ArrayList;
import java.util.Collections;

class MinMax {

    private int maxDepth, player;

    MinMax(int maxDepth, int player){
        this.maxDepth = maxDepth;
        this.player = player;
//        alpha = Integer.MIN_VALUE;
//        beta = Integer.MAX_VALUE;
    }

    void takeTurn(Board board){
        board.clearAvailableMarker();
        Board newBoard = null;
        newBoard = new Board(board);
        Moves.MoveCoord bestMove = getBestMove(newBoard);
        board.execute(bestMove,player);
        System.out.println("CPU Played: "+(char)('A'+bestMove.getCol()) + "" + (bestMove.getRow()+1));
    }

    private Moves.MoveCoord getBestMove(Board board){
        node<Moves.GameState> root = new node<Moves.GameState>(new Moves.GameState(board,null,0));
        generateChildren(root,player);
        int  l = alphaBeta(root, Integer.MIN_VALUE, Integer.MAX_VALUE, true);
        for(node<Moves.GameState> node : root.getChildren()){
            System.out.println("\nMove Score: "+node.getData().getScore());
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
            Evaluation eval = new Evaluation();
            retState = root.getData();
            retState.setScore(eval.evaluate(root.getData().getBoard(),player));
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
                Collections.sort(root.getChildren(),Collections.reverseOrder());
                //System.out.println(root.getChildren());
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
                return root.getChildren().get(0).getData().getScore();
            }
        }
    }

    private void generateChildren(node<Moves.GameState> root,int currentPlayer){
        Board parentBoard = null;
        parentBoard = new Board(root.getData().getBoard());
        ArrayList<Moves.MoveCoord> listOfMoves = root.getData().getBoard().findAvailableMoves(currentPlayer);
        for (Moves.MoveCoord move : listOfMoves) {
            /*Board copy and execution of move*/
            Board childBoard = null;
            childBoard = new Board(parentBoard);
            childBoard.execute(move,currentPlayer);
//            childBoard.display();
//            System.out.println(root.getDepth());
            /*Creation of child node and recursion*/
            Moves.GameState gameState = new Moves.GameState(childBoard, move, 0);
            node<Moves.GameState> child = new node<>(gameState);
            child.setDepth(root.getDepth() + 1);
            /*If max depth is reached, leave children as leaves*/
            if(root.getDepth() < maxDepth) generateChildren(child,currentPlayer % 2 + 1);
            root.addChild(child);
        }
    }
}

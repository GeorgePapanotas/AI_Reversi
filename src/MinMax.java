public class MinMax {

    private int maxDepth;

    public MinMax(int maxDepth){
        this.maxDepth = maxDepth;
    }

    public Moves.MoveCoord getBestMove(Board board ){
        return new Moves.MoveCoord(1,1);
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
                root.getData().setAlpha(Math.max(root.getData().getAlpha(),value));
                if(root.getData().getAlpha() >= root.getData().getBeta()) break;
            }
            root.getData().setScore(value);
            return new Moves.GameState();
        }else{
            value = Integer.MAX_VALUE;
            for (node<Moves.GameState> child :
                    root.getChildren()) {
                value = Math.min(value, alphaBeta(child, true).getScore());
                root.getData().setBeta(Math.min(root.getData().getBeta(),value));
                if(root.getData().getBeta() <= root.getData().getAlpha()) break;
            }
            //TODO: Plug in eval
            root.getData().setBeta(value);
            return new Moves.GameState();
        }
    }
}

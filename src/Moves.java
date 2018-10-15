public interface Moves {
    class MoveCoord{
        private int row, col;

        public MoveCoord(int row, int col){
            this.row = row;
            this.col = col;
        }

        public int getRow() {
            return row;
        }

        public void setRow(int row) {
            this.row = row;
        }

        public int getCol() {
            return col;
        }

        public void setCol(int col) {
            this.col = col;
        }

        public boolean isEnd(){
            return this.col == 7 || this.col == 0 || this.row == 0 || this.row == 7;
        }

        public String toString(){
            return "[ "+this.getCol()+" , "+this.getRow()+" ]";
        }
    }

    class GameState implements Comparable<GameState>{
        private Board board;
        private MoveCoord move;
        private int score, alpha, beta;

        public GameState() {
        }

        public GameState(Board board, MoveCoord move, int score) {
            this.board = board;
            this.move = move;
            this.score = score;
        }

        public Board getBoard() {
            return board;
        }

        public void setBoard(Board board) {
            this.board = board;
        }

        public MoveCoord getMove() {
            return move;
        }

        public void setMove(MoveCoord move) {
            this.move = move;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public int getAlpha() {
            return alpha;
        }

        public void setAlpha(int alpha) {
            this.alpha = alpha;
        }

        public int getBeta() {
            return beta;
        }

        public void setBeta(int beta) {
            this.beta = beta;
        }


        @Override
        public int compareTo(GameState o) {
            return Integer.compare(this.getScore(), o.getScore());
        }
    }
}

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
    }
}

import java.util.ArrayList;

public class mainTest {
    private node<testData> root;
    private ArrayList<node<testData>> children;
    public void main(String args[]){
        root = new node<testData>(new testData(Integer.MIN_VALUE,Integer.MAX_VALUE,0));
        generateChildren(root,(int) Math.random() * 5);
    }
    private void generateChildren(node<testData> parent, int limit){
        children = new ArrayList<>();

        if(limit == 0) return;

        int numOfChildren = (int) (Math.random() *5);

        for(int i = 0; i< numOfChildren; i++){
            node<testData> child = new node<testData>(new testData(parent.getData().getAlpha(),parent.getData().getBeta(),(int) Math.random() * 5));
            parent.addChild(child);
            generateChildren(child, --limit);
        }
    }
}

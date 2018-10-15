import java.util.ArrayList;

public class mainTest {
    private static node<testData> root;

    public static void main(String args[]){
        root = new node<testData>(new testData(Integer.MIN_VALUE,Integer.MAX_VALUE,0));
        int limit =(int) (1 + Math.random() * 4);
        //System.out.println("limit: " + limit);
        generateChildren(root,limit);
        traverse(root);
    }

    private static void generateChildren(node<testData> parent, int limit){

        if(limit <= 0) return;

        int numOfChildren = (int) ((Math.random() *4));
        System.out.println("num of children: "+numOfChildren + "\nlimit: " + limit);

        for(int i = 0; i< numOfChildren; i++){
            node<testData> child = new node<testData>(new testData(parent.getData().getAlpha(),parent.getData().getBeta(),(int) (Math.random() * 15)));
            parent.addChild(child);
            generateChildren(child, limit-1);
        }
    }

    private static void traverse(node<testData> root){
        if(root.isLeaf()){
            System.out.println(root.getData().getScore());
            return;
        }else{
            for(node<testData> child: root.getChildren()){
                traverse(child);
            }
            System.out.println("-");
            System.out.println(root.getData().getScore());
            System.out.println("-");
        }
    }
}

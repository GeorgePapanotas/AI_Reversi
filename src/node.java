import java.util.ArrayList;
import java.util.Comparator;

public class node<V extends Comparable<V>> implements Comparable<node<V>> {

    private V data;
    private ArrayList<node<V>> children;
    private int depth;
    private node<V> parent;

    public node(V data,ArrayList<node<V>> children){
        this.data= data;
        this.children = children;
    }

    public node(V data){
        this.data = data;
        children = new ArrayList<node<V>>();
    }

    public void setData(V data){this.data = data;}

    public V getData(){return this.data;}

    public String toString(){return data.toString();}

    public boolean isLeaf(){
        return children.isEmpty();
    }

    public void prune(int x){
        this.children.remove(x);
    }
    public void addChild(node<V> child){children.add(child);}

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public ArrayList<node<V>> getChildren() {
        return children;
    }

    public void setParent(node<V> parent){
        for(node<V> c : getChildren()){
            c.parent = parent;
        }
    }

    public node<V> getParent(){return this.parent;}

    @Override
    public int compareTo(node<V> o) {
        return (this.getData().compareTo(o.getData()));
    }


}

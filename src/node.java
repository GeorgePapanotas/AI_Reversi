import java.util.ArrayList;

public class node<V> {

    V data;
    ArrayList<node<V>> children = new ArrayList<>();

    public node(V data,ArrayList<node<V>> children){
        this.data= data;
        this.children = children;
    }

    public void setData(V data){this.data = data;}

    public V getData(){return this.data;}

    public String toString(){return data.toString();}

    public boolean isLeaf(){
        return children.isEmpty();
    }


}

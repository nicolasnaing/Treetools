import java.util.*;

public class GraphNode {
    
    ArrayList<Integer> neighbors = new ArrayList<Integer>();
    int value;
    
    public GraphNode(int i) {
        value=i;
    }
    
    void addNeighbor(int data) {
        neighbors.add(data);}
    
    int size() { // number of neighbors
        return neighbors.size();}
    
    Integer get(int index) {
        return neighbors.get(index);
    }
    
}

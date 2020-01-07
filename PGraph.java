import java.util.*;

public class PGraph  {
    ArrayList<GraphNode> nodes = new ArrayList<GraphNode>();
    
    public PGraph (int n) {
        for (int i=0; i<n; i++) {
            GraphNode data = new GraphNode(i);
            nodes.add(data);
        }
    }
    
    int size() {
        return nodes.size();
    }
    
    void printGraph() {
        for (int i=0; i<nodes.size(); i++) {
            String printResult=i + ":";
            for (int j=0; j<nodes.get(i).neighbors.size();j++) {
                printResult+=" " + nodes.get(i).neighbors.get(j);
            }
            System.out.println(printResult);
        }
    }
    
    void addEdge(int end1, int end2) {
        //add catch to check if end1 and end2 are in set
        for (int i=0; i<nodes.size(); i++) {
            if (nodes.get(i).value==end1) {
                nodes.get(i).addNeighbor(end2);}
            if (nodes.get(i).value==end2) {
                nodes.get(i).addNeighbor(end1);}
        }
    }
    
    ArrayList<Integer> removeDups (ArrayList<Integer> data) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        Set setA = new HashSet();
        for (int i=0; i<data.size(); i++) {
            setA.add(data.get(i));
        }
        for(Object object : setA) {
            Integer element = (Integer) object;
            result.add(element);
        }
        return result;
    }
    
    ArrayList<Integer> sieve (ArrayList<Integer> standard, ArrayList<Integer> subject) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        Set setStand = new HashSet();
        for (int i=0;i<standard.size(); i++) {
            setStand.add(standard.get(i));
        }
        for (int j=0; j<subject.size(); j++) {
            if (!setStand.contains(subject.get(j))) {
                result.add(subject.get(j));
            }
        }
        return result;
    }
    
    ArrayList<Integer> distanceFromNode(int nodeValue, int distance) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        if (distance==0) {
            result.add(nodeValue);
            return result;
        }
        else if (distance==1) {
            result=nodes.get(nodeValue).neighbors;
            return result;
        }
        else {
            ArrayList<Integer> resultPrev = new ArrayList<Integer>(); // direct previous
            resultPrev=distanceFromNode(nodeValue,distance-1);
            ArrayList<Integer> resultClumpPrev = new ArrayList<Integer>(); // all previous
            ArrayList<Integer> resultMiddle = new ArrayList<Integer>(); // will get sieved
            
            for (int i=0; i<distance; i++) { // fill ClumpPrev with previous nodes
                ArrayList<Integer> data = distanceFromNode(nodeValue,i);
                for (int j=0; j<data.size(); j++) {
                    resultClumpPrev.add(data.get(j));
                }
            }
            
            for (int i=0; i<resultPrev.size(); i++) { //fill Middle with neighbors of Prev
                for (int j=0; j<nodes.get(resultPrev.get(i)).neighbors.size(); j++) {
                    resultMiddle.add(nodes.get(resultPrev.get(i)).neighbors.get(j));
                }
            }
            resultMiddle=removeDups(resultMiddle);
            result=sieve(resultClumpPrev,resultMiddle);
            
            /*for (int i=0; i<resultPrev.size(); i++) {
                System.out.print(resultPrev.get(i) + " ");
            }
            System.out.println("\n");
            for (int i=0; i<resultClumpPrev.size(); i++) {
                System.out.print(resultClumpPrev.get(i) + " ");
            }
            System.out.println("\n");
            for (int i=0; i<resultMiddle.size(); i++) {
                System.out.print(resultMiddle.get(i) + " ");
            }
            System.out.println("\n");*/
            return result;
        }
    }
    
    public static void main(String args[]) {
        PGraph web = new PGraph(15);
        System.out.println(web.size());
        
        Treetools stalk = new Treetools(); //Treetools object to use adjCoords
        ArrayList<int[]> pairs = new ArrayList<int[]>(); // will collect adjCoords
        pairs=stalk.adjCoords(4);
        for (int i=0; i<pairs.size(); i++) {
            web.addEdge(pairs.get(i)[0], pairs.get(i)[1]); //inserts pairs in better way
        }
        web.printGraph(); // prints the resulting graph
        System.out.print("\n");
        for (int j=0; j<9; j++) {
            ArrayList<Integer> data=web.distanceFromNode(0,j);
            System.out.print("distance " + j+ ": ");
            for (int k=0; k<data.size(); k++) {
                System.out.print(data.get(k) + " ");
            }
            System.out.print("\n");
        }
    }
} 

import java.util.*

public class GraphTools {

	public PGraph fillN (int n) {
    	PGraph web = new PGraph(n);
        
        Treetools stalk = new Treetools(); //Treetools object to use adjCoords
        ArrayList<int[]> pairs = new ArrayList<int[]>(); // will collect adjCoords
        pairs=stalk.adjCoords(4);
        for (int i=0; i<pairs.size(); i++) {
            web.addEdge(pairs.get(i)[0], pairs.get(i)[1]); //inserts pairs in better way
        }

        return web;
    }

    public int indexFinder (PTree tree, int n) {
    	ArrayList<PTree> treeSet= new ArrayList<PTree>();
    	int[] labels = new int[n];
    	for (int i=0; i<n; i++) {
    		labels[i]=i+1;
    	}

    	treeSet= popTrees(n,labels);
    	for (int i=0; i<treeSet.size(); i++) {
    		if treeSet.get(i).equals(tree) {
    			return i;
    		}
    	}
    	return 0;
    }

    public int numWithinDistance (PTree tree, int n, int k) {
    	int index = indexFinder(tree, n);
    	int result = 0;
    	PGraph nGraph = new PGraph(n);
    	nGraph=fillN(n);

    	for (int i=0; i<=n; i++) {
    		ArrayList<Integer> data = new ArrayList<Integer>();
    		data=distanceFromNode(index, i);
    		result+=data.size();
    	}
    	return result;
    }

    public static void main (String[] args) {


    }


import java.io.*;
import java.util.*;

public class RandomTrees {

	public static PTree randomTree (int n, ArrayList<Integer> labels, boolean tick) {
     		if (n==1) {
     			PTree result = new PTree(labels.get(0));
     			return result;
     		}
		else if (n==2) {
     			PTree result = new PTree(-1);
     			result.left = new PTree(labels.get(0));
     			result.right = new PTree(labels.get(1));
     			return result;
     		}
     		else if (n==3) {
     			PTree result1 = new PTree(-1);
     			result1.left = new PTree(labels.get(0));
    			result1.right = new PTree(-1);
     			result1.right.left = new PTree(labels.get(1));
     			result1.right.right = new PTree(labels.get(2));

     			PTree result2 = new PTree(-1);
     			result2.left = new PTree(labels.get(1));
     			result2.right = new PTree(-1);
     			result2.right.left = new PTree(labels.get(2));
     			result2.right.right = new PTree(labels.get(0));

     			PTree result3 = new PTree(-1);
     			result3.left = new PTree(labels.get(2));
     			result3.right = new PTree(-1);
     			result3.right.left = new PTree(labels.get(0));
     			result3.right.right = new PTree(labels.get(1));
     
     			Random rand = new Random();
     			int pick = rand.nextInt(3);
     			if (pick==0) {
        			return result1;
     			}
     			else if (pick==1) {
        			return result2;
     			}
     			else  {
        			return result3;
     			} 
     		}

     		else {
     			if (tick) {
     			Collections.shuffle(labels);}
     
     			PTree result = new PTree(-1);
     			Random rand = new Random();
     			int firstSize= rand.nextInt(n-1)+1;
     			//System.out.println("firstsize: "+ firstSize);

     			ArrayList<Integer> leftLabels = new ArrayList<Integer>();
     			for (int j=0; j<firstSize; j++) {
     				leftLabels.add(labels.get(0));
     				labels.remove(0);
     			}
     
     			//System.out.println("leftlabels: " + leftLabels);
     			//System.out.println("labels: " + labels);

     			result.left=randomTree(firstSize, leftLabels, false);
     			if (labels.size()>0) {
     				result.right=randomTree(n-firstSize, labels, false);
     			}
     			return result;
     		}
	}

    public static int frequency (int[] arr, int k) {
        int result=0;
        for (int i=0; i<arr.length; i++) {
            if (arr[i]==k) {
                result++;
            }
        }
        return result;
    }

    public static ArrayList<PTree> skeletonCollection (int n) {
        ArrayList<PTree> result = new ArrayList<PTree>();
        if (n==1) {
            PTree tree = new PTree(1);
            result.add(tree);
        }
        else if (n==2) {
            PTree tree = new PTree(-1);
            tree.left = new PTree(1);
            tree.right = new PTree(1);
            result.add(tree);
        }
        else if (n==3) {
            PTree tree = new PTree(-1);
            tree.left = new PTree(1);
            tree.right = new PTree(-1);
            tree.right.left = new PTree(1);
            tree.right.right = new PTree(1);
            result.add(tree);
        }
        else {
            for (int i=1; i<=n/2; i++) {
                ArrayList<PTree> skeletonI = new ArrayList<PTree>();
                ArrayList<PTree> skeletonIcomp = new ArrayList<PTree>();

                skeletonI = skeletonCollection(i);
                skeletonIcomp = skeletonCollection(n-i);

                if (i==n/2 && n%2==0) {
                    for (int r=0; r<skeletonI.size(); r++) {
                        for (int s=0; s<=r; s++) {
                            PTree tree = new PTree(-1);
                            tree.left = skeletonI.get(r);
                            tree.right = skeletonIcomp.get(s);
                            result.add(tree);
                        }
                    }
                }

                else {
                    for (int r=0; r<skeletonI.size(); r++) {
                        for (int s=0; s<skeletonIcomp.size(); s++) {
                            PTree tree = new PTree(-1);
                            tree.left = skeletonI.get(r);
                            tree.right = skeletonIcomp.get(s);
                            result.add(tree);
                        }
                    }
                }
            }
        }
        return result;
    }

    public static PTree disperse (PTree skel, ArrayList<Integer> labels, int treeSize) {
        if (treeSize == 1) {
            skel.data=labels.get(0);
            return skel;
        }
        else if (treeSize == 2) {
            skel.left.data=labels.get(0);
            skel.right.data=labels.get(1);
            return skel;
        }
        else {
            int leftSize = skel.left.size();
            int rightSize = skel.right.size();

            ArrayList<Integer> leftLabels = new ArrayList<Integer>();
                for (int j=0; j<leftSize; j++) {
                    leftLabels.add(labels.get(0));
                    labels.remove(0);
                }
            PTree result = new PTree(-1);
            result.left = disperse(skel.left, leftLabels, leftSize);
            result.right = disperse(skel.right, labels, rightSize);
            return result;
        }
    }

	public static int[][] partialFilling (int n, int prec) {
		ArrayList<PTree> setOfTrees = new ArrayList<PTree>();
        Treetools tTools = new Treetools();
        int[] labels = new int[n];
        for (int i=0; i<n; i++) {
            labels[i]=i+1;
        }
        
        ArrayList<PTree> skeletonsCollection = new ArrayList<PTree>();
        // collects all possible skeletons of size n, # is related to Catalan numbers
        ArrayList<PTree> firstInstances = new ArrayList<PTree>(); // first tree of each skeleton type
    
        skeletonsCollection=skeletonCollection(n);
        for (int i=0; i<skeletonsCollection.size(); i++) {
            ArrayList<Integer> values = new ArrayList<Integer>();
            for (int j=0; j<n; j++) {
                values.add(j+1);
            }
            // Collections.shuffle(values);
            PTree result = disperse(skeletonsCollection.get(i), values, n);
            firstInstances.add(result);
        }
        
        int m = skeletonsCollection.size();

		ArrayList<PTree> randomCollection = new ArrayList<PTree>();

		for (int j=0; j<prec; j++) {
			ArrayList<Integer> data = new ArrayList<Integer>();
     			for (int i=0; i<n; i++) {
         			data.add(i+1);
     			} 
        	randomCollection.add(randomTree(n,data,true));
		}

		int[][] result = new int[m][n-1];
        for (int i=0; i<m; i++) {
            int[] compData = new int[prec];
            for (int j=0; j<prec; j++) {
               	Set edgesA= new HashSet();
                Set edgesB= new HashSet();
                Set intersection = new HashSet();
                
                edgesA=tTools.condenseEdges(tTools.edges(firstInstances.get(i)));
                edgesB=tTools.condenseEdges(tTools.edges(randomCollection.get(j)));
                
                for (Object objectA: edgesA) {
                    Set eleA = (Set) objectA;
                    for (Object objectB: edgesB) {
                        Set eleB = (Set) objectB;
                        if (eleA.equals(eleB)) {
                            compData[j]++;
                        }
                    }
                    for (int r=0; r<=n-2; r++) {
                        result[i][r]=frequency(compData,r);
                    }
                }
            }
        }
        return result;
    }

    public static int factorial (int n) {
        int result=1;
        if (n==0 || n==1) {
            return result;
        }
        else {
            return n*factorial(n-1);
        }
    }
    
    public static int choose (int n, int k) {
        return factorial(n)/(factorial(k)*factorial(n-k));
    }

    public static int numberPerSkeleton(int n, PTree skel) {
        if (n==1) {
            return 1;
        }
        else {
            int leftSize=skel.left.size();
            int rightSize=skel.right.size();

            PTree leftSide = new PTree(-1);
            PTree rightSide = new PTree(-1);
            leftSide = skel.left;
            rightSide = skel.right;

            if (leftSide.equals(rightSide)) {
                return choose(n, leftSize)*numberPerSkeleton(leftSize, skel.left)*numberPerSkeleton(rightSize, skel.right)/2;
            }
            else {
                return choose(n, leftSize)*numberPerSkeleton(leftSize, skel.left)*numberPerSkeleton(rightSize, skel.right);
            }
        }
    }	

    public static PTree replaceNode(int target, PTree B, PTree tree) {
        if (tree.data==target) {
            tree=B;
            return tree;
        }
        else if (tree.left==null && tree.right==null) {
            return tree;
        }
        else {
            tree.left=replaceNode(target, B, tree.left);
            tree.right=replaceNode(target, B, tree.right);
            return tree;
        }
    }

    public static PTree randYule(int n) {
        if (n<=2) { // not actually yule, only one type of tree for n=1,2
            ArrayList<Integer> labels = new ArrayList<Integer>();
            for (int i=0; i<n; i++) {
            labels.add(i+1);
            }
            PTree result = new PTree(-1);
            result = randomTree(n, labels, true);
            return result;

        }
        else {
            PTree base = new PTree(-1);
            base.left = new PTree(1);
            base.right = new PTree(2);
            for (int i=3; i<=n; i++) {
                Random rand = new Random();
                int yuleSpot= rand.nextInt(i-1)+1;
                PTree bNode = new PTree(-1);
                bNode.left = new PTree(yuleSpot);
                bNode.right = new PTree(i);
                base=replaceNode(yuleSpot, bNode, base);
            }
            return base;
        }
    }

    public static int[][] yulePartialFilling (int n, int prec) {
        ArrayList<PTree> setOfTrees = new ArrayList<PTree>();
        Treetools tTools = new Treetools();
        int[] labels = new int[n];
        for (int i=0; i<n; i++) {
            labels[i]=i+1;
        }
        
        ArrayList<PTree> skeletonsCollection = new ArrayList<PTree>();
        // collects all possible skeletons of size n, # is related to Catalan numbers
        ArrayList<PTree> firstInstances = new ArrayList<PTree>(); // first tree of each skeleton type
    
        skeletonsCollection=skeletonCollection(n);
        for (int i=0; i<skeletonsCollection.size(); i++) {
            ArrayList<Integer> values = new ArrayList<Integer>();
            for (int j=0; j<n; j++) {
                values.add(j+1);
            }
            // Collections.shuffle(values);
            PTree result = disperse(skeletonsCollection.get(i), values, n);
            firstInstances.add(result);
        }
        
        int m = skeletonsCollection.size();

        ArrayList<PTree> randomCollection = new ArrayList<PTree>();

        for (int j=0; j<prec; j++) {
            ArrayList<Integer> data = new ArrayList<Integer>();
                for (int i=0; i<n; i++) {
                    data.add(i+1);
                } 
            randomCollection.add(randYule(n));
        }

        int[][] result = new int[m][n-1];
        for (int i=0; i<m; i++) {
            int[] compData = new int[prec];
            for (int j=0; j<prec; j++) {
                Set edgesA= new HashSet();
                Set edgesB= new HashSet();
                Set intersection = new HashSet();
                
                edgesA=tTools.condenseEdges(tTools.edges(firstInstances.get(i)));
                edgesB=tTools.condenseEdges(tTools.edges(randomCollection.get(j)));
                
                for (Object objectA: edgesA) {
                    Set eleA = (Set) objectA;
                    for (Object objectB: edgesB) {
                        Set eleB = (Set) objectB;
                        if (eleA.equals(eleB)) {
                            compData[j]++;
                        }
                    }
                    for (int r=0; r<=n-2; r++) {
                        result[i][r]=frequency(compData,r);
                    }
                }
            }
        }
        return result;
    }

    public static int numEdges (PTree tree) {
        if (tree.size()==1) {
            return 0;
        }
        if (tree.size()==2) {
            return 2;
        }
        else {
            return numEdges(tree.left)+numEdges(tree.right)+2;
        }
    }

    public static PTree remyAdd (PTree tree, int target) {
        if (tree.size()==1) {
            PTree result = new PTree(-1);
            result.left = new PTree(tree.data);
            result.right= new PTree(target);
            return result;
        }
        else if (tree.size()==2) {
            Random rand = new Random();
            int secondrand= rand.nextInt(1)+1; //random integer from 1 to 2
            if (secondrand==1) { //expand left
                PTree newLeft = new PTree(-1);
                newLeft.left=tree.left;
                newLeft.right=new PTree(target);
                tree.left=newLeft;
                return tree;
            }
            if (secondrand==2) { // expand right
                PTree newRight = new PTree(-1);
                newRight.left=tree.right;
                newRight.right=new PTree(target);
                tree.right=newRight;
                return tree;
            }
        }
        else {
            int edgesLeft=numEdges(tree.left)+1;
            int edgesRight=numEdges(tree.right)+1;
            Random rand = new Random();
            int firstRand= rand.nextInt(edgesLeft+edgesRight-1)+1;
            if (firstRand<=edgesLeft) {// applies remy process on left branch
                int secondRand = rand.nextInt(edgesLeft-1)+1;
                if (secondRand==1) { // apply remy on the first edge of left branch
                    PTree newLeft = new PTree(-1);
                    newLeft.left=tree.left;
                    newLeft.right=new PTree(target);
                    tree.left=newLeft;
                    return tree;
                }
                else {
                    PTree buffer=remyAdd(tree.left,target);
                    tree.left=buffer;
                    return tree;
                }
            }

            else {//applies remy process on right branch
                int secondRand = rand.nextInt(edgesRight-1)+1;
                if (secondRand==1) { // apply remy on the first edge of left branch
                    PTree newRight = new PTree(-1);
                    newRight.right=tree.right;
                    newRight.left=new PTree(target);
                    tree.right=newRight;
                    return tree;
                }
                else {
                    PTree otherBuffer=remyAdd(tree.right,target);
                    tree.right=otherBuffer;
                    return tree;
                }
            }
        }
        return null;
    }

    public static PTree remyTree (int n) {
        if (n==1) {
            PTree result = new PTree(1);
            return result;
        }
        else {
            PTree result = new PTree(-1);
            result.left = new PTree(1);
            result.right = new PTree(2);
            for (int i=3; i<=n; i++) {
                result=remyAdd(result, i);
            }
            return result;
        }
    }
	
    public static void main (String[] args) {
        /* randomFilling test
        for (int n=9; n<=9; n++) {
            ArrayList<PTree> closet = new ArrayList<PTree>();
            closet=skeletonCollection(n);

            for (int i=0; i<closet.size(); i++) {
                //System.out.print(closet.get(i));
                System.out.print(numberPerSkeleton(n, closet.get(i)));
                System.out.print("\n");
            }
            System.out.print("\n");
        }
        */


        /*
            //--------file writing attempt
        String fileName = "YuleSpreadsheetTests/yuletest9treeDist.csv";
        
        int[][] partialData = new int[1][1];
        partialData = yulePartialFilling(9,10000);

        try {
            // Assume default encoding. // used from https://www.caveofprogramming.com/java/java-file-reading-and-writing-files-in-java.html
            FileWriter fileWriter =
            new FileWriter(fileName);
            
            // Always wrap FileWriter in BufferedWriter.
            BufferedWriter bufferedWriter =
            new BufferedWriter(fileWriter);
            
            // Note that write() does not automatically
            // append a newline character.
            for (int i=0; i<partialData.length; i++) {
                String row = "";
                for (int j=0; j<partialData[0].length; j++) {
                    row+=partialData[i][j];
                    if (j!=partialData[0].length-1) {
                        row+=",";
                    }
                }
                bufferedWriter.write(row);
                bufferedWriter.newLine();
            }
            
            // Always close files.
            bufferedWriter.close();
        }
        catch(IOException ex) {
            System.out.println(
                               "Error writing to file '"
                               + fileName + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }
        */
        for (int i=0; i<10; i++) {
            System.out.println(remyTree(7));
        }
    }
	
}

     
     


     
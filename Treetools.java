import java.util.*;
import java.io.*;

public class Treetools {
    
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
    public static ArrayList<int[]> ksubsets(int k, int[] labels) {
        ArrayList<int[]> result=new ArrayList<int[]>();
        if (k==1) {
            for (int i=0; i<labels.length; i++) {
                int[] data = new int[1];
                data[0]=labels[i];
                result.add(data);
            }
        }
        else {
            for (int i=0; i<=labels.length-k; i++) {
                int[] data2= new int[labels.length-i-1];
                for (int q=0; q<labels.length-i-1; q++) {
                    data2[q]=labels[q+i+1];
                    //System.out.println("data2[" + q + "]=" + data2[q]);
                }
                ArrayList<int[]> subsets2= new ArrayList<int[]>();
                subsets2=ksubsets(k-1, data2);
                for (int j=0; j<subsets2.size(); j++) {
                    int[] data = new int[k];
                    data[0]=labels[i];
                    for (int x=1; x<k; x++) {
                        data[x]=subsets2.get(j)[x-1];
                        
                    }
                    //System.out.println(data[0] + "\t" + data[1]);
                    result.add(data);
                }
            }
        }
        return result;
    }
    public static ArrayList<PTree> popTrees (int n, int[] labels) {
        ArrayList<PTree> result = new ArrayList<PTree>();
        if (n!= labels.length) {
            System.out.println("not equal size");
        }
        else if (n==1) {
            PTree tree = new PTree(labels[0]);
            result.add(tree);
        }
        else if (n==2) {
            PTree tree = new PTree(-1);
            tree.left = new PTree(labels[0]);
            tree.right = new PTree(labels[1]);
            result.add(tree);
        }
        else if (n==3) {
            PTree tree = new PTree(-1);
            tree.left = new PTree(-1);
            tree.right = new PTree(labels[0]);
            tree.left.left=new PTree(labels[1]);
            tree.left.right=new PTree(labels[2]);
            result.add(tree);
            PTree tree1 = new PTree(-1);
            tree1.left = new PTree(-1);
            tree1.right = new PTree(labels[1]);
            tree1.left.left=new PTree(labels[0]);
            tree1.left.right=new PTree(labels[2]);
            result.add(tree1);
            PTree tree2 = new PTree(-1);
            tree2.left = new PTree(-1);
            tree2.right = new PTree(labels[2]);
            tree2.left.left=new PTree(labels[0]);
            tree2.left.right=new PTree(labels[1]);
            result.add(tree2);
        }
        else { // left heavy
            for (int i=1; i<=n/2; i++) {
                ArrayList<int[]> isubsets = ksubsets(i, labels);
                ArrayList<int[]> icompsubsets = ksubsets(n-i, labels); // two sets so far
                if (n%2==0&i==n/2) {
                    for (int j=0; j<choose(n,i)/2; j++) {
                        ArrayList<PTree> itrees = popTrees(i, isubsets.get(j));
                        ArrayList<PTree> icomptrees = popTrees(n-i, icompsubsets.get(choose(n,i)-1-j));
                        for (int k=0; k<itrees.size(); k++) {
                            for (int l=0; l<icomptrees.size(); l++) {
                                PTree tree = new PTree(-1);
                                tree.left=itrees.get(k);
                                tree.right=icomptrees.get(l);
                                result.add(tree);
                            }
                        }
                    }
                }
                else {
                    for (int j=0; j< choose(n,i); j++) {
                        ArrayList<PTree> itrees = popTrees(i, isubsets.get(j));
                        ArrayList<PTree> icomptrees = popTrees(n-i, icompsubsets.get(choose(n,i)-1-j));
                        for (int k=0; k<itrees.size(); k++) {
                            for (int l=0; l<icomptrees.size(); l++) {
                                PTree tree = new PTree(-1);
                                tree.left=itrees.get(k);
                                tree.right=icomptrees.get(l);
                                result.add(tree);
                            }
                        }
                    }
                    
                }
            }
        }
        return result;
    }
    
    public static ArrayList<PTree> adjPairs (int numVals, PTree s) {
        ArrayList<PTree> result= new ArrayList<PTree>();
        if (numVals<3) {
            result.add(s);
            return result;
        }
        if (s.right.data==-1 || s.left.data== -1) {
            if (s.right.data==-1) {
                PTree data1=new PTree(-1); //prelim
                PTree data2=new PTree(-1);
                PTree data3=new PTree(-1);
                PTree data4=new PTree(-1);
                PTree data5=new PTree(-1);
                PTree data6=new PTree(-1);
                
                data1.right= new PTree(-1);
                data2.right= new PTree(-1);
                data3.right= new PTree(-1);
                data4.right= new PTree(-1);
                data5.right= new PTree(-1);
                data6.right= new PTree(-1);
                
                PTree node1=s.left;
                PTree node2=s.right.left;
                PTree node3=s.right.right;
                
                data1.left=node1; //start 1
                data1.right.left=node2;
                data1.right.right=node3;
                data2.left=node1; //start 2
                data2.right.left=node3;
                data2.right.right=node2;
                data3.left=node2; //start 3
                data3.right.left=node1;
                data3.right.right=node3;
                data4.left=node2; //start 4
                data4.right.left=node3;
                data4.right.right=node1;
                data5.left=node3; //start 5
                data5.right.left=node1;
                data5.right.right=node2;
                data6.left=node3; //start 6
                data6.right.left=node2;
                data6.right.right=node1;
                result.add(data1);
                result.add(data2);
                result.add(data3);
                result.add(data4);
                result.add(data5);
                result.add(data6);
            }
            if (s.left.data==-1) {
                PTree data1=new PTree(-1); //prelim
                PTree data2=new PTree(-1);
                PTree data3=new PTree(-1);
                PTree data4=new PTree(-1);
                PTree data5=new PTree(-1);
                PTree data6=new PTree(-1);
                
                data1.left= new PTree(-1);
                data2.left= new PTree(-1);
                data3.left= new PTree(-1);
                data4.left= new PTree(-1);
                data5.left= new PTree(-1);
                data6.left= new PTree(-1);
                
                PTree node1=s.right;
                PTree node2=s.left.right;
                PTree node3=s.left.left;
                
                data1.right=node1; //start 1
                data1.left.right=node2;
                data1.left.left=node3;
                data2.right=node1; //start 2
                data2.left.right=node3;
                data2.left.left=node2;
                data3.right=node2; //start 3
                data3.left.right=node1;
                data3.left.left=node3;
                data4.right=node2; //start 4
                data4.left.right=node3;
                data4.left.left=node1;
                data5.right=node3; //start 5
                data5.left.right=node1;
                data5.left.left=node2;
                data6.right=node3; //start 6
                data6.left.right=node2;
                data6.left.left=node1;
                result.add(data1);
                result.add(data2);
                result.add(data3);
                result.add(data4);
                result.add(data5);
                result.add(data6);
            }
            
            if (s.left.size()>=3 || s.right.size()>=3) {
                PTree rightBranch = new PTree(-1);
                rightBranch=s.right;
                ArrayList<PTree> treeRight = adjPairs(rightBranch.size(), rightBranch);
                for (int i=0; i<treeRight.size(); i++) {
                    PTree secondSetL = new PTree(-1);
                    secondSetL.left=s.left;
                    secondSetL.right=treeRight.get(i);
                    result.add(secondSetL);
                }
                PTree leftBranch = new PTree(-1);
                leftBranch=s.left;
                ArrayList<PTree> treeLeft = adjPairs(leftBranch.size(), leftBranch);
                
                for (int i=0; i<treeLeft.size(); i++) {
                    PTree secondSetR = new PTree(-1);
                    secondSetR.right=s.right;
                    secondSetR.left=treeLeft.get(i);
                    result.add(secondSetR);
                }
            }
        }
        ArrayList<PTree> resultFinal = new ArrayList<PTree>();
        resultFinal.add(result.get(0));
        for (int i=0; i<result.size(); i++) {
            boolean inside=false;
            for (int j=0; j<resultFinal.size(); j++) {
                if (result.get(i).equals(resultFinal.get(j))) {
                    inside = true;}
            }
            if (inside==false) {
                resultFinal.add(result.get(i));}
        }
        resultFinal.remove(0);
        return resultFinal;
    }
    
    public static boolean treeinList(PTree t, ArrayList<PTree> group) {
        boolean result = false;
        for (int i=0; i<group.size(); i++) {
            if (t.equals(group.get(i))) {
                result = true;
                break;
            }
        }
        return result;
    }
        
    public static ArrayList<int[]> adjCoords(int n) {
        ArrayList<PTree> data1 = new ArrayList<PTree>();
        int[] popFeed = new int[n]; // default labels, 1->n
        for (int i=0; i<n; i++) {
            popFeed[i]=i+1;
        }
        data1= popTrees(n, popFeed); // list of n-leaf trees
        
        ArrayList<ArrayList<PTree>> adjList = new ArrayList<ArrayList<PTree>>(); // list of neighbors for tree i in data1
        for (int i=0; i<data1.size(); i++) {
            ArrayList<PTree> data = new ArrayList<PTree>();
            data = adjPairs(n, data1.get(i));
            adjList.add(data);
        }
        
        ArrayList<int[]> result = new ArrayList<int[]>();
        for (int i=0; i<data1.size(); i++) {
            for (int j=i+1; j<data1.size(); j++) {
                if (treeinList(data1.get(j), adjList.get(i))) {
                    int[] resultArr = new int[2];
                    resultArr[0]=i;
                    resultArr[1]=j;
                    result.add(resultArr);
                }
            }
        }
        return result;
    }
    
    public static boolean correctForm(String s) {
        if (s.length()<3) {
            return false;}
        else {
            int numLeft = 0;
            int numRight = 0;
            for (int i=0; i<s.length(); i++) {
                if (s.substring(i,i+1).equals("(")) {
                    numLeft++;}
                if (s.substring(i,i+1).equals(")")) {
                    numRight++;}
            }
            return numLeft==numRight;
        }
    }
    
    public static int commaCount(String t) {
        int result=0;
        for (int i=0; i<t.length(); i++) {
            if (t.substring(i,i+1).equals(",")) {
                result++;}
        }
        return result;
    }
    
    public static PTree yNewick(String t) {
        PTree result = new PTree(-1);
        int comCount=commaCount(t);
        if (comCount==0) {
            int x=Integer.parseInt(t);
            result.data=x;}
        else if (comCount==1) {
            String block1="";
            String block2="";
            int pos = 0;
            for (int i=0; i<t.length(); i++) {
                if (t.substring(i,i+1).equals(",")) {
                    pos=i;
                    break;
                }
            }
            block1=t.substring(1,pos);
            block2=t.substring(pos+1, t.length()-1);
            block1=block1.substring(0,block1.length()-2);
            block2=block2.substring(0,block2.length()-2);
            //System.out.println("block1="+block1);
            //System.out.println("block2="+block2);
            PTree leftN = yNewick(block1);
            PTree rightN = yNewick(block2);
            result.left = leftN;
            result.right = rightN;
            return result;
        }
                    
        else {
            String block1="";
            String block2="";
            if (!t.substring(1,2).equals("(")) {
                int pos = 0;
                for (int i=0; i<t.length(); i++) {
                    if (t.substring(i,i+1).equals(",")) {
                        pos=i;
                        break;
                    }
                }
                block1=t.substring(1,pos);
                block2=t.substring(pos+1, t.length()-1);
                block1=block1.substring(0,block1.length()-2);
                block2=block2.substring(0,block2.length()-2);
                //System.out.println("block1="+block1);
                //System.out.println("block2="+block2);
                PTree leftN = yNewick(block1);
                PTree rightN = yNewick(block2);
                result.left = leftN;
                result.right = rightN;
                return result;}
            else {
                for (int i=1; i<t.length(); i++) {
                    if (correctForm(t.substring(1,i))) {
                        block1=t.substring(1,i+2);
                        block2=t.substring(i+3,t.length()-1);
                        //System.out.println("block1="+block1);
                        //System.out.println("block2="+block2);
                        block1=block1.substring(0,block1.length()-2);
                        block2=block2.substring(0,block2.length()-2);
                        //System.out.println("a");
                        //System.out.println("2block1="+block1);
                        //System.out.println("2block2="+block2);
                        break;
                    }
                }
            }
            PTree leftR=new PTree(-1);
            PTree rightR=new PTree(-1);
            leftR=yNewick(block1);
            rightR=yNewick(block2);
            result.data=-1;
            result.left=leftR;
            result.right=rightR;
        }
        return result;
    }
    
    public static Set edges (PTree tree) {
        Set result= new HashSet();
        if (tree.size()<3) {
            return result;} // no edges
        else {
            if (tree.left.data==-1) {
                ArrayList<Set> element = new ArrayList<Set>();
                Set set1 = new HashSet();
                Set set2 = new HashSet();
                set1=tree.left.collection();
                set2=tree.right.collection();
                String root = "R";
                set2.add(root);
                element.add(set1);
                element.add(set2);
                result.add(element);
            }
            if (tree.right.data==-1) {
                ArrayList<Set> element = new ArrayList<Set>();
                Set set1 = new HashSet();
                Set set2 = new HashSet();
                set1=tree.left.collection();
                set2=tree.right.collection();
                String root = "R";
                set1.add(root);
                element.add(set1);
                element.add(set2);
                result.add(element);
            }
            if (tree.size()>3) {
                Set edgesL = new HashSet();
                Set edgesR = new HashSet();
                edgesL=edges(tree.left);
                edgesR=edges(tree.right);
                Set collL = new HashSet(); // elements in left side
                Set collR = new HashSet(); // elements in right side
                collL= tree.left.collection();
                collR= tree.right.collection();
                for (Object object: edgesL) {
                    ArrayList<Set> list = (ArrayList<Set>) object;
                    String root = "R";
                    if (list.get(0).contains(root)) {
                        list.get(0).addAll(collR);
                    }
                    if (list.get(1).contains(root)) {
                        list.get(1).addAll(collR);
                    }
                    result.add(list);
                }
                for (Object object2: edgesR) {
                    ArrayList<Set> list2 = (ArrayList<Set>) object2;
                    String root = "R";
                    if (list2.get(0).contains(root)) {
                        list2.get(0).addAll(collL);
                    }
                    if (list2.get(1).contains(root)) {
                        list2.get(1).addAll(collL);
                    }
                    result.add(list2);
                }
            }
        }
        return result;
    }
    
    public static Set condenseEdges (Set bigEdges) {
        Set result = new HashSet();
        for (Object object: bigEdges) {
            ArrayList<Set> bigElement = (ArrayList<Set>) object;
            if (bigElement.get(0).contains("R")) {
                result.add(bigElement.get(0));
            }
            if (bigElement.get(1).contains("R")) {
                result.add(bigElement.get(1));
            }
        }
        return result;
    }
    
    public static int[][] bigChart (int n) {
        ArrayList<PTree> setOfTrees = new ArrayList<PTree>();
        int[] labels = new int[n];
        for (int i=0; i<n; i++) {
            labels[i]=i+1;
        }
        setOfTrees=popTrees(n, labels);
        int k= setOfTrees.size();
        int[][] result = new int[k][k];
        for (int i=0; i<k; i++) {
            for (int j=0; j<i; j++) {
                Set edgesI= new HashSet();
                Set edgesJ= new HashSet();
                Set intersection = new HashSet();
                
                edgesI=condenseEdges(edges(setOfTrees.get(i)));
                edgesJ=condenseEdges(edges(setOfTrees.get(j)));
                
                for (Object objectI: edgesI) {
                    Set eleI = (Set) objectI;
                    for (Object objectJ: edgesJ) {
                        Set eleJ = (Set) objectJ;
                        if (eleI.equals(eleJ)) {
                            result[i][j]++;
                        }
                    }
                }
            }
        }
        for (int i=0; i<k; i++) {
            for (int j=k-1; j>i; j--) {
                result[i][j]=result[j][i];
            }
        }
        for (int i=0; i<k; i++) {
            result[i][i]=n-2;
        }
        return result;
    }
    
    public static int frequency(int[] arr, int k) {
        int result=0;
        for (int i=0; i<arr.length; i++) {
            if (arr[i]==k) {
                result++;
            }
        }
        return result;
    }
    
    public static void distributions (int n) {
        ArrayList<PTree> setOfTrees = new ArrayList<PTree>();
        int[] labels = new int[n];
        for (int i=0; i<n; i++) {
            labels[i]=i+1;
        }
        setOfTrees=popTrees(n, labels);
        int k= setOfTrees.size();
        int[][] result = new int[k][k];
        
        ArrayList<PTree> skeletonCollection = new ArrayList<PTree>(); // collects all possible skeletons of size n, # is related to Catalan numbers
        ArrayList<PTree> firstInstances = new ArrayList<PTree>(); // first tree of each skeleton type
        
        for (int i=0; i<setOfTrees.size(); i++) {
            PTree TreeI = new PTree(-1);
            TreeI=setOfTrees.get(i);
            PTree TreeISkel = new PTree(-1);
            TreeISkel=yNewick(TreeI.skeleton());
            
            if  (!treeinList(TreeISkel, skeletonCollection)) {
                skeletonCollection.add(TreeISkel);
                firstInstances.add(TreeI);
            }
        }
        
        //System.out.println(skeletonCollection);
        //System.out.println(firstInstances);
        
        int m = skeletonCollection.size();
        int[] categorization = new int[k];
        for (int i=0; i<setOfTrees.size(); i++) {
            PTree TreeI = new PTree(-1);
            TreeI=setOfTrees.get(i);
            PTree TreeISkel = new PTree(-1);
            TreeISkel=yNewick(TreeI.skeleton());
            for (int j=0; j<m; j++) {
                if (TreeISkel.equals(skeletonCollection.get(j))) {
                    categorization[i]=j;
                }
            }
        }
        
        for (int i=0; i<m; i++) {
            int[] compData = new int[k];
            for (int j=0; j<k; j++) {
                Set edgesA= new HashSet();
                Set edgesB= new HashSet();
                Set intersection = new HashSet();
                
                edgesA=condenseEdges(edges(firstInstances.get(i)));
                edgesB=condenseEdges(edges(setOfTrees.get(j)));
                
                for (Object objectA: edgesA) {
                    Set eleA = (Set) objectA;
                    for (Object objectB: edgesB) {
                        Set eleB = (Set) objectB;
                        if (eleA.equals(eleB)) {
                            compData[j]++;
                        }
                    }
                }
            }
            System.out.println("Skeleton " + i + ": " + skeletonCollection.get(i));
            System.out.println("Frequency of Skeleton: " + frequency(categorization, i));
            for (int r=0; r<=n-2; r++) {
                System.out.println("Frequency of " + r + " Common Edges: " + frequency(compData,r));
            }
        }
    }
    
    public static int[][] distributionsXlsx (int n) {
        ArrayList<PTree> setOfTrees = new ArrayList<PTree>();
        int[] labels = new int[n];
        for (int i=0; i<n; i++) {
            labels[i]=i+1;
        }
        setOfTrees=popTrees(n, labels);
        int k= setOfTrees.size();
        
        ArrayList<PTree> skeletonCollection = new ArrayList<PTree>(); // collects all possible skeletons of size n, # is related to Catalan numbers
        ArrayList<PTree> firstInstances = new ArrayList<PTree>(); // first tree of each skeleton type
        
        for (int i=0; i<setOfTrees.size(); i++) {
            PTree TreeI = new PTree(-1);
            TreeI=setOfTrees.get(i);
            PTree TreeISkel = new PTree(-1);
            TreeISkel=yNewick(TreeI.skeleton());
            
            if  (!treeinList(TreeISkel, skeletonCollection)) {
                skeletonCollection.add(TreeISkel);
                firstInstances.add(TreeI);
            }
        }
        
        int m = skeletonCollection.size();
        int[] categorization = new int[k];
        for (int i=0; i<setOfTrees.size(); i++) {
            PTree TreeI = new PTree(-1);
            TreeI=setOfTrees.get(i);
            PTree TreeISkel = new PTree(-1);
            TreeISkel=yNewick(TreeI.skeleton());
            for (int j=0; j<m; j++) {
                if (TreeISkel.equals(skeletonCollection.get(j))) {
                    categorization[i]=j;
                }
            }
        }
        
        int[][] result = new int[m][n-1];
        
        for (int i=0; i<m; i++) {
            int[] compData = new int[k];
            for (int j=0; j<k; j++) {
                Set edgesA= new HashSet();
                Set edgesB= new HashSet();
                Set intersection = new HashSet();
                
                edgesA=condenseEdges(edges(firstInstances.get(i)));
                edgesB=condenseEdges(edges(setOfTrees.get(j)));
                
                for (Object objectA: edgesA) {
                    Set eleA = (Set) objectA;
                    for (Object objectB: edgesB) {
                        Set eleB = (Set) objectB;
                        if (eleA.equals(eleB)) {
                            compData[j]++;
                        }
                    }
                }
                for (int r=0; r<=n-2; r++) {
                    result[i][r]=frequency(compData,r);
                }
            }
        }
        return result;
    }

    public static void main (String[] args) {
        //test for ksubsets
        /*ArrayList<int[]> data = new ArrayList<int[]>();
         int[] labels = new int[5];
         labels[0]=0;
         labels[1]=1;
         labels[2]=2;
         labels[3]=3;
         labels[4]=4;
         data=ksubsets(3, labels);
         for (int i=0; i<data.size(); i++) {
         for (int j=0; j<data.get(i).length; j++) {
         System.out.print(data.get(i)[j]+"\t");
         }
         System.out.println("\n");
         }*/
        //test for popTrees
        /*ArrayList<PTree> data2 = new ArrayList<PTree>();
        int[] labels = new int[6];
        labels[0]=0;
        labels[1]=1;
        labels[2]=2;
        labels[3]=3;
        labels[4]=4;
        labels[5]=5;
        data2=popTrees(6, labels);
        for (int i=0; i<data2.size(); i++) {
            System.out.println(data2.get(i));
        }
         System.out.println(data2.size());*/
        //test for adjPairs
        /*PTree treeTest = new PTree(-1);
        treeTest.left= new PTree(-1);
        treeTest.right= new PTree(-1);
        treeTest.left.left= new PTree(1);
        treeTest.left.right= new PTree(2);
        treeTest.right.left= new PTree(3);
        treeTest.right.right= new PTree(-1);
        treeTest.right.right.left = new PTree(4);
        treeTest.right.right.right = new PTree(5);
        ArrayList<PTree> dataPairs = new ArrayList<PTree>();
        System.out.println(treeTest+"\n"); // original tree
        dataPairs = adjPairs(5, treeTest);
        for (int i=0; i<dataPairs.size(); i++) {
            System.out.println(dataPairs.get(i)); // adjacent trees
        }*/
        //test for adjCoords
        /*ArrayList<int[]> pairs = new ArrayList<int[]>();
        pairs=adjCoords(4);
        System.out.println(pairs.size()); //adj Coords
        for (int i=0; i<pairs.size(); i++) {
            System.out.println("(" + pairs.get(i)[0] + "," + pairs.get(i)[1] + ")");
        }*/
        //test for yNewick
        //String s = "((1:1,2:1):1,3:1)";
         //PTree yTest = new PTree(-1);
         //yTest = yNewick(s);
         //System.out.println(yTest);
        //test for edges
        /*PTree dataMany = new PTree(-1);
        dataMany.left = new PTree(1);
        dataMany.right = new PTree(-1);
        dataMany.right.left = new PTree(2);
        dataMany.right.right = new PTree(-1);
        dataMany.right.right.left = new PTree(3);
        dataMany.right.right.right = new PTree(-1);
        dataMany.right.right.right.left = new PTree(4);
        dataMany.right.right.right.right = new PTree(-1);
        dataMany.right.right.right.right.left = new PTree(5);
        dataMany.right.right.right.right.right = new PTree(6);
        //dataMany.right.left = new PTree(3);
        //dataMany.right.right = new PTree(4);
        //dataMany.left.left.left = new PTree(1);
        //dataMany.left.left.right = new PTree(3);
        //dataMany.left.right.left = new PTree(2);
        //dataMany.left.right.right = new PTree(6);
        Set dataEdges = new HashSet();
        dataEdges = edges(dataMany);
        System.out.println(dataEdges);*/
        /*test for bigChart+condenseEdges
        ArrayList<PTree> dataT = new ArrayList<PTree>();
        int[] labels = new int[4];
        labels[0]=1;
        labels[1]=2;
        labels[2]=3;
        labels[3]=4;
        dataT=popTrees(4, labels);
        
        Set test1 = new HashSet();
        Set test2 = new HashSet();
        
        test1=edges(dataT.get(13));
        test2=condenseEdges(test1);
        
        System.out.println("test1" + test1);
        System.out.println("test2" + test2);*/
        
        //-----example for bigChart
        
        /*int n = 15;
        int[][] exampleChart = new int[n][n];
        exampleChart = bigChart(4);
        for (int i=0; i<n; i++) {
            int sum = 0;
            for (int j=0; j<n; j++) {
                sum+=exampleChart[i][j];}
            System.out.println("Sum of Tree " + i + ":" + sum);
                System.out.print(exampleChart[i][j]+" ");
        }*/
        //--------file writing attempt
        String fileName = "4treeDist.csv";
        
        //example for distributionsxlsx
        int[][] distChart = new int[2][3];
        distChart=distributionsXlsx(4);
        
        try {
            // Assume default encoding.
            FileWriter fileWriter =
            new FileWriter(fileName);
            
            // Always wrap FileWriter in BufferedWriter.
            BufferedWriter bufferedWriter =
            new BufferedWriter(fileWriter);
            
            // Note that write() does not automatically
            // append a newline character.
            for (int i=0; i<2; i++) {
                String row = "";
                for (int j=0; j<3; j++) {
                    row+=distChart[i][j];
                    if (j!=2) {
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
        System.out.println("done");
        distributions(4);
    }
}

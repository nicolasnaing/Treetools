import java.util.*;

public class Test {
    public static Set ksubsets(int k, int[] labels) { // Set of int[]
        Set result = new HashSet();
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
                }
                Set subsets2= new HashSet();
                subsets2=ksubsets(k-1,data2);
                for (Object object : subsets2) {
                    int[] dataA = (int[]) object;
                    int[] data = new int[k];
                    data[0]=labels[i];
                    for (int x=1; x<k; x++) {
                        data[x]=dataA[x-1];
                    result.add(data);
                    }
                }
            }
        }
        return result;
    }
    
    public static boolean arrayIntersect(int[] arr1, int[] arr2) { //does not fully work for arrays with repeating elements on one side
        Set union = new HashSet();
        for (int i=0; i<arr1.length; i++) {
            union.add(arr1[i]);
        }
        for (int j=0; j<arr2.length; j++) {
            union.add(arr2[j]);
        }
        return (union.size()==arr1.length+arr2.length);
    }
        
    
    public static Set popTrees (int n, int[] labels) {
        Set result = new HashSet();
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
                Set isubsets = ksubsets(i, labels);
                Set icompsubsets = ksubsets(n-i, labels); // two sets so far
                for (Object object: isubsets) {
                    for (Object object2: icompsubsets) {
                        int[] data1= (int[]) object;
                        int[] data2= (int[]) object2;
                        if (arrayIntersect(data1, data2)) {
                            Set itrees = popTrees(i, data1);
                            Set icomptrees = popTrees(n-i, data2);
                            for (Object object3: itrees) {
                                for (Object object4: icomptrees) {
                                    PTree tree1 = (PTree) object3;
                                    PTree tree2= (PTree) object4;
                                    PTree tree = new PTree(-1);
                                    tree.left=tree1;
                                    tree.right=tree2;
                                    boolean copy=false;
                                    for (Object object5: result) {
                                        PTree treeSieve = (PTree) object5;
                                        if (tree.equals(treeSieve)) {
                                            copy=true;
                                        }
                                    }
                                    if (copy==false) {
                                        result.add(tree);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return result;
    }
    public static Set adjPairs (int numVals, PTree s) {
        Set result= new HashSet();
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
                Set treeRight = new HashSet();
                treeRight = adjPairs(rightBranch.size(), rightBranch);
                for (Object objectR: treeRight) {
                    PTree rightTreez = (PTree) objectR;
                    PTree secondSetL = new PTree(-1);
                    secondSetL.left=s.left;
                    secondSetL.right=rightTreez;
                    result.add(secondSetL);
                }
                PTree leftBranch = new PTree(-1);
                
                leftBranch=s.left;
                Set treeLeft = new HashSet();
                treeLeft = adjPairs(leftBranch.size(), leftBranch);
                for (Object objectL: treeLeft) {
                    PTree leftTreez = (PTree) objectL;
                    PTree secondSetR = new PTree(-1);
                    secondSetR.right=s.right;
                    secondSetR.left=leftTreez;
                    result.add(secondSetR);
                }
            }
        }
        Set resultFinal = new HashSet();;
        resultFinal.add(s);
        for (Object object3: result) {
            PTree TreeFirst = (PTree) object3;
            boolean inside=false;
            for (Object object4: resultFinal) {
                PTree TreeFinal = (PTree) object4;
                if (TreeFirst.equals(TreeFinal)) {
                    inside = true;}
            }
            if (inside==false) {
                resultFinal.add(TreeFirst);
            }
        }
        resultFinal.remove(s);
        return resultFinal;
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
    
    public static void main (String[] args) {
        //test for ksubsets
        /*Set data = new HashSet();
        int[] labels = new int[5];
        labels[0]=0;
        labels[1]=1;
        labels[2]=2;
        labels[3]=3;
        labels[4]=4;
        data=ksubsets(3, labels);
        for (Object object: data) {
            int[] dataA = (int[]) object;
            for (int i=0; i<dataA.length; i++) {
                System.out.print(dataA[i]+"\t");
            }
            System.out.print("\n");
        }*/
        //test for arrayIntersection
        /*int[] data1= new int[3];
        data1[0]=0;
        data1[1]=1;
        data1[2]=2;
        int[] data2 = new int[4];
        data2[0]=3;
        data2[1]=4;
        data2[2]=5;
        data2[3]=6;
        System.out.println(arrayIntersect(data1,data2));*/
        
        //test for popTrees
        /*Set data2 = new HashSet();
        int[] labels = new int[6];
        labels[0]=0;
        labels[1]=1;
        labels[2]=2;
        labels[3]=3;
        labels[4]=4;
        labels[5]=5;
        data2=popTrees(6, labels);
        for (Object object: data2) {
            PTree treeR = (PTree) object;
            System.out.println(treeR);
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
        Set dataTest = new HashSet();
        System.out.println(treeTest+"\n"); // original tree
        dataTest = adjPairs(5, treeTest);
        for (Object object: dataTest) {
            PTree printTree = (PTree) object;
            System.out.println(printTree); // adjacent trees
        }*/
        //test for yNewick
        /*String s = "((1:1,2:1):1,3:1)";
        PTree yTest = new PTree(-1);
        yTest = yNewick(s);
        System.out.println(yTest);*/
        //test for edges
        PTree dataMany = new PTree(-1);
        dataMany.left = new PTree(-1);
        dataMany.right = new PTree(-1);
        dataMany.left.left = new PTree(1);
        dataMany.left.right = new PTree(2);
        dataMany.right.left = new PTree(3);
        dataMany.right.right = new PTree(4);
        //dataMany.left.left.left = new PTree(1);
        //dataMany.left.left.right = new PTree(3);
        //dataMany.left.right.left = new PTree(2);
        //dataMany.left.right.right = new PTree(6);
       
        Set dataEdges = new HashSet();
        dataEdges = edges(dataMany);
        System.out.println(dataEdges);

    
    }
}


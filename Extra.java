public class Extra {
    PTree tree = new PTree();
    tree.root = new PNode(-1);
    tree.root.left = new PNode(1);
    tree.root.right = new PNode(-1);
    tree.root.right.right = new PNode(-1);
    tree.root.right.right.left = new PNode(5);
    tree.root.right.right.right = new PNode(2);
    tree.root.right.left = new PNode(4);
    //System.out.println(newick(tree.root));
    
    //------------------------------trees---------
    ArrayList<PTree> test1 = new ArrayList<PTree>();
    int[] labels1= new int[5];
    labels1[0]=5;
    labels1[1]=2;
    labels1[2]=3;
    labels1[3]=4;
    labels1[4]=0;
    /*ArrayList<int[]> test2 = new ArrayList<int[]>();
     test2=ksubsets(labels1,2);
     System.out.println(test2.size());
     for (int i=0; i<test2.size(); i++) {
     System.out.println(test2.get(i)[0]+ "\t" + test2.get(i)[1])
     ;}*/
    /*test1= popTrees(5,labels1);
     System.out.println(test1.size());
     for (int i=0; i<20; i++) {
     System.out.println(newick(test1.get(i).root));
     }*/
    ArrayList<Integer> openParens = new ArrayList<Integer>();
    for (int i=1; i<s.length()-3; i++) {
        if (s.substring(i,i+1).equals("(")) {
            openParens.add(i);}
        if (s.substring(i,i+3).equals("):1")) {
            StringBuilder sb = new StringBuilder(s);
            int startIndex=openParens.get(openParens.size()-1);
            sb.deleteCharAt(startIndex);
            sb.delete(i-1,i+2);
            System.out.println(sb.toString());
            String block1 = "";
            String block2 = "";
            String block3 = "";
            
            int w=2;
            
            while (!correctForm(sb.substring(1,w))) {
                w++;
            }
            block1=sb.substring(1,w+1);
            
            int x=w+3;
            while (!correctForm(sb.substring(w+2,x))) {
                x++;
            }
            block2=sb.substring(w+2,x+1);
            
            int y=x+3;
            while (!correctForm(sb.substring(x+2,y))) {
                y++;
            }
            block3=sb.substring(x+2,y+1);
            
            System.out.println(block1);
            System.out.println(block2);
            System.out.println(block3);
            
            
            String s1 = "("+ block1 + "," + block2 + "," + block3 + ")";
            String s2 = "("+ block1 + "," + block3 + "," + block2 + ")";
            String s3 = "("+ block2 + "," + block1 + "," + block3 + ")";
            String s4 = "("+ block2 + "," + block3 + "," + block1 + ")";
            String s5 = "("+ block3 + "," + block1 + "," + block2 + ")";
            String s6 = "("+ block3 + "," + block2 + "," + block1 + ")";
            
            
            StringBuilder sb1 = new StringBuilder(s1);
            StringBuilder sb2 = new StringBuilder(s2);
            StringBuilder sb3 = new StringBuilder(s3);
            StringBuilder sb4 = new StringBuilder(s4);
            StringBuilder sb5 = new StringBuilder(s5);
            StringBuilder sb6 = new StringBuilder(s6);
            
            sb1.insert(openParens.get(openParens.size()-1),"(");
            sb2.insert(openParens.get(openParens.size()-1),"(");
            sb3.insert(openParens.get(openParens.size()-1),"(");
            sb4.insert(openParens.get(openParens.size()-1),"(");
            sb5.insert(openParens.get(openParens.size()-1),"(");
            sb6.insert(openParens.get(openParens.size()-1),"(");
            
            
            sb1.insert(i,"):1");
            sb2.insert(i,"):1");
            sb3.insert(i,"):1");
            sb4.insert(i,"):1");
            sb5.insert(i,"):1");
            sb6.insert(i,"):1");
            
            s1=sb1.toString();
            s2=sb2.toString();
            s3=sb3.toString();
            s4=sb4.toString();
            s5=sb5.toString();
            s6=sb6.toString();
            
            /*System.out.println("s1=" + s1);
             System.out.println("s2=" + s2);
             System.out.println("s3=" + s3);
             System.out.println("s4=" + s4);
             System.out.println("s5=" + s5);
             System.out.println("s6=" + s6);*/
            
            result.add(s1);
            result.add(s2);
            result.add(s3);
            result.add(s4);
            result.add(s5);
            result.add(s6);
        }
        
    }
   
    
    //System.out.println(test.newick(tree.root.right));
    ArrayList<PTree> data = new ArrayList<PTree>();
    data=test.adjPairs(tree2,6);
    System.out.println("size="+data.size());
    for (int i=0; i<data.size();i++) {
        System.out.println(test.newick(data.get(i).root));
    }
    //System.out.println("equals="+nodetest1.equals(tree2));
    //System.out.println("sizetree="+tree2.getSize());


}

import java.util.*;
/* Class containing left and right child of current
 node and key value*/
class PTree
{
    int data;
    PTree left, right;
    
    public PTree(int item)
    {
        data = item;
    }
    
    public String toString() { //newick
        String result="(";
        if (left==null && right==null) {
            result="" + data;}
        else if (left==null && right!=null) {
            result="(" + right.data + ":1)";}
        else if (left!=null && right==null) {
            result="(" + left.data + ":1)";}
        else if (left.data!=-1 && right.data!=-1) { // both fill
            result="(" + left.data + ":1," + right.data + ":1)";}
        else if (left.data==-1 && right.data!=-1) { // right fill
            result="(" + left + ":1," + right.data + ":1)";}
        else if (left.data!=-1 && right.data==-1) { // left fill
            result="(" + left.data + ":1," + right + ":1)";}
        else if (left.data==-1 && right.data==-1) {
            result="(" + left + ":1," + right + ":1)";}
        return result;
    }
    
    public String skeleton() { //newick
        String result="(";
        if (left==null && right==null) {
            result="" + 1;}
        else if (left==null && right!=null) {
            result="(" + 1 + ":1)";}
        else if (left!=null && right==null) {
            result="(" + 1 + ":1)";}
        else if (left.data!=-1 && right.data!=-1) { // both fill
            result="(" + 1 + ":1," + 1 + ":1)";}
        else if (left.data==-1 && right.data!=-1) { // right fill
            result="(" + left.skeleton() + ":1," + 1 + ":1)";}
        else if (left.data!=-1 && right.data==-1) { // left fill
            result="(" + 1 + ":1," + right.skeleton() + ":1)";}
        else if (left.data==-1 && right.data==-1) {
            result="(" + left.skeleton() + ":1," + right.skeleton() + ":1)";}
        return result;
    }
    
    int size() {
        if (data==-1) {
            return left.size()+right.size();
        }
        if (data!=-1) {
            return 1;
        }
        return 0;

    }
    
    boolean equals (PTree b) {
        if (this.size()!=b.size()) {
            return false;}
        if (data==b.data && data!=-1) {
            return true;}
        else if (data!=b.data && data!=-1 && b.data!=-1) {
            return false;}
        else {
            return (left.equals(b.left) && right.equals(b.right)) || ((left.equals(b.right) && right.equals(b.left)));}
    }
    Set collection () {
        Set result = new HashSet();
        if (data==-1) {
            Set setL = new HashSet();
            Set setR = new HashSet();
            setL=left.collection();
            setR=right.collection();
            result.addAll(setL);
            result.addAll(setR);
        }
        if (data!=-1) {
            result.add(data);}
        return result;
    }
    
    public static void main (String[] args) {
        PTree data = new PTree(-1);
        data.left= new PTree(-1);
        data.right= new PTree(-1);
        data.left.left = new PTree(1);
        data.left.right = new PTree(2);
        data.right.left = new PTree(3);
        data.right.right = new PTree(4);
        System.out.println(data);
        System.out.println(data.left);
        System.out.println(data.left.skeleton());
        
    }
}
        


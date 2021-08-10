# RandomTrees Methods
1.	public static PTree randomTree (int n, ArrayList labels, boolean tick)

•	takes an int and returns a random tree with data values

•	here is any arrayList of size with distinct entries, I used the ordered ArrayList from 1 to n

•	is used to cut off shuffling , this is used in the recursive calls of this method to save operations

2.	public static int frequency (int[] arr, int k)

•	returns the frequency that appears in , used to make bucket lists in the later functions that make the chart outputs

3.	public static ArrayList skeletonCollection (int n)

•	returns the ArrayList of skeletons of size n, a skeleton of a number n is a unlabeled tree with n spots that do not have children (same as a tree shape)
•	size of this ArrayList is related to Catalan numbers (not exact but same idea so approximately the same value)

4.	public static int[][] partialFilling (int n, int prec)

•	makes a table of size <# of skeletons of n leaves> by the frequency of a specific number of common edges between a skeleton and randomly generated trees
•	prec is the number of random trees to compare to, current code uses 5000
•	number of columns is n-1 because the number of edges that two trees of size n can have ranges from 0 to n-2 inclusive, for a total of (n-2)+1=n-1 possible values

5.	public static int factorial (int n)

•	returns n! (n factorial), used in numberPerSkeleton function later

6.	public static int choose (int n, int k) 

•	returns nCk (n choose k), used in numberPerSkeleton function later

7.	public static int numberPerSkeleton(int n, PTree skel) 

•	returns the number of possible trees of data values for a given skeleton
•	no specific formula, uses recursion and computes a big product with 1/2's mixed in to not overcount (for symmetric parts)

8.	public static PTree replaceNode(int target, PTree B, PTree tree) 

•	finds the data value in the tree and replaces it with the tree
•	used for the Yule method of random tree generation
•	is the 2-leaf tree with data values and the next data value to be added (see description of randYule)

9.	public static PTree randYule(int n)

•	randomly generates a tree of size using the Yule method
•	shell function for replaceNode, the tree construction process consists of starting with a 2-leaf tree and calling replaceNode enough times to get a n-leaf tree
•	for a 5 leaf tree, the three trees (B) to add would be the 2-leaf trees with values (target1, 3), (target2, 4), and (target3, 5)

10.	public static int[][] yulePartialFilling (int n, int prec)

•	same output as the previous partialFilling but uses Yule random generation instead of the previous randomization method

11.	public static int numEdges (PTree tree)

•	returns the number of edges in
•	includes all edges, not just pendant edges

12.	public static PTree remyAdd (PTree tree, int target)

•	adding method of remyTree
•	similar design to replaceNode but target selection accounts for # of edges on each side instead of # of data values

13.	public static PTree remyTree (int n)

•	randomly generates a tree of size using the Remy method
•	shell function for remyAdd, the tree construction process consists of starting with a 2-leaf tree and calling remyAdd enough times to get a n-leaf tree

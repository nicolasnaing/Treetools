# Treetools Methods
1.	int factorial (int n)
•	returns n!=n*(n-1)*...21, helper function for choose
2.	int choose (int n, int k)
•	returns nCk = (n!)/[k!*(n-k)!], used to attach a number to the quantity of trees generated
3.	ArrayList<int[]> ksubsets (int k, int[] labels)
•	takes an int[] labels and an int k, returns ArrayList of all possible int[] subsets of labels with k elements, helper function for popTrees
•	note that the return type is an ArrayList<int[]>, 2d array, seemed to work better than sets as the method itself is recursive
4.	ArrayList popTrees (int n, int[] labels)
•	takes an int n and an int[] labels (size n), returns an ArrayList of PTrees of all possible PTrees with n leaves with values from labels
•	generates trees recursively, lets left side cycle through popTrees(k) and right side cycle through popTrees(n-k) for 1<k<floor(n/2) + extra handling for k = n/2 with n even (see code)
•	for a 1 leaf tree, the data value is placed in the root, while for all trees with size>1, -1 is placed in the root to indicate no data
•	places -1 in each node with children as they contain no data values
•	number of trees of size n is equal to 135*...*(2n-3) (odd factorial idea)
•	below is an example of two of the 15 trees made with n=4 and labels [1,2,3,4]  
5.	ArrayList adjPairs (int numVals, PTree s)
•	takes an PTree s and int numVals (number of leaves in s), returns an ArrayList of the PTrees that are adjacent to s
•	there is a specific method to get adjacent trees, involves taking an edge, removing it, swapping around the remains , and then reinserting an edge
•	for this work, a tree is not adjacent to itself (it can be accounted for with the code with extra manipulation, but overall it does not really matter as the distance between a tree and itself is 0)
•	for numVals<3, adjPairs returns the empty set as trees with 1 or 2 leaves have no neighbors
•	for numVals>=3, the output ArrayList has size 2(numVals)-4
6.	boolean treeinList (PTree t, ArrayList group)
•	takes a PTree t and a ArrayList group, returns true if t is equal to any of the PTrees in group, false otherwise
•	basically a function that checks if a certain character is in a string/ certain number is in an array but tailored for the PTree data structure
7.	ArrayList<int[]> adjCoords (int n)
•	takes an int n, returns an ArrayList of ordered pairs of the trees that are adjacent
•	trees are ordered through popTrees
•	uses adjPairs and treeinList
•	returns ArrayList<int[]>, which is a 2D array
8.	boolean correctForm (String s)
•	takes a String s, returns true if s is a valid tree format, used in yNewick
•	bit of a weird function as it is not very intuitive/involves a bunch of brute force, but gets the job done
•	the idea behind it involves checking if the string has an equal number of opening and closing parentheses, as this means that removing one of these layers will lead to another tree, which motivates a recursive approach
9.	int commaCount (String t)
•	takes a String t, returns the number of commas in t, used in yNewick
•	also not the easiest to understand, underlying idea is that commas separate branches of trees, which implies that if I can track the commas in some way, then I can use recursion to get the Newick format
10.	PTree yNewick (String t)
•	newick parser, takes a String t, returns a PNode with data values as described in t
•	all of the edge lengths are assumed to be 1, code should be tweakable to account for variable edge lengths but that wasn't really an explored area in this research so far
11.	Set edges (PTree tree)
•	returns a set of the edges of the inputted tree
•	each element of the set is a partition of the values of the tree corresponding to the edge
12.	Set condenseEdges (Set bigEdges)
•	splits the set BigEdges into "half", bigEdges should be the output of edges()
•	since the partitions corresponding to an edge have size 2, we can record only one half of each edge and still be able to determine the corresponding tree
•	the half that will be kept is the half with the root, denoted as "R"
13.	int[][] bigChart(int n)
•	makes a chart listing the number of edges for all pairs of trees of size n
•	see 4tree.xlsx, 5tree.xlsx, and 6tree.xlsx in the repo as example outputs
•	the driver function of Treetools has a file writing component to convert int[][]-> csv, and then I converted the csv into an xlsx to add extra formatting (outer row/column for indices, sum of # of common edges on the right)

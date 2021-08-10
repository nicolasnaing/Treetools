# PGraph Methods
1.	PGraph (int n)

•	takes an int n, fills n blank slots in nodes (object's local ArrayList)

2.	int size ()

•	returns number of nodes in the graph

3.	void printGraph ()

•	prints all vertices of the PGraph and their neighbors (described in nodes)

•	example output for 4 leaf trees -> 15 total trees: 

0: 1 2 3 12

1: 0 2 6 13

2: 0 1 9 14

3: 0 4 5 12

4: 3 5 7 14

5: 3 4 10 13

6: 1 7 8 13

7: 4 6 8 14

8: 6 7 11 12

9: 2 10 11 14

10: 5 9 11 13

11: 8 9 10 12

12: 0 3 8 11

13: 1 5 6 10

14: 2 4 7 9

4.	void addEdge (int end1, int end2)

•	takes ints end1 and end2, adds end2 to end1's neighbors and adds end1 to end2's neighbors

5.	ArrayList removeDups (ArrayList data)

•	removeDups- takes an ArrayList data, returns data with duplicate entries removed, used in distanceFromNode

6.	ArrayList sieve (ArrayList standard, ArrayList subject)

•	takes an ArrayList standard and an ArrayList subject, returns subject with entries that are in both standard and subject removed, used in distanceFromNode

7.	ArrayList distanceFromNode (int nodeValue, int distance)

•	takes an int nodeValue and an int distance, returns ArrayList of the indices of trees that are "distance" away from the tree described by nodeValue given a tree t, a tree s is 0 away if s=t, 1 away is s is adjacent to t, 2 away if s is adjacent to a tree adjacent to t (and not 0 or 1 away from t) and so on

-----procedure for finding "distance rings" of 20 leaf tree------

1.	Obtain newick format of a 20-leaf tree (popTrees would take too much time to run as it would generate all trees, so this would be self-made)

2.	Use yNewick to get a PNode described by the string in (1), then store this PNode inside a PTree (PTree and PNode aren't that different as PTree acts as a shell, will correct eventually), forms "distance ring 0"
	
3.	Use adjPairs (Treetools) on (2) to get an ArrayList of adjacent PTrees, should have size 36, forms "distance ring 1"

4.	Repeat step 3 for each PTree in the ArrayList from (3), sieve out the repeated trees (sieve method in PGraph only works for integer lists, can rebuild with similar idea + treeinList method from Treetools), creates "distance ring 2", "distance ring 3", and so on

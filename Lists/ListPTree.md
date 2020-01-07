# PTree Methods
1.	Ptree (int item)
•	constructor method, stores the value item into a node
2.	String toString ()
•	returns the Newick format of the given tree, note that for single leaf trees, this method just returns the data value at the single leaf, with no parentheses/edge lengths ("1" as an example)
3.	int size ()
•	returns number of values in the tree
4.	boolean equals (PTree b)
•	compares current tree to a PTree b and returns true if they are equal, false otherwise
•	two trees are equal if and only if they have the same data values split up throughout the tree in the same way (same edges)
5.	Set collection()
•	returns the set of the values in the tree
•	not essential when defining a tree data structure but comes in handy when finding the edges of a tree (see Treetools)

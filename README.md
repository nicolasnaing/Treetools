# Treetools

## Introduction
This repository contains methods used to compute the distance between binary trees. While the notion of distance may be difficult to understand as the trees are not numerically comparable, one way to determine which trees are "closer" or "further" apart is to use the number of common edges between a pair of trees, where an edge is defined by the partition of data values that it creates. A tree with n leaves has n-2 edges, so

-if a pair of trees has n-2 common edges, the two trees are equal,

-if a pair of trees has A common edges and another pair of trees (with one tree from the previous pair) has B common edges, then the first pair is "closer" if A>B and the second pair is "closer" if A<B (vice versa with "further")

I have updated this repo with code starting from Spring 2018, so I have created functions for binary trees under Prof. Cleary for three terms now. In my independent study for Spring 2019, I worked on making spreadsheets that list out the number of common edges between trees of given size n. So far I have made charts for n=4,5,6, and will try to make my code more efficient to go on to n=7 and beyond. 

## Methods

For a general guideline on what methods each file contains:

ListTreetools.md - covers basic tree methods such as generating all trees of a given size, finding the edges of a tree

ListPTree.md - functions similarly to Tree API but built from scratch so I can have a better sense of the data structure

ListGraphNode.md - data structure of a node in a graph, has data value and neighbors

ListPGraph.md - covers functions on graphs such as distance (k-neighborhoods)

ListRandomTrees.md - covers functions on random Tree generation, includes Yule and Remy random tree generation 

These files can be found in the "Lists" folder.

## Sets Folder

This folder has the same methods as Treetools but with some of the ArrayLists converted into Sets. While this is more efficient in theory as the trees do not have a defined order that ArrayLists have, I do not plan on using these methods very much as my recursive based approaches to the code are easier to think through and write out by using ArrayLists.

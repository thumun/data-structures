# CS106 Lab 5: Binary Trees

### Due: Sunday, April 20th at 11:59pm

---

## Overview

For this assignment, we will be working with polling data from the 2020 U.S. presidential election. Before the two main political parties put forward their nominees for president, the Democratic and Republican parties hold primary elections to determine who their nominees will be. The Republican nominee had been essentially predetermined to be Donald Trump. The Democratic party had been converging on a decision, but the outcome has been somewhat uncertain earlier on. To make repeated predictions about the likely outcome of the Democratic party nomination, pollsters (statisticians) regularly conduct polls (surveys) to sample Democratic primary voters and ask whom they plan to vote. These results are compiled, released,  and eagerly tracked by the news media and public to determine which candidate currently has the largest support percentage.

In this assignment, the task is to take the poll results given as input via CSV files and update the entries of a binary tree to store the name and current polling percentage for each candidate. There is an
the optional part at the end to look at the "evolution" of the candidates over time.

---

## Part 1: Implementing a Binary Tree

It would help if you started by implementing the given `BinaryTree` interface as a `LinkedBinaryTree` so that generic objects that implement the `compareTo` method from the `Comparable` interface can be inserted into your tree. Requirements are shown below, and then some tips for each method.

### Requirements

1. Implement the `BinaryTree` interface as a `LinkedBinaryTree`. Make sure the implementation is entirely recursive and is a linked data structure, i.e., you should not use any for or while loops, arrays, `ArrayList's, etc. 
*Hint: you may find it helpful to use private or public helper methods that are called from the publicly defined methods in the interface.
* Note that the data structure itself should be recursive.
In other words, **the left and right children of a tree should themselves be trees.** You should not have a `Node` class.

2. Your implementation should be properly encapsulated, i.e., no implementation details should be made visible outside of the `LinkedBinaryTree` class. The user should not need additional methods to use the methods in the `BinaryTree` interface (even if you implement extra public methods).

3. Insertion should be done using the `compareTo` method of the given element to put smaller elements into the left subtree, and larger elements are put into the right subtree. Insertion of elements that are already in the tree should *replace* the current element. When you put your polling data into the tree, this will be equivalent to updating the candidate's poll numbers. (In reality, we should also delete candidates who have dropped out, but we'll omit this for now unless you do the optional extension.)

4. As part of implementing the `BinaryTree` interface, you will implement all three orders for tree traversal. Your returned String should be in the form:

~~~
element1 element2  ... elementn
~~~

Where the order is determined by the correct order for the specific traversal, note that these methods should also use a recursive design.
There should be one space between each element. This can be a bit tricky - look up the `trim()` method for `String's, which may help.

5. You should `Override` the `toString` method to return a `String` that looks like the following (use `\t` for tab):

~~~
Tree:
Pre:    b a c
In:     a b c
Post:   a c b
~~~

The first traversal is a pre-order traversal, the second is in-order, and the last is post-order.

### Method Summary

* Constructor: think back to what we did for our `Node` constructors (in terms of how to initialize the left and right trees of "this" tree). Before any elements are inserted, it's okay for the data to be null.

* `void insert(E element)`: should be fully recursive. *Hint: you may need to initialize the left and right trees as data is added.*

* `E getRootElement()`

* `int size()`: many ways to do this. Think about the pros and cons of maintaining an instance variable vs. computing size each time.
*Hint: no loops!*

* `boolean isEmpty()`

* `String toStringInOrder()`: should be fully recursive. *Hint: look up the trim method for strings.*

* `String toStringPreOrder()`: same advice as in-order.

* `String toStringPostOrder()`: same advice as in-order.

### Examples

<!--These examples are based on our in-class examples (the second one is not exactly the same since it wasn't sorted in class).--> An example code and output for `Integer` type.
~~~
// testing in main
BinaryTree<Integer> intTree = new LinkedBinaryTree<Integer>();
intTree.insert(8);
intTree.insert(11);
intTree.insert(5);
intTree.insert(17);
intTree.insert(1);
intTree.insert(9);
intTree.insert(3);
System.out.println(intTree);
~~~

~~~
// output
Tree:
Pre:  8 5 1 3 11 9 17
In:   1 3 5 8 9 11 17
Post:	3 1 5 9 17 11 8
~~~
An example code and output for `Character` type.
~~~
// testing in main
BinaryTree<Character> letterTree = new LinkedBinaryTree<Character>();
letterTree.insert('A');
letterTree.insert('C');
letterTree.insert('G');
letterTree.insert('B');
letterTree.insert('D');
letterTree.insert('G'); // inserting again, should replace
letterTree.insert('F');
letterTree.insert('E');
letterTree.insert('H');
letterTree.insert('I');
System.out.println("size:" + letterTree.size());
System.out.println(letterTree);
~~~

~~~
// output
size:9
Tree:
Pre:  A C B G D F E H I
In:	  A B C D E F G H I
Post:	B E F D I H G C A
~~~

---

## Part 2: Storing Polling Data

The polling data will include the candidate's full name, last name, and the percentage of the people polled who said they would vote for that candidate.

### Requirements

1. Create a class to store one row of the polling data (think about an appropriate name for this class). Recall the recommended steps of object design: fields first, constructor, then necessary methods.

2. Have your created class implement the `Comparable` interface so that polling data objects are put in order based on the candidate's *last* name.

3. Override the `toString` method to return a `String` with the following formatting (for example):

~~~
Elizabeth Warren:17.4
~~~

Where full name is first, then a colon, then the candidate's polling percentage.

---

## Part 3: Getting Polling Data from CSVs

The website [FiveThirtyEight](https://data.fivethirtyeight.com/) makes polling data for presidential primary candidates available. We have preprocessed this data for you so that only the relevant data is included, and you will receive one file per conducted poll.

Each polling data CSV has the following format:

~~~
answer,candidate,pct
Bennet,Michael F. Bennet,1.3
Biden,Joseph R. Biden Jr.,23.8
Bloomberg,Michael Bloomberg,1.8
Buttigieg,Pete Buttigieg,8.1
Gabbard,Tulsi Gabbard,3.1
Klobuchar,Amy Klobuchar,4.2
Patrick,Deval Patrick,0.0
Sanders,Bernard Sanders,30.8
Steyer,Tom Steyer,0.0
Warren,Elizabeth Warren,17.4
Yang,Andrew Yang,0.7
~~~

The first column gives the candidate's last name, the second column gives the candidate's full name, and the final column is the percent the candidate is polling at in this poll. Note that the given percent can be a floating-point number.
Recall from previous labs that you can read in data from a file using the OpenCV library `CSVReaderHeaderAware`. You should feel free to do that for this lab as well.

Each file is named something like `dempres_20190310_1.csv` where `dempres` indicates that these are polling results for the Democratic Party presidential primary, `20190310` indicates that the polling results were completed on March 10, 2019, and `_1` indicates that these are the results for the first poll completed on that date (there may be multiple from different sources). I have included about one file for each month so that you can see the evolution of the
candidates.

Your job is to take the polling data in each file and insert it into the binary tree. Your resulting tree should contain the polling data for each candidate from the most recent date for which there is data from the files given on the command line (since your tree should update the node for the same candidate on different dates rather than having duplicate nodes for the same candidate). Each polling result will only include some of the candidates.

### Requirements:

1. Take filename input from the command line into the main method of your Main.java. You may be given multiple filenames. An example of given arguments might be:

~~~
poll_data/dempres_20190103_1.csv poll_data/dempres_20190202_1.csv poll_data/dempres_20190302_1.csv
~~~

Note that `poll_data/` is required since the files are located in that directory. The output of the above argument should be

~~~
Tree:
Pre:  Bernard Sanders:21.1 Joseph R. Biden Jr.:37.0 Beto O'Rourke:5.0 Joseph Kennedy III:9.0 Kamala D. Harris:9.0 Hillary Rodham Clinton:3.0 Cory A. Booker:5.9 Michael Bloomberg:1.9 Sherrod Brown:0.9 Steve Bullock:0.0 Julián Castro:0.2 Pete Buttigieg:0.4 Kirsten E. Gillibrand:3.3 Andrew Cuomo:0.0 John K. Delaney:0.0 Eric Garcetti:0.0 Tulsi Gabbard:1.5 John Hickenlooper:1.0 Jay Robert Inslee:0.0 Eric H. Holder:0.0 John Kerry:1.0 Amy Klobuchar:0.9 Terry R. McAuliffe:0.0 Gavin Newsom:0.0 Richard Neece Ojeda:1.0 Elizabeth Warren:5.2 Tom Steyer:1.0 Howard Schultz:0.0 Eric Swalwell:0.0
In:   Joseph R. Biden Jr.:37.0 Michael Bloomberg:1.9 Cory A. Booker:5.9 Sherrod Brown:0.9 Steve Bullock:0.0 Pete Buttigieg:0.4 Julián Castro:0.2 Hillary Rodham Clinton:3.0 Andrew Cuomo:0.0 John K. Delaney:0.0 Tulsi Gabbard:1.5 Eric Garcetti:0.0 Kirsten E. Gillibrand:3.3 Kamala D. Harris:9.0 John Hickenlooper:1.0 Eric H. Holder:0.0 Jay Robert Inslee:0.0 Joseph Kennedy III:9.0 John Kerry:1.0 Amy Klobuchar:0.9 Terry R. McAuliffe:0.0 Gavin Newsom:0.0 Beto O'Rourke:5.0 Richard Neece Ojeda:1.0 Bernard Sanders:21.1 Howard Schultz:0.0 Tom Steyer:1.0 Eric Swalwell:0.0 Elizabeth Warren:5.2
Post: Michael Bloomberg:1.9 Pete Buttigieg:0.4 Julián Castro:0.2 Steve Bullock:0.0 Sherrod Brown:0.9 Cory A. Booker:5.9 Tulsi Gabbard:1.5 Eric Garcetti:0.0 John K. Delaney:0.0 Andrew Cuomo:0.0 Kirsten E. Gillibrand:3.3 Hillary Rodham Clinton:3.0 Eric H. Holder:0.0 Jay Robert Inslee:0.0 John Hickenlooper:1.0 Kamala D. Harris:9.0 Gavin Newsom:0.0 Terry R. McAuliffe:0.0 Amy Klobuchar:0.9 John Kerry:1.0 Joseph Kennedy III:9.0 Richard Neece Ojeda:1.0 Beto O'Rourke:5.0 Joseph R. Biden Jr.:37.0 Howard Schultz:0.0 Eric Swalwell:0.0 Tom Steyer:1.0 Elizabeth Warren:5.2 Bernard Sanders:21.1
~~~

2. You should process the given files in increasing date order. You may assume that the files are given in this order.

3. Use your overridden `toString` method to print the tree *after polling results from each new date are inserted*. Thus, your resulting printed information should include one snapshot of the tree per given polling data CSV.
Note: You may print the final tree at the end of all the files. Either way is fine, we'll just be looking for the final tree somewhere in your output.</font>

---

## Part 4: Runtime Analysis

Answer the runtime analysis questions in the README.

---

### Extensions

**Optional (not Extra Credit but for additional practice)**

1. Implement a boolean `remove(E element)` operation for the `LinkedBinaryTree.` It should return `true` if the element
was in the tree and successfully removed and `false` if given an element that is not in the tree.
It would help if you were sure that this method works seamlessly with the other methods. For example, an in-order traversal should return a string containing the remaining elements in sorted order even after an element has been removed. Describe your chosen design for this method in the README.

2. In cases where you are given multiple polling results on the same date, average the per-candidate results before inserting the data into the tree. i.e., if Biden received 25% in one poll and 31% in another poll from the same date, the inserted polling result should show Biden receiving 28% of the vote.

3. Ensure that the polls are processed in date order no matter what order they are given to you on the command line.

4. The given CSV files cover a long period - use your code (+ additional code) to analyze the evolution of the candidates (i.e., you could plot the number of promising candidates over time or look for indications that a candidate was going to drop out, etc.). Write up your analysis in your README.

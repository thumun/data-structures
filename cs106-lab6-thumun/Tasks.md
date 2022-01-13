## Overview: 
For this assignment, you will continue your previous investigation of Democratic primary polling data by using a heap to sort the candidates by their polling numbers (to determine which candidates are currently in the lead).

## Part 1: Implementing a Heap
It would be best if you started by implementing the given PriorityQueue interface as an ArrayHeap so that generic objects that implement the compareTo method from the Comparable interface can be inserted into your priority queue. Requirements are shown below, and then some tips for each method.

# Requirements
1. Implement the PriorityQueue interface as an ArrayHeap with the maximum at the top. Unlike last week when we were using recursion, you're encouraged to use loops in your heap implementation. You may find it useful to use private or public helper methods called from the publicly defined methods in the interface. However, you still do not need a Node class - the entire data structure will be contained within the ArrayHeap class.

2. You should use the compareTo method of the given element to determine which values are greater or less within the priority queue. We will only read one file for this week, so we do not need to deal with duplicate entries. In general, there is not a problem with having duplicate entries in a heap, but we'll avoid this by just using one file. Since this heap will be used to store polling data, you should be implementing this as a maximum heap so that we will be able to retrieve the current top candidate quickly.

    Last week we used the candidate's last name to determine the ordering. For this week, copy over your Candidate class (or whatever you called it) and modify the compareTo method instead of comparing the candidate's polling numbers. If there is a tie, use the candidate's last names (edit: still A->Z).

3. You should Override the toString method in your ArrayHeap class to return a String representation of the heap for debugging and auto-grading purposes. For example, if I add the following numbers to a new heap one by one:
-2, 3, 9, -7, 1, 2, 6, -3
and then print the heap; I will get something like this:

        9

        1 6

        -3 -2 2 3

        -7

   Each line represents one level of the heap, so 9 is at the root (maximum of all the numbers). It has children 1 and 6. The children of 1 are -3 and -2. The children of    6 are 2 and 3. -3 has one child in the last level: -7.

## Method Summary
* **Constructor and instance variables:** you should have only one instance variable, the array representing the heap. Since we want to add elements (thus resizing the array), we will use an ArrayList for this array. You should have one constructor that takes in no arguments (we will add another constructor later).

* **int size()**

* **boolean isEmpty()**

* **E max():** return the root of the heap. If the heap is empty, you can either return null or throw an exception.

* **void insert(E element):** first, add the element to the end of the array. Then we need to "bubble up" the element until we have "re-heapified" our data structure. You do not need to deal with duplicates (i.e. if you add the same element twice, it will appear twice in the heap). Suggested helper methods:

* **swap:** this could either take in one index (the index of the child) and then find the parent index and swap the data in these two positions. Or it could take in both indices (i.e., a parent index and a child index). Make sure you work with indices, though; otherwise, you will have to go searching for the element, which is slow.

* **parent:** takes in a child index and returns the index of the parent

* **leftChild:** takes in the index of a parent and returns the index of their left child (note that this could be off the end of the array)

* **rightChild:** takes in the index of a parent and returns the index of their right child (note that this could be off the end of the array)

* **bubbleUp:** this should bubble up the element at the given index

* **toString():** this method should print out each level of the tree (as shown above). To write this, you can loop through the array elements, adding a new line whenever you reach a new power of 2. You can begin testing this method by printing out the ArrayList directly.

* **E removeMax():** wait to write this until you have tested insert and toString (see examples below). Remove max should save the root element to return later. Then put the last element in the heap at the root and "bubble down" until the heap is re-heapified. To do this, exchange this new parent element with the larger of its children until it is in the right place or back at the end. I would recommend adding a bubbleDown method.

* **Examples:** These examples show the heap creation process (not removal). Put this testing (as well as additional testing of the other methods) in a separate helper method in main.

Example 1:

        // testing in Main
        Integer[] arr = {-2,3,9,-7,1,2,6,-3};

        // TODO: create a new heap of Integers
        // TODO: use a for loop to insert the elements above into the heap

        System.out.println(heap);
        
  // output
        9
        1 6
        -3 -2 2 3
        -7
        
   Example 2:

        // testing in Main
        ArrayHeap<Character> letterHeap = new ArrayHeap<Character>();
        letterHeap.insert('A');
        letterHeap.insert('C');
        letterHeap.insert('G');
        letterHeap.insert('B');
        letterHeap.insert('D');
        letterHeap.insert('G'); // inserting again, will still both copies
        letterHeap.insert('F');
        letterHeap.insert('E');
        letterHeap.insert('H');
        letterHeap.insert('I');
        System.out.println("size:" + letterHeap.size());
        System.out.println(letterHeap);
  
  // output
        size:10
        I
        H G
        E G C F
        A D B
        
# Part 2: Heap Sort (in-place)
   In the next part of the assignment, implement Heap Sort in-place. This will sort an array low to high. In Part 3, we will see another way to use heaps to sort out-of-place but high to low. Heaps are very versatile. Steps:


* Create a new constructor that takes in an unsorted ArrayList:
    
            public ArrayHeap(ArrayList<E> array) {
                ...
            }
    
* Inside this constructor, run Phase I of Heap Sort to create a heap out of the array. You can test this part in Main using the code below:
// create an ArrayList using static method "asList"

        Integer[] arr = {-2,3,9,-7,1,2,6,-3};
        ArrayList<Integer> array = new ArrayList<Integer>(Arrays.asList(arr));

// make a new heap out of the array

        ArrayHeap<Integer> heap = new ArrayHeap<Integer>(array);
        System.out.println(heap); // should print the same heap as before (9, 1 6, ...)

* In a new method called sort (no arguments, no return), run Phase II of Heap Sort (in-place). Note that this will destroy the heap! And the original order of the array. But it will sort the array in place. Here is some testing code:


        heap.sort();
        System.out.println(array); // print out the original array, now sorted (below)

// output

        [-7, -3, -2, 1, 2, 3, 6, 9]


# Part 3: Heap Sort of Candidates using removeMax
We will now use heaps to sort differently. Using the polling data from the previous Lab, this time, read in only one file (to avoid dealing with duplicates). Create a new heap, then add the candidates to the heap one by one. Then, afterward, use removeMax in a loop to remove and print each candidate in turn. This will create a printout of the candidates from highest polling number to lowest polling number.

Make sure to copy over your Candidate class and change your compareTo method so that polling data objects are ordered based on the candidate's polling number (using the last name to break ties).

Here is an example command-line argument:

        poll_data/dempres_20190718_3.csv

And this is what the output should look like (you're welcome to include intermediate printouts, but this should be included at some point). Edit: updated from previously incorrect printout!

        Joseph R. Biden Jr.:27.0
        Bernard Sanders:20.0
        Elizabeth Warren:18.0
        Kamala D. Harris:12.0
        Pete Buttigieg:7.0
        Beto O'Rourke:2.0
        Tulsi Gabbard:2.0
        Andrew Yang:1.0
        Tom Steyer:1.0
        Amy Klobuchar:1.0
        John Hickenlooper:1.0
        Kirsten E. Gillibrand:1.0
        John K. Delaney:1.0
        Julián Castro:1.0
        Cory A. Booker:1.0
        Bill de Blasio:0.0
        Marianne Williamson:0.0
        Joe Sestak:0.0
        Tim Ryan:0.0
        Seth Moulton:0.0
        Wayne Messam:0.0
        Jay Robert Inslee:0.0
        Mike Gravel:0.0
        Steve Bullock:0.0
        Michael F. Bennet:0.0

## Huffman Coding

**Optional** (worth a small amount of extra credit)

In this part of the Lab, you will create a program that can read and write text expressed using a variable bitrate encoding. Such schemes can be used to compress files so that both storage and transmission consume fewer resources.

The canonical variable bitrate algorithm is called Huffman coding after its inventor, David Huffman, who, as a student at MIT, came up with the technique and proved its optimality in response to a homework assignment professor. You can read more about the algorithm in its [Wikipedia](https://en.wikipedia.org/wiki/Huffman_coding) article. More common characters are encoded using short bit strings, while rarer characters use much longer bit strings. The savings on the short, common characters more than make up for the extra bits for the rarely occurring longer characters.

To avoid confusion, Huffman coding uses a prefix code scheme, meaning that no character's encoding forms the beginning sequence of any other. So, for example, if the code 00 represents the letter e, then no other code may begin with 00 – they must all use 01, 10, or 11 instead. This ensures that the encoded bit string has a unique interpretation. It also means that the encoding scheme can be conveniently represented using a binary tree, with symbols at the leaves and the corresponding code for that symbol determined by the path to it from the root. By convention, left branches correspond to a 0 in the code, and right branches to a 1. So if the leaf node for the letter t is reached by going left-right-left from the root, then the code for t would be 010.

# Implementation suggestions
You will write three files (add all files to your source code directory in Lab (6) and two separate programs for this assignment. I would recommend using a linked data structure for this part, not a heap. If you do the last part, you'll use a priority queue to sort the frequencies.


**HuffEncode.java** this program will take a text file and encode it using the coding below

**HuffDecode.java** this program will take an encoded file and decode it. (If you encode a file and then decode it again, you should get the same file back.)

**HuffTree.java** both programs should make use of this class, which contains the tree data structure

Shown here are the codes you should use for this assignment. Rather than building the coding tree from a particular input as described in the Wikipedia article, your program should build a tree representing the encoding given here. (But see the optional part below if you want to build a custom tree.) I would recommend that HuffTree.java have its custom insert method since you will need to insert each symbol as a leaf into the tree, creating intermediate nodes as you go (note that you do not need explicit edge labels, as left will always mean 0 and right will always mean 1). 

Hint: draw a picture of the tree as it would look after the first few symbols are inserted. You may also want to store the parent of each node.

public static final String[] CODES = {"000", "110", "0010", "0011", "0100", "0110", "0111", "1001", "1010", "10000", "10111", "11111", "010100", "010101", "010110", "100010", "100011", "101101", "111000", "111001", "111011", "111100", "1011000", "1110100", "1110101", "11110110", "11110111", "010111101", "010111111", "101100100", "101100101", "111101000", "111101010", "0101110001", "0101110010", "0101110011", "0101110111", "0101111000", "0101111001", "0101111100", "0101111101", "1011001101", "1011001110", "1011001111", "1111010010", "1111010111", "01011100000", "01011100001", "01011101000", "01011101001", "01011101011", "10110011000", "11110100110", "11110100111", "11110101100", "11110101101", "010111010101", "010111011000", "010111011001", "010111011010", "101100110011", "0101110101000", "0101110101001", "0101110110111", "01011101101100", "01011101101101", "10110011001000", "10110011001011", "101100110010011", "1011001100100100", "1011001100100101", "1011001100101000", "1011001100101001", "10110011001010100", "10110011001010101", "10110011001010110", "101100110010101111", "10110011001010111011", "101100110010101110000", "101100110010101110001", "101100110010101110010", "101100110010101110011", "101100110010101110100", "101100110010101110101"};

public static final char[] SYMBOLS = {'e', ' ', 's', 'h', 'i', 'n', 'o', 'a', 't', 'l', 'd', 'r', 'p', ',', 'y', 'g', 'f', 'w', 'm', 'c', '\n', 'u', 'v', '.', 'b', '\"', 'k', '-', 'P', 'A', 'T', '\'', 'I', 'j', 'z', 'q', 'W', 'S', 'R', '?', 'M', 'B', 'N', 'x', '!', 'H', 'V', ';', 'K', 'Y', 'G', 'O', 'F', 'D', 'C', 'E', '(', ')', 'X', 'L', ':', '*', 'J', '1', '2', '0', '8', 'U', 'Z', '7', '5', '6', '3', '/', '9', 'Q', '4', '\[', '#', '\]', '%', '=', '@', '$'};


Recommended key methods inside the HuffTree.java class (more will be needed, but this might help you get started):

* empty constructor that makes a new HuffTree but without any symbols

* void addNode(char symbol, String code): call this for each pair above; this will create a new leaf node (and potentially other nodes along the way)

* String decode(String message): this will decode a file such as the one below

To test your program, here is a secret file that you will only be able to view when you have completed the decoding program: [mystery.txt](http://cs.haverford.edu/faculty/smathieson/teaching/s20/labs/lab6/mystery_haverford.txt).

# Further exploration
To take this a step further, you can write and demonstrate a method that will automatically build a Huffman coding tree from the distribution of characters in some input file. Note that the exact set of characters in the file will not be known in advance. See this [Letter frequency article](https://en.wikipedia.org/wiki/Letter_frequency) for some starting values.

Codes reproduced in table format below:

        Symbol	Code
        e	        000

        (space)	    110

        s	        0010

        h	        0011

        i	        0100

        n	        0110

        o	        0111

        a	        1001

        t	        1010

        l	        10000

        d	        10111

        r	        11111

        p	        010100

        ,	        010101

        y	        010110

        g	        100010

        f	        100011

        w	        101101

        m	        111000

        c	        111001

        (newline)	111011

        u	        111100

        v	        1011000

        .	        1110100

        b	        1110101

        "	        11110110

        k	        11110111

        -	        010111101
        -	        
        P	        010111111

        A	        101100100

        T	        101100101

        ’	        111101000

        I	        111101010

        j	        0101110001

        z	        0101110010

        q	        0101110011

        W	        0101110111

        S	        0101111000

        R	        0101111001

        ?	        0101111100

        M	        0101111101

        B	        1011001101

        N	        1011001110

        x	        1011001111

        !	        1111010010

        H	        1111010111

        V	        01011100000

        ;	        01011100001

        K	        01011101000

        Y	        01011101001

        G	        01011101011
        O	        10110011000
        F	        11110100110
        D	        11110100111
        C	        11110101100
        E	        11110101101
        (	        010111010101
        )	        010111011000
        X	        010111011001
        L	        010111011010
        :	        101100110011
        *	        0101110101000
        J	        0101110101001
        1	        0101110110111
        2	        01011101101100
        0	        01011101101101
        8	        10110011001000
        U	        10110011001011
        Z	        101100110010011
        7	        1011001100100100
        5	        1011001100100101
        6	        1011001100101000
        3	        1011001100101001
        /	        10110011001010100
        9	        10110011001010101
        Q	        10110011001010110
        4	        101100110010101111
        \[	        10110011001010111011
        #	        101100110010101110000
        \]	        101100110010101110001
        %	        101100110010101110010
        =	        101100110010101110011
        @	        101100110010101110100
        $	        101100110010101110101

Credit for the Huffman portion: Nick Howe

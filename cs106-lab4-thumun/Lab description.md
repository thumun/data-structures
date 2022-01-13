# CS106 Lab 4: Stacks and Queues
## Due: Friday, March 26 at 11:59 pm

# Overview
In this assignment, we’ll be working with Stacks and Queues. There are four parts to this assignment. Make sure each part is working and is thoroughly tested before moving on to the next part. The last part is worth only 10% of the credit.

In all your work, try to use good top-down design principles, and remember to make a small change, test, repeat. You can also set debugging breakpoints in your code (double click to the left of the line that is causing issues). Then click the “bug” icon to run in debugging mode.

This lab will give you more experience with writing and testing data structures. We’ll also practice generics and throwing exceptions. In Part 1 you will build a stack, in Part 2 you will build a double-ended queue (deque for short), and in Parts 3 and 4 you will use these data structures to perform arithmetic computation.

You will be given some starter files for this lab. You should **not** make changes to the following files: `Deque.java`, `EmptyQueueException.java`, `FullQueueException.java`, `Stack.java`, `VerifyFormat_ArrayDeque.java`, and `VerifyFormat_LinkedStack.java`.

Notes:
* There is no pre-lab for Lab 4. However, we encourage you to watch [this video on Iterators] (https://www.dropbox.com/s/7j5yn77a6d8ocya/iterator.mov?dl=0), reproducing the code in the video would with building iterators. 
* Make sure to fill out the README at the end of the lab!
* You may find Infix, Prefix and Postfix Expressions.pdf available in the repo helpful. We encourage you to read through the doc. 
* You are allowed to use java.lang.Math library for arithmetic computations such as `Math.pow()` and `Math.log()`, which may be helpful for Part 3.
* As always, make sure to submit projects with no compilation errors! Run the unmodified `Verify` files to help you!

# Part 1: Linked Stack
To start, we will create a LinkedStack class that will implement the Stack interface. The relevant files are:

 * `Stack.java`: the stack interface, which you don’t need to change
 * `Node.java`: you will write this (we will use nodes that know about their next but not prev)
 * `LinkedStack.java`: this is where you will implement the stack
 * `Main.java`: this file is for your testing
 * `VerifyFormat_*.java`: use these to test whether your work will be graded correctly
    
The idea behind a LinkedStack is that the top of the stack will be a node. This node will know about its next node (and so on). The idea is similar to a LinkedList, but we only allow operations from the top of the stack (analogous to the head of a LinkedList). I would recommend having a piece of paper and pencil when you do this lab, to diagram what is happening with each method.

For Part 1 you will write a constructor, the five methods necessary to implement the Stack interface, and a toString method for testing. This will all be inside the LinkedStack class. Use `Main.java` for frequent testing, and run `VerifyFormat_LinkedStack.java` to check the output format. You must demonstrate that you have thoroughly tested all methods in `Main.java`.

## Fields and Constructor
First spend some time thinking about what fields you might need (which may change as you continue implementing the stack). Then write these fields and a default constructor.

Note that since we are using Nodes to hold the individual elements, there is no upper limit to our stack, unlike the array-based implementations. Note that your Node and LinkedStack class should be **generic**, using type `E` as a placeholder for any type. For the Node class, you may use code from Lab 3 (if you successfully implemented a generic Node) or our in-class work.

## Stack Methods
* `isEmpty()` Write a method that returns true if the stack is empty and false otherwise.
* `size()` Write a method that returns the number of elements on the stack.
* `peek()` Write a method that allows the user to see what is on the top of the stack, but this does not change the stack. Throw a relevant exception if the stack is empty.
* `push()` Write a method push, which should add an element to the stack. Since we are using a linked data structure, we will not need to throw an exception, since the stack will never be full.
* `pop()` Write a pop method that removes and returns an element from the stack. Throw a relevant exception if the stack is empty.
* `toString()` Write a toString method that prints out all the elements in the stack, with the top of the stack at the end (i.e. “last in”). Hint: you may need to use another stack!

The `toString` method should be in the following format: `(<firstInElement>, <secondInElement>, <thirdInElement>, ..., <lastInElement>)`.

Make sure to thoroughly test all methods in `Main.java` for this part, and run `VerifyFormat_LinkedStack.java` to check your output format.

# Part 2: Array Deque
A double-ended queue (“deque”) is one where inserting and removing can happen at **both** ends. You will be implementing the Deque interface using an array in `ArrayDeque.java`. In class, we discussed how to implement the Queue ADT using a circular array (wrap-around idea). In this part of the lab, we will build upon this idea to allow insertion and removal from **both** ends of the queue.

## Requirements:
1. Implement the included `Deque` interface using an array in a circular (wrap-around) fashion. You should include two constructors: one that initializes an array with default capacity 1000 and one that allows the user to choose the capacity of the array.
2. Override the `toString` method for `ArrayDeque` to return a String that contains the contents of the current Deque in the following format (same as Part 1): `(<front element>, <front+1 element>, ..., <back-1 element>, <back element>)`
3. Make sure your methods throw the appropriate exceptions when necessary (see `Deque` interface for more details).
4. Thoroughly test all methods using your own tests in `Main.java` (and run `VerifyFormat_ArrayDeque.java` to make sure your output format is correct).

## Part 3: Post-fix
This part will give you practice using stacks in a real parsing application. You will write a program that computes the result of arithmetic expressions. The first version will read an expression in postfix notation. For full credit, implement a form of the **shunting yard algorithm** to interpret regular infix expressions (Part 4). Implement this part in `Postfix.java`.

### Input
Your program should read in an expression provided by the user (passed in as command-line arguments) in the form shown below:
```
"3 2 + 5 /"
```
The quotation marks are **required** because
1. the parentheses would otherwise be interpreted by the shell rather than being passed to your program as input.
2. we want the entire expression in one `String` to parse it easily using a Scanner. (i.e. we do not want `args` to have more than one argument for simplicity.)

Try running `Postfix.java` with these arguments and make sure they are printed out (this is just to demonstrate how we can read in the tokens). After you’ve finished implementing this part, the program with these arguments should print out the result of 1.

### Tokenization
Since we are reading the expression from the command line arguments, it will be of type `String`. We can convert this to a series of discrete tokens using a `Scanner`. This will separate numbers, operators, parentheses, etc., and feed them to us one at a time. To see how the scanner works, run `Postfix.java`. You may also use this as the starting point for your own program.

### Postfix Computation
First, we will only accept arithmetic expressions in postfix notation. For example, `3 2 + 5 /` means `(3+2)/5` in our standard infix notation. As a reminder, here is the algorithm for computing the value of postfix expressions:
```
    Scan the tokens in order from left to right:
        If the token is a number, push it on the stack
        If the token is an operator, pop two numbers off the stack, combine them
            using the operator, and push the result onto the stack
    Once all the tokens have been processed in this way, the stack should contain exactly one element: the result.
```

### Requirements:
1. For this assignment, you will use the generic `LinkedStack` class you wrote in Part 1, **not** any Java built-ins. Since we have heterogeneous input, we will use `LinkedStack<Object>`.
2. Implement this computation as a static method inside `Postfix.java` that takes in the input of type `Scanner` and returns a `double` (the computed result). Note that you will need a separate case for each operator (`+`,`-`, etc).
3. Make sure to test your method thoroughly with a variety of input computations.

**Note: parentheses (AKA parens) are a part of the grouping operators. However, no parens are required in postfix notation.**

# Part 4: Infix Computation 
Handling infix expressions is slightly more complicated since you have to deal with parentheses. However, it turns out that infix expressions can be converted into postfix ones using a single stack. The value may then be computed using the postfix expression algorithm given above. The outline below is a simplified version of the one given on Wikipedia: [Shunting-yard algorithm](https://en.wikipedia.org/wiki/Shunting-yard_algorithm).

Note that to follow this implementation you will need one stack of type `LinkedStack<Object>`. For the output queue, use the `ArrayDeque<Object>` you wrote in Part 2 (we will not be using it as a deque, only a queue, but it is good practice to implement a deque). After you use the Shunting-yard algorithm to convert infix to postfix, you can then call your postfix method to compute the final result. For this part we will need to convert the output queue to a `String`; I would recommend adding a separate method to `ArrayDeque` to convert the output queue to a `String` with spaces (not commas). Then pass this `String` into the `Scanner` constructor.

![...](https://github.com/Haverford-College-USA/cs106-lab4/blob/main/shunting_yard.png)

# Hints and Comments
Both the stack and the output queue for the shunting-yard algorithm will need to hold data of heterogeneous types: `Double` (not `double`) for the numbers, and `Character` (not `char`) for the operators (not to mention `String` for function names if you try the extension). When you pop an element off the stack and need to figure out what type it has, use the `instanceof` operator.

Make sure to test your program on a range of inputs. Here is an example, but you should have several more demonstrating all the functionality:

    "(3+5)*2"
    "2/(1+8)*10"
    
Note: you can ignore the part about left and right associativity and assume the user has entered the expression in a non-ambiguous manner.

# The extension (Optional)
For the normal assignment, you need only consider the normal arithmetic operators `+`, `-`, `*`, `/`, and `^` (addition, subtraction, multiplication, division, and exponentiation), plus parentheses for grouping. You may ignore the unary minus (e.g., `5+(-3)`). The full Shunting-yard algorithm given on the Wikipedia page can handle the unary minus, constants such as `pi`, and functional expressions like `sin(0)` and `max(3,4)`. For an extension, augment your program to compute expressions that include elements like these. Describe the augmentations in your README.

Parts 3 and 4 based on materials by [Nick Howe](https://www.smith.edu/academics/faculty/nicholas-howe)

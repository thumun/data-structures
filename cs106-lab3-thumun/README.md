## CS 106 Lab 3 - Course lottery simulation using linked list
## Deadline - Midnight, March 19, 2021

Name: Neha Thumu 

Number of Late Days Using for this lab: 0 [used the free extension to submit Sunday 3/21]

---
## Suggestions
1. Read the assignment at least twice -- understanding the requirement
2. Revise the relevant concepts
3. Sketch a proper plan -- design
4. Implementation-- write the code
5. Debug and test-- conduct unit testing i.e. test each peice separately and then conduct integration testing (everything together)
6. Write the report

### Writeup

1. Describe the process you followed to arrive at the solution.

	For making the linked list: I used the example in the textbook as a base and added any methods that I
	thought would be useful. I ended up splitting the add methods into two parts (add and insertBefore) because
	the code in insertBefore was used more than once. 
	
	For making the tags: 
	
		--find
			This was pretty straightforward but I ended up accidentally messing it up multiple times by adding code
			meant for the print tag. In order to make this, I used the same logic as the add method in the linked list class.
			(In order to compare the input studentName with the nodes in the linked list.) If it worked out, a string detailing position was printed. 
			If the student was either not in the lottery or their position was past waitlist capacity, then it was printed that they were not on the lottery.
			
		--print
			This one was took quite a bit of trial and error. At first, it seemed relatively simple (and I think it is in the end) but I made quite a few
			mistakes that made me re-do the code. I put the header print statement (info about what was in the command line) at the very beginning so it 
			always prints. Then it uses the same iterating logic as before (in --find and add method). The status (enrolled/waitlisted and number) is 
			calculated based on the count & the two capacities (from courseinfo). Then, based on the mode, the message is printed. (The message is altered 
			based on if the person is enrolled or wait listed.) If the person's position is past waitlist capacity, then they are not added to the --print output.
	
	
2. Describe your StudentNode class design. What variables and methods do you have in it?

	It is very similar to the Student class from the previous lab. This class has enums for Programs (majors/minors), Institution,
	and Status (enrolled/waitlisted). There are variables for the enums, classyear, studentname, previouslottery, and points.
	As well as student nodes for previous and next. 
	
	There are two constructors that both take data from the csv files. One of them also takes points as an input. 
	
	The get methods are for: student name, points, classyear, and status. There are set methods for the enums: major, minor,
	institution, and status. There are also set methods for points (where it adds up all the points) and previous lottery. There is also
	a method called compare to which compares two nodes in order to find the one of higher value (primarily based on points- if
	point value is the same then name and if that's the same then class year). 
	
	There are set and get methods for the student nodes which allows for going through the nodes. 

3. How do you keep the linked list in sorted order?

	Using the add method. The header and trailer are the two ends of the linked list. When a node is added, it first checks
	if the list is empty (only had header and trailer) if so, adds node between them and links previous to header and next
	to trailer. If there are other nodes, then the node node is compared to each one using compareTo method and inserted 
	based on where it belongs points-wise. 

4. Describe your implementation of each part of the optional extension done, if any.
	
	N/A

---

### Lab questionnaire

(None of your answers below will affect your grade; this is to help refine lab
assignments in the future)

1. Approximately, how many hours did you take to complete this lab? (provide
  your answer as a single integer on the line below)
  
  Approximately 10

2. How difficult did you find this lab? (1-5, with 5 being very difficult and 1
  being very easy)
  
  3

3. Describe the biggest challenge you faced on this lab:

 Not organizing my code properly and thereby having to restructure/re-do core elements multiple times : ( 

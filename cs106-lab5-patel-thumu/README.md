## CS 106 Lab 5 - Binary Trees - Back to pair programming

Add your name [here](https://docs.google.com/spreadsheets/d/1LHZOr9Jjvd6Ps-9_n5Wk4FLxz_cNuv8qAHpRjL71lo0/edit?usp=sharing), if you have not added. (The pairs MUST NOT be the same as previous pairs)

Pair programming resources: 

[Watch this video](https://drive.google.com/file/d/14buip5KWRc0963UfReaJCfKsG5mapiBW/view) AND
[Read this document](https://drive.google.com/file/d/1UFoy62RRCVqUbdS_lzwlOtkgu8eL_NjA/view?usp=sharing)

Member1 name: Neha Thumu

Member2 name: Avi Patel

Number of Late Days Using for this lab: 0
(as long as one of you have non-penalty extension, the pair can use it, just note that here)

Any special request/approval from the professors: N/A

---

### Analysis Questions

1. In Lab 3 we used insertion sort with Linked Lists to gradually build up
a sorted list of Students. If the total number of Students is n, what was the big-O runtime
of this algorithm? Briefly explain your answer.

	O(n^2) because for n students, need to traverse through the loop n times. 

2. In this assignment (Lab 5) we used insertion sort with a Binary Tree, then
used a tree traversal to obtain a sorted list of candidates. If the total
number of candidates is n, what is the big-O runtime of this algorithm? Briefly 
explain your answer.

	If the Binary Tree is unbalanced, then the runtime would be O(n^2). Because the tree only has a right or left branch and iterates n times. 
	
	If the Binary Tree is balanced, then the runtime would be O(n log (n)). Because the use of the compareTo to figure out left/right cuts the tree in half which is lowers the runtime to log (n). But it iterates through n times, hence n log (n). 

### Pair programming log (will be graded)
You must fill the pair programming log. The format of the pair programming log can be downloaded [from here](https://drive.google.com/file/d/18iMHmSW7zJMoVBThzoKmvNCzPIA93lAR/view?usp=sharing). You can fill it, then use Add File-> Upload file options on your the repo using web browser. 

---

### Lab Questionnaire

(None of your answers below will affect your grade; this is to help refine lab
assignments in the future)

1. Approximately, how many hours did you take to complete this lab? (provide
  your answer as a single integer on the line below)
  	
	5 

2. How difficult did you find this lab? (1-5, with 5 being very difficult and 1
  being very easy)
  	
	3 

3. Describe the biggest challenge you faced on this lab:
	
	Implementing the insert was difficult due to the recursive nature of it 

4. Was the **VerifyFormat** file useful to you during testing?
	
	Yes 

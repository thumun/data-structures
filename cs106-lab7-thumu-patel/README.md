## CS 106 Lab 7 - Deduplication (A pair programming lab)
## Choosing the partner
You are free to choose your partner for this lab i.e. you can choose to work with a student who you have already worked with or someone else. 

Name:

Number of Late Days Using for this lab:

---
## Tasks: 
[Tasks are listed here](https://github.com/Haverford-College-USA/cs106-lab7/blob/master/Tasks.md)

## Files
The lab files are available [here as well](https://drive.google.com/drive/folders/1m3ZQEFMVSLzL-BC3qnRieLtZEk3r_u3e?usp=sharing) 

### Runtime Analysis

See questions in the lab writeup, answer below.

How many seconds does each duplication counting method take when run on one of the given datasets? Explain which dataset you used - try to use the largest one you can without running out of memory or patience.

    AllPairs = 134.63 s
	  Quick sort = 0.093 s
	  Hash = 0.037 s

	  Dataset = SWVF_1_22_med.txt

Create a graph showing the number of rows deduplicated on the x-axis and the runtime (in seconds) on the y-axis for each of these methods (where each method is a series on the graph). Save the graph as the file deduplication.png in your repository and be sure to git commit and push to submit this graph. Note that this will require a way to vary the number of rows you're reading/using from any given file. Again try to use the largest numbers you can so you're able to see a pattern.

    The comparison of the three deduplication methods was added as deduplication.png 
    The comparison of just the Hash and QuickSort deduplication methods was added as quick_hash_zoomIn.png. This plot shows 
    more clearly the difference between run-times of hash and quickSort deduplication methods. 

Interpret your results. Explain which deduplication method is most efficient in your README, and provide a theoretical explanation of the runtime in big-O notation.

We observe that hash linear deduplication is the most efficient method as shown on quick_hash_zoomIn.png, since the hash linear deduplication run-time follows a linear trend that is below the run-time plot of quickSort deduplication. We experimentally observe a linear curve since in the hashLinearDeduplication method, we are only iterating across all n elements from the voterRows ArrayList in VoterData. Thus, the Big-O runtime for hash linear deduplication is O(n). Furthermore, the big-O runtime of allPairsDeduplication method was O(n^2) since within the method we use a nested for-loop iterating from 0 to the n where n is the number of elements in the voterRows ArrayList in VoterData. This run-time supports the non-linear curve that rapidly grows as the number of rows increases as illustrated in deduplication.png. Finally, we deduced that the quickSortDeduplication has a big-O runtime of O(nlog(n)) where n is the number of elements from voterRows ArrayList in VoterData because the quickSort algorithm has this runtime. This big-O runtime is supported by our plot of hash linear and quicksort deduplication runtimes in quick_hash_zoomin.png where we observe that quicksort run-time grows faster as the number of rows increases than hash linear. 

---

### Lab Questionnaire

(None of your answers below will affect your grade; this is to help refine lab
assignments in the future)

1. Approximately, how many hours did you take to complete this lab? (provide
  your answer as a single integer on the line below) 7

2. How difficult did you find this lab? (1-5, with 5 being very difficult and 1
  being very easy) 3

3. Describe the biggest challenge you faced on this lab: Developing a technique to plot the run-times of the three deduplication methods as a function of row size using a row step size variable to reduce the amount of computing power and time to produce the graphs.

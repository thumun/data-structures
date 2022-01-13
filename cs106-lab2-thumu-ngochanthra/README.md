## CS 106 Lab 2: Data Structure Design

Name: Neha Thumu and Mata Ngochanthra

---

### 3.2

1. If any of your validation methods indicate that your precondition assump- tions were not met by the data, document what preconditions were violated and in what way. Update your validation methods one error at a time, documenting all issues, until you can read in all the data. If this did not happen, mention that.

	Our preconditions for Race and and the Recid degree were violated. We did not take Native American into account for Race. And we did not take the following into account for Recid degree: (MO3), (F5), (F6), (F7), (CO3). This led to 69 entries of the data table to not process. After adjusting the preconditions, the data is all able to be read.

2. Describe a person who would generate data that would not pass your (updated) precondition assumptions.

The person who would generate data that would not pass precondition is one who has typo information.

This person would also break the current parameters: 

	sex: N/A
	race: Pacific Islander [not listed in current preconditions for race]
	cChargeDegree: N/A 
	cChargeDesc: - string so can’t break-
	decileScore: N/A  
	scoreText：N/A 
	twoYearRecid：if the person is under investigation- would create a grey area not listed in precondition
	rChargeDegree：suspected charge or 4th degree misdemeanor (exists in NJ)
	rChargeDesc：-string so can’t break-
	
	*for N/A: means that the preconditions should hold up for all possible values for that field

### 5.1

Choices made: We decided that if someone had committed a misdemeanor it would not count as recidivism. As, from what we could see, quite a few of the misdemeanors seemed to be rather inconsequential. (Even those ranked as M1. For example, possessing cannabis is a M1 degree but that is legal in some states now.)

Our resulting analysis can be seen in the main file. We made another table using the same parameters as before (except we were removing those who committed a misdemeanor later on). 

### Read about pair programming and how to navigate through it

I recommend you to read the pair ["Pair Programming Guide - Please read this before you start working on the assignment.pdf"](https://drive.google.com/file/d/1tmSYwfKKXeMVDXvT6q5fPHPbF_vMF9Z4/view?usp=sharing). Make sure you fill the log provided in **pair-programming folder** as accurately as possible. The log shall be graded. 

### Work on pre-lab

Pre-labs are to prepare you for the actual lab. Please take a look at pre-lab2-tasks.pdf in cs106-lab2/tasks/ to read the pre-lab2 tasks. 

### Work on the lab tasks
Tasks that you have to complete are listed in lab2_tasks.pdf provided in cs106-lab2/tasks/. Please spend time reading the tasks and planning how you would complete them with your partner before implementing them.  

### Reuse of what you have already completed
The tasks of this lab, build upon the tasks that you have completed in lab1. Please make reuse of the code that you have already written in lab1. 


### Lab resources are also available in the Google Drive folder shared with you, which has lectures and slides. 

[Google Drive Links for lecture, lab, and assignment](https://drive.google.com/drive/folders/1EuAYlyaFLN97TI7PzW0b8PfPxxaD5Zsk?usp=sharing)

### Additional Lab Questionnaire

(None of your answers below will affect your grade; this is to help refine lab
assignments in the future)

1. Approximately how many hours did you take to complete this lab? (provide your answer as a single integer on the line below)

	8

2. How difficult did you find this lab? (1-5, with 5 being very difficult and 1 being very easy)

	3

3. Describe the biggest challenge you faced in this lab:

	We came across a lot of little errors. That we eventually figured out but cost us quite a bit of time. 

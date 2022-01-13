/**
* Lab 3 for CS106 - Linked List and Lotteries
*
* This lab is creating a linked list for the lottery system of classes. Each student in the lottery
* is a node (called student node). They are listed in order (highest to lowest) based on their point 
* value. This order determines their position in the lottery (if they are enrolled, on the waitlist, or 
* not in the lottery (position beyond waitlist capacity)). 
*
* @author: Neha Thumu 
* @version: March 21, 2021
*/

package lottery.linkedlist;

import java.io.FileReader;
import java.util.ArrayList;

import com.opencsv.CSVReader;

public class Main {

	public static void main(String[] args) {
		try {
			// reading data from given csv file
			CSVReader reader;
			reader = new CSVReader(new FileReader(args[0]));
			ArrayList<String[]> data = new ArrayList<String[]>(
					reader.readAll());
			reader.close();

			// transforming data in student nodes & making linked list based on that
			StudentLinkedList linkedList = new StudentLinkedList(data.get(0));
			for (int i = 1; i < data.size(); i++) {
				StudentNode student = new StudentNode(data.get(i));
				linkedList.add(student);
			}

			// checking if the linked lists are properly printing
			// printList(linkedList);

			// --find tag: printing position of given person's lottery
			// cycles through the list and tries to find person based on name
			// then, prints their position based along with info about enrolled/waitlisted
			// if not on list, then will print that
			if (args.length > 1 && args[1].equals("--find")) {
				String studentName = args[2];
				String courseName = args[3];
				String status = "";
				int count = 1;
				boolean studentFound = false;

				if (linkedList.size() != 0) {
					StudentNode currentStudent = linkedList.first();

					// cycles through until currentStudent is last (not trailer)
					while (currentStudent.getNext() != null) {
						if (currentStudent.getName().equals(studentName)) {

							// count decides if enrolled or waitlisted & position
							if (count <= linkedList.getCourseInfo()
									.getEnrollCapacity()) {
								status = "E" + count;
							} else {
								status = "W" + (count - linkedList
										.getCourseInfo().getEnrollCapacity());
							}

							// if waitlist full, then given student not in lottery
							if (count > linkedList.getCourseInfo()
									.getWaitlistCapacity()
									+ linkedList.getCourseInfo()
											.getEnrollCapacity()) {
								studentFound = false;
							} else {
								studentFound = true;
								System.out.println(String.format(
										"%s's position in %s lottery is %s.",
										studentName, courseName, status));
							}
						}
						count++;
						currentStudent = currentStudent.getNext();
					}
				}

				// if student not found, capacity reached, or if the linked list is empty
				if (!studentFound) {
					System.out.println(
							String.format("%s is not on the lottery for %s.",
									studentName, courseName));
				}

				// --print tag: printing the lottery results based on mode options are all, enrolled, and waitlisted
			} else if (args.length > 1 && args[1].equals("--print")) {
				String courseName = args[2];
				String mode = args[3];
				int count = 1;

				// heading: basic info from --print tag input
				System.out.println(
						String.format("%s Lottery: %s", courseName, mode));

				if (linkedList.size() != 0) {
					StudentNode currentStudent = linkedList.first();

					// cycles through until currentStudent is last (not trailer)
					while (currentStudent.getNext() != null) {
						String status;
						boolean isEnrolled;
						String outputMsg;

						// checking if within enroll capacity & setting msg based on that
						if (count <= linkedList.getCourseInfo()
								.getEnrollCapacity()) {
							status = "E" + count + ".";
							isEnrolled = true;
							outputMsg = String.format("%s %s %s %s", status,
									currentStudent.getName(),
									currentStudent.getClassYear(),
									currentStudent.getPoints());

						} else {
							// checking if within waitlust capacity & setting msg based on that
							status = "W" + (count - linkedList.getCourseInfo()
									.getEnrollCapacity()) + ".";
							isEnrolled = false;

							// if past waitlist & enrolled capacity
							if (count > linkedList.getCourseInfo()
									.getWaitlistCapacity()
									+ linkedList.getCourseInfo()
											.getEnrollCapacity()) {
								outputMsg = String.format(
										"%s is not on the lottery for %s",
										currentStudent.getName(), courseName);

							// if within waitlist capacity
							} else {
								outputMsg = String.format("%s %s %s %s", status,
										currentStudent.getName(),
										currentStudent.getClassYear(),
										currentStudent.getPoints());
							}
						}

						// outputs depending on the mode
						if (mode.equals("all")) {
							System.out.println(outputMsg);

						} else if (mode.equals("enrolled")) {
							if (isEnrolled) {
								System.out.println(outputMsg);
							}

						} else {
							System.out.println(outputMsg);

						}

						count++;
						currentStudent = currentStudent.getNext();
					}

				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	/**
	 * printing content of nodes in a given linked list
	 * 
	 * @param ArrayList<String[]> linkedList
	 * @return N/A
	 */
	public static void printList(StudentLinkedList linkedList) {
		if (linkedList.size() != 0) {
			StudentNode currentStudent = linkedList.first();

			// iterating through and printing name and point fields of nodes
			while (currentStudent.getNext() != null) {
				System.out.println(currentStudent.getName() + "  "
						+ currentStudent.getPoints());
				currentStudent = currentStudent.getNext();
			}
		}
	}
}

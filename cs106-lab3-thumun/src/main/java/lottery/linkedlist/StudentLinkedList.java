/**
* This class that takes student notes and orgainzes them into a linked list. The list is 
* ordered from highest to lowest. This ordering scheme determines the node's enrollment status. 
*
* There are also methods implemented to get information from the list (first node, last node, 
* size, if it is empty). There are also methods to add new nodes to the list. As well as one
* to get information from the courseInfo object. 
*
* @author: Neha Thumu 
* @version: March 21, 2021
*/

package lottery.linkedlist;

public class StudentLinkedList {
	private StudentNode header;
	private StudentNode trailer;
	private int size = 0;
	CourseInfo courseInfo;

	/**
	 * constructor for the linked list. Sets the header and tailer as none &
	 * sets object of courseinfo
	 * 
	 * @param String[] courseInfo
	 * @return N/A
	 */
	public StudentLinkedList(String[] courseInfo) {
		header = new StudentNode(null, null);
		trailer = new StudentNode(header, null);
		header.setNext(trailer);
		this.courseInfo = new CourseInfo(courseInfo);
	}

	/**
	 * getting the size of the linked list
	 * 
	 * @param N/A
	 * @return N/A
	 */
	public int size() {
		return size;
	}

	/**
	 * checking if the linked list is empty
	 * 
	 * @param N/A
	 * @return N/A
	 */
	public boolean isEmpty() {
		return (size == 0);
	}

	/**
	 * getting the first node in the list
	 * 
	 * @param N/A
	 * @return N/A
	 */
	public StudentNode first() {
		if (isEmpty()) {
			return null;
		} else {
			return header.getNext();
		}
	}

	/**
	 * getting the last node in the list
	 * 
	 * @param N/A
	 * @return N/A
	 */
	public StudentNode last() {
		if (isEmpty()) {
			return null;
		} else {
			return header.getPrevious();
		}
	}

	/**
	 * the method to add a given student to the existing list. If the list is
	 * empty, then student added between header and trailer. If other items in
	 * list, then student compared with other nodes and added accordingly
	 * 
	 * @param StudentNode student
	 * @return N/A
	 */
	public void add(StudentNode student) {
		student.setPoints(courseInfo); // adding info to points attr of student
		if (isEmpty()) {
			// if linked list empty, student added btwn header and trailer
			header.setNext(student);
			student.setPrevious(header);
			student.setNext(trailer);
			trailer.setPrevious(student);
			size += 1;
		} else {
			// If other items in list, then student compared with other nodes
			// and added before node it's greater than
			StudentNode currentStudent = first();

			while (currentStudent.getNext() != null) { // null = trailor
				if (student.compareTo(currentStudent) >= 0) {
					insertBefore(currentStudent, student);
					return;
				}

				currentStudent = currentStudent.getNext();
			}
			// if student node not greater than any in list, added to the end of
			// list (before trailer)
			insertBefore(trailer, student);
		}
	}

	/**
	 * method that helps the add method. Logic for inserting a student node
	 * before the node it's greater than. Essentially, just relinking the
	 * previous and next
	 * 
	 * @param StudentNode predessorStudent, StudentNode student
	 * @return N/A
	 */
	private void insertBefore(StudentNode predessorStudent,
			StudentNode student) {
		predessorStudent.getPrevious().setNext(student);
		student.setPrevious(predessorStudent.getPrevious());
		student.setNext(predessorStudent);
		predessorStudent.setPrevious(student);
		size += 1;
	}

	/**
	 * to get info from courseInfo
	 * 
	 * @param N/A
	 * @return N/A
	 */
	public CourseInfo getCourseInfo() {
		return courseInfo;
	}
}

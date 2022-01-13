package prelab;
import java.util.*;
import java.lang.StringBuilder;

public class AllStudents {
	ArrayList<Student> allStudent;
	
	public AllStudents() {
		this.allStudent  = new ArrayList<Student>();
	}
	
	
	// input = array of strings 
	// use a constructor from student class - to convert to student obj (??)
	// add it to the arraylist
	
	
	public void addStringArray(ArrayList<String[]> rows) throws Exception{
		for (int i = 0; i < rows.size(); i++) {
			Student Temp = new Student(rows.get(i)); 
			allStudent.add(Temp);
		}
	}
	
	
	/*
	public void addStringArray(Student temp) throws Exception{
		allStudent.add(temp);
		//return allStudent;
	}
	*/
	
	// fix toString in student file 
	// figure out string builder 
	@Override
	public String toString() { // think there's a problem here 
		StringBuilder output = new StringBuilder();
		for (int i = 0; i < allStudent.size(); i++) { // 6 = num of columns 
			output.append(allStudent.get(i).toString() + "\n");
		}
		return output.toString();
	}
}

package zut.wi.edziekanat.entity;

import java.util.List;




public class GrupaLab 
{	
	String id;	
	
	int licznosc;		
	
	List<Student> students;

	public int getLicznosc() {
		return licznosc;
	}

	public void setLicznosc(int licznosc) {
		this.licznosc = licznosc;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public String getId() {
		return id;
	}
	
	

}

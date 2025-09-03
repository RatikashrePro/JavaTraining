// Student Management system : Store, Search, and update student data
package com;

import java.util.*;

class Student{
	int id;
	int marks;
	String name;
	Student(int id, String name, int marks){
		this.id= id;
		this.name= name;
		this.marks= marks;
	}
	public String toString(){
		return "Id: "+ id + " Name: " + name + " Marks: "+marks;
	}
}	

public class Project1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Student> students= new ArrayList<Student>();
		System.out.println("Welcome to student management system");
		Scanner sc= new Scanner(System.in);
		int ch;
		do {
			System.out.println("Choose an option");
			System.out.println("\t1.Add student");
			System.out.println("\t2.Search student using id");
			System.out.println("\t3.Delete student using id");
			System.out.println("\t4.Display all student");
			System.out.println("\t5.Exit ");
			System.out.println("Choice:");
			ch=sc.nextInt();
			if(ch==1) {
				System.out.println("Enter id");
				int id=sc.nextInt();
				System.out.println("Enter Name");
				String name=sc.next();
				System.out.println("Enter marks");
				int marks=sc.nextInt();
				students.add(new Student(id,name,marks));
			}
			else if(ch==2) {
				System.out.println("Enter id to search");
				int searchid=sc.nextInt();
				int f=0;
				for(Student s: students) {
					if(s.id==searchid) {
						System.out.println(s.toString());
						f=1;
						break;
					}
				}
				if(f==0) {
					System.out.println("id not found in db");
				}
		
			}
			else if(ch==3) {
				System.out.println("Enter id to delete");
				int delid=sc.nextInt();
				int f=0;
				for(Student s: students) {
					if(s.id==delid) {
						System.out.println("Record deleted");
						students.remove(s);
						f=1;
						break;
					}
				}
				if(f==0) {
					System.out.println("id not found in db");
				}
				

			}
			else if(ch==4) {
				int f=0;
				if(students.isEmpty()) {
					System.out.println("No records to display");
					f=1;
				}
				if(f==0) {
				Iterator i= students.iterator();
				while(i.hasNext()) {
					System.out.println(i.next());
					
				}
				}
			}


		}while(ch!=5);
		sc.close();
		

	}


}

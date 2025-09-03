//package com;
//import java.util.Scanner;
// 
//abstract class Emp{
//	Scanner sc = new Scanner(System.in);
//	int uid, age;
//	String name;
//	double salary;
//	String desig;
//	double bonus;
//	
//	Emp(){
//		System.out.println("ENter ID :");   uid= sc.nextInt();
//		System.out.println("ENter Name :"); name= sc.next();
//		System.out.println("ENter AGE :");  age = sc.nextInt();
//	}
//	public void display()
//	{
//		System.out.println("ID :" + uid);
//		System.out.println("NAME :"+ name);
//		System.out.println("Age :"+ age);
//		System.out.println("Salary :"+ salary);
//		System.out.println("Bonus :" + bonus);
//		System.out.println("Designtion :"+ desig);
//	}
//	
//	abstract void raiseSalary();
//	abstract void bonus();
//}
//class Clerk extends Emp{
//	
//	Clerk(){
//		 salary=25000;
//		 desig="CLEKR";
//	}
//	void raiseSalary() {
//		salary= salary+ 5000;
//	}
//	
//	void  bonus() {
//		bonus = 10000;
//	}
//}
//class Tester extends Emp{
//	
// 
//	Tester(){
//		salary=40000;
//		desig="Tester";
//	}
//	void raiseSalary() {
//		salary= salary+ 5000;
//	}
//	
//	void bonus() {
//		bonus = 40000;
//	}
//}
//class Developer extends Emp
//{
//	
//	Developer(){
//		
//		salary=70000;
//		desig="Developer";
//		
//	}
//	void raiseSalary() {
//		salary= salary+ 5000;
//	}
//	
//	void bonus() {
//		bonus = 50000;
//	}
//}
// 
//class Manager extends Emp
//{
//	
//	Manager(){
//		
//		salary=90000;
//		desig="Manager";
//	}
//	void raiseSalary() {
//		salary= salary+ 5000;
//	}
//	
//	void bonus() {
//		bonus = 80000;
//	}
//}
// 
//public class  Dummy2 {
//	public static void main(String[] args) {
//		
//		Scanner sc = new Scanner(System.in);
//		int ch1,ch2=0;
//		Clerk c = null;
//		Developer d = null;
//		Tester t = null;
//		Manager m = null;
//		do {
//			System.out.println("1 ) Create ");
//			System.out.println("2 ) Display ");
//			System.out.println("3 ) Raise Salary ");
//			System.out.println("4 ) Add Bonus ");
//			System.out.println("5 ) Exit ");
//			ch1=sc.nextInt();
//			if(ch1==1) {
//				do {
//					System.out.println("	1 ) Clerk  ");
//					System.out.println("	2 ) Developer ");
//					System.out.println("	3 ) Tester  ");
//					System.out.println("	4 ) Manager ");
//					System.out.println("	5 ) Exit ");
//					ch2=sc.nextInt();
//					if(ch2==1) {
//						c = new Clerk();
//					}
//					if(ch2==2) {
//						 d = new Developer();
//					}
//					if(ch2==3) {
//						 t = new Tester();
//					}
//					if(ch2==4) {
//						 m = new Manager();
//					}
//					
//				}while(ch2!=5);
//			}
//			if(ch1==2) {
//				do {
//					System.out.println("	1 ) Clerk  ");
//					System.out.println("	2 ) Developer ");
//					System.out.println("	3 ) Tester  ");
//					System.out.println("	4 ) Manager ");
//					System.out.println("	5 ) Exit ");
//					ch2=sc.nextInt();
//					if(ch2==1) {
//						c.display();
//					}
//					if(ch2==2) {
//						d.display();
//					}
//					if(ch2==3) {
//						t.display();
//					}
//					if(ch2==4) {
//						m.display();
//					}
//					
//				}while(ch2!=5);
//			}
//			if(ch1==3) {
//				do {
//					System.out.println("	1 ) Clerk  ");
//					System.out.println("	2 ) Developer ");
//					System.out.println("	3 ) Tester  ");
//					System.out.println("	4 ) Manager ");
//					System.out.println("	5 ) Exit ");
//					ch2=sc.nextInt();
//					if(ch2==1) {
////						c.dispay(); RaiseSalaey method
//						c.raiseSalary();
//						System.out.println("Salary Raised....!");
//					}
//					if(ch2==2) {
//						d.raiseSalary();
//						System.out.println("Salary Raised....!");
//					}
//					if(ch2==3) {
//						t.raiseSalary();
//						System.out.println("Salary Raised....!");
//					}
//					if(ch2==4) {
//						m.raiseSalary();
//						System.out.println("Salary Raised....!");
//					}
//					
//				}while(ch2!=5);
//			}
//			if(ch1==4) {
//				do {
//					System.out.println("	1 ) Clerk  ");
//					System.out.println("	2 ) Developer ");
//					System.out.println("	3 ) Tester  ");
//					System.out.println("	4 ) Manager ");
//					System.out.println("	5 ) Exit ");
//					ch2=sc.nextInt();
//					if(ch2==1) {
////						c.dispay(); RaiseSalaey method
//						c.bonus();
//						System.out.println("Bonus added....!");
//					}
//					if(ch2==2) {
//						d.bonus();
//						System.out.println("Bonus added....!");
//					}
//					if(ch2==3) {
//						t.bonus();
//						System.out.println("Bonus added....!");
//					}
//					if(ch2==4) {
//						m.bonus();
//						System.out.println("Bonus added....!");
//					}
//					
//				}while(ch2!=5);
//			}
//			if(ch1==5) {
//				System.out.println("Thank you.....!");
//				System.exit(0);
//			}
//		}while(ch1!=5);
//        sc.close();
//	}
//}
//package com;
//
//import java.util.Scanner;
//
//class Emp{
//	Scanner sc=new Scanner(System.in);
//	int uid,age;
//	String name, desig;
//	double salary;
//	Emp(){
//		System.out.println("Enter ID: ");
//		uid=sc.nextInt();
//		System.out.println("Enter Name: ");
//		name=sc.next();
//		System.out.println("Enter Age: ");
//		age=sc.nextInt();
//	}
//	void display() {
//		System.out.println("=================================");
//		System.out.println(uid + " : " +name+ " : "+age+ " : "+salary+" : "+desig);
//	}
//	
//}
//
//class Clerk extends Emp{
//	
//	Clerk(){
//		desig="Clerk";
//		salary=25000; 
//	}
//	
//	void raiseSalary() {
//		salary+=5000;
//	}
//}
//
//class Tester extends Emp{
//	Tester(){
//		 desig="Tester";
//		 salary=40000;
//	}
//	void raiseSalary() {
//		salary+=10000;
//	}
//}
//
//class Developer extends Emp{
//	Developer(){
//		 desig="Developer";
//		 salary=60000;
//	}
//	void raiseSalary() {
//		salary+=20000;
//	}
//}
//
//class Manager extends Emp{
//	Manager(){
//		 desig="Manager";
//		 salary=90000;
//		
//	}
//	void raiseSalary() {
//		salary+=25000;
//	}
//}
//public class Dummy {
//
//	public static void main(String[] args) {
////		Clerk a =new Clerk();
////		a.display();
////		Tester b =new Tester();
////		b.display();
////		Developer c =new Developer();
////		c.display();
////		Manager d =new Manager();
////		d.display();
//		int choice, ch;
//		Clerk c=null;
//		Developer d=null;
//		Tester t=null;
//		Manager m=null;
//		Scanner sc=new Scanner(System.in);
//		do {
//			System.out.println("Choose one of the below options:");
//			System.out.println("1.Create"+"\n"+"2.Display"+"\n"+"3.Raise Salary \n4.Exit");
//			choice= sc.nextInt();
//			switch(choice) {
//			case 1:
//				do {
//					System.out.println("Choose the desig \n 1.Clerk \n 2.Developer \n 3.Tester \n 4. Manager \n 5.Exit");
//				    ch=sc.nextInt();
//				    switch(ch) {
//				    case 1: 
//				    	c=new Clerk();
//				    	break;
//				    case 2:
//				    	d=new Developer();
//				    	break;
//				    case 3:
//				    	t=new Tester();
//				    	break;
//				    case 4:
//				    	m=new Manager();
//				    	break;
//				    default:
//				    	System.out.println("Exited");
//				    	ch=5;
//				 
//				    }
//				}while(ch<5);
//				break;
//			case 2:
//				
//				do {
//					System.out.println("Choose the desig \n 1.Clerk \n 2.Developer \n 3.Tester \n 4. Manager \n 5.Exit");
//				    ch=sc.nextInt();
//				    switch(ch) {
//				    case 1: 
//				    	if(c!=null) c.display();
//				    	else System.out.println("~~~~~~~~~~~~~~No data found~~~~~~~~~~~~~~~");
//				    	break;
//				    case 2:
//				    	if(d!=null) d.display();
//				    	else System.out.println("~~~~~~~~~~~~~~No data found~~~~~~~~~~~~~~~");
//				    	break;
//				    case 3:
//				    	if(t!=null) t.display();
//				    	else System.out.println("~~~~~~~~~~~~~~No data found~~~~~~~~~~~~~~~");
//				    	break;
//				    case 4:
//				    	if(m!=null) m.display();
//				    	else System.out.println("~~~~~~~~~~~~~~No data found~~~~~~~~~~~~~~~");
//				    	break;
//				    default:
//				    	System.out.println("==================Exited===================");
//				    	ch=10;
//				    }
//				}while(ch<5);
//				break;
//				
//			case 3:
//				do {
//					System.out.println("Choose the desig \n 1.Clerk \n 2.Developer \n 3.Tester \n 4. Manager \n 5.Exit");
//				    ch=sc.nextInt();
//				    switch(ch) {
//				    case 1: 
//				    	if(c!=null) { c.raiseSalary();
//				    	System.out.println("Update done");}
//				    	else System.out.println("~~~~~~~~~~~~~~No data found~~~~~~~~~~~~~~~");
//				    	break;
//				    case 2:
//				    	if(d!=null) {d.raiseSalary();
//				    	System.out.println("Update done");}
//				    	else System.out.println("~~~~~~~~~~~~~~No data found~~~~~~~~~~~~~~~");
//				    	break;
//				    case 3:
//				    	if(t!=null) { t.raiseSalary();
//				    	System.out.println("Update done");}
//				    	else System.out.println("~~~~~~~~~~~~~~No data found~~~~~~~~~~~~~~~");
//				    	break;
//				    case 4:
//				    	if(m!=null) { m.raiseSalary();
//				    	System.out.println("Update done");}
//				    	else System.out.println("~~~~~~~~~~~~~~No data found~~~~~~~~~~~~~~~");
//				    	break;
//				    default:
//				    	System.out.println("=================Exited===================");
//				    	ch=10;
//				    }
//				}while(ch<5);
//				break;
//				
//			default:
//				System.out.println("Exited.....Bye!");
//				choice=4;
//			}
//		}while(choice<4);
//		
//		sc.close();
//	}
//
//}
package com;

import java.util.Scanner; 

class A{
	Scanner sc= new Scanner(System.in);
	void display() throws InvalidException {
		System.out.println("Enter the age: ");
		int age= sc.nextInt();
		if(age>60) {
			throw new InvalidException("Age should not be greater than 60");
		}
		else if(age<18) {
			throw new InvalidException("Age should not be less than 18");
		}
		else {
			System.out.println("Valid age"); 
		}
		
	}
}

class InvalidException extends Exception{
	InvalidException(String msg){
		super(msg);
	}
}
public class Demo5 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			A a= new A();
			a.display();
		}
		catch(InvalidException e) {
			System.out.println(e.getMessage());
		}
	}

}

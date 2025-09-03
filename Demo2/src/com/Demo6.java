package com;
//import java.io.FileNotFoundException;
import java.io.IOException;
//import java.util.Scanner;
class AA{
	
	void display() throws InvalidExceptionn, IOException,ClassNotFoundException
	{
		for(int i=0;i<=20;i++) {
			System.out.println("I :"+i);
			if(i==19) throw new InvalidExceptionn("Invalid age ");		// raise exp manually
			if(i==17) throw new NullPointerException();
			if(i==15) throw new IOException();
			if(i==14) throw new ClassNotFoundException();
		}
	}
}
public class Demo6 {
	public static void main(String[] args) {
		try {																// to ad dthe risky code
			AA a = new AA();
			a.display();
		}
		catch (NullPointerException e) {									// to handle to give alternate msg
			System.out.println("NullPointerException");
		}
		catch (InvalidExceptionn e) {
			System.out.println(e.getMessage());
		}
		
		catch (IOException e) {
			System.out.println("IOException");
		}
		catch (ClassNotFoundException e) {
			System.out.println("ClassNotFound Exception");
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		finally {															// finally will execute alsways
			System.out.println("Thank you");
		}
	}
}
class InvalidExceptionn extends Exception{
	InvalidExceptionn(String msg){
		super(msg);
	}
}
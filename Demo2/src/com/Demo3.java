//Exception handling
package com;

import java.util.*;



public class Demo3
{
    public static void main(String[] args) 
    {   
    	A();
    }
    static void A() {
    	B();
    	System.out.println("This is class A");
    }
    static void B() {
    	C();
    	System.out.println("This is class B");
    }
    static void C() {
    	try {
    	System.out.println("This is class C");
    	Scanner sc=new Scanner(System.in);
    	System.out.println("Enter A value: ");
    	int a=sc.nextInt();
    	System.out.println("Enter B value: ");
    	int b=sc.nextInt();
    	int c=a/b;
    	System.out.println("Result: "+c);
    	sc.close();
    	}
    	catch(InputMismatchException ie){
    		System.out.println("Enter valid input only");
    		
    	}
    	catch(ArithmeticException ae) {
    		System.out.println("B value cannot be zero");
    	}
    	finally {
    		System.out.println("Finally block");
    	}
    }
}
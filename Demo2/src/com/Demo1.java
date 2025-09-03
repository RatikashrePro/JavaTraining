//final keyword

package com;

class Car{
	private int model=2000;
	private String color="Blue"; // can be accessed using getter and setter only
	final int speed=200;
	final void gear() {   // final method cannot be overridden 
		System.out.println("5 gear");
	}
}

class BMW extends Car{
	void ABC() {
		System.out.println("BMW class");
	}
}

final class Bentley extends Car{ // class Bentley cannot be extended
	
}
public class Demo1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BMW b1= new BMW();
		b1.gear();
		
		
	}

}

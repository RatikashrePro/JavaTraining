package com;

@FunctionalInterface  // single abstract method
interface Bank{
	int depo();
}
interface B {
	void roi();
	void atm();
}

interface c extends Bank, B{ //marker class---> has not methods of its own
	
	
}

public class Demo1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Bank d= () -> {return 5;};
		System.out.println(d.depo());
	}

}

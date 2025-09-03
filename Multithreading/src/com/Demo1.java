package com;

class Person implements Runnable{
	public synchronized void run() {
		for(int i=0;i<=5;i++) {
			try {
				System.out.println(Thread.currentThread().getName()+" : "+i);
				Thread.sleep(1000);
			}catch(InterruptedException e) {
				System.out.println(e);
			}
		}
	}
}
public class Demo1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Person p= new Person();
		
		Thread p1=new Thread(p); 
		Thread p2= new Thread(p);p1.setName("ratika");
		p2.setName("deepti");

		p1.start(); p2.start();


	}

}

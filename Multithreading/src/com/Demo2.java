package com;
class Car implements Runnable{
	public void run(){
		for(int i = 0 ;i<=25 ;i++){
			try{
				if(Thread.currentThread().getName().equals("Bmw")){
					System.out.println(Thread.currentThread().getName() +" : "+ i);
				}
				if(Thread.currentThread().getName().equals("Benz")){
					System.out.println("	"+Thread.currentThread().getName() +" : "+ i);
				}
				Thread.sleep(500);
			}catch(Exception e ){}
		}
	}
}
public class Demo2 {
	public static void main(String[] args) {
		Car a = new Car();
		Thread t1= new Thread(a);		Thread t2= new Thread(a);
		t1.setName("Bmw");			t2.setName("Benz");
		t1.start();				t2.start();
		t1.setPriority(5);
		t2.setPriority(1);
		for(int k = 0 ;k<=25 ;k++){
			try{
				System.out.println(" 			K "+ k);
				Thread.sleep(500);
				//if(k==10){ t1.suspend(); }
			//if(k==15){ t1.resume(); }
				//if(k==17) {t2.suspend();}
				//if(k==20) { System.out.println("Is ALive :"+ t2.isAlive());}
				if(k==21) {t2.stop();}
				if(k==22) { System.out.println("Is ALive :"+ t2.isAlive());}
			}
			catch(Exception e ){}
		}

	}
}

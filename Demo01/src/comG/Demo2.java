package comG;


interface Bankk{
	abstract void roi();
	abstract public void ATM();
}

class Sbii implements Bankk{
	@Override
	public void roi() {
		System.out.println("10%");
	}
	
	@Override
	public void ATM(){
		System.out.println("DEBIT");
	}
}

class Axiss implements Bankk{
	@Override
	public void roi() {
		System.out.println("12%");
	}
	
	@Override
	public void ATM(){
		System.out.println("DEBIT, CREDIT");
	}
}
public class Demo2 {
	public static void main(String args[]) {
		
	}

}

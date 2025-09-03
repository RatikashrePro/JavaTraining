package comG;

abstract class Bank{
	abstract void roi();
	abstract void ATM();
}

class Sbi extends Bank{
	@Override
	void roi() {
		System.out.println("10%");
	}
	
	@Override
	void ATM(){
		System.out.println("DEBIT");
	}
}

class Axis extends Bank{
	@Override
	void roi() {
		System.out.println("12%");
	}
	
	@Override
	void ATM(){
		System.out.println("DEBIT, CREDIT");
	}
}
public class Demo1 {

	public static void main(String[] args) {
		Sbi s= new Sbi();
		s.roi();
		Axis a= new Axis();
		a.roi();

	}

}

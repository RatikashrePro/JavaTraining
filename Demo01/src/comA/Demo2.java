package comA;

class Bank{
	public void roi() {
		System.out.println("10%");
	}
}

class Sbi extends Bank{
	
}

class Axis extends Bank{
	@Override
	public void roi() {
		System.out.println("12%"); //same signature but diff implementation in child class
	}
}

public class Demo2 {
	public static void main(String args[]) {
		Sbi s= new Sbi();
		s.roi();
		Axis a = new Axis();
		a.roi();
	}
}

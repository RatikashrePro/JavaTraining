package comF;

interface I{
	public static final int a =1000;
	int b=100; // by default the variables inside the interface are public static and final
	//I(){}
	int x=100;
	//private static int y=1000;
	abstract void roi(); 
	abstract void abc();
	default void aa() {}
	static void bb() {}
	
}
interface J{
	
}
interface K extends J,I{
	
}
public class Demo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//I i = new I();

	}

}

package comA;
class A{
	A(){
		System.out.println("A()");
	}
	A(int a){
	   System.out.println("A(int a)");
	}
	void draw() {
		System.out.println("Drawing....");
	}
	void draw(int r) {
		System.out.println("Drawing..."+ 3.14*r*r);
	}
	void draw(int l, int b) {
		System.out.println("Drawing..."+l*b);
	}
}

class B extends A{
	@Override 
	public void draw(int r) {
		System.out.println("Drawing..." + 2*3.14*r);
	}
}
public class Demo01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		A a=new A();
		a.draw();
		B b=new B();
		b.draw();
		b.draw(10);
		b.draw(10,20);

	}

}

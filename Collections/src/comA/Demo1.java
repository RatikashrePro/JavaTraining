package comA;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Demo1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList a1= new ArrayList(5);
		//System.out.println("==================");

		a1.add(100);
		a1.add(200);
		a1.add(300);
		a1.add(400);
		a1.add(500);
		System.out.println(a1);
		for(int i=0;i<a1.size();i++) {
			System.out.println(a1.get(i));
		}
		System.out.println("==================");
		Iterator i= a1.iterator();
		while(i.hasNext()) {
			System.out.println(i.next());
		}
		
		Collections.reverse(a1);
		System.out.println(a1);
		Collections.shuffle(a1);
		System.out.println(a1);
		Collections.min(a1);
		Collections.max(a1);
		Collections.swap(a1, 1, 4);
		
		Collections.frequency()
		Collections.fill(a1, 7);
		

	}

}

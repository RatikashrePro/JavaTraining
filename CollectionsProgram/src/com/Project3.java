//Shopping cart
package com;

import java.util.*;


class ShoppingCart{
	int quantity;
	int price;
	String productname;
	int pid;
	ShoppingCart(int id, String name, int price, int quantity ){
		this.pid= id;
		this.productname= name;
		this.price= price;
		this.quantity=quantity;
	}
	public String toString(){
		return "Id: "+ pid + " Name: " + productname + " Price: "+price + " Quantity: "+quantity;
	}	
}

public class Project3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<ShoppingCart> cart= new ArrayList<ShoppingCart>();
		System.out.println("Welcome to student management system");
		Scanner sc= new Scanner(System.in);
		int ch;
		do {
			System.out.println("Choose an option");
			System.out.println("\t1.Add Product into cart");
			System.out.println("\t2.Search product using id");
			System.out.println("\t3.Remove product from cart");
			System.out.println("\t4.Display all products");
			System.out.println("\t5.Cart total");
			System.out.println("\t6.Exit ");
			System.out.println("Choice:");
			ch=sc.nextInt();
			if(ch==1) {
				System.out.println("Enter product id");
				int id=sc.nextInt();
				System.out.println("Enter product Name");
				String name=sc.next();
				System.out.println("Enter product price");
				int price=sc.nextInt();
				System.out.println("Enter quantity");
				int quantity=sc.nextInt();

				cart.add(new ShoppingCart(id,name,price, quantity));
			}
			else if(ch==2) {
				System.out.println("Enter id to search");
				int searchid=sc.nextInt();
				int f=0;
				for(ShoppingCart s: cart) {
					if(s.pid==searchid) {
						System.out.println(s.toString());
						f=1;
						break;
					}
				}
				if(f==0) {
					System.out.println("Product not found");
				}
		
			}
			else if(ch==3) {
				System.out.println("Enter id to delete");
				int delid=sc.nextInt();
				int f=0;
				for(ShoppingCart s: cart) {
					if(s.pid==delid) {
						System.out.println("Item removed from cart");
						cart.remove(s);
						f=1;
						break;
					}
				}
				if(f==0) {
					System.out.println("id not found in db");
				}
				

			}
			else if(ch==4) {
				int f=0;
				if(cart.isEmpty()) {
					System.out.println("No items in cart");
					f=1;
				}
				if(f==0) {
				Iterator i= cart.iterator();
				while(i.hasNext()) {
					System.out.println(i.next());
					
					
				}
				}
			}
			else if(ch==5) {
				System.out.println("Total cart value:");
				int total=0;
				for(ShoppingCart c: cart) {
					total+=c.price*c.quantity;
				}
				System.out.println(total);
			}


		}while(ch!=6);
		sc.close();
		

	}

}


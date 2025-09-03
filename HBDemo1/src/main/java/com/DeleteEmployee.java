package com;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class DeleteEmployee {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Configuration cfg= new Configuration(); cfg.configure("hibernate.cfg.xml");
	SessionFactory factory= cfg.buildSessionFactory();
		Session session = factory.openSession();
		Transaction tx= session.beginTransaction();
		Employee e= new Employee();
		Scanner sc= new Scanner(System.in);
		System.out.println("ID"); int id= sc.nextInt();
		e.setId(id);
		session.save(e);
		tx.commit();
		System.out.println("Data inserted ....!");
		sc.close();
	}

}

package com;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class MainApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Configuration cfg= new Configuration(); cfg.configure("hibernate.cfg.xml");
		SessionFactory factory= cfg.buildSessionFactory();
		Session session = factory.openSession();
		Transaction tx= session.beginTransaction();
		Employee e= new Employee();
		e.setId(200);
		e.setName("S S Ratikashre");
		e.setAge(22);
		e.setDesig("Developer");
		e.setSalary(4000000);
		session.save(e);
		tx.commit();
		System.out.println("Data inserted ....!");
	}

}

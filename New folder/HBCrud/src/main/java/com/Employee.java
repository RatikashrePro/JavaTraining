package com;
 
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
 
@Getter
@Setter
@Entity
@Table(name="myemp")
public class Employee {
	@Id
	private int id;
	private String name;
	private int age;
	private int salary;
	private String desig;
}
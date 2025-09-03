package com;

import java.util.*;
import java.util.stream.*;

class Employee {
    int id;
    String name;
    String department;
    double salary;

    Employee(int id, String name, String department, double salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return id + " - " + name + " - " + department + " - " + salary;
    }
}

public class Streamapi1 {

	public static void main(String[] args) {
		List<Employee> employees = Arrays.asList(
			    new Employee(1, "Alice", "IT", 60000),
			    new Employee(2, "Bob", "HR", 45000),
			    new Employee(3, "Charlie", "IT", 75000),
			    new Employee(4, "David", "Finance", 55000),
			    new Employee(5, "Eva", "HR", 70000)
			);

		//1. Find employees with salary > 50,000
		employees.stream().filter(n->n.salary>50000).collect(Collectors.toList()).forEach(System.out::println);
		//2. Get a list of employee names
		List<String> l=employees.stream().map(n->n.name).collect(Collectors.toList());
		System.out.println(l);
		//3. Find the highest paid employee
		System.out.println("Highest sal "+employees.stream().max(Comparator.comparingDouble( n-> n.salary)).get());
		//4. Group employees by department
		Map<String, List<Employee>> byDept=employees.stream().collect(Collectors.groupingBy(e -> e.department));
		System.out.println(byDept);
		//5. Find average salary of all employees
		System.out.println(employees.stream().mapToDouble(e->e.salary).average().getAsDouble());
		//6. Find names of employees in IT department
		employees.stream().filter(n->n.department=="IT").forEach(System.out:: println);;
		//7. Count employees in each department
		employees.stream().reduce(n -> n.department).forEach(System.out:: println);

		//8. Get list of top 2 highest paid employees
		
		
	}

}

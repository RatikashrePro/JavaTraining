package com;

import java.util.InputMismatchException;
import java.util.Scanner;

abstract class Emp {
    Scanner sc = new Scanner(System.in);
    int uid, age;
    String name;
    double salary;
    String desig;
    double bonus = 50000;

    Emp() {
        System.out.println("Enter ID :");
        uid = sc.nextInt();
        System.out.println("Enter Name :");
        name = sc.next();
        System.out.println("Enter AGE :");
        age = sc.nextInt();
    }

    public void display() {
        System.out.println("ID :" + uid);
        System.out.println("NAME :" + name);
        System.out.println("Age :" + age);
        System.out.println("Salary :" + salary);
        System.out.println("Designation :" + desig);
    }

    abstract void raiseSalary();
    abstract void Bonus();
}

class Clerk extends Emp {
    Clerk() {
        salary = 25000;
        desig = "Clerk";
    }
    void raiseSalary() { salary = salary + 5000; }
    void Bonus() { salary = salary + bonus; }
}

class Tester extends Emp {
    Tester() {
        salary = 40000;
        desig = "Tester";
    }
    void raiseSalary() { salary = salary + 5000; }
    void Bonus() { salary = salary + bonus; }
}

class Developer extends Emp {
    Developer() {
        salary = 70000;
        desig = "Developer";
    }
    void raiseSalary() { salary = salary + 5000; }
    void Bonus() { salary = salary + bonus; }
}

class Manager extends Emp {
    Manager() {
        salary = 90000;
        desig = "Manager";
    }
    void raiseSalary() { salary = salary + 5000; }
    void Bonus() { salary = salary + bonus; }
}

public class Demo1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int ch1 = 0, ch2 = 0;
        Clerk c = null;
        Developer d = null;
        Tester t = null;
        Manager m = null;

        do {
            try {
                System.out.println("\n1) Create ");
                System.out.println("2) Display ");
                System.out.println("3) Raise Salary ");
                System.out.println("4) Bonus ");
                System.out.println("5) Exit ");
                System.out.print("Enter your choice: ");
                ch1 = sc.nextInt();

                if (ch1 == 1) {
                    do {
                        System.out.println("\t1) Clerk ");
                        System.out.println("\t2) Developer ");
                        System.out.println("\t3) Tester ");
                        System.out.println("\t4) Manager ");
                        System.out.println("\t5) Exit ");
                        ch2 = sc.nextInt();

                        switch (ch2) {
                            case 1: c = new Clerk(); break;
                            case 2: d = new Developer(); break;
                            case 3: t = new Tester(); break;
                            case 4: m = new Manager(); break;
                        }
                    } while (ch2 != 5);
                }

                if (ch1 == 2) {
                    do {
                        System.out.println("\t1) Clerk ");
                        System.out.println("\t2) Developer ");
                        System.out.println("\t3) Tester ");
                        System.out.println("\t4) Manager ");
                        System.out.println("\t5) Exit ");
                        ch2 = sc.nextInt();

                        try {
                            switch (ch2) {
                                case 1: if (c == null) throw new NullPointerException("Clerk not created yet!"); c.display(); break;
                                case 2: if (d == null) throw new NullPointerException("Developer not created yet!"); d.display(); break;
                                case 3: if (t == null) throw new NullPointerException("Tester not created yet!"); t.display(); break;
                                case 4: if (m == null) throw new NullPointerException("Manager not created yet!"); m.display(); break;
                            }
                        } catch (NullPointerException e) {
                            System.out.println("Error: " + e.getMessage());
                        }

                    } while (ch2 != 5);
                }

                if (ch1 == 3) {
                    do {
                        System.out.println("\t1) Clerk ");
                        System.out.println("\t2) Developer ");
                        System.out.println("\t3) Tester ");
                        System.out.println("\t4) Manager ");
                        System.out.println("\t5) Exit ");
                        ch2 = sc.nextInt();

                        try {
                            switch (ch2) {
                                case 1: if (c == null) throw new NullPointerException("Clerk not created yet!"); c.raiseSalary(); System.out.println("Salary Raised!"); break;
                                case 2: if (d == null) throw new NullPointerException("Developer not created yet!"); d.raiseSalary(); System.out.println("Salary Raised!"); break;
                                case 3: if (t == null) throw new NullPointerException("Tester not created yet!"); t.raiseSalary(); System.out.println("Salary Raised!"); break;
                                case 4: if (m == null) throw new NullPointerException("Manager not created yet!"); m.raiseSalary(); System.out.println("Salary Raised!"); break;
                            }
                        } catch (NullPointerException e) {
                            System.out.println("Error: " + e.getMessage());
                        }

                    } while (ch2 != 5);
                }

                if (ch1 == 4) {
                    do {
                        System.out.println("\t1) Clerk ");
                        System.out.println("\t2) Developer ");
                        System.out.println("\t3) Tester ");
                        System.out.println("\t4) Manager ");
                        System.out.println("\t5) Exit ");
                        ch2 = sc.nextInt();

                        try {
                            switch (ch2) {
                                case 1: if (c == null) throw new NullPointerException("Clerk not created yet!"); c.Bonus(); System.out.println("Bonus Added!"); break;
                                case 2: if (d == null) throw new NullPointerException("Developer not created yet!"); d.Bonus(); System.out.println("Bonus Added!"); break;
                                case 3: if (t == null) throw new NullPointerException("Tester not created yet!"); t.Bonus(); System.out.println("Bonus Added!"); break;
                                case 4: if (m == null) throw new NullPointerException("Manager not created yet!"); m.Bonus(); System.out.println("Bonus Added!"); break;
                            }
                        } catch (NullPointerException e) {
                            System.out.println("Error: " + e.getMessage());
                        }

                    } while (ch2 != 5);
                }

                if (ch1 == 5) {
                    System.out.println("Thank you.....!");
                    System.exit(0);
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a number.");
                sc.nextLine(); // clear the invalid input
            }

        } while (ch1 != 5);

        sc.close();
    }
}



























//package com;
//
//import java.util.Scanner;
//abstract class Emp{
//	Scanner sc = new Scanner(System.in);	
//	int uid, age;
//	String name;
//	double salary;
//	String desig;
//	double bonus=50000;
//	Emp(){
//		System.out.println("ENter ID :");   uid= sc.nextInt();
//		System.out.println("ENter Name :"); name= sc.next();
//		System.out.println("ENter AGE :");  age = sc.nextInt();
//	}
//	public void display()
//	{
//		System.out.println("ID :" + uid);
//		System.out.println("NAME :"+ name);
//		System.out.println("Age :"+ age);
//		System.out.println("Salary :"+ salary);
//		System.out.println("Designtion :"+ desig);
//	}
//	abstract void raiseSalary();
//	abstract void Bonus();
//}
//class Clerk extends Emp{
//	
//	Clerk(){
//		 salary=25000;
//		 desig="Clerk";
//	}
//	
//	void raiseSalary() {
//		salary= salary+ 5000;
//	}
//	void Bonus() {
//		salary = salary+bonus;
//	}
//}
//class Tester extends Emp{
//	
//	Tester(){
//	    salary=40000;
//		 desig="Tester";
//	}
//	void raiseSalary() {
//		salary= salary+ 5000;
//	}
//	void Bonus() {
//		salary = salary+bonus;
//	}
//}
//class Developer extends Emp
//{
//	
//	Developer(){
//		 salary=70000;
//		 desig="Developer";
//	}
//	void raiseSalary() {
//		salary= salary+ 5000;
//	}
//	void Bonus() {
//		salary = salary+bonus;
//	}
//}
//class Manager extends Emp
//{
//	double salary=90000;
//	String desig="Manager";
//	Manager(){
//	}
//	void raiseSalary() {
//		salary= salary+ 5000;
//	}
//	void Bonus() {
//		salary = salary+bonus;
//	}
//}
//
//public class Demo1 {
//	public static void main(String[] args) {
//		
//		Scanner sc = new Scanner(System.in);
//		int ch1,ch2=0;
//		Clerk c = null;
//		Developer d = null;
//		Tester t = null;
//		Manager m = null;
//		do {
//			System.out.println("1 ) Create ");
//			System.out.println("2 ) Display ");
//			System.out.println("3 ) Raise Salary ");
//			System.out.println("4 ) Bonus ");
//			System.out.println("5 ) Exit ");
//			ch1=sc.nextInt();
//			if(ch1==1) {
//				do {
//					System.out.println("	1 ) Clerk  ");
//					System.out.println("	2 ) Developer ");
//					System.out.println("	3 ) Tester  ");
//					System.out.println("	4 ) Manager ");
//					System.out.println("	5 ) Exit ");
//					ch2=sc.nextInt();
//					if(ch2==1) {
//						c = new Clerk();
//					}
//					if(ch2==2) {
//						 d = new Developer();
//					}
//					if(ch2==3) {
//						 t = new Tester();
//					}
//					if(ch2==4) {
//						 m = new Manager();
//					}
//					
//				}while(ch2!=5);
//			}
//			if(ch1==2) {
//				do {
//					System.out.println("	1 ) Clerk  ");
//					System.out.println("	2 ) Developer ");
//					System.out.println("	3 ) Tester  ");
//					System.out.println("	4 ) Manager ");
//					System.out.println("	5 ) Exit ");
//					ch2=sc.nextInt();
//					if(ch2==1) {
//						c.display();
//					}
//					if(ch2==2) {
//						d.display();
//					}
//					if(ch2==3) {
//						t.display();
//					}
//					if(ch2==4) {
//						m.display();
//					}
//					
//				}while(ch2!=5);
//			}
//			if(ch1==3) {
//				do {
//					System.out.println("	1 ) Clerk  ");
//					System.out.println("	2 ) Developer ");
//					System.out.println("	3 ) Tester  ");
//					System.out.println("	4 ) Manager ");
//					System.out.println("	5 ) Exit ");
//					ch2=sc.nextInt();
//					if(ch2==1) {
//						c.raiseSalary();
//						System.out.println("Salary Raised....!");
//					}
//					if(ch2==2) {
//						d.raiseSalary();
//						System.out.println("Salary Raised....!");
//					}
//					if(ch2==3) {
//						t.raiseSalary();
//						System.out.println("Salary Raised....!");
//					}
//					if(ch2==4) {
//						m.raiseSalary();
//						System.out.println("Salary Raised....!");
//					}
//					if(ch1==5) {
//						System.out.println("Thank you.....!");
//					
//					}
//				}while(ch2!=5);
//			}
//			if(ch1==4) {
//				do {
//					System.out.println("	1 ) Clerk  ");
//					System.out.println("	2 ) Developer ");
//					System.out.println("	3 ) Tester  ");
//					System.out.println("	4 ) Manager ");
//					System.out.println("	5 ) Exit ");
//					ch2=sc.nextInt();
//					if(ch2==1) {
//						c.Bonus();
//						System.out.println("Bonus added....!");
//					}
//					if(ch2==2) {
//						d.Bonus();
//						System.out.println("Bonus added....!");
//
//					}
//					if(ch2==3) {
//						t.Bonus();
//						System.out.println("Bonus added....!");
//
//					}
//					if(ch2==4) {
//						m.Bonus();
//						System.out.println("Bonus added....!");
//
//					}
//					
//				}while(ch2!=5);
//			}
//			if(ch1==5) {
//				System.out.println("Thank you.....!");
//				System.exit(0);
//			}
//		}while(ch1!=5);
//	sc.close();
//	}
//}
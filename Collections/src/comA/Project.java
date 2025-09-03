package comA;

import java.util.*;

// ===== Abstract Employee =====
abstract class Emp {
    Scanner sc = new Scanner(System.in);
    int uid, age;
    String name;
    double salary;
    String desig;

    Emp() {
        System.out.println("Enter ID :");   uid= sc.nextInt();
        System.out.println("Enter Name :"); name= sc.next();
        System.out.println("Enter AGE :");  age = sc.nextInt();
    }

    public void display() {
        System.out.println("ID :" + uid);
        System.out.println("NAME :" + name);
        System.out.println("Age :" + age);
        System.out.println("Salary :" + salary);
        System.out.println("Designation :" + desig);
        System.out.println("---------------------------");
    }

    abstract void leavesleft();
    abstract void raisesalary();   // ✅ Added here for polymorphism
}

// ===== Clerk =====
class Clerk extends Emp {
    Clerk() {
        System.out.println("Enter salary: ");
        salary = sc.nextDouble();
        desig = "Clerk";
    }

    @Override
    void raisesalary() {
        System.out.println("How much do you want to increase:");
        double extrasalary = sc.nextDouble();
        double oldSalary = salary;
        salary += extrasalary;
        System.out.println("The updated salary is: " + salary);
        double percentage = (extrasalary / oldSalary) * 100;
        System.out.println("Percentage of salary increased: " + percentage + "%");
    }

    @Override
    void leavesleft() {
        int leaves = 4;
        System.out.println("No of leaves left is : " + leaves);
    }
}

// ===== Tester =====
class Tester extends Emp {
    Tester() {
        System.out.println("Enter salary: ");
        salary = sc.nextDouble();
        desig = "Tester";
    }

    @Override
    void raisesalary() {
        System.out.println("How much do you want to increase:");
        double extrasalary = sc.nextDouble();
        double oldSalary = salary;
        salary += extrasalary;
        System.out.println("The updated salary is: " + salary);
        double percentage = (extrasalary / oldSalary) * 100;
        System.out.println("Percentage of salary increased: " + percentage + "%");
    }

    @Override
    void leavesleft() {
        int leaves = 4;
        System.out.println("No of leaves left is : " + leaves);
    }
}

// ===== Developer =====
class Developer extends Emp {
    Developer() {
        System.out.println("Enter salary: ");
        salary = sc.nextDouble();
        desig = "Developer";
    }

    @Override
    void raisesalary() {
        System.out.println("How much do you want to increase:");
        double extrasalary = sc.nextDouble();
        double oldSalary = salary;
        salary += extrasalary;
        System.out.println("The updated salary is: " + salary);
        double percentage = (extrasalary / oldSalary) * 100;
        System.out.println("Percentage of salary increased: " + percentage + "%");
    }

    @Override
    void leavesleft() {
        int leaves = 4;
        System.out.println("No of leaves left is : " + leaves);
    }
}

// ===== Manager =====
class Manager extends Emp {
    Manager() {
        System.out.println("Enter salary: ");
        salary = sc.nextDouble();
        desig = "Manager";
    }

    @Override
    void raisesalary() {
        System.out.println("How much do you want to increase:");
        double extrasalary = sc.nextDouble();
        double oldSalary = salary;
        salary += extrasalary;
        System.out.println("The updated salary is: " + salary);
        double percentage = (extrasalary / oldSalary) * 100;
        System.out.println("Percentage of salary increased: " + percentage + "%");
    }

    @Override
    void leavesleft() {
        int leaves = 4;
        System.out.println("No of leaves left is : " + leaves);
    }
}

// ===== Main Project =====
public class Project {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int ch1, ch2=0;
        ArrayList<Emp> al= new ArrayList<>();

        do {
            System.out.println("\n===== MENU =====");
            System.out.println("1 ) Create Employee ");
            System.out.println("2 ) Display Employees ");
            System.out.println("3 ) Raise Salary ");
            System.out.println("4 ) Leaves left ");
            System.out.println("5 ) Delete employee ");
            System.out.println("6 ) Exit ");
            System.out.print("Enter choice: ");
            ch1 = sc.nextInt();

            if(ch1==1) {
                do {
                    System.out.println("\n--- Create Employee ---");
                    System.out.println("1 ) Clerk  ");
                    System.out.println("2 ) Developer ");
                    System.out.println("3 ) Tester  ");
                    System.out.println("4 ) Manager ");
                    System.out.println("5 ) Back ");
                    System.out.print("Enter choice: ");
                    ch2 = sc.nextInt();

                    if(ch2==1) al.add(new Clerk());
                    if(ch2==2) al.add(new Developer());
                    if(ch2==3) al.add(new Tester());
                    if(ch2==4) al.add(new Manager());

                }while(ch2!=5);
            }

            if(ch1==2) {   // Display
                System.out.println("\n--- Employee List ---");
                for(Emp e : al) {
                    e.display();
                }
            }

            if(ch1==3) {   // Raise Salary
                System.out.println("\n--- Raise Salary ---");
                Iterator i= al.iterator();
                while(i.hasNext()) {
                	Emp e= (Emp) i.next();
                	e.raisesalary();
                }
              
            }

            if(ch1==4) {   // Leaves left
                System.out.println("\n--- Leaves Left ---");
                Iterator i= al.iterator();
                while(i.hasNext()) {
                	Emp e= (Emp) i.next();
                	e.display();
                	e.leavesleft();
                }
            }
             if(ch1==5) {
            	 System.out.println("Enter id to delete");
            	 int id=sc.nextInt();
            	 int c=0,f=0;
            	 for(Emp e:al) {
            		 if(e.uid==id) {
            			 al.remove(c);
            			 f=1;
            			 break;
            		 }
            		 c++;
            	 }
            	 if(f==0)
            	 {
            		 System.out.println("id not found");
            	 }
            	 else {
            		 System.out.println("id deleted");
            	 }
             }
            if(ch1==6) {
                System.out.println("Thank you.....!");
                System.exit(0);
            }
        } while(ch1!=6);

        sc.close();
    }
}

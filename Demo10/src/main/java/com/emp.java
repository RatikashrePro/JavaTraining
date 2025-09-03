import java.util.*;
import java.util.stream.Collectors;

// ==================== Model Layer ====================
class NegativeSalaryException extends Exception {
    public NegativeSalaryException(String message) {
        super(message);
    }
}

abstract class Emp {
    double salary;
    String desig;
    int age, id;
    String name;

    Emp(String desig, double salary, String name, int age, int id) {
        this.desig = desig;
        this.salary = salary;
        this.name = name;
        this.age = age;
        this.id = id;
    }

    void display() {
        System.out.println("Designation: " + desig);
        System.out.println("Salary: " + salary);
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("ID: " + id);
    }

    abstract void raiseSalary(double inc);
    abstract void reduceSalary(double dec) throws NegativeSalaryException;
}

class Developer extends Emp {
    Developer(String desig, double salary, String name, int age, int id) {
        super(desig, salary, name, age, id);
    }

    void raiseSalary(double inc) {
        this.salary += inc;
        System.out.println("*SALARY RAISED*");
    }

    void reduceSalary(double dec) throws NegativeSalaryException {
        if (this.salary - dec < 0) {
            throw new NegativeSalaryException("Salary cannot be negative!");
        }
        this.salary -= dec;
        System.out.println("*SALARY REDUCED*");
    }
}

class Tester extends Developer {
    Tester(String desig, double salary, String name, int age, int id) {
        super(desig, salary, name, age, id);
    }
}

class Manager extends Developer {
    Manager(String desig, double salary, String name, int age, int id) {
        super(desig, salary, name, age, id);
    }
}

class Clerk extends Developer {
    Clerk(String desig, double salary, String name, int age, int id) {
        super(desig, salary, name, age, id);
    }
}

// ==================== DAO Layer ====================
class EmployeeDAO {
    private final List<Emp> employees = new ArrayList<>();

    public void addEmployee(Emp emp) {
        employees.add(emp);
    }

    public List<Emp> getAllEmployees() {
        return employees;
    }

    public Emp getEmployeeById(int id) {
        return employees.stream().filter(e -> e.id == id).findFirst().orElse(null);
    }

    public boolean deleteEmployee(int id) {
        int sizeBefore = employees.size();
        employees.removeIf(e -> e.id == id);
        return employees.size() < sizeBefore;
    }
}

// ==================== Service Layer ====================
class EmployeeService {
    private final EmployeeDAO dao = new EmployeeDAO();

    public void createEmployee(int type, String name, int age, int id) {
        Emp emp = switch (type) {
            case 1 -> new Clerk("Clerk", 5000.0, name, age, id);
            case 2 -> new Manager("Manager", 6000.0, name, age, id);
            case 3 -> new Tester("Tester", 7000.0, name, age, id);
            default -> null;
        };
        if (emp != null) dao.addEmployee(emp);
    }

    public List<Emp> getAllEmployees() {
        return dao.getAllEmployees();
    }

    public Emp getEmployeeById(int id) {
        return dao.getEmployeeById(id);
    }

    public void raiseSalary(int id, double inc) {
        Emp emp = dao.getEmployeeById(id);
        if (emp != null) emp.raiseSalary(inc);
    }

    public void reduceSalary(int id, double dec) {
        Emp emp = dao.getEmployeeById(id);
        if (emp != null) {
            try {
                emp.reduceSalary(dec);
            } catch (NegativeSalaryException e) {
                System.out.println("Custom Exception: " + e.getMessage());
            }
        }
    }

    public boolean deleteEmployee(int id) {
        return dao.deleteEmployee(id);
    }
}

// ==================== View Layer ====================
class EmployeeView {
    private final Scanner sc = new Scanner(System.in);
    private final EmployeeService service = new EmployeeService();

    public void showMenu() {
        while (true) {
            try {
                System.out.println("\n--- Employee Management ---");
                System.out.println("1. Create Employee");
                System.out.println("2. Display All Employees");
                System.out.println("3. Raise Employee Salary");
                System.out.println("4. Reduce Employee Salary");
                System.out.println("5. Delete Employee");
                System.out.println("0. Exit");
                System.out.print("Enter choice: ");
                int choice = sc.nextInt();

                switch (choice) {
                    case 1 -> createEmployee();
                    case 2 -> displayAllEmployees();
                    case 3 -> raiseSalary();
                    case 4 -> reduceSalary();
                    case 5 -> deleteEmployee();
                    case 0 -> {
                        System.out.println("Thank you...!");
                        return;
                    }
                    default -> System.out.println("Invalid menu choice!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a number.");
                sc.nextLine();
            }
        }
    }

    private void createEmployee() {
        System.out.println("Enter 1 for Clerk, 2 for Manager, 3 for Tester:");
        int type = sc.nextInt();
        System.out.print("Enter Name: ");
        String name = sc.next();
        System.out.print("Enter Age: ");
        int age = sc.nextInt();
        System.out.print("Enter ID: ");
        int id = sc.nextInt();

        service.createEmployee(type, name, age, id);
        System.out.println("Employee created successfully!");
    }

    private void displayAllEmployees() {
        List<Emp> employees = service.getAllEmployees();
        if (employees.isEmpty()) {
            System.out.println("No employees available!");
        } else {
            employees.forEach(emp -> {
                emp.display();
                System.out.println("-------------------");
            });
        }
    }

    private void raiseSalary() {
        System.out.print("Enter Employee ID: ");
        int id = sc.nextInt();
        System.out.print("Enter Increment Amount: ");
        double inc = sc.nextDouble();
        service.raiseSalary(id, inc);
    }

    private void reduceSalary() {
        System.out.print("Enter Employee ID: ");
        int id = sc.nextInt();
        System.out.print("Enter Decrement Amount: ");
        double dec = sc.nextDouble();
        service.reduceSalary(id, dec);
    }

    private void deleteEmployee() {
        System.out.print("Enter Employee ID to delete: ");
        int id = sc.nextInt();
        boolean removed = service.deleteEmployee(id);
        if (removed) {
            System.out.println("Employee deleted successfully!");
        } else {
            System.out.println("Employee not found!");
        }
    }
}

// ==================== Main ====================
public class emp {
    public static void main(String[] args) {
        System.out.println("Welcome to Employee Portal");
        System.out.println("-------------------------");
        new EmployeeView().showMenu();
    }
}

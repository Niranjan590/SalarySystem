package salarydemo;

import java.util.ArrayList;
import java.util.Scanner;

abstract class Employee {
	protected String firstName;
	protected String lastName;
	protected String socialSecurityNumber;
	protected String contractType;
	
	
	public Employee(String firstName, String lastName, String socialSecurityNumber, String contractType) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.socialSecurityNumber = socialSecurityNumber;
		this.contractType = contractType;
	}
	public abstract double income();
	
	public String getPosition() {
		return this.getClass().getSimpleName();
		
	}
	
	public String getContractType() {
		return contractType;
		
	}
	@Override
	public String toString() {
		return "Name: " + firstName + "" + lastName + "\n" +
	"Position: " + getPosition() + "\n" +
	"Contract Type: " + contractType + "\n" +
	"Social Number: " + socialSecurityNumber + "\n"+
	"Salary: " + income() + "Euro\n";
		
	}
}

class FullTimeEmployee extends Employee{
	private double weeklySalary;

	public FullTimeEmployee(String firstName, String lastName, String socialSecurityNumber, String contractType,
			double weeklySalary) {
		super(firstName, lastName, socialSecurityNumber, contractType);
		this.weeklySalary = weeklySalary;
	}
	
	@Override
	public double income() {
		return weeklySalary;
		
	}
}

class ParTimeEmployee extends Employee{
	private double hourlyWage;
	private int hoursWorked;
	public ParTimeEmployee(String firstName, String lastName, String socialSecurityNumber, String contractType,
			double hourlyWage, int hoursWorked) {
		super(firstName, lastName, socialSecurityNumber, contractType);
		this.hourlyWage = hourlyWage;
		this.hoursWorked = hoursWorked;
	}
	
	@Override
	public double income() {
		return hourlyWage * hoursWorked;
	}
}

class CommissionEmployee extends Employee{
	private double sales;
	private double commissionRate;
	public CommissionEmployee(String firstName, String lastName, String socialSecurityNumber, String contractType,
			double sales, double commissionRate) {
		super(firstName, lastName, socialSecurityNumber, contractType);
		this.sales = sales;
		this.commissionRate = commissionRate;
	}
	
	@Override
	public double income() {
		return sales * commissionRate;
		
	}
}

class BaseCommissionEmployee extends CommissionEmployee {
	private double baseSalary;

	public BaseCommissionEmployee(String firstName, String lastName, String socialSecurityNumber, String contractType,
			double sales, double commissionRate, double baseSalary) {
		super(firstName, lastName, socialSecurityNumber, contractType, sales, commissionRate);
		this.baseSalary = baseSalary;
	}
	
	@Override
	public double income() {
		return baseSalary + super.income() + (0.1 * baseSalary);
	}
}

public class SalarySystemDemo {
	private static ArrayList<Employee> employees = new ArrayList<>();
	private static Scanner obj = new Scanner(System.in);
	
	public static void main(String[] args) {
		while (true) {
			System.out.println("===========================");
			System.out.println("| 1. Register Employee     |");
			System.out.println("| 2. Print Employee Data   |");
			System.out.println("| 3. Print by Position     |");
			System.out.println("| 4. Print by Contract     |");
			System.out.println("| 5. Exit                  |");
			System.out.println("===========================");
			System.out.println("Choose option");
			int choice =obj.nextInt();
					obj.nextLine();
					
			switch (choice) {
			case 1:
				registerEmployee();
				break;
			case 2:
				printEmployee();
				break;
			case 3:
				printByPosition();
				break;
			case 4:
				printByContract();
				break;
			case 5:
				System.out.println("Thank you for choosing our program");
				return;
				default:
					System.out.println("choose between the offered options!");
			}		
					
		}
	}
	
	private static void registerEmployee() {
		System.out.println("==================================");
		System.out.println("| 1. Full Time Emplloyee          |");
		System.out.println("| 2. Part Time Employee           |");
		System.out.println("| 3. Commission Emplloyee         |");
		System.out.println("| 4. Base Commission Employee     |");
		System.out.println("| 5. Exit                         |");
		System.out.println("==================================");
		System.out.println("Choose option");
		int choice = obj.nextInt();
		obj.nextLine();
		
		System.out.println("First name: ");
		String firstName = obj.nextLine();
		System.out.println("Last name: ");
		String lastName = obj.nextLine();
		System.out.println("Social Security Number: ");
		String ssn = obj.nextLine();
		System.out.println("Contract Type (Anually/Seasonly): ");
		String contractType = obj.nextLine();
		
		switch (choice) {
		case 1: 
			employees.add(new FullTimeEmployee(firstName, lastName, ssn, contractType,3000));
			break;
		case 2:
			System.out.println("Hours Worked: ");
			int hours = obj.nextInt();
			employees.add(new ParTimeEmployee(firstName, lastName, ssn, contractType, 70 , hours));
			break;
		case 3:
			System.out.println("Total Sales: ");
			double sales = obj.nextDouble();
			employees.add(new CommissionEmployee(firstName, lastName, ssn, contractType, sales, 400));
			break;
		case 4:
			System.out.println("Total Sales: ");
			double baseSales = obj.nextDouble();
			employees.add(new BaseCommissionEmployee(firstName, lastName, ssn, contractType, 800, baseSales,400));
			break;
			default:
				System.out.println("Invalid option. ");
		}
		System.out.println("Employee registered successfully!\n");
	}
	
	private static void printEmployee() {
		if (employees.isEmpty()) {
			System.out.println("No employees registered.");
			return;	
		}
		employees.forEach(System.out::println);
	}
	
	private static void printByPosition() {
		employees.stream().sorted((a,b) -> a.getPosition().compareTo(b.getPosition())).forEach(System.out::println);
	
	}
	public static void printByContract() {
		employees.stream().sorted((a,b) -> a.getContractType().compareTo(b.getContractType())).forEach(System.out::println);
	}
	

}

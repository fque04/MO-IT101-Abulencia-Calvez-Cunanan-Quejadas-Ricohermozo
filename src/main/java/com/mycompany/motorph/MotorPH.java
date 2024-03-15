package com.mycompany.motorph;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import javax.swing.*;


public class MotorPH {

    private static Scanner fileScanner;
    private static String employeeNumber;
    private static List<EmployeeData> employees;
    protected static int hoursOfWork;
    
    public static void main(String[] args) 
    {
        boolean loginProceed = true;
        String username = "",  password = "";
        while(loginProceed)
        {
             username = JOptionPane.showInputDialog(null, "Enter your username");
             password  = JOptionPane.showInputDialog(null, "Enter password");

             if(username.equals("Admin") && password.equals("admin"))
             {
                loginProceed = false;
             }
             else{
                JOptionPane.showMessageDialog(null, "Invalid Username or Password");
             }
        }

        if(username.equals("Admin") && password.equals("admin"))
        {
            JOptionPane.showMessageDialog(null, "Login Successful");
            employees = EmployeeFileReader.getEmployees();
            System.out.println("===========================================");
            System.out.println("   MotorPH Master Data Management System   ");
            System.out.println("===========================================");
            System.out.println("1. Payroll");
            System.out.println("2. Attendance");

            System.out.println("3. Employee Masterlist Data");
            System.out.println("===========================================");
            
            Scanner input = new Scanner(System.in);
            System.out.println("Choose the screen you would like to view:");
            String option = input.nextLine();
            int choice = Integer.parseInt(option.trim());
            
            switch (choice) {
                case 1:
                    EmployeeFileReader.main(new String[]{});
                    payroll(input, employees);
                    break;
                
                //for attendance and employeelist
                
                case 3:
                    EmployeeFileReader.main(new String[]{});
                    viewEmployeeProfiles();
                    break;

            
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
                        
            }
            input.close();
        }

        
    }

    public static boolean validateDate(String date)
    {
        boolean shouldContinue = false;

        if(date.length() < 10)
        {
            shouldContinue = false;
            
        }
        else if(date.length() == 10)
        shouldContinue = true;

        return shouldContinue;
    }
    
    public static void payroll(Scanner input, List <EmployeeData> employees) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy") //02/01/2000
            .withResolverStyle(ResolverStyle.SMART);

            String startDateInput = "";
            LocalDate startDate = null;
        while(validateDate(startDateInput) == false)
        {
            System.out.print("Enter start date (MM/dd/yyyy): ");
            startDateInput = input.nextLine();

            try {
                 startDate = LocalDate.parse(startDateInput, dateFormatter);
            } catch (Exception e) {
                System.out.println("Invalid Time Format! Please Enter Date with the format: MM/dd/yyyy");
            }
        }

        LocalDate endDate = null;
        String endDateInput = "";
        while(validateDate(endDateInput) == false)
        {
            System.out.print("Enter end date (MM/dd/yyyy): ");
            endDateInput = input.nextLine();

            try {
                 endDate = LocalDate.parse(startDateInput, dateFormatter);
            } catch (Exception e) {
                System.out.println("Invalid Time Format! Please Enter Date with the format: MM/dd/yyyy");
            }
        }
        
    if (startDate != null && endDate != null) {
        System.out.print("Enter employee number: ");
        employeeNumber = input.nextLine();

        EmployeeData employee = null;
        for (EmployeeData e : employees) {
            if (e.getEmployeeNumber().equals(employeeNumber)) {
                employee = e;
                break;
            }
        }   

        if (employee == null) {
            System.out.println("Employee not found.");
            return;
        }
        
        List <AttendanceData> AttendanceDATA  =  new AttendanceFileReader().readAttendanceData(); 
        double hoursOfWorkVar = CalculateHoursOfWork.totalHours(AttendanceDATA, employeeNumber, CalculateHoursOfWork.validateDate(CalculateHoursOfWork.checkDate(startDateInput)), CalculateHoursOfWork.validateDate(CalculateHoursOfWork.checkDate(endDateInput)));
        double grossIncome = 0;
        System.out.println("Employee Number: " + employee.getEmployeeNumber());
        System.out.println("Last Name: " + employee.getLastName());
        System.out.println("First Name: " + employee.getFirstName());
        System.out.println("Position: " + employee.getPosition());
        System.out.println("Status: " + employee.getStatus());
        System.out.println("\nEarnings");
        System.out.println("Monthly Rate: " + String.format("%.2f", employee.getBasicSalary()));
        System.out.println("Hourly Rate: " + String.format("%.2f", employee.getHourlyRate()));
        System.out.println("Hours of Work: " +(int)(hoursOfWorkVar - CalculateHoursOfWork.calculateHoursOfLate(AttendanceDATA, employee.getEmployeeNumber()))); // 13
        System.out.println("Late Deduction: " + (CalculateHoursOfWork.calculateHoursOfLate(AttendanceDATA, employee.getEmployeeNumber()) * employee.getHourlyRate()));
        grossIncome = (int)(hoursOfWorkVar - CalculateHoursOfWork.calculateHoursOfLate(AttendanceDATA, employee.getEmployeeNumber()))  * employee.getHourlyRate();
        System.out.println("Gross Income: " + grossIncome);
        System.out.println("\nBenefits");
        System.out.println("Rice Subsidy: " + String.format("%.2f", employee.getRiceSubsidy()));
        System.out.println("Phone Allowance: " + String.format("%.2f", employee.getPhoneAllowance()));
        System.out.println("Clothing Allowance: " + String.format("%.2f", employee.getClothingAllowance()));
        double totalBenefits = employee.getRiceSubsidy() + employee.getPhoneAllowance() + employee.getClothingAllowance();
        System.out.println("Total Benefits: " + String.format("%.2f", totalBenefits));
        System.out.println("\nDeductions");
        System.out.println("SSS Deductions: " + String.format("%.2f", SalaryDeductions.calculateSssContribution(employee.getBasicSalary()))); 
        System.out.println("Pag-ibig Contribution: " + String.format("%.2f", SalaryDeductions.calculatePagibigContribution(employee.getBasicSalary())));
        System.out.println("Philhealth Contribution: " + String.format("%.2f", SalaryDeductions.calculatePhilhealthContribution(employee.getBasicSalary())));           
        double TaxableIncome = employee.getBasicSalary() - SalaryDeductions.calculatePagibigContribution(employee.getBasicSalary()) -
        SalaryDeductions.calculatePhilhealthContribution(employee.getBasicSalary()) - SalaryDeductions.calculateSssContribution(employee.getBasicSalary())
        - (CalculateHoursOfWork.calculateHoursOfLate(AttendanceDATA, employee.getEmployeeNumber()) * employee.getHourlyRate());
        double withHoldingTax = SalaryDeductions.calculateWitholdingTax(TaxableIncome);
        System.out.println("Taxable Income: " + TaxableIncome);
        System.out.println("Witholding Tax: " + String.format("%.2f", withHoldingTax));
        double totalDeductions = SalaryDeductions.calculateSssContribution(employee.getBasicSalary()) + 
        SalaryDeductions.calculatePagibigContribution(employee.getBasicSalary()) + 
        SalaryDeductions.calculatePhilhealthContribution(employee.getBasicSalary()) + withHoldingTax;
        System.out.println("Total Deductions: " + String.format("%.2f", totalDeductions));
        System.out.println("\nSummary: ");
        System.out.println("Gross Income: " + grossIncome);
        System.out.println("Total Benefits: " + String.format("%.2f", totalBenefits));
        System.out.println("Total Deductions: " + String.format("%.2f", totalDeductions));
        double netPay = grossIncome + totalBenefits - totalDeductions;
        System.out.println("\nTake Home Pay: " + String.format("%.2f", netPay));
        }
    }    
    
    public static void viewEmployeeProfiles() {
        System.out.println("Employee Masterlist Data");
        System.out.println("===========================================");
        for (EmployeeData employee : employees) {
            System.out.println("Name: " + employee.getFirstName() + " " + employee.getLastName());
            System.out.println("Address: " + employee.getAddress());
            System.out.println("Position: " + employee.getPosition());
            // Assuming there's a method to get the birthday
            System.out.println("Birthday: " + employee.getBirthday());
            System.out.println("Phone Number: " + employee.getPhoneNumber());
            System.out.println("===========================================");
        }
    }

}
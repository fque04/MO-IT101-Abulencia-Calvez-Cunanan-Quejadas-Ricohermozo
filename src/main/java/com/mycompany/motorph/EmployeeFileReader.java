package com.mycompany.motorph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeeFileReader {
    // List to store EmployeeData objects read from the file
    private static List<EmployeeData> employees = new ArrayList<>();

    // Method to read employee data from a file
    public static void main(String[] args) {
        // File path to the employee data file
        String fileName = ("C:\\Users\\Gen Calvez\\Documents\\NetBeansProjects\\MotorPH\\src\\main\\java\\com\\mycompany\\motorph\\EmployeeListData.txt");

        try (Scanner scanner = new Scanner(new File(fileName))) {
            // Read each line of the file
            while (scanner.hasNextLine()) {
                // Split the line into an array of data using the '|' delimiter
                String line = scanner.nextLine();
                String[] data = line.split("\\|");

                try {
                    // Create an EmployeeData object from the data and add it to the list
                    EmployeeData employee = new EmployeeData(data[0].trim(), data[1].trim(), data[2].trim(), data[3].trim(), data[4].trim(), data[5].trim(), data[6].trim(), data[7].trim(), data[8].trim(), data[9].trim(), data[10].trim(), data[11].trim(), data[12].trim(),
                            Double.parseDouble(data[13].trim()), Double.parseDouble(data[14].trim()), Double.parseDouble(data[15].trim()), Double.parseDouble(data[16].trim()), Double.parseDouble(data[17].trim()), Double.parseDouble(data[18].trim()));

                    employees.add(employee);
                } catch (NumberFormatException e) {
                    // If there's a format error while parsing doubles, skip adding the employee
                }
            }
        } catch (FileNotFoundException e) {
            // If the file is not found, print the stack trace
            e.printStackTrace();
        }
    }

    // Method to retrieve the list of employees
    public static List<EmployeeData> getEmployees() {
        return employees;
    }
}

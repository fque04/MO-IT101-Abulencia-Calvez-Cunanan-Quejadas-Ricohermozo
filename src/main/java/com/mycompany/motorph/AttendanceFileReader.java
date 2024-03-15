package com.mycompany.motorph;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AttendanceFileReader extends EmployeeData {

    public List <AttendanceData> attendanceData = new ArrayList<AttendanceData>();

    public AttendanceFileReader() {
        super(null, null, null, null, null, null, null, null, null, null, null, null, null, 0, 0, 0, 0, 0, 0);
    }
      
    public AttendanceFileReader(String employeeNumber, String lastName, String firstName) {
        super(employeeNumber, lastName, firstName, null, null, null, null, null, null, null, null, null, null, 0, 0, 0, 0, 0, 0);
    }

    public List<AttendanceData> readAttendanceData() {
    List<AttendanceData> attendanceDataList = new ArrayList<>();
    File file = new File("C:\\Users\\Gen Calvez\\Documents\\NetBeansProjects\\MotorPH\\src\\main\\java\\com\\mycompany\\motorph\\Attendance.txt");
    try (Scanner scanner = new Scanner(file)) {
        scanner.nextLine(); // Skip the header line
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] tokens = line.split("\\|");
            if (tokens.length == 6) {
                try {
                    String employeeNumber = tokens[0];
                    String lastName = tokens[1];
                    String firstName = tokens[2];
                    LocalDate date = LocalDate.parse(tokens[3], dateFormatter);
                    LocalTime timeIn = LocalTime.parse(tokens[4], timeFormatter);
                    LocalTime timeOut = LocalTime.parse(tokens[5], timeFormatter);

                    AttendanceData attendanceData = new AttendanceData(date+"", timeIn+"", timeOut+"", employeeNumber, lastName, firstName);
                    attendanceDataList.add(attendanceData);
                } catch (DateTimeParseException e) {
                    System.err.println("Error parsing date/time: " + e.getMessage());
                }
            } else {
                System.err.println("Invalid line format: " + line);
            }
        }
    } catch (FileNotFoundException e) {
        System.err.println("File not found: " + e.getMessage());
    }
    return attendanceDataList;
}

}

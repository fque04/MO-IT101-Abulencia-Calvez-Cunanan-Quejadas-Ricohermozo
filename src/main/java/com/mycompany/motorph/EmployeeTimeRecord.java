package com.mycompany.motorph;

import java.time.Duration;
import java.time.LocalTime;

public class EmployeeTimeRecord extends EmployeeData {    
    private LocalTime timeIn;
    private LocalTime timeOut;

    public EmployeeTimeRecord(String employeeNumber, String lastName, String firstName, String birthday, String address, String phoneNumber, String sssNumber, String philhealthNumber, String tinNumber, String pagibigNumber, String status, String position, String immediateSupervisor, double basicSalary, double riceSubsidy, double phoneAllowance, double clothingAllowance, double grossSemiMonthlyRate, double hourlyRate, LocalTime timeIn, LocalTime timeOut) {
    super(employeeNumber, lastName, firstName, birthday, address, phoneNumber, sssNumber, philhealthNumber, tinNumber, pagibigNumber, status, position, immediateSupervisor, basicSalary, riceSubsidy, phoneAllowance, clothingAllowance, grossSemiMonthlyRate, hourlyRate);
    this.timeIn = timeIn;
    this.timeOut = timeOut;
    }

    public LocalTime getTimeIn() {
        return timeIn;
    }

    public void setTimeIn(LocalTime timeIn) {
        this.timeIn = timeIn;
    }

    public LocalTime getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(LocalTime timeOut) {
        this.timeOut = timeOut;
    }
    
    public long getHoursWorked() {
        LocalTime start = getTimeIn();
        LocalTime end = getTimeOut();

        // Adjust the start time to account for the 10-minute grace period
        if (start.isBefore(LocalTime.of(8, 11))) {
            start = start.plusMinutes(10);
        }

        return Duration.between(start, end).toHours();
    }
}
    
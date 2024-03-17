package com.mycompany.motorph;

public class AttendanceData extends EmployeeData {
    private String date;
    private String timeIn;
    private String timeOut;
    
    public AttendanceData(String date, String timeIn, String timeOut, String employeeNumber, String lastName, String firstName){
        super(employeeNumber, lastName, firstName, null, null, null, null, null, null, null, null, null, null, 0, 0, 0, 0, 0, 0);
        this.date = date;
        this.timeIn = timeIn;
        this.timeOut = timeOut;  
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTimeIn() {
        return timeIn;
    }

    public void setTimeIn(String timeIn) {
        this.timeIn = timeIn;
    }

    public String getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(String timeOut) {
        this.timeOut = timeOut;
    }
    
}

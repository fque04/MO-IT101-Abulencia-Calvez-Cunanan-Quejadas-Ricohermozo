package com.mycompany.motorph;

import java.util.*;
public class CalculateHoursOfWork
{
    public static void main(String[] args)
    {
       try
       {

        List <AttendanceData> AttendanceDATA  =  new AttendanceFileReader().readAttendanceData(); 
        System.out.println(AttendanceDATA.get(0).getDate()+"");
        System.out.println(validateDate(checkDate(AttendanceDATA.get(0).getDate() + "")));
        System.out.println(validateDate(checkDate(AttendanceDATA.get(1).getDate() + "")));
        System.out.println(validateDate(checkDate("01/09/2022")));  
        System.out.println("Hours of Work: " + totalHours(AttendanceDATA, "10001", validateDate(checkDate("01/09/2022")), validateDate(checkDate("02/09/2022"))));
        System.out.println("Hours of Work in Detail:\nTIME IN:" +
        AttendanceDATA.get(0).getTimeIn() + "\nTIME OUT:" + AttendanceDATA.get(0).getTimeOut());

        System.out.println("Hours Late: " + calculateHoursOfLate(AttendanceDATA, "10001"));
    
        }
       catch(Exception error)
       {
        System.out.println(error);
       }
    }

    public static int calculateHoursOfLate(List<AttendanceData> SampEmp, String empNumber)
    {
        int totalDeductions = 0;
        int x = 0;
        while(x != SampEmp.size() - 1)
        {
            if(SampEmp.get(x).getEmployeeNumber().equals(empNumber))
            {
                totalDeductions = totalDeductions + lateDeduction(SampEmp.get(x).getTimeIn());
            }
            x++;
        }
        return totalDeductions;
    }

    public static int lateDeduction(String timeIn)
    {
        String newTimeIn = "";
        int deduction = 0;

        for(int i = 0; i<timeIn.length(); i++)
        {
            if(timeIn.charAt(i) != ':')
            {
                newTimeIn = newTimeIn + timeIn.charAt(i);
            }
        }

        if(Integer.parseInt(newTimeIn) - 800 > 10 && Integer.parseInt(newTimeIn) - 800 <= 100)
        {
            deduction = 1;
        }

        else if(Integer.parseInt(newTimeIn) - 800 > 100)
        {
            if(Integer.parseInt(newTimeIn) % 100 != 0) // 09:01 ->901
            {
                deduction = ((Integer.parseInt(newTimeIn) - 800 )/100) + 1;
            }
            else if(Integer.parseInt(newTimeIn) % 100 == 0)
            {
                deduction = (Integer.parseInt(newTimeIn) - 800)/100;
            }
        }
        return deduction;
    }

    public static int totalHours(List<AttendanceData> SampEmp, String employeeID, int startDate, int endDate)
    {
        int attendanceCount = 0;
        boolean countLoop = true;
        int x = 0;
        
        while(countLoop)
        {
           
            if(startDate <= validateDate(checkDate(SampEmp.get(x).getDate())) && employeeID.equals(SampEmp.get(x).getEmployeeNumber()) && startDate != endDate)
            {
                attendanceCount++;
            }
            else if(startDate == validateDate(checkDate(SampEmp.get(x).getDate())) && startDate == endDate && employeeID.equals(SampEmp.get(x).getEmployeeNumber()))
            {
                attendanceCount++;
                countLoop = false;
            }
            else if(endDate < validateDate(checkDate(SampEmp.get(x).getDate())) && employeeID.equals(SampEmp.get(x).getEmployeeNumber()))
            {
                attendanceCount++;
            }
            
             if( validateDate(checkDate(SampEmp.get(x).getDate())) >= endDate && employeeID.equals(SampEmp.get(x).getEmployeeNumber()))
            {
                countLoop = false;
            }
            x++;
        }

        return attendanceCount * 8;
    }


    public static int validateDate(String date)
    {
        String dateStr = "";
        if(date.charAt(0) == '2')   //20220109 -> 01092022
        {
           dateStr = date.substring(4,6) + date.substring(6, date.length()) + date.substring(0, 4); 
        }
        else{
            dateStr = date;
        }
        return Integer.parseInt(dateStr);
    }

    public static String checkDate(String date)
    {

        String dateTotal ="";

        if(date.contains("/"))
        {
            for(int i = 0; i < date.length(); i++)
            {
                if(date.charAt(i) != '/')
                dateTotal = dateTotal + date.charAt(i);
            }
        }

        else if(date.contains("-"))
        {
            for(int i = 0; i < date.length(); i++)
            {
                if(date.charAt(i) != '-')
                dateTotal = dateTotal + date.charAt(i);
            }
        }
        
        return dateTotal;  

    }
}


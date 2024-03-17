package com.mycompany.motorph;

public class SalaryDeductions {

    private static double salary;
    private static double sssContribution;
    private static double pagibigContribution;
    private static double philhealthContribution;
    private static double witholdingTax;
    
public static double calculateTaxableIncome(double salary, double sssContribution, double pagibigContribution, double philhealthContribution){
    return salary - sssContribution - pagibigContribution - philhealthContribution; 
}

    static double calculateSssContribution(double salary) { 
        return (salary <= 3250) ? 135 :
               (salary <= 3750) ? 157.50 :
               (salary <= 4250) ? 180 :
               (salary <= 4750) ? 202.50 :
               (salary <= 5250) ? 225 :
               (salary <= 5750) ? 247.50 :
               (salary <= 6250) ? 270 :
               (salary <= 6750) ? 292.5 :
               (salary <= 7250) ? 315 :
               (salary <= 7750) ? 337.5 :
               (salary <= 8250) ? 360 :
               (salary <= 8750) ? 382.5 :
               (salary <= 9250) ? 405 :
               (salary <= 9750) ? 427.5 :
               (salary <= 10250) ? 450 :
               (salary <= 10750) ? 472.5 :
               (salary <= 11250) ? 495 :
               (salary <= 11750) ? 517.5 :
               (salary <= 12250) ? 540 :
               (salary <= 12750) ? 562.5 :
               (salary <= 13250) ? 585 :
               (salary <= 13750) ? 607.5 :
               (salary <= 14250) ? 630 :
               (salary <= 14750) ? 652.5 :
               (salary <= 15250) ? 675 :
               (salary <= 15750) ? 697.5 :
               (salary <= 16250) ? 720 :
               (salary <= 16750) ? 742.5 :
               (salary <= 17250) ? 765 :
               (salary <= 17750) ? 787.5 :
               (salary <= 18250) ? 810 :
               (salary <= 18750) ? 832.5 :
               (salary <= 19250) ? 855 :
               (salary <= 19750) ? 877.5 :
               (salary <= 20250) ? 900 :
               (salary <= 20750) ? 922.5 :
               (salary <= 21250) ? 945 :
               (salary <= 21750) ? 967.5 :
               (salary <= 22250) ? 990 :
               (salary <= 22750) ? 1012.5 :
               (salary <= 23250) ? 1035 :
               (salary <= 23750) ? 1057.5 :
               (salary <= 24250) ? 1080 :
               (salary <= 24750) ? 1102.5 :
                1125;
    }
    
    private static final double PAGIBIG_EMPLOYEE_RATE_1 = 0.01;
    private static final double PAGIBIG_EMPLOYEE_RATE_2 = 0.02;
    private static final double PAGIBIG_MAX_CONTRIBUTION = 100;
    
    static double calculatePagibigContribution(double salary) {
        double employeeRate = (salary <= 1500) ? PAGIBIG_EMPLOYEE_RATE_1 : PAGIBIG_EMPLOYEE_RATE_2;
        double contribution = salary * employeeRate;
        return (contribution > PAGIBIG_MAX_CONTRIBUTION) ? PAGIBIG_MAX_CONTRIBUTION : contribution;
    }
    
    public static double calculateWitholdingTax(double taxableIn) {
        if (taxableIn <= 20832) {
            return 0;
        } else if (taxableIn<=33333) {
            return 0.2 * (taxableIn - 20832);
        } else if (taxableIn<= 66667) {
            return 2500 + (0.25 * (taxableIn - 33333));
        } else if (taxableIn<=166667){
            return 10833 + (0.3 * (taxableIn - 66667));
        } else if (taxableIn<=666667){
            return 40833.33 + (0.32 * (taxableIn - 166667));
        } else {
            return 200833.33 + (0.35 * (taxableIn - 666667));
        }
    }
    
    public static double calculatePhilhealthContribution(double salary) {
        final double PHILHEALTH_RATE = 0.03;
        final double PHILHEALTH_EMPLOYEE_SHARE = 0.5;
        double philHealthContribution = salary * PHILHEALTH_RATE;

        philHealthContribution = (salary <= 10000.0) ? 300 :
            (salary <= 59999.99) ? Math.min(philHealthContribution, 1800.0) :
            1800.00;

    return philHealthContribution * PHILHEALTH_EMPLOYEE_SHARE;
    
    }
    
    public class TotalBenefits extends EmployeeData{
        
        public TotalBenefits(String employeeNumber, String lastName, String firstName, String birthday, String address, String phoneNumber, String sssNumber, String philhealthNumber, String tinNumber, String pagibigNumber, String status, String position, String immediateSupervisor, double basicSalary, double riceSubsidy, double phoneAllowance, double clothingAllowance, double grossSemiMonthlyRate, double hourlyRate) {
            super(employeeNumber, lastName, firstName, birthday, address, phoneNumber, sssNumber, philhealthNumber, tinNumber, pagibigNumber, status, position, immediateSupervisor, basicSalary, riceSubsidy, phoneAllowance, clothingAllowance, grossSemiMonthlyRate, hourlyRate);
        }
        
        private double calculateTotalBenefits(){
            return getRiceSubsidy() + getPhoneAllowance() + getClothingAllowance();
        }
        public double getTotalBenefits(){
            return calculateTotalBenefits();
        }

     
    }
    
}


package com.mycompany.motorph;

/**
 * The SalaryDeductions class provides methods to calculate various deductions from an employee's salary.
 */
public class SalaryDeductions {

    private static double salary;
    private static double sssContribution;
    private static double pagibigContribution;
    private static double philhealthContribution;
    private static double witholdingTax;
    
    /**
     * Calculates the taxable income based on the given salary and deductions.
     * 
     * @param salary The employee's salary.
     * @param sssContribution The SSS contribution.
     * @param pagibigContribution The Pag-ibig contribution.
     * @param philhealthContribution The PhilHealth contribution.
     * @return The taxable income.
     */
    public static double calculateTaxableIncome(double salary, double sssContribution, double pagibigContribution, double philhealthContribution){
        return salary - sssContribution - pagibigContribution - philhealthContribution; 
    }

    /**
     * Calculates the SSS contribution based on the given salary.
     * 
     * @param salary The employee's salary.
     * @return The SSS contribution.
     */
    static double calculateSssContribution(double salary) { 
        // Calculation based on salary brackets
        return (salary <= 3250) ? 135 :
               (salary <= 3750) ? 157.50 :
               // Other salary brackets...
               1125;
    }
    
    // Constants for Pag-ibig contribution calculation
    private static final double PAGIBIG_EMPLOYEE_RATE_1 = 0.01;
    private static final double PAGIBIG_EMPLOYEE_RATE_2 = 0.02;
    private static final double PAGIBIG_MAX_CONTRIBUTION = 100;
    
    /**
     * Calculates the Pag-ibig contribution based on the given salary.
     * 
     * @param salary The employee's salary.
     * @return The Pag-ibig contribution.
     */
    static double calculatePagibigContribution(double salary) {
        double employeeRate = (salary <= 1500) ? PAGIBIG_EMPLOYEE_RATE_1 : PAGIBIG_EMPLOYEE_RATE_2;
        double contribution = salary * employeeRate;
        return (contribution > PAGIBIG_MAX_CONTRIBUTION) ? PAGIBIG_MAX_CONTRIBUTION : contribution;
    }
    
    /**
     * Calculates the withholding tax based on the taxable income.
     * 
     * @param taxableIncome The taxable income.
     * @return The withholding tax.
     */
    public static double calculateWitholdingTax(double taxableIncome) {
        // Tax calculation based on taxable income brackets
        // Implement the tax calculation logic here...
        return 0.0; // Placeholder return value
    }
    
    /**
     * Calculates the PhilHealth contribution based on the given salary.
     * 
     * @param salary The employee's salary.
     * @return The PhilHealth contribution.
     */
    public static double calculatePhilhealthContribution(double salary) {
        // Constants for PhilHealth contribution calculation
        final double PHILHEALTH_RATE = 0.03;
        final double PHILHEALTH_EMPLOYEE_SHARE = 0.5;
        
        // Calculation of PhilHealth contribution
        double philHealthContribution = salary * PHILHEALTH_RATE;
        philHealthContribution = (salary <= 10000.0) ? 300 :
            (salary <= 59999.99) ? Math.min(philHealthContribution, 1800.0) :
            1800.00;

        return philHealthContribution * PHILHEALTH_EMPLOYEE_SHARE;
    }
    
    /**
     * The TotalBenefits class extends the EmployeeData class and calculates the total benefits for an employee.
     */
    public class TotalBenefits extends EmployeeData {
        
        public TotalBenefits(String employeeNumber, String lastName, String firstName, String birthday, String address, String phoneNumber, String sssNumber, String philhealthNumber, String tinNumber, String pagibigNumber, String status, String position, String immediateSupervisor, double basicSalary, double riceSubsidy, double phoneAllowance, double clothingAllowance, double grossSemiMonthlyRate, double hourlyRate) {
            super(employeeNumber, lastName, firstName, birthday, address, phoneNumber, sssNumber, philhealthNumber, tinNumber, pagibigNumber, status, position, immediateSupervisor, basicSalary, riceSubsidy, phoneAllowance, clothingAllowance, grossSemiMonthlyRate, hourlyRate);
        }
        
        /*
         * Calculates the total benefits for an employee.
         * 
         * @return The total benefits.
         */
        private double calculateTotalBenefits(){
            return getRiceSubsidy() + getPhoneAllowance() + getClothingAllowance();
        }
        
        /**
         * Gets the total benefits for an employee.
         * 
         * @return The total benefits.
         */
        public double getTotalBenefits(){
            return calculateTotalBenefits();
        }
    }
}

/**
 * The EmployeeData class represents the data of an employee.
 * It encapsulates various attributes such as employee number, name, contact information,
 * identification numbers (SSS, Philhealth, TIN, Pag-ibig), employment status, position,
 * salary details, and allowances.
 */
public class EmployeeData {
    // Private fields representing employee attributes
    private String employeeNumber;
    private String lastName;
    private String firstName;
    private String birthday;
    private String address;
    private String phoneNumber;
    private String sssNumber;
    private String philhealthNumber;
    private String tinNumber;
    private String pagibigNumber;
    private String status;
    private String position;
    private String immediateSupervisor;
    private double basicSalary;
    private double riceSubsidy;
    private double phoneAllowance;
    private double clothingAllowance;
    private double grossSemiMonthlyRate;
    private double hourlyRate;
    
    /**
     * Constructs an EmployeeData object with the specified attributes.
     * @param employeeNumber The unique identifier for the employee.
     * @param lastName The last name of the employee.
     * @param firstName The first name of the employee.
     * @param birthday The birthday of the employee.
     * @param address The address of the employee.
     * @param phoneNumber The phone number of the employee.
     * @param sssNumber The SSS number of the employee.
     * @param philhealthNumber The PhilHealth number of the employee.
     * @param tinNumber The TIN number of the employee.
     * @param pagibigNumber The Pag-ibig number of the employee.
     * @param status The employment status of the employee.
     * @param position The position of the employee.
     * @param immediateSupervisor The immediate supervisor of the employee.
     * @param basicSalary The basic salary of the employee.
     * @param riceSubsidy The rice subsidy received by the employee.
     * @param phoneAllowance The phone allowance received by the employee.
     * @param clothingAllowance The clothing allowance received by the employee.
     * @param grossSemiMonthlyRate The gross semi-monthly rate of the employee.
     * @param hourlyRate The hourly rate of the employee.
     */
    public EmployeeData(String employeeNumber, String lastName, String firstName, String birthday, String address, String phoneNumber, String sssNumber, String philhealthNumber, String tinNumber, String pagibigNumber, String status, String position, String immediateSupervisor, double basicSalary, double riceSubsidy, double phoneAllowance, double clothingAllowance, double grossSemiMonthlyRate, double hourlyRate) {
        // Initialize the employee attributes with the provided values
        this.employeeNumber = employeeNumber;
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthday = birthday;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.sssNumber = sssNumber;
        this.philhealthNumber = philhealthNumber;
        this.tinNumber = tinNumber;
        this.pagibigNumber = pagibigNumber;
        this.status = status;
        this.position = position;
        this.immediateSupervisor = immediateSupervisor;
        this.basicSalary = basicSalary;
        this.riceSubsidy = riceSubsidy;
        this.phoneAllowance = phoneAllowance;
        this.clothingAllowance = clothingAllowance;
        this.grossSemiMonthlyRate = grossSemiMonthlyRate;
        this.hourlyRate = hourlyRate;
    }

    // Getter and setter methods for accessing and modifying employee attributes
    // Omitted for brevity
}

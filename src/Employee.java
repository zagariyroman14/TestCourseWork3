import java.io.Serializable;

public class Employee implements Serializable {
    private String surname;
    private String position;
    private int hourlyWage;
    private int numberHoursWorked;
    public Employee(String surname, String position, int hourlyWage, int numberHoursWorked) {
        this.surname = surname;
        this.position = position;
        this.hourlyWage = hourlyWage;
        this.numberHoursWorked = numberHoursWorked;
    }
    public String getSurname() {
        return surname;
    }

    public String getPosition() {
        return position;
    }

    public int getHourlyWage() {
        return hourlyWage;
    }

    public int getNumberHoursWorked() {
        return numberHoursWorked;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setHourlyWage(int hourlyWage) {
        this.hourlyWage = hourlyWage;
    }

    public void setNumberHoursWorked(int numberHoursWorked) {
        this.numberHoursWorked = numberHoursWorked;
    }

    public int calculateMonthlySalary() {
        return hourlyWage * numberHoursWorked;
    }
    public int calculatePremium() {
        int premium = 0;
        if (numberHoursWorked >= 180)
            premium = (int) (calculateMonthlySalary() * 0.15);
        else if (numberHoursWorked > 120) {
            premium = (int) (calculateMonthlySalary() * 0.1);
        }
        return premium;
    }
    public int calculateTux() {
        return (int) (calculateMonthlySalary() * 0.18);
    }

    public int calculateNetSalary() {
        return calculateMonthlySalary() + calculatePremium() - calculateTux();
    }
}

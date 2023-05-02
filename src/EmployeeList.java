import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
public class EmployeeList {
    private static ArrayList<Employee> employeeList;
    public EmployeeList() {
        employeeList = new ArrayList<>();
    }
    public ArrayList<Employee> getEmployeeList() {
        return employeeList;
    }

    public static void setEmployeeList(ArrayList<Employee> employeeList) {
        EmployeeList.employeeList = employeeList;
    }
    public void addEmployee(Employee employee) {
        employeeList.add(employee);
    }

    public void deleteEmployee(int n) {
        if (n >= 0 && n < employeeList.size()) {
            System.out.println("Видалення даних про свірботника з прізвищем: " + employeeList.get(n).getSurname());
            employeeList.remove(n);
            System.out.println("Дані успішно видалено!");
        }
        else System.out.println("ПОМИЛКА! Введено неправильний номер прізвища!");
    }
    public void changeEmployee(int surnameNum, int columnNum) {
        Scanner scanner = new Scanner(System.in);
        if (columnNum == 1) {
            System.out.println("\nВведіть нове прізвище співробітника:");
            employeeList.get(surnameNum).setSurname(scanner.next());
            System.out.println("Дані успішно змінено!");
        }
        else if (columnNum == 2) {
            System.out.println("\nВведіть нову посаду співробітника:");
            employeeList.get(surnameNum).setPosition(scanner.next());
            System.out.println("Дані успішно змінено!");
        }
        else if (columnNum == 3) {
            System.out.println("\nВведіть нову кількість годин роботи за місяць для цього співробітника:");
            employeeList.get(surnameNum).setNumberHoursWorked(scanner.nextInt());
            System.out.println("Дані успішно змінено!");
        }
        else if (columnNum == 4) {
            System.out.println("\nВведіть нову зарплату за годину цього співробітника:");
            employeeList.get(surnameNum).setHourlyWage(scanner.nextInt());
            System.out.println("Дані успішно змінено!");
        }
    }
    public static int calculateSumHourlyWage(ArrayList<Employee> employees) {
        int sum = 0;
        for (Employee employee : employees) {
            sum += employee.getHourlyWage();
        }
        return sum;
    }
    public static int calculateSumNumberHoursWorked(ArrayList<Employee> employees) {
        int sum = 0;
        for (Employee employee : employees) {
            sum += employee.getNumberHoursWorked();
        }
        return sum;
    }
    public static int calculateSumMonthlySalary(ArrayList<Employee> employees) {
        int sum = 0;
        for (Employee employee : employees) {
            sum += employee.calculateMonthlySalary();
        }
        return sum;
    }
    public static int calculateSumPremium(ArrayList<Employee> employees) {
        int sum = 0;
        for (Employee employee : employees) {
            sum += employee.calculatePremium();
        }
        return sum;
    }
    public static int calculateSumTux(ArrayList<Employee> employees) {
        int sum = 0;
        for (Employee employee : employees) {
            sum += employee.calculateTux();
        }
        return sum;
    }
    public static int calculateSumNetSalary(ArrayList<Employee> employees) {
        int sum = 0;
        for (Employee employee : employees) {
            sum += employee.calculateNetSalary();
        }
        return sum;
    }
    public long getCountEmployee() {
        return employeeList.stream().count();
    }
    public Stream<Employee> getSomeCountRecords(int count) {
        return employeeList.stream().limit(count);
    }
    public Stream<Employee> sortAscendingBySurname() {
        return employeeList.stream().sorted(Comparator.comparing(Employee::getSurname));
    }
    public Stream<Employee> sortAscendingByNumberHoursWorked() {
        return employeeList.stream().sorted(Comparator.comparingInt(Employee::getNumberHoursWorked));
    }
    public Stream<Employee> sortAscendingByHourlyWage() {
        return employeeList.stream().sorted(Comparator.comparingInt(Employee::getHourlyWage));
    }
    public Stream<Employee> sortAscendingByMonthlySalary() {
        return employeeList.stream().sorted(Comparator.comparingInt(Employee::calculateMonthlySalary));
    }
    public Stream<Employee> sortAscendingByPremium() {
        return employeeList.stream().sorted(Comparator.comparingInt(Employee::calculatePremium));
    }
    public Stream<Employee> sortAscendingByTux() {
        return employeeList.stream().sorted(Comparator.comparingInt(Employee::calculateTux));
    }
    public Stream<Employee> sortAscendingByNetSalary() {
        return employeeList.stream().sorted(Comparator.comparingInt(Employee::calculateNetSalary));
    }
    public Stream<Employee> sortDescendingBySurname() {
        return employeeList.stream().sorted(Comparator.comparing(Employee::getSurname).reversed());
    }
    public Stream<Employee> sortDescendingByNumberHoursWorked() {
        return employeeList.stream().sorted((x,y) -> y.getNumberHoursWorked() - x.getNumberHoursWorked());
    }
    public Stream<Employee> sortDescendingByHourlyWage() {
        return employeeList.stream().sorted((x,y) -> y.getHourlyWage() - x.getHourlyWage());
    }
    public Stream<Employee> sortDescendingByMonthlySalary() {
        return employeeList.stream().sorted((x,y) -> y.calculateMonthlySalary() - x.calculateMonthlySalary());
    }
    public Stream<Employee> sortDescendingByPremium() {
        return employeeList.stream().sorted((x,y) -> y.calculatePremium() - x.calculatePremium());
    }
    public Stream<Employee> sortDescendingByTux() {
        return employeeList.stream().sorted((x,y) -> y.calculateTux() - x.calculateTux());
    }
    public Stream<Employee> sortDescendingByNetSalary() {
        return employeeList.stream().sorted((x,y) -> y.calculateNetSalary() - x.calculateNetSalary());
    }
    public Stream<Employee> filterByNumberHoursWorkedInRange(int a, int b) {
        return employeeList.stream().filter(x -> x.getNumberHoursWorked() >= a && x.getNumberHoursWorked() <= b);
    }
    public Stream<Employee> filterByHourlyWageInRange(int a, int b) {
        return employeeList.stream().filter(x -> x.getHourlyWage() >= a && x.getHourlyWage() <= b);
    }
    public Stream<Employee> filterByMonthlySalaryInRange(int a, int b) {
        return employeeList.stream().filter(x -> x.calculateMonthlySalary() >= a && x.calculateMonthlySalary() <= b);
    }
    public Stream<Employee> filterByPremiumInRange(int a, int b) {
        return employeeList.stream().filter(x -> x.calculatePremium() >= a && x.calculatePremium() <= b);
    }
    public Stream<Employee> filterByTuxInRange(int a, int b) {
        return employeeList.stream().filter(x -> x.calculateTux() >= a && x.calculateTux() <= b);
    }
    public Stream<Employee> filterByNetSalaryInRange(int a, int b) {
        return employeeList.stream().filter(x -> x.calculateNetSalary() >= a && x.calculateNetSalary() <= b);
    }
    public Stream<Employee> filterBySurnameByValue(String value) {
        return employeeList.stream().filter(x -> x.getSurname().equalsIgnoreCase(value));
    }
    public Stream<Employee> filterByPositionByValue(String value) {
        return employeeList.stream().filter(x -> x.getPosition().equalsIgnoreCase(value));
    }
    public Stream<Employee> filterByNumberHoursWorkedByValue(int value) {
        return employeeList.stream().filter(x -> x.getNumberHoursWorked() == value);
    }
    public Stream<Employee> filterByHourlyWageByValue(int value) {
        return employeeList.stream().filter(x -> x.getHourlyWage() == value);
    }
    public Stream<Employee> filterByMonthlySalaryByValue(int value) {
        return employeeList.stream().filter(x -> x.calculateMonthlySalary() == value);
    }
    public Stream<Employee> filterByPremiumByValue(int value) {
        return employeeList.stream().filter(x -> x.calculatePremium() == value);
    }
    public Stream<Employee> filterByTuxByValue(int value) {
        return employeeList.stream().filter(x -> x.calculateTux() == value);
    }
    public Stream<Employee> filterByNetSalaryByValue(int value) {
        return employeeList.stream().filter(x -> x.calculateNetSalary() == value);
    }
    public Stream<Employee> filterByNumberHoursWorkedByMax() {
        return employeeList.stream().max(Comparator.comparingInt(Employee::getNumberHoursWorked)).stream();
    }
    public Stream<Employee> filterByHourlyWageByMax() {
        return employeeList.stream().max(Comparator.comparingInt(Employee::getHourlyWage)).stream();
    }
    public Stream<Employee> filterByMonthlySalaryByMax() {
        return employeeList.stream().max(Comparator.comparingInt(Employee::calculateMonthlySalary)).stream();
    }
    public Stream<Employee> filterByPremiumByMax() {
        return employeeList.stream().max(Comparator.comparingInt(Employee::calculatePremium)).stream();
    }
    public Stream<Employee> filterByTuxByMax() {
        return employeeList.stream().max(Comparator.comparingInt(Employee::calculateTux)).stream();
    }
    public Stream<Employee> filterByNetSalaryByMax() {
        return employeeList.stream().max(Comparator.comparingInt(Employee::calculateNetSalary)).stream();
    }
    public Stream<Employee> filterByNumberHoursWorkedByMin() {
        return employeeList.stream().min(Comparator.comparingInt(Employee::getNumberHoursWorked)).stream();
    }
    public Stream<Employee> filterByHourlyWageByMin() {
        return employeeList.stream().min(Comparator.comparingInt(Employee::getHourlyWage)).stream();
    }
    public Stream<Employee> filterByMonthlySalaryByMin() {
        return employeeList.stream().min(Comparator.comparingInt(Employee::calculateMonthlySalary)).stream();
    }
    public Stream<Employee> filterByPremiumByMin() {
        return employeeList.stream().min(Comparator.comparingInt(Employee::calculatePremium)).stream();
    }
    public Stream<Employee> filterByTuxByMin() {
        return employeeList.stream().min(Comparator.comparingInt(Employee::calculateTux)).stream();
    }
    public Stream<Employee> filterByNetSalaryByMin() {
        return employeeList.stream().min(Comparator.comparingInt(Employee::calculateNetSalary)).stream();
    }

    public static void printTable(Stream<Employee> employeeStream) {      // метод для виведення таблиці потрібних запитів з даними полів
        ArrayList<Employee> employees = employeeStream.collect(Collectors.toCollection(ArrayList::new));
        DecimalFormat df = new DecimalFormat("#.##");
        int MAX_SURNAME_LENGTH = employees.stream().max(Comparator.comparingInt(o -> o.getSurname().
                length())).get().getSurname().length();
        if (MAX_SURNAME_LENGTH < 8) MAX_SURNAME_LENGTH = 8;

        int MAX_POSITION_LENGTH = employees.stream().max(Comparator.comparingInt(o -> o.getPosition().
                length())).get().getPosition().length();
        if (MAX_POSITION_LENGTH < 6) MAX_POSITION_LENGTH = 6;

        final int SIZE = employees.size();
        final int HORIZONTAL_LINE_LENGTH = MAX_SURNAME_LENGTH + MAX_POSITION_LENGTH + 113;
        String leftSurnamePadding, rightSurnamePadding, leftPositionPadding, righPositionPadding;

        String a1 = " ".repeat((MAX_SURNAME_LENGTH + 6 - 8) / 2);
        String a2 = ((MAX_SURNAME_LENGTH - 8) % 2 != 0) ? a1 + " " : a1;
        String a3 = " ".repeat((MAX_POSITION_LENGTH + 6 - 6) / 2);
        String a4 = ((MAX_POSITION_LENGTH - 6) % 2 != 0) ? a3 + " " : a3;

        String b1 = " ".repeat((MAX_SURNAME_LENGTH + MAX_POSITION_LENGTH + 12 - 5) / 2);
        String b2 = ((MAX_SURNAME_LENGTH + MAX_POSITION_LENGTH - 5) % 2 != 0) ? b1 + " " : b1;
        b1 += " ";

        String c1 = " ".repeat((MAX_SURNAME_LENGTH + MAX_POSITION_LENGTH + 12 - 16) / 2);
        String c2 = ((MAX_SURNAME_LENGTH + MAX_POSITION_LENGTH - 16) % 2 != 0) ? c1 + " " : c1;
        c1 += " ";
        System.out.println();
        System.out.printf("\n%s%s\n"," ".repeat(MAX_SURNAME_LENGTH + MAX_POSITION_LENGTH + 15),
                "ВІДОМІСТЬ З ДАНИМИ ПРО НАРАХУВАННЯ ЗАРПЛАТИ СПІВРОБІТНИКАМ");
        System.out.println("-".repeat(HORIZONTAL_LINE_LENGTH));

        System.out.printf("|%s|%s|   %s  |        %s        |%s|%s|     %s    |\n", " ".repeat(MAX_SURNAME_LENGTH + 6),
                " ".repeat(MAX_POSITION_LENGTH + 6), "Кількість", "Зарплата, грн", " ".repeat(13),
                " ".repeat(14), "Чиста зарплата");

        System.out.printf("|%s%s%s|%s%s%s| %s |%s| %s | %s | %s |\n", a1, "Прізвище", a2, a3, "Посада", a4,
                "годин роботи", "-".repeat(29),"Премія, грн","Податки, грн", "(з урахуванням премії");

        System.out.printf("|%s|%s|   %s  |   %s  |   %s  |%s|%s|   %s   |\n", " ".repeat(MAX_SURNAME_LENGTH + 6),
                " ".repeat(MAX_POSITION_LENGTH + 6), "за місяць", "за годину", "за місяць",
                " ".repeat(13), " ".repeat(14), "та податків), грн");

        System.out.println("-".repeat(HORIZONTAL_LINE_LENGTH));

        for (Employee e : employees) {
            leftSurnamePadding = " ".repeat((MAX_SURNAME_LENGTH + 6 - e.getSurname().length()) / 2);
            rightSurnamePadding = ((MAX_SURNAME_LENGTH - e.getSurname().length()) % 2 != 0 ) ? leftSurnamePadding +
                    " " : leftSurnamePadding;

            leftPositionPadding = " ".repeat((MAX_POSITION_LENGTH + 6 - e.getPosition().length()) / 2);
            righPositionPadding = ((MAX_POSITION_LENGTH - e.getPosition().length()) % 2 != 0) ? leftPositionPadding +
                    " " : leftPositionPadding;

            System.out.printf("|%s%s%s|%s%s%s|  %-12s|  %-12s|  %-12s|  %-11s|  %-12s|  %-21s|\n", leftSurnamePadding,
                    e.getSurname(), rightSurnamePadding, leftPositionPadding, e.getPosition(), righPositionPadding,
                    e.getNumberHoursWorked(), e.getHourlyWage(), e.calculateMonthlySalary(), e.calculatePremium(),
                    e.calculateTux(), e.calculateNetSalary());
            System.out.println("-".repeat(HORIZONTAL_LINE_LENGTH));
        }

        System.out.println("-".repeat(HORIZONTAL_LINE_LENGTH));
        System.out.printf("|%s%s%s|  %-12s|  %-12s|  %-12s|  %-11s|  %-12s|  %-21s|\n", b1, "РАЗОМ", b2,
                calculateSumNumberHoursWorked(employees), calculateSumHourlyWage(employees),
                calculateSumMonthlySalary(employees), calculateSumPremium(employees),
                calculateSumTux(employees), calculateSumNetSalary(employees));

        System.out.println("-".repeat(HORIZONTAL_LINE_LENGTH));

        System.out.printf("|%s%s%s|  %-12s|  %-12s|  %-12s|  %-11s|  %-12s|  %-21s|\n", c1, "СЕРЕДНЄ ЗНАЧЕННЯ", c2,
                df.format(calculateSumNumberHoursWorked(employees) / (double)SIZE),
                df.format(calculateSumHourlyWage(employees) / (double)SIZE),
                df.format(calculateSumMonthlySalary(employees) / (double)SIZE),
                df.format(calculateSumPremium(employees) / (double)SIZE),
                df.format(calculateSumTux(employees) / (double)SIZE),
                df.format(calculateSumNetSalary(employees) / (double)SIZE));

        System.out.println("-".repeat(HORIZONTAL_LINE_LENGTH));
    }
}


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class FileIO {
    public static void saveToCSV(ArrayList<Employee> employeeList, String fileName) throws IOException {
        Path path = Path.of(fileName);
        if (!Files.exists(path)){
            System.out.println("\nСтворення файлу " + fileName + "...");
            Files.createFile(path);
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        for (Employee employee : employeeList) {
            writer.write(employee.getSurname() + " - " + employee.getPosition() + " - " + employee.getNumberHoursWorked()
                    + " - " + employee.getHourlyWage() + " - " + employee.calculateMonthlySalary() + " - " +
                    employee.calculatePremium() + " - " + employee.calculateTux() + " - " +
                    employee.calculateNetSalary() + "\n");
        }
        writer.close();
        System.out.println("\nДані збережено до CSV-файлу: " + fileName);
    }
    public static void importFromCSV(String fileName) throws IOException {
        ArrayList<Employee> employees = new ArrayList<>();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" - ");
                String surname = parts[0];
                String position = parts[1];
                int numberHoursWorked = Integer.parseInt(parts[2]);
                int hourlyWage = Integer.parseInt(parts[3]);
                Employee employee = new Employee(surname, position, hourlyWage, numberHoursWorked);
                employees.add(employee);
            }
            EmployeeList.setEmployeeList(employees);
            System.out.println("\nДані імпортовано до колекції з файлу: " + fileName);
            System.out.println("\nЗаписи відомості імпортовані з файлу: ");
            EmployeeList.printTable(employees.stream());
            reader.close();
        } catch (FileNotFoundException | NullPointerException e) {
            System.out.println("ПОМИЛКА! Такого файлу не існує!");
        }
    }
    public static void serialize(ArrayList <Employee> employeeList, String fileName) throws IOException {
        Path path = Path.of(fileName);
        if (!Files.exists(path)){
            System.out.println("\nСтворення файлу " + fileName + "...");
            Files.createFile(path);
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(employeeList);
            fileOutputStream.close();
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("\nДані серіалізовано до файлу: " + fileName);
    }
    public static void deserialize(String fileName) {
        ArrayList<Employee> employees;
        try {
            FileInputStream fileInputStream = new FileInputStream(fileName);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            employees = (ArrayList<Employee>) objectInputStream.readObject();
            EmployeeList.setEmployeeList(employees);
            System.out.println("\nДані десеріалізовано з файлу: " + fileName);
            System.out.println("\nЗаписи відомості десеріалізовані з файлу: ");
            EmployeeList.printTable(employees.stream());
            fileInputStream.close();
            objectInputStream.close();
        }
        catch (IOException | ClassNotFoundException e) {
            System.out.println("ПОМИЛКА! Такого файлу не існує!");
        }
    }
}

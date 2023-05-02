import java.io.IOException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;
public class Main {
    static EmployeeList sheet = new EmployeeList();
    private final static String COLUMN_1 = "Прізвище";
    private final static String COLUMN_2 = "Посада";
    private final static String COLUMN_3 = "Кількість годин роботи за місяць";
    private final static String COLUMN_4 = "Зарплата за годину";
    private final static String COLUMN_5 = "Місячна зарплата";
    private final static String COLUMN_6 = "Премія";
    private final static String COLUMN_7 = "Податки";
    private final static String COLUMN_8 = "Чиста зарплата";
    private final static String TEXT = """
            
            ВАРІАНТИ РЕКВІЗИТІВ
             1 - Кількість годин роботи за місяць
             2 - Зарплата за годину
             3 - Місячна зарплата
             4 - Премія
             5 - Податки
             6 - Чиста зарплата
             
            Оберіть варіант реквізиту відомості за яким ви хочете здійснити фільтрацію:""";
    private final static String ERROR_EMPTY_COLLECTION = "ПОМИЛКА! Ви не додали жодного співробітника до відомості! " +
            "\nОберіть варіант 1, щоб додати дані про співробітників";
    public static void main(String[] args) throws IOException, NoSuchElementException {
        Scanner scanner = new Scanner(System.in);
        int option = 0;
        while (option != 8) {
            System.out.println("\nВАРІАНТИ ДІЙ КОРИСТУВАЧА:");
            System.out.println("""
                     1 - Додати дані про співробітників
                     2 - Видалити дані про співробітника
                     3 - Змінити дані про співробітника
                     4 - Надрукувати таблицю з даними про нарахування зарплат співробітникам
                     5 - Використати певні запити
                     6 - Зберегти дані про співробітників до файлу
                     7 - Імпортувати дані про співробітників з файлу
                     8 - Завершити роботу програми\
                    """);
            System.out.println("\nОберіть варіант дії користувача, ввівши відповідне число від 1 до 8:");
            try {
                option  = scanner.nextInt();
                switch (option) {
                    case 1 -> {
                        System.out.println("\nВведіть кількість співробітників, яку ви хочете додати до відомості:");
                        int countRequest = scanner.nextInt();
                        for (int i = 0; i < countRequest; i++) {
                            System.out.println("\nВведіть прізвище співробітника:");
                            String surname = scanner.next();
                            System.out.println("Введіть посаду співробітника:");
                            String position = scanner.next();
                            System.out.println("Введіть заробітню плату за годину:");
                            int hourlyWage = scanner.nextInt();
                            System.out.println("Введіть кількість годин роботи за місяць:");
                            int numberHoursWorked = scanner.nextInt();
                            Employee employee = new Employee(surname, position, hourlyWage, numberHoursWorked);
                            sheet.addEmployee(employee);
                        }
                    }
                    case 2 -> {
                        if (sheet.getEmployeeList().isEmpty()) {
                            System.out.println(ERROR_EMPTY_COLLECTION);
                            break;
                        }
                        System.out.println("\nПРІЗВИЩА СПІВРОБІТНИКІВ:");
                        int k = 1;
                        for (Employee employee : sheet.getEmployeeList()) {
                            System.out.println(" " + k + " - " + employee.getSurname());
                            k++;
                        }
                        System.out.println("\nОберіть прізвище співробітника для видалення даних про нього");
                        sheet.deleteEmployee(scanner.nextInt() - 1);
                    }
                    case 3 -> {
                        if (sheet.getEmployeeList().isEmpty()) {
                            System.out.println(ERROR_EMPTY_COLLECTION);
                            break;
                        }
                        System.out.println("\nПРІЗВИЩА СПІВРОБІТНИКІВ:");
                        int k = 1;
                        for (Employee employee : sheet.getEmployeeList()) {
                            System.out.println(" " + k + " - " + employee.getSurname());
                            k++;
                        }
                        System.out.println("\nОберіть прізвище співробітника для змінення даних про нього");
                        int surnameNumber = scanner.nextInt();
                        if (surnameNumber < 1 || surnameNumber > sheet.getEmployeeList().size()) {
                            System.out.println("ПОМИЛКА! Введено неіснуючий номер прізвища!");
                            break;
                        }
                        System.out.println("\nВАРІАНТИ РЕКВІЗИТІВ ДЛЯ ЗМІНЕННЯ:");
                        System.out.println(" 1 - " + COLUMN_1);
                        System.out.println(" 2 - " + COLUMN_2);
                        System.out.println(" 3 - " + COLUMN_3);
                        System.out.println(" 4 - " + COLUMN_4);
                        System.out.println("\nОберіть варіант реквізиту який буде змінено:");
                        int columnNumber = scanner.nextInt();
                        switch (columnNumber) {
                            case 1 -> System.out.println("\nПоточне " + COLUMN_1 + " = " +
                                    sheet.getEmployeeList().get(surnameNumber - 1).getSurname());
                            case 2 -> System.out.println("\nПоточна " + COLUMN_2 + " = " +
                                    sheet.getEmployeeList().get(surnameNumber - 1).getPosition());
                            case 3 -> System.out.println("\nПоточна " + COLUMN_3 + " = " +
                                    sheet.getEmployeeList().get(surnameNumber - 1).getNumberHoursWorked());
                            case 4 -> System.out.println("\nПоточна " + COLUMN_4 + " = " +
                                    sheet.getEmployeeList().get(surnameNumber - 1).getHourlyWage());
                            default -> System.out.println("ПОМИЛКА! Введено неіснуючий номер реквізиту!");
                        }
                        sheet.changeEmployee(surnameNumber - 1, columnNumber);
                    }
                    case 4 -> {
                        if (sheet.getEmployeeList().isEmpty()) {
                            System.out.println(ERROR_EMPTY_COLLECTION);
                            break;
                        }
                        EmployeeList.printTable(sheet.getEmployeeList().stream());
                    }
                    case 5 -> {
                        if (sheet.getEmployeeList().isEmpty()) {
                            System.out.println(ERROR_EMPTY_COLLECTION);
                            break;
                        }
                        System.out.println("\nВАРІАНТИ ЗАПИТІВ:");
                        System.out.println(" 1 - Поточна кількість співробітників у відомості");
                        System.out.println(" 2 - Певна кількість записів з початку відомості");
                        System.out.println(" 3 - Сортування за зростанням певного поля");
                        System.out.println(" 4 - Сортування за спаданням певного поля");
                        System.out.println(" 5 - Сортування прізвищ за алфавітом");
                        System.out.println(" 6 - Пошук даних за певним значенням поля");
                        System.out.println(" 7 - Пошук значень полів за певним діапазоном чисел");
                        System.out.println(" 8 - Пошук максимального значення за певним реквізитом");
                        System.out.println(" 9 - Пошук мінімального значення за певним реквізитом");
                        System.out.println("\nОберіть варіант певного запиту, ввівши відповідне число від 1 до 9:");
                        int requestNumber = scanner.nextInt();
                        useRequests(requestNumber);
                    }
                    case 6 -> {
                        if (sheet.getEmployeeList().isEmpty()) {
                            System.out.println(ERROR_EMPTY_COLLECTION);
                            break;
                        }
                        System.out.println("\nВАРІАНТИ ЗБЕРЕЖЕННЯ ДАНИХ ДО ФАЙЛУ:");
                        System.out.println(" 1 - Збереження даних колекції до CSV-файлу");
                        System.out.println(" 2 - Бінарна сереалізація колекції до файлу");
                        System.out.println("\nОберіть варіант збереження даних:");
                        switch (scanner.nextInt()) {
                            case 1 -> {
                                System.out.println("\nВведіть назву CSV-файлу(.csv):");
                                FileIO.saveToCSV(sheet.getEmployeeList(), scanner.next());
                            }
                            case 2 -> {
                                System.out.println("\nВведіть назву файлу(.bin, .xml):");
                                FileIO.serialize(sheet.getEmployeeList(), scanner.next());
                            }
                            default -> System.out.println("ПОМИЛКА! Введено неіснуючий варіант збереження даних!");
                        }
                    }
                    case 7 -> {
                        System.out.println("\nВАРІАНТИ ІМПОРТУ ДАНИХ З ФАЙЛУ:");
                        System.out.println(" 1 - Імпорт даних у колекцію з CSV-файлу");
                        System.out.println(" 2 - Бінарна десеріалізація колекції із файлу");
                        System.out.println("\nОберіть варіант імпорту даних:");
                        try {
                            switch (scanner.nextInt()) {
                                case 1 -> {
                                    System.out.println("\nВведіть назву CSV-файлу(.csv):");
                                    FileIO.importFromCSV(scanner.next());
                                }
                                case 2 -> {
                                    System.out.println("\nВведіть назву файлу(.bin, .xml):");
                                    FileIO.deserialize(scanner.next());
                                }
                                default -> System.out.println("ПОМИЛКА! Введено неіснуючий варіант імпорту даних!");
                            }
                        }
                        catch (Exception e) {
                            System.out.println("Схоже щось пішло не так...");
                            System.out.println("Можливо цей файл порожній");
                        }
                    }
                    case 8 -> System.out.println("\nРОБОТА ПРОГРАМИ ЗАВЕРШЕНА");
                    default -> System.out.println("\nПОМИЛКА! Введено неіснуючий варіант дії!");
                }
            }
            catch (InputMismatchException e) {
                System.out.println("ПОМИЛКА! Неправильний формат введених даних!");
                scanner.next(); // очистка буфера вводу
            }
        }
    }
    public static void useRequests(int requestNumber) {
        Scanner scanner = new Scanner(System.in);
        int columnNumber;
        try {
            switch (requestNumber) {
                case 1 -> System.out.println("\nПоточна кількість співробітників у відомості = " + sheet.getCountEmployee());
                case 2 -> {
                    System.out.println("\nВведіть кількість записів з початку, яку потрібно отримати:");
                    int countRecords = scanner.nextInt();
                    System.out.println("\nПерші " + countRecords + " записів у відомості:");
                    EmployeeList.printTable(sheet.getSomeCountRecords(countRecords));
                }
                case 3 -> {
                    System.out.println(TEXT);
                    switch (scanner.nextInt()) {
                        case 1 -> EmployeeList.printTable(sheet.sortAscendingByNumberHoursWorked());
                        case 2 -> EmployeeList.printTable(sheet.sortAscendingByHourlyWage());
                        case 3 -> EmployeeList.printTable(sheet.sortAscendingByMonthlySalary());
                        case 4 -> EmployeeList.printTable(sheet.sortAscendingByPremium());
                        case 5 -> EmployeeList.printTable(sheet.sortAscendingByTux());
                        case 6 -> EmployeeList.printTable(sheet.sortAscendingByNetSalary());
                        default -> System.out.println("ПОМИЛКА! Введено неіснуючий номер реквізиту");
                    }
                }
                case 4 -> {
                    System.out.println(TEXT);
                    switch (scanner.nextInt()) {
                        case 1 -> EmployeeList.printTable(sheet.sortDescendingByNumberHoursWorked());
                        case 2 -> EmployeeList.printTable(sheet.sortDescendingByHourlyWage());
                        case 3 -> EmployeeList.printTable(sheet.sortDescendingByMonthlySalary());
                        case 4 -> EmployeeList.printTable(sheet.sortDescendingByPremium());
                        case 5 -> EmployeeList.printTable(sheet.sortDescendingByTux());
                        case 6 -> EmployeeList.printTable(sheet.sortDescendingByNetSalary());
                        default -> System.out.println("ПОМИЛКА! Введено неіснуючий номер реквізиту");
                    }
                }
                case 5 -> {
                    System.out.println("\nВАРІАНТИ СОРТУВАННЯ:");
                    System.out.println(" 1 - А -> Я" + "\n 2 - Я -> А");
                    System.out.println("Оберіть варіант сортування: ");
                    int sortOption = scanner.nextInt();
                    if (sortOption == 1) EmployeeList.printTable(sheet.sortAscendingBySurname());
                    else if (sortOption == 2) EmployeeList.printTable(sheet.sortDescendingBySurname());
                    else System.out.println("ПОМИЛКА! Введено неіснуючий варіант сортування");
                }
                case 6 -> {
                    System.out.println("\nВАРІАНТИ РЕКВІЗИТІВ" + "\n 1 - " + COLUMN_1 + "\n 2 - " + COLUMN_2 +
                            "\n 3 - " + COLUMN_3 + "\n 4 - " + COLUMN_4 + "\n 5 - " + COLUMN_5 + "\n 6 - " +
                            COLUMN_6 + "\n 7 - " + COLUMN_7 + "\n 8 - " + COLUMN_8);
                    System.out.println("\nОберіть варіант реквізиту");
                    columnNumber = scanner.nextInt();
                    String textValue = null;
                    int numberValue = 0;
                    if (columnNumber == 1 || columnNumber == 2) {
                        System.out.println("\nВведіть текстове значення за яким вам потрібно знайти дані:");
                        textValue = scanner.next();
                    }
                    else if (columnNumber > 2 && columnNumber < 9) {
                        System.out.println("\nВведіть одне число, якому повинно дорівнювати значення поля:");
                        numberValue = scanner.nextInt();
                    }
                    switch (columnNumber) {
                        case 1 -> EmployeeList.printTable(sheet.filterBySurnameByValue(textValue));
                        case 2 -> EmployeeList.printTable(sheet.filterByPositionByValue(textValue));
                        case 3 -> EmployeeList.printTable(sheet.filterByNumberHoursWorkedByValue(numberValue));
                        case 4 -> EmployeeList.printTable(sheet.filterByHourlyWageByValue(numberValue));
                        case 5 -> EmployeeList.printTable(sheet.filterByMonthlySalaryByValue(numberValue));
                        case 6 -> EmployeeList.printTable(sheet.filterByPremiumByValue(numberValue));
                        case 7 -> EmployeeList.printTable(sheet.filterByTuxByValue(numberValue));
                        case 8 -> EmployeeList.printTable(sheet.filterByNetSalaryByValue(numberValue));
                        default -> System.out.println("ПОМИЛКА! Введено неіснуючий номер реквізиту");
                    }
                }
                case 7 -> {
                    System.out.println(TEXT);
                    columnNumber = scanner.nextInt();
                    int firstNumber = 0, lastNumber = 0;
                    if (columnNumber > 0 && columnNumber < 7) {
                        System.out.println("\nВведіть два числа, що будуть відповідати початку та закінченню діапазону: ");
                        firstNumber = scanner.nextInt();
                        lastNumber = scanner.nextInt();
                    }
                    switch (columnNumber) {
                        case 1 -> EmployeeList.printTable(sheet.filterByNumberHoursWorkedInRange(firstNumber, lastNumber));
                        case 2 -> EmployeeList.printTable(sheet.filterByHourlyWageInRange(firstNumber, lastNumber));
                        case 3 -> EmployeeList.printTable(sheet.filterByMonthlySalaryInRange(firstNumber, lastNumber));
                        case 4 -> EmployeeList.printTable(sheet.filterByPremiumInRange(firstNumber, lastNumber));
                        case 5 -> EmployeeList.printTable(sheet.filterByTuxInRange(firstNumber, lastNumber));
                        case 6 -> EmployeeList.printTable(sheet.filterByNetSalaryInRange(firstNumber, lastNumber));
                        default -> System.out.println("ПОМИЛКА! Введено неіснуючий номер реквізиту");
                    }
                }
                case 8 -> {
                    System.out.println(TEXT);
                    switch (scanner.nextInt()) {
                        case 1 -> EmployeeList.printTable(sheet.filterByNumberHoursWorkedByMax());
                        case 2 -> EmployeeList.printTable(sheet.filterByHourlyWageByMax());
                        case 3 -> EmployeeList.printTable(sheet.filterByMonthlySalaryByMax());
                        case 4 -> EmployeeList.printTable(sheet.filterByPremiumByMax());
                        case 5 -> EmployeeList.printTable(sheet.filterByTuxByMax());
                        case 6 -> EmployeeList.printTable(sheet.filterByNetSalaryByMax());
                        default -> System.out.println("ПОМИЛКА! Введено неіснуючий номер реквізиту");
                    }
                }
                case 9 -> {
                    System.out.println(TEXT);
                    switch (scanner.nextInt()) {
                        case 1 -> EmployeeList.printTable(sheet.filterByNumberHoursWorkedByMin());
                        case 2 -> EmployeeList.printTable(sheet.filterByHourlyWageByMin());
                        case 3 -> EmployeeList.printTable(sheet.filterByMonthlySalaryByMin());
                        case 4 -> EmployeeList.printTable(sheet.filterByPremiumByMin());
                        case 5 -> EmployeeList.printTable(sheet.filterByTuxByMin());
                        case 6 -> EmployeeList.printTable(sheet.filterByNetSalaryByMin());
                        default -> System.out.println("ПОМИЛКА! Введено неіснуючий номер реквізиту");
                    }
                }
                default -> System.out.println("ПОМИЛКА! Введено неправильний номер запиту!");
            }
        }
        catch (InputMismatchException e) {
            System.out.println("ПОМИЛКА! Неправильний формат введених даних!");
            scanner.next(); // очистка буфера вводу
        } catch (NoSuchElementException e) {
            System.out.println("ПОМИЛКА! Введені вами дані відсутні!");
        }
    }
}
package Lab2;

import java.time.LocalDate;
import java.util.Arrays;

import Lab1.src.Company;
import Lab1.src.Department;
import Lab1.src.Employee;
import Lab1.src.Project;
import java.io.*;

public class Main {

    public static void main(String[] args) {
        // Створюємо тестовий об'єкт Company
        Company company = createTestCompany();
        Employee employee = new Employee("Ian Drek", "Kitrum", 130000);

        // Створюємо серіалізатори
//        Serializer<Employee> jsonSerializerEmployee = new JsonSerializer<>(Employee.class);
//        Serializer<Company> jsonSerializerCompany = new JsonSerializer<>(Company.class);
//        Serializer<Company> xmlSerializer = new XmlSerializer<>(Company.class);
        Serializer<Company> txtSerializer = new TxtSerializer<>(Company.class);
//
//        // Використання серіалізаторів для запису об'єкта у файли
//        jsonSerializerEmployee.writeToFile(employee, new File("employee.json"));
//        jsonSerializerCompany.writeToFile(company, new File("company.json"));
//        xmlSerializer.writeToFile(company, new File("company.xml"));
//        txtSerializer.writeToFile(company, new File("company.txt"));
//
//        // Використання серіалізаторів для читання об'єкта з файлів
//        Employee jsonDeserializedEmployee = jsonSerializerEmployee.readFromFile(new File("employee.json"));
//        Company xmlDeserializedCompany = xmlSerializer.readFromFile(new File("company.xml"));
        Company txtDeserializedCompany = new CompanyDeserializer().deserialize("company.txt");

        // Виведення результатів
//        System.out.println("JSON Deserialized: " + jsonDeserializedEmployee);
//        System.out.println("XML Deserialized: " + xmlDeserializedCompany);
        System.out.println("TXT Deserialized: " + txtDeserializedCompany);
    }

    private static Company createTestCompany() {
        Employee employee1 = new Employee("Ian Drek", "Kitrum", 55000);
        Employee employee2 = new Employee("Yan Gorsky", "GeoComply", 33000);
        Project project1 = new Project("Project A", Arrays.asList(employee1, employee2));
        Project project2 = new Project("Project B", Arrays.asList(employee1));
        Department department1 = new Department("Development Department", Arrays.asList(project1));
        Department department2 = new Department("QA Department", Arrays.asList(project2));
        return new Company("IT Solutions", Arrays.asList(department1, department2));
    }
}
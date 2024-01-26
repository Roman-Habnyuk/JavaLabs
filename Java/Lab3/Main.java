package Lab3;

import Lab1.src.Company;
import Lab1.src.Department;
import Lab1.src.Employee;
import Lab1.src.Project;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Company company = createTestCompany();
        CompanyService companyService = new CompanyService();
        CompanyServiceStream companyServiceStream = new CompanyServiceStream();

        List<Department> qaDepart = companyService.getDepartmentsByPartName(company.getDepartments(), "QA");
        List<Department> sortedDepart = companyService.sortDepartmentsByName(company.getDepartments());
        List<Project> sortedProjectsByEmployee = companyService.sortProjectsByEmployeeCount(company.getAllProjects());
        System.out.println("Search department by partname 'QA': " + qaDepart);
        System.out.println("Sorted Departments by Name: " + sortedDepart);
        System.out.println("Sorted Projects by Employee Count: " + sortedProjectsByEmployee);

        List<Department> qaDepartStream = companyServiceStream.getDepartmentsByPartName(company.getDepartments(), "QA");
        List<Employee> sortedEmployees = companyServiceStream.sortBySalary(company.getAllEmployees());
        List<Project> sortedProjects = companyServiceStream.sortProjectsByEmployeeCount(company.getAllProjects());
        List<Department> sortedDepartments = companyServiceStream.sortDepartmentsByName(company.getDepartments());

        System.out.println("Search department by partname 'QA': " + qaDepartStream);
        System.out.println("Sorted Employees by Salary (Stream API): " + sortedEmployees);
        System.out.println("Sorted Projects by Employee Count (Stream API): " + sortedProjects);
        System.out.println("Sorted Departments by Name (Stream API): " + sortedDepartments);
    }

    private static Company createTestCompany() {
        Employee employee1 = new Employee("Ian Drek", "Kitrum", 55000);
        Employee employee2 = new Employee("Yan Gorsky", "GeoComply", 33000);
        Employee employee3 = new Employee("Yuli Cira", "TechMagic", 25000);

        Project project1 = new Project("Project A", Arrays.asList(employee1, employee2));
        Project project2 = new Project("Project B", Arrays.asList(employee1, employee2, employee3));
        Department department1 = new Department("Development Department", Arrays.asList(project1));
        Department department2 = new Department("QA Department", Arrays.asList(project2));
        return new Company("IT Solutions", Arrays.asList(department1, department2));
    }
}
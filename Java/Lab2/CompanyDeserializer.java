package Lab2;
import Lab1.src.Company;
import Lab1.src.Department;
import Lab1.src.Employee;
import Lab1.src.Project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CompanyDeserializer {

    private Pattern companyPattern = Pattern.compile("Company\\{name='([^']*)' departments='(\\[.+\\])'\\}");
    private Pattern departmentPattern = Pattern.compile("Department\\{name='([^']*)', projects=(\\[.+\\])");
    private Pattern projectPattern = Pattern.compile("Project\\{name='([^']*)', employees=(\\[.+\\])\\}");
    private Pattern employeePattern = Pattern.compile("Employee\\{name='([^']*)', position='([^']*)', salary=(\\d+)\\}");

    public Company deserialize(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            StringBuilder jsonStringBuilder = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                jsonStringBuilder.append(line.trim());
            }

            String json = jsonStringBuilder.toString();
            return parseCompany(json);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Company parseCompany(String json) {
        Matcher matcher = companyPattern.matcher(json);

        if (matcher.find()) {
            String companyName = matcher.group(1);
            String departmentsJson = matcher.group(2);

            List<Department> departments = parseDepartments(departmentsJson);

            return new Company(companyName, departments);
        } else {
            return null;
        }
    }

    private List<Department> parseDepartments(String json) {
        Matcher matcher = departmentPattern.matcher(json);
        List<Department> departments = new ArrayList<>();

        while (matcher.find()) {
            String departmentName = matcher.group(1);
            String projectsJson = matcher.group(2);

            List<Project> projects = parseProjects(projectsJson);
            departments.add(new Department(departmentName, projects));
        }

        return departments;
    }

    private List<Project> parseProjects(String json) {
        Matcher matcher = projectPattern.matcher(json);
        List<Project> projects = new ArrayList<>();

        while (matcher.find()) {
            String projectName = matcher.group(1);
            String employeesJson = matcher.group(2);

            List<Employee> employees = parseEmployees(employeesJson);
            projects.add(new Project(projectName, employees));
        }

        return projects;
    }

    private List<Employee> parseEmployees(String json) {
        Matcher matcher = employeePattern.matcher(json);
        List<Employee> employees = new ArrayList<>();

        while (matcher.find()) {
            String employeeName = matcher.group(1);
            String position = matcher.group(2);
            int salary = Integer.parseInt(matcher.group(3));

            employees.add(new Employee(employeeName, position, salary));
        }

        return employees;
    }
}
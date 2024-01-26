package Lab3;

import Lab1.src.Department;
import Lab1.src.Employee;
import Lab1.src.Project;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CompanyServiceStream implements Services{

    public List<Employee> sortBySalary(List<Employee> employees) {
        return employees.stream()
                .sorted(Comparator.comparingInt(Employee::getSalary).reversed())
                .collect(Collectors.toList());
    }

    public List<Project> sortProjectsByEmployeeCount(List<Project> projects) {
        Comparator<Project> comparator = Comparator.comparingInt(project -> project.getEmployees().size());
        return projects.stream()
                .sorted(comparator.reversed())
                .collect(Collectors.toList());
    }

    public List<Department> sortDepartmentsByName(List<Department> departments) {
        return departments.stream()
                .sorted(Comparator.comparing(Department::getName))
                .collect(Collectors.toList());
    }

    public List<Department> getDepartmentsByPartName(List<Department> departments, String partName) {
        if (partName == null || partName.isEmpty()) {
            return new ArrayList<>(departments);
        }

        return departments.stream()
                .filter(department -> department.getName().contains(partName))
                .collect(Collectors.toList());
    }
}
package Lab3;

import Lab1.src.Department;
import Lab1.src.Employee;
import Lab1.src.Project;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
public class CompanyService implements Services{
    public List<Employee> sortBySalary(List<Employee> employees) {
        employees.sort(Comparator.comparingInt(Employee::getSalary).reversed());
        return employees;
    }
    public List<Project> sortProjectsByEmployeeCount(List<Project> projects) {
        Comparator<Project> comparator = Comparator.comparingInt(project -> project.getEmployees().size());
        projects.sort(comparator.reversed());
        return projects;
    }

    public List<Department> sortDepartmentsByName(List<Department> departments) {
        Comparator<Department> comparator = Comparator.comparing(Department::getName);
        departments.sort(comparator);
        return departments;
    }

    public List<Department> getDepartmentsByPartName(List<Department> departments, String partName) {
        if (partName == null || partName.isEmpty()) {
            return new ArrayList<>(departments);
        }

        List<Department> filteredDepartments = new ArrayList<>();

        for (Department department : departments) {
            if (department.getName().contains(partName)) {
                filteredDepartments.add(department);
            }
        }

        return filteredDepartments;
    }
}
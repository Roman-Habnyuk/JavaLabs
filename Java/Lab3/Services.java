package Lab3;

import Lab1.src.Department;
import Lab1.src.Employee;
import Lab1.src.Project;

import java.util.List;

public interface Services {
    List<Project> sortProjectsByEmployeeCount(List<Project> projects);

    List<Department> sortDepartmentsByName(List<Department> departments);

    List<Department> getDepartmentsByPartName(List<Department> departments, String partName);

    List<Employee> sortBySalary(List<Employee> employees);
}
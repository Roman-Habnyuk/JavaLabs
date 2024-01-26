package Lab2;

import Lab1.src.Company;
import Lab1.src.Department;
import Lab1.src.Employee;
import Lab1.src.Project;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.*;
import java.util.regex.Pattern;

public class TxtSerializer<T> implements Serializer<T> {
    private final ObjectMapper objectMapper;
    private final Class<T> type;

    public TxtSerializer(Class<T> type) {
        this.type = type;
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    @Override
    public void serialize(T object, File file) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(convertObjectToString(object));
        }
    }

    @Override
    public T deserialize(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            return convertStringToObject(stringBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void writeToFile(T object, File file) {
        try {
            serialize(object, file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public T readFromFile(File file) {
        return deserialize(file);
    }

    private String convertObjectToString(T object) {
        if (object == null) {
            return "";
        }

        if (object instanceof Company) {
            return companyToString((Company) object);
        } else if (object instanceof Department) {
            return departmentToString((Department) object);
        } else if (object instanceof Project) {
            return projectToString((Project) object);
        } else if (object instanceof Employee) {
            return employeeToString((Employee) object);
        }

        return "";
    }

    private T convertStringToObject(String serializedObject) {
        try {
            return objectMapper.readValue(serializedObject, type);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String companyToString(Company company) {
        StringBuilder result = new StringBuilder();
        result.append("Company{")
                .append("name='").append(company.getName())
                .append("' departments='").append(company.getDepartments())
                .append("'}");
        return result.toString();
    }

    private String departmentToString(Department department) {
        StringBuilder result = new StringBuilder();
        result.append("Department{")
                .append("name='").append(department.getName())
                .append("' projects='").append(department.getProjects())
                .append("'}");
        return result.toString();
    }

    private String projectToString(Project project) {
        StringBuilder result = new StringBuilder();
        result.append("Project{")
                .append("name='").append(project.getName())
                .append("' employees='").append(project.getEmployees())
                .append("'}");
        return result.toString();
    }

    private String employeeToString(Employee employee) {
        StringBuilder result = new StringBuilder();
        result.append("Employee{")
                .append("name='").append(employee.getName())
                .append("', position='").append(employee.getPosition())
                .append("', salary='").append(employee.getSalary())
                .append("'}");
        return result.toString();
    }
}
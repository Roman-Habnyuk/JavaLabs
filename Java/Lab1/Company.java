package Lab1.src;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Клас, що представляє ІТ компанію.
 */
@XmlRootElement
public class Company implements Serializable {

    private String name;
    private List<Department> departments;

    public Company(String name, List<Department> departments) {
        this.name = name;
        this.departments = departments;
    }
    private Company(Builder builder) {
        this.name = builder.name;
        this.departments = builder.departments;
    }
    public Company() {
        this.departments = new ArrayList<>();
    }
    /**
     * Метод для отримання назви компанії.
     */
    public String getName() {
        return name;
    }

    /**
     * Метод для отримання списку відділів компанії.
     */
    public List<Department> getDepartments() {
        return departments;
    }

    /**
     * Метод для отримання рядкового представлення компанії.
     */
    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                ", departments=" + departments +
                '}';
    }

    /**
     * Метод для порівняння компаній за допомогою equals.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return Objects.equals(name, company.name);
    }

    /**
     * Метод для отримання хеш-коду компанії.
     */
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
    public List<Project> getAllProjects() {
        return departments.stream()
                .flatMap(department -> department.getProjects().stream())
                .distinct()
                .collect(Collectors.toList());
    }

    public List<Employee> getAllEmployees() {
        return departments.stream()
                .flatMap(department -> department.getProjects().stream())
                .flatMap(project -> project.getEmployees().stream())
                .distinct()
                .collect(Collectors.toList());
    }
    /**
     * Внутрішній клас Builder для побудови об'єкту Company.
     */
    public static class Builder {
        private String name;
        private List<Department> departments;

        /**
         * Конструктор з обов'язковим параметром - назва компанії.
         */
        public Builder name(String name) {
            this.name = name;
            return this;
        }

        /**
         * Метод для додавання списку відділів компанії.
         */
        public Builder departments(List<Department> departments) {
            this.departments = departments;
            return this;
        }
        /**
         * Метод для побудови об'єкту Company.
         */
        public Company build() {
            return new Company(this);
        }
    }

}
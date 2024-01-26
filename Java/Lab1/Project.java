package Lab1.src;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Project implements Serializable {
    private String name;
    private List<Employee> employees;

    public Project(String name, List<Employee> employees) {
        this.employees = employees;
        this.name = name;
    }
    public Project(Builder builder) {
        this.employees = new ArrayList<>();
        this.name = builder.name;
    }
    public Project() {
        this.employees = new ArrayList<>();
    }
    /**
     * Метод для отримання назви проекту.
     */
    public String getName() {
        return name;
    }

  
    public List<Employee> getEmployees() {
        return employees;
    }

    
    @Override
    public String toString() {
        return "Project{" +
        "name='" + name + '\'' +
        ", employees=" + employees +
        '}';
    }

    /**
     * Метод для порівняння проектів за допомогою equals.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return Objects.equals(name, project.name) &&
        Objects.equals(employees, project.employees);
    }

    /**
     * Метод для отримання хеш-коду проекту.
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, employees);
    }

    /**
     * Внутрішній клас Builder для побудови об'єкту Project.
     */
    public static class Builder {
        private String name;
        private List<Employee> employees;

        /**
         * Конструктор з обов'язковим параметром - назва проекту.
         */
        public Builder name(String name) {
            this.name = name;
            return this;
        }

        /**
         * Метод для додавання списку працівників проекту.
         */
        public Builder employees(List<Employee> employees) {
            this.employees = employees;
            return this;
        }

        /**
         * Метод для побудови об'єкту Project.
         */
        public Project build() {
            return new Project(this);
        }
    }
}
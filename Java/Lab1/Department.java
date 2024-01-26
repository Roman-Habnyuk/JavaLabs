package Lab1.src;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Клас, що представляє відділ в ІТ компанії.
 */
public class Department implements Serializable {
    private String name;
    private List<Project> projects;

    public Department(String name, List<Project> projects) {
        this.name = name;
        this.projects = projects;
    }
    private Department(Builder builder) {
        this.name = builder.name;
        this.projects = builder.projects;
    }
    public Department() {
        this.projects = new ArrayList<>();
    }
    /**
     * Метод для отримання назви відділу.
     */
    public String getName() {
        return name;
    }

    /**
     * Метод для отримання списку проектів відділу.
     */
    public List<Project> getProjects() {
        return projects;
    }

    /**
     * Метод для отримання рядкового представлення відділу.
     */
    @Override
    public String toString() {
        return "Department{" +
                "name='" + name + '\'' +
                ", projects=" + projects +
                '}';
    }

    /**
     * Метод для порівняння відділів за допомогою equals.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return Objects.equals(name, that.name);
    }

    /**
     * Метод для отримання хеш-коду відділу.
     */
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }


    /**
     * Внутрішній клас Builder для побудови об'єкту Department.
     */
    public static class Builder {
        private String name;
        private List<Project> projects;

        /**
         * Конструктор з обов'язковим параметром - назва відділу.
         */
        public Builder name(String name) {
            this.name = name;
            return this;
        }

        /**
         * Метод для додавання списку проектів відділу.
         */
        public Builder projects(List<Project> projects) {
            this.projects = projects;
            return this;
        }

        public Builder project(Project project) {
            if(this.projects == null) {
                this.projects = new ArrayList<>();
            }
            this.projects.add(project);
            return this;
        }

        /**
         * Метод для побудови об'єкту Department.
         */
        public Department build() {
            return new Department(this);
        }
    }
}
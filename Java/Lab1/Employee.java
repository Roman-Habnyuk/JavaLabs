package Lab1.src;

import Lab2.LocalDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Клас, що представляє працівника в ІТ компанії.
 */
public class Employee implements Serializable {

    private String name;
    private String position;
    private int salary;



    public Employee(String name, String position, int salary) {
        this.name = name;
        this.position = position;
        this.salary = salary;
    }

    public Employee(Builder builder) {
        this.name = builder.name;
        this.position = builder.position;
        this.salary = builder.salary;
    }
    public Employee() {
        this.name = "John";
        this.position = " ";
        this.salary = 0;
    }

    /**
     * Метод для отримання імені працівника.
     */
    public String getName() {
        return name;
    }

    /**
     * Метод для отримання посади працівника.
     */
    public String getPosition() {
        return position;
    }

    /**
     * Метод для отримання зарплати працівника.
     */
    public int getSalary() {
        return salary;
    }


    /**
     * Метод для отримання рядкового представлення працівника.
     */
    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", position='" + position + '\'' +
                ", salary=" + salary +
                '}';
    }

    /**
     * Метод для порівняння працівників за допомогою equals.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return salary == employee.salary &&
                Objects.equals(name, employee.name) &&
                Objects.equals(position, employee.position);
    }

    /**
     * Метод для отримання хеш-коду працівника.
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, position, salary);
    }

    /**
     * Внутрішній клас Builder для побудови об'єкту Employee.
     */
    public static class Builder {
        private String name;
        private String position;
        private int salary;

        /**
         * Конструктор з обов'язковим параметром - ім'я працівника.
         */
        public Builder name(String name) {
            this.name = name;
            return this;
        }

        /**
         * Метод для додавання посади працівника.
         */
        public Builder position(String position) {
            this.position = position;
            return this;
        }

        /**
         * Метод для додавання зарплати працівника.
         */
        public Builder salary(int salary) {
            this.salary = salary;
            return this;
        }



        /**
         * Метод для побудови об'єкту Employee.
         */
        public Employee build() {
            return new Employee(this);
        }
    }
}
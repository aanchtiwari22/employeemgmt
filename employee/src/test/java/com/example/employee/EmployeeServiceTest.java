package com.example.employee;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class EmployeeServiceTest {
    @Test
    public void testSaveEmployee() {
        Employee employee = new Employee();
        employee.setName("John");
        employee.setRole("Developer");

        assertEquals("John", employee.getName());
        assertEquals("Developer", employee.getRole());
    }
}

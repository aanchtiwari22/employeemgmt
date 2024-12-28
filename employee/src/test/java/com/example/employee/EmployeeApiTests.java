package com.example.employee;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class EmployeeApiTests {

    @Test
    public void testGetAllEmployees() {
        RestAssured.baseURI = "http://localhost:8081";

        // Test GET /api/employees
        given()
            .contentType(ContentType.JSON)
        .when()
            .get("/api/employees")
        .then()
            .statusCode(200)
            .body("$", notNullValue()); // Ensure response body is not null
    }

    @Test
    public void testCreateEmployee() {
        RestAssured.baseURI = "http://localhost:8081";

        // Test POST /api/employees
        String newEmployee = """
            {
                "name": "John Doe",
                "role": "Developer"
            }
        """;

        given()
            .contentType(ContentType.JSON)
            .body(newEmployee)
        .when()
            .post("/api/employees")
        .then()
            .statusCode(200)
            .body("name", equalTo("John Doe"))
            .body("role", equalTo("Developer"));
    }

    @Test
    public void testUpdateEmployee() {
        RestAssured.baseURI = "http://localhost:8081";

        // First, create a new employee
        String newEmployee = """
            {
                "name": "Jane Smith",
                "role": "Tester"
            }
        """;

        int employeeId = given()
            .contentType(ContentType.JSON)
            .body(newEmployee)
        .when()
            .post("/api/employees")
        .then()
            .statusCode(200)
            .extract()
            .path("id");

        // Test PUT /api/employees/{id}
        String updatedEmployee = """
            {
                "name": "Jane Doe",
                "role": "Lead Tester"
            }
        """;

        given()
            .contentType(ContentType.JSON)
            .body(updatedEmployee)
        .when()
            .put("/api/employees/" + employeeId)
        .then()
            .statusCode(200)
            .body("name", equalTo("Jane Doe"))
            .body("role", equalTo("Lead Tester"));
    }

    @Test
    public void testDeleteEmployee() {
        RestAssured.baseURI = "http://localhost:8081";

        // First, create a new employee
        String newEmployee = """
            {
                "name": "Mark Taylor",
                "role": "Architect"
            }
        """;

        int employeeId = given()
            .contentType(ContentType.JSON)
            .body(newEmployee)
        .when()
            .post("/api/employees")
        .then()
            .statusCode(200)
            .extract()
            .path("id");

        // Test DELETE /api/employees/{id}
        given()
        .when()
            .delete("/api/employees/" + employeeId)
        .then()
            .statusCode(200);

        // Verify the employee was deleted
        given()
        .when()
            .get("/api/employees/" + employeeId)
        .then()
            .statusCode(404);
    }
}

package com.api.employeemanagement.controller;

import com.api.employeemanagement.models.Employee;
import com.api.employeemanagement.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/employeemanagement")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;


    @GetMapping("/all")
    @Operation(summary = "Get all employees")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved employees")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        //Aqui deveria ser implementada uma validação para garantir a consistencia dos dados vindos da requisição
        //Um validator poderia resolver a necessidade.

        try {
            List<Employee> employees = employeeService.listEmployees();
            return ResponseEntity.ok(employees);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("{id}")
    @Operation(summary = "Get employee by ID")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved employee")
    @ApiResponse(responseCode = "404", description = "Employee not found")
    public ResponseEntity<Employee> getClientByNumeroConta(@PathVariable Integer id) {
        try {
            Employee employee = employeeService.getEmployeeById(id);
            if (employee != null) {
                return ResponseEntity.ok(employee);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    @Operation(summary = "Create a new employee")
    @ApiResponse(responseCode = "200", description = "Employee created successfully")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        try {
            employeeService.addEmployee(employee);
            return ResponseEntity.status(HttpStatus.CREATED).body(employee);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete an employee by ID")
    @ApiResponse(responseCode = "200", description = "Employee deleted successfully")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Integer id) {
        try {
            employeeService.deleteEmployee(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping
    @Operation(summary = "Edit an existing employee")
    @ApiResponse(responseCode = "200", description = "Employee updated successfully")
    public ResponseEntity<Employee> editEmployee(@RequestBody Employee employeeUpdated) {
        //Aqui deveria ser implementada uma validação para garantir a consistencia dos dados vindos da requisição
        //Um validator poderia resolver a necessidade.
        try {
            int id = employeeUpdated.getId();
            Employee employeeFound = employeeService.getEmployeeById(id);
            if (employeeFound == null) {
                return ResponseEntity.notFound().build();
            }
            employeeService.updateEmployee(employeeUpdated);
            return ResponseEntity.ok(employeeUpdated);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}

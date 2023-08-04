package com.api.employeemanagement.repository;

import com.api.employeemanagement.models.Employee;

import java.util.List;

public interface EmployeeRepository{

    void addEmployee(Employee employee);

    List<Employee> listEmployees();

    Employee getEmployeeById(Integer id);

    void updateEmployee(Employee employee);

    void deleteEmployee(Integer id);
}

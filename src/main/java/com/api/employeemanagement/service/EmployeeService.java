package com.api.employeemanagement.service;

import com.api.employeemanagement.models.Employee;
import com.api.employeemanagement.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeService implements EmployeeRepository {

    private final Integer INITIAL_ID = 1;

    private Map<Integer, Employee> employeeDB = new HashMap<Integer, Employee>();

    @Override
    public void addEmployee(Employee employee) {
        Integer maxId = Integer.MIN_VALUE;

        if(employeeDB.size() > 0) {
            for (Integer id : employeeDB.keySet()) {
                if (id > maxId) {
                    maxId = id;
                }
            }
            employee.setId(maxId + 1);
            employeeDB.put(maxId + 1, employee);
        } else {
            employee.setId(INITIAL_ID);
            employeeDB.put(INITIAL_ID, employee);
        }
    }

    @Override
    public List<Employee> listEmployees() {
        return new ArrayList<>(employeeDB.values());
    }

    @Override
    public Employee getEmployeeById(Integer id) {
        return employeeDB.get(id);
    }

    @Override
    public void updateEmployee(Employee employee) {
        employeeDB.put(employee.getId(), employee);
    }

    @Override
    public void deleteEmployee(Integer id) {
        employeeDB.remove(id);
    }
}

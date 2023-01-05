package com.employee.dao;


import com.employee.entity.Employee;

import java.util.List;

public interface EmployeeDao {

    boolean addEmployee(Employee employee);

    List<Employee> getAllEmployee();

    Employee getEmployeeByEid(int eid);

    boolean deleteEmployee(int eid);

    boolean updateEmployee(Employee employee);
}

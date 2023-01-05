package com.employee.service;

import com.employee.vo.Employee;
import com.employee.vo.ImageVO;

import java.util.List;

public interface EmployeeService {

    boolean addEmployee(Employee employee);

    List<Employee> getAllEmployee();

    Employee getEmployeeByEid(int eid);

    boolean deleteEmployee(int eid);

    boolean updateEmployee(Employee employee);

    boolean addEmployeePhoto(int eid, ImageVO imageVO);
}

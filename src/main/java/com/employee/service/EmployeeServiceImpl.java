package com.employee.service;

import com.employee.converter.VOEntityConverterUtil;
import com.employee.dao.EmployeeDao;
import com.employee.vo.Employee;
import com.employee.vo.ImageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    @Override
    public boolean addEmployee(Employee employee) {
        return employeeDao.addEmployee(VOEntityConverterUtil.convert(employee));
    }

    @Override
    public List<Employee> getAllEmployee() {
        return employeeDao.getAllEmployee().stream().map(VOEntityConverterUtil::convert).collect(Collectors.toList());
    }

    @Override
    public Employee getEmployeeByEid(int eid) {
        return VOEntityConverterUtil.convert(employeeDao.getEmployeeByEid(eid));
    }

    @Override
    public boolean deleteEmployee(int eid) {
        return employeeDao.deleteEmployee(eid);
    }

    @Override
    public boolean updateEmployee(Employee employee) {
        return employeeDao.updateEmployee(VOEntityConverterUtil.convert(employee));
    }

    @Override
    public boolean addEmployeePhoto(int eid, ImageVO imageVO) {
        Employee emp =getEmployeeByEid(eid);
        emp.setImageUrl(imageVO.getImageUrl());
        return addEmployee(emp);
    }

    private List<Employee> getAllEmp() {
        Employee emp1 = new Employee(1, "Kishan", "kishan@gmail.com", "US1", 9878777);
        Employee emp2 = new Employee(2, "RABIN", "RABIN@gmail.com", "US2", 9878377);
        Employee emp3 = new Employee(3, "MALIK", "MALIK@gmail.com", "US3", 9878477);
        Employee emp4 = new Employee(4, "DIPEN", "DIPEN@gmail.com", "US4", 9878577);
        return Arrays.asList(emp1, emp2, emp3, emp4);
    }
}

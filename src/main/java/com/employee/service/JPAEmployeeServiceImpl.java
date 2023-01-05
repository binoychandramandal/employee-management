package com.employee.service;

import com.employee.converter.VOEntityConverterUtil;
import com.employee.dao.EmployeeDao;
import com.employee.dao.JPAEmployeeDao;
import com.employee.vo.Employee;
import com.employee.vo.ImageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Qualifier("jpaes")
public class JPAEmployeeServiceImpl implements EmployeeService {

    @Autowired
    private JPAEmployeeDao employeeDao;

    @Override
    public boolean addEmployee(Employee employee) {
        try {
            employeeDao.save(VOEntityConverterUtil.convert(employee));
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public List<Employee> getAllEmployee() {
        List<Employee> result = new ArrayList<>();
        employeeDao.findAll().forEach(x -> result.add(VOEntityConverterUtil.convert(x)));
        return result;
    }

    @Override
    public Employee getEmployeeByEid(int eid) {
        return VOEntityConverterUtil.convert(employeeDao.findById(eid).orElse(null));
    }

    @Override
    public boolean deleteEmployee(int eid) {
        try {
            employeeDao.deleteById(eid);
        }catch (Exception e){
            return false;
        }
         return true;
    }

    @Override
    public boolean updateEmployee(Employee employee) {
        return VOEntityConverterUtil.convert(employeeDao.save(VOEntityConverterUtil.convert(employee)))!=null;
    }

    @Override
    public boolean addEmployeePhoto(int eid, ImageVO imageVO) {
       Employee emp= getEmployeeByEid(eid);
       emp.setImageUrl(imageVO.getImageUrl());
        return addEmployee(emp);
    }
}

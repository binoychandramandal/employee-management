package com.employee.dao;

import com.employee.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class EmployeeDaoImpl implements EmployeeDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean addEmployee(Employee employee) {
        int response =  jdbcTemplate.update("insert into employee(name,email,address,phone) values(?,?,?,?)",employee.getName(),employee.getEmail(),employee.getAddress(),employee.getPhone());
        return response>0;
    }

    @Override
    public List<Employee> getAllEmployee() {
       return jdbcTemplate.query("select * from employee",new BeanPropertyRowMapper<>(Employee.class));
    }

    @Override
    public Employee getEmployeeByEid(int eid) {
//        try {
//            return (Employee) jdbcTemplate.queryForObject("select * from employee where eid=?", new Object[]{eid}, new BeanPropertyRowMapper(Employee.class));
//        }catch (Exception e){
//            return null;
//        }

        String sql = "SELECT * FROM EMPLOYEE WHERE EID = ?";

        return jdbcTemplate.queryForObject(sql, new Object[]{eid}, (rs, rowNum) ->
                new Employee(
                        rs.getInt("eid"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("address"),
                        rs.getInt("phone")
                ));
    }

    @Override
    public boolean deleteEmployee(int eid) {
        return jdbcTemplate.update("delete from employee where eid=?",eid)==1;
    }

    @Override
    public boolean updateEmployee(Employee employee) {
        return jdbcTemplate.update("update employee set name=?, phone=?, address=?, email=? where eid=?",employee.getName(),employee.getPhone(),employee.getAddress(),employee.getEmail(),employee.getEid())>0;
    }
}

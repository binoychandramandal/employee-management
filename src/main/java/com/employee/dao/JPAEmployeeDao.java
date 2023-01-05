package com.employee.dao;

import com.employee.entity.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JPAEmployeeDao extends CrudRepository<Employee,Integer> {

}

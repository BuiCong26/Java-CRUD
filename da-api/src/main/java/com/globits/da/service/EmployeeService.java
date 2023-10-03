package com.globits.da.service;

import com.globits.da.dto.search.SearchEmployeeDTO;
import com.globits.da.domain.Employee;

import java.util.List;
public interface EmployeeService {
    List<Employee> findAll();
    Employee findById(int id);
    Employee findWithConditions(SearchEmployeeDTO searchEmployeeDTO);
    void deleteById(int id);
}

package com.globits.da.repository;

import com.globits.da.domain.Employee;
import com.globits.da.dto.search.SearchEmployeeDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee,Integer> {

//    @Query("SELECT e from Employee e")
//    List<Employee> findAllEmployee();
    @Query(value = "call FindEmployeeByDTO(:#{#searchEmployeeDTO.id}, :#{#searchEmployeeDTO.age})", nativeQuery = true)
    Employee findEmployeeByObject(SearchEmployeeDTO searchEmployeeDTO);
//    @Query(value = "select e from Employee e where age =:age", nativeQuery = true)
//    List<Employee> findByAge(int age);
}

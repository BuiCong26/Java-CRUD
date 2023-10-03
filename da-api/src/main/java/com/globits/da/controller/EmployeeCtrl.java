package com.globits.da.controller;

import com.globits.da.domain.Employee;
import com.globits.da.dto.search.SearchEmployeeDTO;
import com.globits.da.service.EmployeeService;
import com.globits.da.service.impl.EmployeeServiceImp;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@AllArgsConstructor
@RestController
public class EmployeeCtrl {

    private EmployeeService employeeService;
    private EmployeeServiceImp employeeServiceImp;

    @GetMapping(value = "/findAll")
    public List<Employee> findAll(){
        return employeeService.findAll();
    }
    @GetMapping(value = "/findById")
    public Employee findById(@RequestParam("id")int id){
        return employeeService.findById(id);
    }
    @PostMapping(value = "/findWithNameAndAge")
    public Employee findById(@RequestBody SearchEmployeeDTO searchEmployeeDTO){
        return employeeService.findWithConditions(searchEmployeeDTO);
    }
    @PostMapping(value = "/deleteById")
    public void deleteById(@RequestParam("id") int id){
        employeeService.deleteById(id);
    }
    @GetMapping(value = "/exportExcel")
    public String exportExcel(){
        try{
            if(employeeServiceImp.exportExcel()){
                return "Xuat file thanh cong";
            }else{
                return "Xuat file khong thanh cong";
            }
        }catch (Exception e){
            e.getMessage();
        }
        return null;
    }
}

package com.globits.da.controller;

import com.globits.da.domain.District;
import com.globits.da.dto.DistrictDTO;
import com.globits.da.service.DistrictService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@AllArgsConstructor
@RequestMapping( "/district")
@RestController
public class DistrictCtrl {
    DistrictService districtService;
    @PostMapping("/addDistrict")
    public void addNewDistrict(@RequestBody List<District> district){
        districtService.addListDistrict(district);
    }
    @PostMapping("/updateDistrict")
    public void updateDistrict(@RequestBody District district){
        districtService.updateDistrictById(district);
    }
    @GetMapping("/deleteDistrict")
    public void deleteDistrict(@RequestParam("id")int id){
        districtService.deleteDistrictById(id);
    }
    @GetMapping("/findAllDistrict")
    public List<DistrictDTO> findAllDistrict(){
        return districtService.findAllDistrict();
    }
    @GetMapping("/findDistrictByProvinceId")
    public List<DistrictDTO> findDistrictByProvinceId(@RequestParam("id")int id){
        return districtService.findAllDistrictByProvinceId(id);
    }
    @GetMapping("/deleteDistrictByProvinceId")
    public void deleteDistrictByProvinceId(@RequestParam("id")int id){
        districtService.deleteDistrictByProvinceId(id);
    }
}

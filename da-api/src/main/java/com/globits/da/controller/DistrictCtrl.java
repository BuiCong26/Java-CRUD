package com.globits.da.controller;

import com.globits.da.domain.District;
import com.globits.da.dto.DistrictDTO;
import com.globits.da.dto.ProvinceDTO;
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
    @GetMapping("/findAllDistrictWithCommune")
    public List<DistrictDTO> findAllDistrictWithCommune(){
        return districtService.findAllDistrictWithCommune();
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
    @PostMapping("/addDistrictAndListCommune")
    public void addProvince(@RequestBody DistrictDTO district){
        districtService.addDistrictAndListCommune(district);
    }
    @PostMapping("/updateDistrictAndCommune")
    public void updateProvinceAndDistrict(@RequestBody DistrictDTO district){
        districtService.updateDistrictAndCommune(district);
    }
}

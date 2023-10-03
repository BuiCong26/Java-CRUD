package com.globits.da.controller;

import com.globits.da.domain.Province;
import com.globits.da.dto.ProvinceDTO;
import com.globits.da.repository.ProvinceRepo;
import com.globits.da.service.ProvinceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor
@RequestMapping( "/province")
@RestController
public class ProvinceCtrl {
    ProvinceService provinceService;
    @PostMapping("/addProvince")
    public void addNewProvince(@RequestBody List<ProvinceDTO> province){
        provinceService.addListProvince(province);
    }
    @GetMapping("/findAllProvinceWithDistrict")
    public List<ProvinceDTO> findAllProvinceWithDistrict(){
        return provinceService.findAllProvinceWithDistrict();
    }
    @PostMapping("/updateProvince")
    public void updateProvince(@RequestBody ProvinceDTO province){
        provinceService.updateProvinceById(province);
    }
    @GetMapping("/deleteProvince")
    public void deleteProvince(@RequestParam("id")int id){
        provinceService.deleteProvinceById(id);
    }
    @GetMapping("/findAllProvince")
    public List<Province> findAllProvince() throws Exception {
         return provinceService.findAllProvince();
    }
    @PostMapping("/addProvinceAndListDistrict")
    public void addProvince(@RequestBody ProvinceDTO province){
        provinceService.addProvinceAndListDistrict(province);
    }
    @PostMapping("/updateProvinceAndDistrict")
    public void updateProvinceAndDistrict(@RequestBody ProvinceDTO province){
        provinceService.updateProvinceAndDistrict(province);
    }
}

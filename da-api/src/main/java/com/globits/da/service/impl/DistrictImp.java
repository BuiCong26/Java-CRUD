package com.globits.da.service.impl;

import com.globits.da.domain.District;
import com.globits.da.domain.Province;
import com.globits.da.dto.DistrictDTO;
import com.globits.da.repository.DistrictRepo;
import com.globits.da.repository.ProvinceRepo;
import com.globits.da.service.DistrictService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class DistrictImp implements DistrictService {
    DistrictRepo districtRepo;
    @Transactional
    @Override
    public List<DistrictDTO> findAllDistrict(){
        List<District> districts = districtRepo.findAll();
        List<DistrictDTO> lstDistrict = new ArrayList<>();
        for(District d: districts){
            lstDistrict.add(entityToDTO(d));
        }
        return lstDistrict;
    }
    @Transactional
    @Override
    public void addListDistrict(List<District> districts){
        try{
            districtRepo.saveAll(districts);
            System.out.println("Add successed!!!");
        }catch (Exception e){
            e.getMessage();
            System.out.println("Add failed!!!");
        }
    }
    @Transactional
    @Override
    public void updateDistrictById(District district){
        try{
            District district1 = districtRepo.getOne(district.getId());
            if(district1 != null){
                district1 = District.builder()
                        .id(district.getId())
                        .name(district.getName())
                        .code(district.getCode())
                        .type(district.getType())
                        .build();
                districtRepo.save(district1);
                System.out.println("Update district id = " + district.getId()+" success!!!");
            }
        }catch (Exception e){
            e.getMessage();
        }
    }
    @Transactional
    @Override
    public void deleteDistrictById(int id){
        try{
            if(districtRepo.getOne(id) != null){
                districtRepo.deleteById(id);
                System.out.println("Delete district id = " + id +" success!!!");
            }
        }catch (Exception e){
            e.getMessage();
        }
    }
    @Transactional
    @Override
    public void deleteDistrictByProvinceId(int id){
        try{
            districtRepo.removeDistrictByProvinceId(id);
        }catch (Exception e){
            e.getMessage();
        }
    }
    @Override
    public List<DistrictDTO> findAllDistrictByProvinceId(int id){
        List<DistrictDTO> lstDistrict = new ArrayList<>();
        List<District> districts = districtRepo.findDistrictByProvinceId(id);
        for(District d: districts){
            lstDistrict.add(entityToDTO(d));
        }
        return lstDistrict;
    }
    public DistrictDTO entityToDTO(District district){
        DistrictDTO d = DistrictDTO.builder()
                .id(district.getId())
                .name(district.getName())
                .code(district.getCode())
                .type(district.getType())
                .province_id(district.getProvince().getId())
                .build();
        return d;
    }
}

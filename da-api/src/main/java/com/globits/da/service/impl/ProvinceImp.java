package com.globits.da.service.impl;

import com.globits.da.domain.District;
import com.globits.da.domain.Province;
import com.globits.da.dto.DistrictDTO;
import com.globits.da.dto.ProvinceDTO;
import com.globits.da.repository.DistrictRepo;
import com.globits.da.repository.ProvinceRepo;
import com.globits.da.service.ProvinceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProvinceImp implements ProvinceService {
    ProvinceRepo provinceRepo;
    DistrictRepo districtRepo;
    DistrictImp districtImp;
    @Transactional
    @Override
    public List<Province> findAllProvince() throws Exception{
        List<Province> p = null;
        try {
            p = provinceRepo.findAll();
            return p;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return p;
    }
    @Transactional
    @Override
    public void addListProvince(List<ProvinceDTO> provinces){
        try{
            for(ProvinceDTO p : provinces){
                Province saveProvince = Province.builder()
                        .name(p.getName())
                        .code(p.getCode())
                        .type(p.getType())
                        .build();
                provinceRepo.save(saveProvince);
            }

            System.out.println("Add successed!!!");
        }catch (Exception e){
            e.getMessage();
            System.out.println("Add failed!!!");
        }
    }
    @Transactional
    @Override
    public void updateProvinceById(ProvinceDTO province){
        try{
            Province province1 = provinceRepo.getOne(province.getId());
            if(province1 != null){
                province1 = Province.builder()
                        .id(province.getId())
                        .name(province.getName())
                        .code(province.getCode())
                        .type(province.getType())
                        .build();
                provinceRepo.save(province1);
                System.out.println("Update province id = " + province.getId()+" success!!!");
            }
        }catch (Exception e){
            e.getMessage();
        }
    }
    @Transactional
    @Override
    public void deleteProvinceById(int id){
        try{
            if(provinceRepo.getOne(id) != null){
                provinceRepo.deleteById(id);
                System.out.println("Delete province id = " + id +" success!!!");
            }
        }catch (Exception e){
            e.getMessage();
        }
    }

    @Transactional
    @Override
    public void addProvinceAndListDistrict(ProvinceDTO province){
        Province a = Province.builder()
                .code(province.getCode())
                .name(province.getName())
                .type(province.getType())
                .build();
        provinceRepo.save(a);
        for(DistrictDTO d: province.getDistricts()){
            District saveDistrict = District.builder()
                    .name(d.getName())
                    .code(d.getCode())
                    .type(d.getType())
                    .province(a)
                    .build();
            districtRepo.save(saveDistrict);
        }

    }
    @Override
    public List<ProvinceDTO> findAllProvinceWithDistrict(){
        List<ProvinceDTO> lstProvinceDTO = new ArrayList<>();
        List<Province> lstProvince = provinceRepo.findAll();
        for(Province p : lstProvince){
            lstProvinceDTO.add(entityToDTO(p));
        }
        return lstProvinceDTO;
    }

    @Transactional
    @Override
    public void updateProvinceAndDistrict(ProvinceDTO provinceUpdate){
        Optional<Province> provinceOpt = provinceRepo.findById(provinceUpdate.getId());
        Province province = provinceOpt.get();
        List<District> districts = new ArrayList<>();
        List<DistrictDTO> districtDTOS = provinceUpdate.getDistricts();
        if(province != null){

            for(DistrictDTO d : districtDTOS){
                Optional<District> districtOpt = districtRepo.findById(d.getId());
                District district = districtOpt.get();
                if(district != null){
                    district = District.builder()
                            .id(d.getId())
                            .name(d.getName())
                            .code(d.getCode())
                            .type(d.getType())
                            .province(province)
                            .build();
                    districts.add(district);
                    districtRepo.save(district);
                }

            }
            province = Province.builder()
                    .id(provinceUpdate.getId())
                    .name(provinceUpdate.getName())
                    .code(provinceUpdate.getCode())
                    .type(provinceUpdate.getType())
                    .districts(districts)
                    .build();
            provinceRepo.save(province);

        }
    }

    public ProvinceDTO entityToDTO(Province province){
        List<District> districts = province.getDistricts();
        List<DistrictDTO> lstDistrict = new ArrayList<>();
        for(District d: districts){
            lstDistrict.add(districtImp.entityToDTO(d));
        }
        ProvinceDTO p = ProvinceDTO.builder()
                .id(province.getId())
                .name(province.getName())
                .code(province.getCode())
                .type(province.getType())
                .districts(lstDistrict)
                .build();
        return p;
    }
}

package com.globits.da.service.impl;

import com.globits.da.domain.Commune;
import com.globits.da.domain.District;
import com.globits.da.domain.Province;
import com.globits.da.dto.CommuneDTO;
import com.globits.da.dto.DistrictDTO;
import com.globits.da.dto.ProvinceDTO;
import com.globits.da.repository.CommuneRepo;
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
    CommuneRepo communeRepo;
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
        Province addProvince = Province.builder()
                .code(province.getCode())
                .name(province.getName())
                .type(province.getType())
                .build();
        provinceRepo.save(addProvince);
        for(DistrictDTO d: province.getDistricts()){
            District addDistrict = District.builder()
                    .name(d.getName())
                    .code(d.getCode())
                    .type(d.getType())
                    .province(addProvince)
                    .build();
            districtRepo.save(addDistrict);
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
    public void updateProvinceAndDistrict(ProvinceDTO updateProvince){
        Optional<Province> provinceOpt = provinceRepo.findById(updateProvince.getId());
        Province province = provinceOpt.get();
        List<District> districts = new ArrayList<>();
        List<DistrictDTO> districtDTOS = updateProvince.getDistricts();
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
                    .id(updateProvince.getId())
                    .name(updateProvince.getName())
                    .code(updateProvince.getCode())
                    .type(updateProvince.getType())
                    .districts(districts)
                    .build();
            provinceRepo.save(province);

        }
    }

    @Transactional
    @Override
    public void addProvinceAndListDistrictAndListCommune(ProvinceDTO provinceDTO){
        Province p = Province.builder()
                .name(provinceDTO.getName())
                .code(provinceDTO.getCode())
                .type(provinceDTO.getType())
                .build();
        provinceRepo.save(p);
        for(DistrictDTO districtDTO : provinceDTO.getDistricts()){
            District d = District.builder()
                    .name(districtDTO.getName())
                    .code(districtDTO.getCode())
                    .type(districtDTO.getType())
                    .province(p)
                    .build();
            districtRepo.save(d);
            for(CommuneDTO communeDTO : districtDTO.getCommunes()){
                Commune c = Commune.builder()
                        .name(communeDTO.getName())
                        .code(communeDTO.getCode())
                        .type(communeDTO.getType())
                        .district(d)
                        .build();
                communeRepo.save(c);
            }
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

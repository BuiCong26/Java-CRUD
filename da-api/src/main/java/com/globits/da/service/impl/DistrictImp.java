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
import com.globits.da.service.DistrictService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DistrictImp implements DistrictService {
    DistrictRepo districtRepo;
    CommuneRepo communeRepo;
    CommuneImp communeImp;
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
    @Transactional
    @Override
    public void addDistrictAndListCommune(DistrictDTO district){
        District saveDistrict = District.builder()
                .code(district.getCode())
                .name(district.getName())
                .type(district.getType())
                .build();
        districtRepo.save(saveDistrict);
        for(CommuneDTO d: district.getCommunes()){
            Commune saveCommune = Commune.builder()
                    .name(d.getName())
                    .code(d.getCode())
                    .type(d.getType())
                    .district(saveDistrict)
                    .build();
            communeRepo.save(saveCommune);
        }

    }
    @Override
    public List<DistrictDTO> findAllDistrictWithCommune(){
        List<DistrictDTO> lstDistrictDTO = new ArrayList<>();
        List<District> lstDistrict = districtRepo.findAll();
        for(District d : lstDistrict){
            lstDistrictDTO.add(entityToDTO(d));
        }
        return lstDistrictDTO;
    }

    @Transactional
    @Override
    public void updateDistrictAndCommune(DistrictDTO updateDistrict){
        Optional<District> districtOpt = districtRepo.findById(updateDistrict.getId());
        District district = districtOpt.get();
        List<Commune> communes = new ArrayList<>();
        List<CommuneDTO> communeDTOS = updateDistrict.getCommunes();
        if(district != null){

            for(CommuneDTO d : communeDTOS){
                Optional<Commune> communeOpt = communeRepo.findById(d.getId());
                Commune commune = communeOpt.get();
                if(commune != null){
                    commune = Commune.builder()
                            .id(d.getId())
                            .name(d.getName())
                            .code(d.getCode())
                            .type(d.getType())
                            .district(district)
                            .build();
                    communes.add(commune);
                    communeRepo.save(commune);
                }

            }
            district = District.builder()
                    .id(updateDistrict.getId())
                    .name(updateDistrict.getName())
                    .code(updateDistrict.getCode())
                    .type(updateDistrict.getType())
                    .communes(communes)
                    .build();
            districtRepo.save(district);

        }
    }
    public DistrictDTO entityToDTO(District district){
        DistrictDTO d= null;
        List<Commune> communes = district.getCommunes();
        List<CommuneDTO> lstCommune = new ArrayList<>();
        if(district.getProvince() != null){
            d = DistrictDTO.builder()
                    .id(district.getId())
                    .name(district.getName())
                    .code(district.getCode())
                    .type(district.getType())
                    .province_id(district.getProvince().getId())
                    .build();
        } else if(communes != null){
            for(Commune c: communes){
                lstCommune.add(communeImp.entityToDTO(c));
            }
            d = DistrictDTO.builder()
                    .id(district.getId())
                    .name(district.getName())
                    .code(district.getCode())
                    .type(district.getType())
                    .communes(lstCommune)
                    .build();
        }

        return d;
    }
}

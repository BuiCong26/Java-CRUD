package com.globits.da.service;

import com.globits.da.domain.District;
import com.globits.da.domain.District;
import com.globits.da.dto.DistrictDTO;
import com.globits.da.dto.DistrictDTO;

import java.util.List;

public interface DistrictService {
    List<DistrictDTO> findAllDistrict();
    void addListDistrict(List<District> districts);
    void updateDistrictById(District district);
    void deleteDistrictById(int id);
    void deleteDistrictByProvinceId(int id);
//    void addListDistrictHaveDistrictId(List<District> districts);
    List<DistrictDTO> findAllDistrictByProvinceId(int id);
    void addDistrictAndListCommune(DistrictDTO district);
    List<DistrictDTO> findAllDistrictWithCommune();
    void updateDistrictAndCommune(DistrictDTO lstDistrict);
}

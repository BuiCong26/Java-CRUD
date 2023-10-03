package com.globits.da.service;

import com.globits.da.domain.Province;
import com.globits.da.dto.ProvinceDTO;

import java.util.List;

public interface ProvinceService {
    List<Province> findAllProvince() throws Exception;
    void addListProvince(List<ProvinceDTO> provinces);
    void updateProvinceById(ProvinceDTO province);
    void deleteProvinceById(int id);
    void addProvinceAndListDistrict(ProvinceDTO province);
    List<ProvinceDTO> findAllProvinceWithDistrict();
    void updateProvinceAndDistrict(ProvinceDTO lstProvince);
}

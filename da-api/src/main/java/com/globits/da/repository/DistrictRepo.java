package com.globits.da.repository;

import com.globits.da.domain.District;
import com.globits.da.domain.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistrictRepo extends JpaRepository<District, Integer> {
    void removeDistrictByProvinceId(int id);
    List<District> findDistrictByProvinceId(int id);
}

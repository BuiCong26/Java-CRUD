package com.globits.da.dto;

import com.globits.da.domain.Province;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DistrictDTO {

    private int id;
    private String name;
    private String code;
    private String type;
    private int province_id;
}

package com.globits.da.dto.search;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SearchEmployeeDTO {
    private int id;
    private String name;
    private String code;
    private String phone;
    private String email;
    private int age;
}

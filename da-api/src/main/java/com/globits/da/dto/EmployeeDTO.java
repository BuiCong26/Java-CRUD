package com.globits.da.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Component
public class EmployeeDTO {
    private Integer id;
    private String name;
    private String code;
    private String phone;
    private String email;
    private Integer age;
}

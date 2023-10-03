package com.globits.da.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Component
public class SimpleDTO {
    private String code;
    private String name;
//    private int age;
    private Integer age;
}

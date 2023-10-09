package com.globits.da.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Component
public class CommuneDTO {

    private int id;
    private String name;
    private String code;
    private String type;
    private int district_id;
}

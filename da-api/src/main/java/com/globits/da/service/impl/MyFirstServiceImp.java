package com.globits.da.service.impl;

import com.globits.da.dto.SimpleDTO;
import com.globits.da.service.MyFirstApiService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MyFirstServiceImp implements MyFirstApiService {
    @Override
    public String myFirstApi(String name) throws Exception{
        return name;
    }
    @Override
    public SimpleDTO myFirstDTO(SimpleDTO simpleDTO) throws Exception{
        SimpleDTO simpleDTO1 = SimpleDTO.builder()
                .age(simpleDTO.getAge())
                .code(simpleDTO.getCode())
                .name(simpleDTO.getName()).build();
        return simpleDTO1;
    }
}

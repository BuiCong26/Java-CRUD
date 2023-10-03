package com.globits.da.service;


import com.globits.da.dto.SimpleDTO;

public interface  MyFirstApiService {
    String myFirstApi(String name) throws Exception;
    SimpleDTO myFirstDTO(SimpleDTO simpleDTO) throws Exception;
}

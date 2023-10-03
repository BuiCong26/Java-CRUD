package com.globits.da.service;

import com.globits.da.domain.Commune;

import java.util.List;

public interface CommuneService {
    List<Commune> findAllCommune();
    void addListCommune(List<Commune> communes);
    void updateCommuneById(Commune commune);
    void deleteCommuneById(int id);
}

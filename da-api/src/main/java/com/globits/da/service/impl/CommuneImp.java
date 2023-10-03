package com.globits.da.service.impl;

import com.globits.da.domain.Commune;
import com.globits.da.repository.CommuneRepo;
import com.globits.da.service.CommuneService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class CommuneImp implements CommuneService {
    CommuneRepo communeRepo;
    @Transactional
    @Override
    public List<Commune> findAllCommune(){
        return communeRepo.findAll();
    }
    @Transactional
    @Override
    public void addListCommune(List<Commune> communes){
        try{
            communeRepo.saveAll(communes);
            System.out.println("Add successed!!!");
        }catch (Exception e){
            e.getMessage();
            System.out.println("Add failed!!!");
        }
    }
    @Transactional
    @Override
    public void updateCommuneById(Commune commune){
        try{
            Commune commune1 = communeRepo.getOne(commune.getId());
            if(commune1 != null){
                commune1 = Commune.builder()
                        .id(commune.getId())
                        .name(commune.getName())
                        .code(commune.getCode())
                        .type(commune.getType())
                        .build();
                communeRepo.save(commune1);
                System.out.println("Update commune id = " + commune.getId()+" success!!!");
            }
        }catch (Exception e){
            e.getMessage();
        }
    }
    @Transactional
    @Override
    public void deleteCommuneById(int id){
        try{
            if(communeRepo.getOne(id) != null){
                communeRepo.deleteById(id);
                System.out.println("Delete commune id = " + id +" success!!!");
            }
        }catch (Exception e){
            e.getMessage();
        }
    }
}

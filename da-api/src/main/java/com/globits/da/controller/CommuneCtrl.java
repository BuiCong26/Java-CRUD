package com.globits.da.controller;

import com.globits.da.domain.Commune;
import com.globits.da.service.CommuneService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RequestMapping( "/commune")
@RestController
public class CommuneCtrl {
    CommuneService communeService;
    @PostMapping("/addCommune")
    public void addNewCommune(@RequestBody List<Commune> commune){
        communeService.addListCommune(commune);
    }
    @PostMapping("/updateCommune")
    public void updateCommune(@RequestBody Commune commune){
        communeService.updateCommuneById(commune);
    }
    @GetMapping("/deleteCommune")
    public void deleteCommune(@RequestParam("id")int id){
        communeService.deleteCommuneById(id);
    }
    @GetMapping("/findAllCommune")
    public List<Commune> findAllCommune(){
        return communeService.findAllCommune();
    }
}

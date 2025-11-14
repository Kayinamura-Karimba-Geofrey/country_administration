package com.example.Country.Administrative.System.service;

import com.example.Country.Administrative.System.entity.Village;
import com.example.Country.Administrative.System.repository.VillageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VillageService {
    
    @Autowired
    private VillageRepository villageRepository;
    
    public List<Village> getAllVillages() {
        return villageRepository.findAll();
    }
    
    public Optional<Village> getVillageByCode(String code) {
        return villageRepository.findByCode(code);
    }
    
    public List<Village> getVillagesByCell(String cellCode) {
        return villageRepository.findByCellCode(cellCode);
    }
    
    public List<Village> getVillagesBySector(String sectorCode) {
        return villageRepository.findBySectorCode(sectorCode);
    }
    
    public List<Village> getVillagesByDistrict(String districtCode) {
        return villageRepository.findByDistrictCode(districtCode);
    }
    
    public List<Village> getVillagesByProvince(String provinceCode) {
        return villageRepository.findByProvinceCode(provinceCode);
    }
    
    public List<Village> searchVillagesByName(String name) {
        return villageRepository.findByNameContainingIgnoreCase(name);
    }
    
    public List<Village> searchVillagesByCellAndName(String cellCode, String name) {
        return villageRepository.findByCellCodeAndNameContainingIgnoreCase(cellCode, name);
    }
    
    public Village saveVillage(Village village) {
        return villageRepository.save(village);
    }
    
    public List<Village> saveAllVillages(List<Village> villages) {
        return villageRepository.saveAll(villages);
    }
    
    public void deleteVillage(String code) {
        villageRepository.deleteById(code);
    }
    
    public boolean existsByCode(String code) {
        return villageRepository.existsByCode(code);
    }
    
    public long count() {
        return villageRepository.count();
    }
}



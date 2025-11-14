package com.example.Country.Administrative.System.service;

import com.example.Country.Administrative.System.entity.Sector;
import com.example.Country.Administrative.System.repository.SectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SectorService {
    
    @Autowired
    private SectorRepository sectorRepository;
    
    public List<Sector> getAllSectors() {
        return sectorRepository.findAll();
    }
    
    public Optional<Sector> getSectorByCode(String code) {
        return sectorRepository.findByCode(code);
    }
    
    public List<Sector> getSectorsByDistrict(String districtCode) {
        return sectorRepository.findByDistrictCode(districtCode);
    }
    
    public List<Sector> getSectorsByProvince(String provinceCode) {
        return sectorRepository.findByProvinceCode(provinceCode);
    }
    
    public List<Sector> searchSectorsByName(String name) {
        return sectorRepository.findByNameContainingIgnoreCase(name);
    }
    
    public List<Sector> searchSectorsByDistrictAndName(String districtCode, String name) {
        return sectorRepository.findByDistrictCodeAndNameContainingIgnoreCase(districtCode, name);
    }
    
    public Sector saveSector(Sector sector) {
        return sectorRepository.save(sector);
    }
    
    public List<Sector> saveAllSectors(List<Sector> sectors) {
        return sectorRepository.saveAll(sectors);
    }
    
    public void deleteSector(String code) {
        sectorRepository.deleteById(code);
    }
    
    public boolean existsByCode(String code) {
        return sectorRepository.existsByCode(code);
    }
    
    public long count() {
        return sectorRepository.count();
    }
}



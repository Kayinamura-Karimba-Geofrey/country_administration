package com.example.Country.Administrative.System.service;

import com.example.Country.Administrative.System.entity.District;
import com.example.Country.Administrative.System.repository.DistrictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DistrictService {
    
    @Autowired
    private DistrictRepository districtRepository;
    
    public List<District> getAllDistricts() {
        return districtRepository.findAll();
    }
    
    public Optional<District> getDistrictByCode(String code) {
        return districtRepository.findByCode(code);
    }
    
    public List<District> getDistrictsByProvince(String provinceCode) {
        return districtRepository.findByProvinceCode(provinceCode);
    }
    
    public List<District> searchDistrictsByName(String name) {
        return districtRepository.findByNameContainingIgnoreCase(name);
    }
    
    public List<District> searchDistrictsByProvinceAndName(String provinceCode, String name) {
        return districtRepository.findByProvinceCodeAndNameContainingIgnoreCase(provinceCode, name);
    }
    
    public District saveDistrict(District district) {
        return districtRepository.save(district);
    }
    
    public List<District> saveAllDistricts(List<District> districts) {
        return districtRepository.saveAll(districts);
    }
    
    public void deleteDistrict(String code) {
        districtRepository.deleteById(code);
    }
    
    public boolean existsByCode(String code) {
        return districtRepository.existsByCode(code);
    }
    
    public long count() {
        return districtRepository.count();
    }
}



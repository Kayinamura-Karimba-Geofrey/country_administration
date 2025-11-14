package com.example.Country.Administrative.System.service;

import com.example.Country.Administrative.System.entity.Province;
import com.example.Country.Administrative.System.repository.ProvinceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProvinceService {
    
    @Autowired
    private ProvinceRepository provinceRepository;
    
    public List<Province> getAllProvinces() {
        return provinceRepository.findAll();
    }
    
    public Optional<Province> getProvinceByCode(String code) {
        return provinceRepository.findByCode(code);
    }
    
    public List<Province> searchProvincesByName(String name) {
        return provinceRepository.findByNameContainingIgnoreCase(name);
    }
    
    public Province saveProvince(Province province) {
        return provinceRepository.save(province);
    }
    
    public List<Province> saveAllProvinces(List<Province> provinces) {
        return provinceRepository.saveAll(provinces);
    }
    
    public void deleteProvince(String code) {
        provinceRepository.deleteById(code);
    }
    
    public boolean existsByCode(String code) {
        return provinceRepository.existsByCode(code);
    }
    
    public long count() {
        return provinceRepository.count();
    }
}



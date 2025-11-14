package com.example.Country.Administrative.System.service;

import com.example.Country.Administrative.System.entity.Cell;
import com.example.Country.Administrative.System.repository.CellRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CellService {
    
    @Autowired
    private CellRepository cellRepository;
    
    public List<Cell> getAllCells() {
        return cellRepository.findAll();
    }
    
    public Optional<Cell> getCellByCode(String code) {
        return cellRepository.findByCode(code);
    }
    
    public List<Cell> getCellsBySector(String sectorCode) {
        return cellRepository.findBySectorCode(sectorCode);
    }
    
    public List<Cell> getCellsByDistrict(String districtCode) {
        return cellRepository.findByDistrictCode(districtCode);
    }
    
    public List<Cell> getCellsByProvince(String provinceCode) {
        return cellRepository.findByProvinceCode(provinceCode);
    }
    
    public List<Cell> searchCellsByName(String name) {
        return cellRepository.findByNameContainingIgnoreCase(name);
    }
    
    public List<Cell> searchCellsBySectorAndName(String sectorCode, String name) {
        return cellRepository.findBySectorCodeAndNameContainingIgnoreCase(sectorCode, name);
    }
    
    public Cell saveCell(Cell cell) {
        return cellRepository.save(cell);
    }
    
    public List<Cell> saveAllCells(List<Cell> cells) {
        return cellRepository.saveAll(cells);
    }
    
    public void deleteCell(String code) {
        cellRepository.deleteById(code);
    }
    
    public boolean existsByCode(String code) {
        return cellRepository.existsByCode(code);
    }
    
    public long count() {
        return cellRepository.count();
    }
}



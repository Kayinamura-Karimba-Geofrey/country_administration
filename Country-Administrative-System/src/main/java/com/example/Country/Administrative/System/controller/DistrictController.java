package com.example.Country.Administrative.System.controller;

import com.example.Country.Administrative.System.entity.District;
import com.example.Country.Administrative.System.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/districts")
@CrossOrigin(origins = "*")
public class DistrictController {
    
    @Autowired
    private DistrictService districtService;
    
    @GetMapping
    public ResponseEntity<List<District>> getAllDistricts() {
        List<District> districts = districtService.getAllDistricts();
        return ResponseEntity.ok(districts);
    }
    
    @GetMapping("/{code}")
    public ResponseEntity<District> getDistrictByCode(@PathVariable String code) {
        Optional<District> district = districtService.getDistrictByCode(code);
        return district.map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/province/{provinceCode}")
    public ResponseEntity<List<District>> getDistrictsByProvince(@PathVariable String provinceCode) {
        List<District> districts = districtService.getDistrictsByProvince(provinceCode);
        return ResponseEntity.ok(districts);
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<District>> searchDistrictsByName(@RequestParam String name) {
        List<District> districts = districtService.searchDistrictsByName(name);
        return ResponseEntity.ok(districts);
    }
    
    @GetMapping("/search/province")
    public ResponseEntity<List<District>> searchDistrictsByProvinceAndName(
            @RequestParam String provinceCode, 
            @RequestParam String name) {
        List<District> districts = districtService.searchDistrictsByProvinceAndName(provinceCode, name);
        return ResponseEntity.ok(districts);
    }
    
    @PostMapping
    public ResponseEntity<District> createDistrict(@RequestBody District district) {
        District savedDistrict = districtService.saveDistrict(district);
        return ResponseEntity.ok(savedDistrict);
    }
    
    @PutMapping("/{code}")
    public ResponseEntity<District> updateDistrict(@PathVariable String code, @RequestBody District district) {
        if (!districtService.existsByCode(code)) {
            return ResponseEntity.notFound().build();
        }
        district.setCode(code);
        District updatedDistrict = districtService.saveDistrict(district);
        return ResponseEntity.ok(updatedDistrict);
    }
    
    @DeleteMapping("/{code}")
    public ResponseEntity<Void> deleteDistrict(@PathVariable String code) {
        if (!districtService.existsByCode(code)) {
            return ResponseEntity.notFound().build();
        }
        districtService.deleteDistrict(code);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/count")
    public ResponseEntity<Long> getDistrictCount() {
        long count = districtService.count();
        return ResponseEntity.ok(count);
    }
}

package com.example.Country.Administrative.System.controller;

import com.example.Country.Administrative.System.entity.Province;
import com.example.Country.Administrative.System.service.ProvinceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/provinces")
@CrossOrigin(origins = "*")
public class ProvinceController {
    
    @Autowired
    private ProvinceService provinceService;
    
    @GetMapping
    public ResponseEntity<List<Province>> getAllProvinces() {
        List<Province> provinces = provinceService.getAllProvinces();
        return ResponseEntity.ok(provinces);
    }
    
    @GetMapping("/{code}")
    public ResponseEntity<Province> getProvinceByCode(@PathVariable String code) {
        Optional<Province> province = provinceService.getProvinceByCode(code);
        return province.map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<Province>> searchProvincesByName(@RequestParam String name) {
        List<Province> provinces = provinceService.searchProvincesByName(name);
        return ResponseEntity.ok(provinces);
    }
    
    @PostMapping
    public ResponseEntity<Province> createProvince(@Valid @RequestBody Province province) {
        Province savedProvince = provinceService.saveProvince(province);
        return ResponseEntity.ok(savedProvince);
    }
    
    @PutMapping("/{code}")
    public ResponseEntity<Province> updateProvince(@PathVariable String code, @Valid @RequestBody Province province) {
        if (!provinceService.existsByCode(code)) {
            return ResponseEntity.notFound().build();
        }
        province.setCode(code);
        Province updatedProvince = provinceService.saveProvince(province);
        return ResponseEntity.ok(updatedProvince);
    }
    
    @DeleteMapping("/{code}")
    public ResponseEntity<Void> deleteProvince(@PathVariable String code) {
        if (!provinceService.existsByCode(code)) {
            return ResponseEntity.notFound().build();
        }
        provinceService.deleteProvince(code);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/count")
    public ResponseEntity<Long> getProvinceCount() {
        long count = provinceService.count();
        return ResponseEntity.ok(count);
    }
}

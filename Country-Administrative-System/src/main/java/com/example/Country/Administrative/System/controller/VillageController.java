package com.example.Country.Administrative.System.controller;

import com.example.Country.Administrative.System.entity.Village;
import com.example.Country.Administrative.System.service.VillageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/villages")
@CrossOrigin(origins = "*")
public class VillageController {
    
    @Autowired
    private VillageService villageService;
    
    @GetMapping
    public ResponseEntity<List<Village>> getAllVillages() {
        List<Village> villages = villageService.getAllVillages();
        return ResponseEntity.ok(villages);
    }
    
    @GetMapping("/{code}")
    public ResponseEntity<Village> getVillageByCode(@PathVariable String code) {
        Optional<Village> village = villageService.getVillageByCode(code);
        return village.map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/cell/{cellCode}")
    public ResponseEntity<List<Village>> getVillagesByCell(@PathVariable String cellCode) {
        List<Village> villages = villageService.getVillagesByCell(cellCode);
        return ResponseEntity.ok(villages);
    }
    
    @GetMapping("/sector/{sectorCode}")
    public ResponseEntity<List<Village>> getVillagesBySector(@PathVariable String sectorCode) {
        List<Village> villages = villageService.getVillagesBySector(sectorCode);
        return ResponseEntity.ok(villages);
    }
    
    @GetMapping("/district/{districtCode}")
    public ResponseEntity<List<Village>> getVillagesByDistrict(@PathVariable String districtCode) {
        List<Village> villages = villageService.getVillagesByDistrict(districtCode);
        return ResponseEntity.ok(villages);
    }
    
    @GetMapping("/province/{provinceCode}")
    public ResponseEntity<List<Village>> getVillagesByProvince(@PathVariable String provinceCode) {
        List<Village> villages = villageService.getVillagesByProvince(provinceCode);
        return ResponseEntity.ok(villages);
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<Village>> searchVillagesByName(@RequestParam String name) {
        List<Village> villages = villageService.searchVillagesByName(name);
        return ResponseEntity.ok(villages);
    }
    
    @GetMapping("/search/cell")
    public ResponseEntity<List<Village>> searchVillagesByCellAndName(
            @RequestParam String cellCode, 
            @RequestParam String name) {
        List<Village> villages = villageService.searchVillagesByCellAndName(cellCode, name);
        return ResponseEntity.ok(villages);
    }
    
    @PostMapping
    public ResponseEntity<Village> createVillage(@RequestBody Village village) {
        Village savedVillage = villageService.saveVillage(village);
        return ResponseEntity.ok(savedVillage);
    }
    
    @PutMapping("/{code}")
    public ResponseEntity<Village> updateVillage(@PathVariable String code, @RequestBody Village village) {
        if (!villageService.existsByCode(code)) {
            return ResponseEntity.notFound().build();
        }
        village.setCode(code);
        Village updatedVillage = villageService.saveVillage(village);
        return ResponseEntity.ok(updatedVillage);
    }
    
    @DeleteMapping("/{code}")
    public ResponseEntity<Void> deleteVillage(@PathVariable String code) {
        if (!villageService.existsByCode(code)) {
            return ResponseEntity.notFound().build();
        }
        villageService.deleteVillage(code);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/count")
    public ResponseEntity<Long> getVillageCount() {
        long count = villageService.count();
        return ResponseEntity.ok(count);
    }
}

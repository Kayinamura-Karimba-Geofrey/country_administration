package com.example.Country.Administrative.System.controller;

import com.example.Country.Administrative.System.entity.Sector;
import com.example.Country.Administrative.System.service.SectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/sectors")
@CrossOrigin(origins = "*")
public class SectorController {
    
    @Autowired
    private SectorService sectorService;
    
    @GetMapping
    public ResponseEntity<List<Sector>> getAllSectors() {
        List<Sector> sectors = sectorService.getAllSectors();
        return ResponseEntity.ok(sectors);
    }
    
    @GetMapping("/{code}")
    public ResponseEntity<Sector> getSectorByCode(@PathVariable String code) {
        Optional<Sector> sector = sectorService.getSectorByCode(code);
        return sector.map(ResponseEntity::ok)
                     .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/district/{districtCode}")
    public ResponseEntity<List<Sector>> getSectorsByDistrict(@PathVariable String districtCode) {
        List<Sector> sectors = sectorService.getSectorsByDistrict(districtCode);
        return ResponseEntity.ok(sectors);
    }
    
    @GetMapping("/province/{provinceCode}")
    public ResponseEntity<List<Sector>> getSectorsByProvince(@PathVariable String provinceCode) {
        List<Sector> sectors = sectorService.getSectorsByProvince(provinceCode);
        return ResponseEntity.ok(sectors);
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<Sector>> searchSectorsByName(@RequestParam String name) {
        List<Sector> sectors = sectorService.searchSectorsByName(name);
        return ResponseEntity.ok(sectors);
    }
    
    @GetMapping("/search/district")
    public ResponseEntity<List<Sector>> searchSectorsByDistrictAndName(
            @RequestParam String districtCode, 
            @RequestParam String name) {
        List<Sector> sectors = sectorService.searchSectorsByDistrictAndName(districtCode, name);
        return ResponseEntity.ok(sectors);
    }
    
    @PostMapping
    public ResponseEntity<Sector> createSector(@RequestBody Sector sector) {
        Sector savedSector = sectorService.saveSector(sector);
        return ResponseEntity.ok(savedSector);
    }
    
    @PutMapping("/{code}")
    public ResponseEntity<Sector> updateSector(@PathVariable String code, @RequestBody Sector sector) {
        if (!sectorService.existsByCode(code)) {
            return ResponseEntity.notFound().build();
        }
        sector.setCode(code);
        Sector updatedSector = sectorService.saveSector(sector);
        return ResponseEntity.ok(updatedSector);
    }
    
    @DeleteMapping("/{code}")
    public ResponseEntity<Void> deleteSector(@PathVariable String code) {
        if (!sectorService.existsByCode(code)) {
            return ResponseEntity.notFound().build();
        }
        sectorService.deleteSector(code);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/count")
    public ResponseEntity<Long> getSectorCount() {
        long count = sectorService.count();
        return ResponseEntity.ok(count);
    }
}

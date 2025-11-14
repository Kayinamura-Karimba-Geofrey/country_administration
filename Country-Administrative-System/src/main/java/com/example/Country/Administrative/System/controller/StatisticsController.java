package com.example.Country.Administrative.System.controller;

import com.example.Country.Administrative.System.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/statistics")
@CrossOrigin(origins = "*")
public class StatisticsController {
    
    @Autowired
    private ProvinceService provinceService;
    
    @Autowired
    private DistrictService districtService;
    
    @Autowired
    private SectorService sectorService;
    
    @Autowired
    private CellService cellService;
    
    @Autowired
    private VillageService villageService;
    
    @GetMapping
    public ResponseEntity<Map<String, Object>> getStatistics() {
        Map<String, Object> statistics = new HashMap<>();
        
        statistics.put("provinces", provinceService.count());
        statistics.put("districts", districtService.count());
        statistics.put("sectors", sectorService.count());
        statistics.put("cells", cellService.count());
        statistics.put("villages", villageService.count());
        
        return ResponseEntity.ok(statistics);
    }
    
    @GetMapping("/provinces")
    public ResponseEntity<Long> getProvinceCount() {
        return ResponseEntity.ok(provinceService.count());
    }
    
    @GetMapping("/districts")
    public ResponseEntity<Long> getDistrictCount() {
        return ResponseEntity.ok(districtService.count());
    }
    
    @GetMapping("/sectors")
    public ResponseEntity<Long> getSectorCount() {
        return ResponseEntity.ok(sectorService.count());
    }
    
    @GetMapping("/cells")
    public ResponseEntity<Long> getCellCount() {
        return ResponseEntity.ok(cellService.count());
    }
    
    @GetMapping("/villages")
    public ResponseEntity<Long> getVillageCount() {
        return ResponseEntity.ok(villageService.count());
    }
}

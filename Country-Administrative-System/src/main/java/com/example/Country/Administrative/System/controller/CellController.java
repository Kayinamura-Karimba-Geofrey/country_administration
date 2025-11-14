package com.example.Country.Administrative.System.controller;

import com.example.Country.Administrative.System.entity.Cell;
import com.example.Country.Administrative.System.service.CellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cells")
@CrossOrigin(origins = "*")
public class CellController {
    
    @Autowired
    private CellService cellService;
    
    @GetMapping
    public ResponseEntity<List<Cell>> getAllCells() {
        List<Cell> cells = cellService.getAllCells();
        return ResponseEntity.ok(cells);
    }
    
    @GetMapping("/{code}")
    public ResponseEntity<Cell> getCellByCode(@PathVariable String code) {
        Optional<Cell> cell = cellService.getCellByCode(code);
        return cell.map(ResponseEntity::ok)
                   .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/sector/{sectorCode}")
    public ResponseEntity<List<Cell>> getCellsBySector(@PathVariable String sectorCode) {
        List<Cell> cells = cellService.getCellsBySector(sectorCode);
        return ResponseEntity.ok(cells);
    }
    
    @GetMapping("/district/{districtCode}")
    public ResponseEntity<List<Cell>> getCellsByDistrict(@PathVariable String districtCode) {
        List<Cell> cells = cellService.getCellsByDistrict(districtCode);
        return ResponseEntity.ok(cells);
    }
    
    @GetMapping("/province/{provinceCode}")
    public ResponseEntity<List<Cell>> getCellsByProvince(@PathVariable String provinceCode) {
        List<Cell> cells = cellService.getCellsByProvince(provinceCode);
        return ResponseEntity.ok(cells);
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<Cell>> searchCellsByName(@RequestParam String name) {
        List<Cell> cells = cellService.searchCellsByName(name);
        return ResponseEntity.ok(cells);
    }
    
    @GetMapping("/search/sector")
    public ResponseEntity<List<Cell>> searchCellsBySectorAndName(
            @RequestParam String sectorCode, 
            @RequestParam String name) {
        List<Cell> cells = cellService.searchCellsBySectorAndName(sectorCode, name);
        return ResponseEntity.ok(cells);
    }
    
    @PostMapping
    public ResponseEntity<Cell> createCell(@RequestBody Cell cell) {
        Cell savedCell = cellService.saveCell(cell);
        return ResponseEntity.ok(savedCell);
    }
    
    @PutMapping("/{code}")
    public ResponseEntity<Cell> updateCell(@PathVariable String code, @RequestBody Cell cell) {
        if (!cellService.existsByCode(code)) {
            return ResponseEntity.notFound().build();
        }
        cell.setCode(code);
        Cell updatedCell = cellService.saveCell(cell);
        return ResponseEntity.ok(updatedCell);
    }
    
    @DeleteMapping("/{code}")
    public ResponseEntity<Void> deleteCell(@PathVariable String code) {
        if (!cellService.existsByCode(code)) {
            return ResponseEntity.notFound().build();
        }
        cellService.deleteCell(code);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/count")
    public ResponseEntity<Long> getCellCount() {
        long count = cellService.count();
        return ResponseEntity.ok(count);
    }
}

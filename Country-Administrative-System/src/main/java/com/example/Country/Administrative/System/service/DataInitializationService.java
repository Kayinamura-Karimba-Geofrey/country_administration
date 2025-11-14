package com.example.Country.Administrative.System.service;

import com.example.Country.Administrative.System.entity.*;
import com.example.Country.Administrative.System.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class DataInitializationService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ProvinceRepository provinceRepository;

    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private SectorRepository sectorRepository;

    @Autowired
    private CellRepository cellRepository;

    @Autowired
    private VillageRepository villageRepository;

    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void initializeData() {
        try {
            System.out.println("Starting data initialization...");

            // Check all tables and only load data for empty tables
            boolean needsInitialization = provinceRepository.count() == 0 
                    || districtRepository.count() == 0 
                    || sectorRepository.count() == 0 
                    || cellRepository.count() == 0 
                    || villageRepository.count() == 0;

            if (!needsInitialization) {
                System.out.println("All tables already have data, skipping initialization.");
                printStatistics();
                return;
            }

            // Load provinces if empty
            if (provinceRepository.count() == 0) {
                loadProvinces();
            } else {
                System.out.println("Provinces already exist, skipping.");
            }

            // Load districts if empty
            if (districtRepository.count() == 0) {
                loadDistricts();
            } else {
                System.out.println("Districts already exist, skipping.");
            }

            // Load sectors if empty
            if (sectorRepository.count() == 0) {
                loadSectors();
            } else {
                System.out.println("Sectors already exist, skipping.");
            }

            // Load cells if empty
            if (cellRepository.count() == 0) {
                loadCells();
            } else {
                System.out.println("Cells already exist, skipping.");
            }

            // Load villages if empty
            if (villageRepository.count() == 0) {
                loadVillages();
            } else {
                System.out.println("Villages already exist, skipping.");
            }

            System.out.println("Data initialization completed successfully!");
            printStatistics();

        } catch (Exception e) {
            System.err.println("Error during data initialization: " + e.getMessage());
            System.err.println("Application will continue to run, but data may not be loaded.");
            e.printStackTrace();
        }
    }

    private void loadProvinces() throws IOException {
        System.out.println("Loading provinces...");
        List<Province> provinces = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new ClassPathResource("province.txt").getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2) {
                    Province province = new Province(parts[0].trim(), parts[1].trim());
                    provinces.add(province);
                }
            }
        }

        batchPersist(provinces);
        System.out.println("Loaded " + provinces.size() + " provinces");
    }

    private void loadDistricts() throws IOException {
        System.out.println("Loading districts...");
        List<District> districts = new ArrayList<>();
        int skipped = 0;

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new ClassPathResource("district.txt").getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 3) {
                    String code = parts[0].trim();
                    String provinceCode = parts[1].trim();
                    String name = parts[2].trim();

                    // Fetch parent province entity
                    Province parentProvince = provinceRepository.findById(provinceCode).orElse(null);
                    if (parentProvince == null) {
                        System.err.println("Warning: Skipping district '" + code + "' - Province not found: " + provinceCode);
                        skipped++;
                        continue;
                    }

                    District district = new District(code, provinceCode, name);
                    district.setProvince(parentProvince);
                    districts.add(district);
                }
            }
        }

        batchPersist(districts);
        System.out.println("Loaded " + districts.size() + " districts" + (skipped > 0 ? " (skipped " + skipped + " invalid records)" : ""));
    }

    private void loadSectors() throws IOException {
        System.out.println("Loading sectors...");
        List<Sector> sectors = new ArrayList<>();
        int skipped = 0;

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new ClassPathResource("sector.txt").getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 3) {
                    String code = parts[0].trim();
                    String districtCode = parts[1].trim();
                    String name = parts[2].trim();

                    // Fetch parent district entity
                    District parentDistrict = districtRepository.findById(districtCode).orElse(null);
                    if (parentDistrict == null) {
                        System.err.println("Warning: Skipping sector '" + code + "' - District not found: " + districtCode);
                        skipped++;
                        continue;
                    }

                    Sector sector = new Sector(code, districtCode, name);
                    sector.setDistrict(parentDistrict);
                    sectors.add(sector);
                }
            }
        }

        batchPersist(sectors);
        System.out.println("Loaded " + sectors.size() + " sectors" + (skipped > 0 ? " (skipped " + skipped + " invalid records)" : ""));
    }

    private void loadCells() throws IOException {
        System.out.println("Loading cells...");
        List<Cell> cells = new ArrayList<>();
        int skipped = 0;

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new ClassPathResource("cell.txt").getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 3) {
                    String code = parts[0].trim();
                    String sectorCode = parts[1].trim();
                    String name = parts[2].trim();

                    // Fetch parent sector entity
                    Sector parentSector = sectorRepository.findById(sectorCode).orElse(null);
                    if (parentSector == null) {
                        System.err.println("Warning: Skipping cell '" + code + "' - Sector not found: " + sectorCode);
                        skipped++;
                        continue;
                    }

                    Cell cell = new Cell(code, sectorCode, name);
                    cell.setSector(parentSector);
                    cells.add(cell);
                }
            }
        }

        batchPersist(cells);
        System.out.println("Loaded " + cells.size() + " cells" + (skipped > 0 ? " (skipped " + skipped + " invalid records)" : ""));
    }

    private void loadVillages() throws IOException {
        System.out.println("Loading villages...");
        List<Village> villages = new ArrayList<>();
        int skipped = 0;

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new ClassPathResource("village.txt").getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null && villages.size() < 10000) {
                String[] parts = line.split(",");
                if (parts.length >= 3) {
                    String code = parts[0].trim();
                    String cellCode = parts[1].trim();
                    String name = parts[2].trim();

                    // Fetch parent cell entity
                    Cell parentCell = cellRepository.findById(cellCode).orElse(null);
                    if (parentCell == null) {
                        skipped++;
                        if (skipped <= 10) { // Only log first 10 warnings to avoid spam
                            System.err.println("Warning: Skipping village '" + code + "' - Cell not found: " + cellCode);
                        } else if (skipped == 11) {
                            System.err.println("Warning: (Additional skipped villages will not be logged...)");
                        }
                        continue;
                    }

                    Village village = new Village(code, cellCode, name);
                    village.setCell(parentCell);
                    villages.add(village);
                }
            }
        }

        batchPersist(villages);
        System.out.println("Loaded " + villages.size() + " villages (limited to first 10,000 for performance)" + 
                          (skipped > 0 ? " (skipped " + skipped + " invalid records)" : ""));
    }

    private <T> void batchPersist(List<T> entities) {
        int batchSize = 50;
        for (int i = 0; i < entities.size(); i++) {
            entityManager.persist(entities.get(i));
            if (i > 0 && i % batchSize == 0) {
                entityManager.flush();
                entityManager.clear();
            }
        }
        entityManager.flush();
        entityManager.clear();
    }

    private void printStatistics() {
        System.out.println("\n=== Database Statistics ===");
        System.out.println("Provinces: " + provinceRepository.count());
        System.out.println("Districts: " + districtRepository.count());
        System.out.println("Sectors: " + sectorRepository.count());
        System.out.println("Cells: " + cellRepository.count());
        System.out.println("Villages: " + villageRepository.count());
        System.out.println("========================\n");
    }
}


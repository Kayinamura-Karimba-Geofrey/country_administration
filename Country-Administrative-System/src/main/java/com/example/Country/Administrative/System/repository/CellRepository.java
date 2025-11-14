package com.example.Country.Administrative.System.repository;

import com.example.Country.Administrative.System.entity.Cell;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CellRepository extends JpaRepository<Cell, String> {
    
    Optional<Cell> findByCode(String code);
    
    List<Cell> findBySectorCode(String sectorCode);
    
    List<Cell> findByNameContainingIgnoreCase(String name);
    
    @Query("SELECT c FROM Cell c WHERE c.sectorCode = :sectorCode AND LOWER(c.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Cell> findBySectorCodeAndNameContainingIgnoreCase(@Param("sectorCode") String sectorCode, 
                                                           @Param("name") String name);
    
    @Query("SELECT c FROM Cell c JOIN c.sector s WHERE s.districtCode = :districtCode")
    List<Cell> findByDistrictCode(@Param("districtCode") String districtCode);
    
    @Query("SELECT c FROM Cell c JOIN c.sector s JOIN s.district d WHERE d.provinceCode = :provinceCode")
    List<Cell> findByProvinceCode(@Param("provinceCode") String provinceCode);
    
    boolean existsByCode(String code);
}



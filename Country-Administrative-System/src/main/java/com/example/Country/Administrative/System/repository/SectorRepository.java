package com.example.Country.Administrative.System.repository;

import com.example.Country.Administrative.System.entity.Sector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SectorRepository extends JpaRepository<Sector, String> {
    
    Optional<Sector> findByCode(String code);
    
    List<Sector> findByDistrictCode(String districtCode);
    
    List<Sector> findByNameContainingIgnoreCase(String name);
    
    @Query("SELECT s FROM Sector s WHERE s.districtCode = :districtCode AND LOWER(s.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Sector> findByDistrictCodeAndNameContainingIgnoreCase(@Param("districtCode") String districtCode, 
                                                               @Param("name") String name);
    
    @Query("SELECT s FROM Sector s JOIN s.district d WHERE d.provinceCode = :provinceCode")
    List<Sector> findByProvinceCode(@Param("provinceCode") String provinceCode);
    
    boolean existsByCode(String code);
}



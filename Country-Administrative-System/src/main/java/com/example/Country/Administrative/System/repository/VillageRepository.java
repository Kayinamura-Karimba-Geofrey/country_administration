package com.example.Country.Administrative.System.repository;

import com.example.Country.Administrative.System.entity.Village;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VillageRepository extends JpaRepository<Village, String> {
    
    Optional<Village> findByCode(String code);
    
    List<Village> findByCellCode(String cellCode);
    
    List<Village> findByNameContainingIgnoreCase(String name);
    
    @Query("SELECT v FROM Village v WHERE v.cellCode = :cellCode AND LOWER(v.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Village> findByCellCodeAndNameContainingIgnoreCase(@Param("cellCode") String cellCode, 
                                                            @Param("name") String name);
    
    @Query("SELECT v FROM Village v JOIN v.cell c WHERE c.sectorCode = :sectorCode")
    List<Village> findBySectorCode(@Param("sectorCode") String sectorCode);
    
    @Query("SELECT v FROM Village v JOIN v.cell c JOIN c.sector s WHERE s.districtCode = :districtCode")
    List<Village> findByDistrictCode(@Param("districtCode") String districtCode);
    
    @Query("SELECT v FROM Village v JOIN v.cell c JOIN c.sector s JOIN s.district d WHERE d.provinceCode = :provinceCode")
    List<Village> findByProvinceCode(@Param("provinceCode") String provinceCode);
    
    boolean existsByCode(String code);
}



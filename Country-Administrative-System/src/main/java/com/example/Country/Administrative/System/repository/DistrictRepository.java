package com.example.Country.Administrative.System.repository;

import com.example.Country.Administrative.System.entity.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DistrictRepository extends JpaRepository<District, String> {
    
    Optional<District> findByCode(String code);
    
    List<District> findByProvinceCode(String provinceCode);
    
    List<District> findByNameContainingIgnoreCase(String name);
    
    @Query("SELECT d FROM District d WHERE d.provinceCode = :provinceCode AND d.name LIKE %:name%")
    List<District> findByProvinceCodeAndNameContainingIgnoreCase(@Param("provinceCode") String provinceCode, 
                                                                 @Param("name") String name);
    
    boolean existsByCode(String code);
}



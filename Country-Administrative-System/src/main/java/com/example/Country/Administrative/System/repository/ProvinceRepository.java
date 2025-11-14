package com.example.Country.Administrative.System.repository;

import com.example.Country.Administrative.System.entity.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProvinceRepository extends JpaRepository<Province, String> {
    
    Optional<Province> findByCode(String code);
    
    List<Province> findByNameContainingIgnoreCase(String name);
    
    boolean existsByCode(String code);
}

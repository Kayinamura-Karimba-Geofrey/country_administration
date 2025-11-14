package com.example.Country.Administrative.System.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "cells")
public class Cell {
    
    @Id
    @Column(name = "code", length = 8)
    @NotBlank
    @Size(min = 8, max = 8)
    private String code;
    
    @Column(name = "sector_code", length = 6, nullable = false)
    @NotBlank
    @Size(min = 6, max = 6)
    private String sectorCode;
    
    @Column(name = "name", nullable = false)
    @NotBlank
    private String name;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sector_code", insertable = false, updatable = false)
    private Sector sector;
    
    // Constructors
    public Cell() {}
    
    public Cell(String code, String sectorCode, String name) {
        this.code = code;
        this.sectorCode = sectorCode;
        this.name = name;
    }
    
    // Getters and Setters
    public String getCode() {
        return code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    
    public String getSectorCode() {
        return sectorCode;
    }
    
    public void setSectorCode(String sectorCode) {
        this.sectorCode = sectorCode;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Sector getSector() {
        return sector;
    }
    
    public void setSector(Sector sector) {
        this.sector = sector;
    }
    
    @Override
    public String toString() {
        return "Cell{" +
                "code='" + code + '\'' +
                ", sectorCode='" + sectorCode + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}

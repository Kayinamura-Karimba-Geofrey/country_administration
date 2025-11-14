package com.example.Country.Administrative.System.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "sectors")
public class Sector {
    
    @Id
    @Column(name = "code", length = 6)
    @NotBlank
    @Size(min = 6, max = 6)
    private String code;
    
    @Column(name = "district_code", length = 4, nullable = false)
    @NotBlank
    @Size(min = 4, max = 4)
    private String districtCode;
    
    @Column(name = "name", nullable = false)
    @NotBlank
    private String name;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "district_code", insertable = false, updatable = false)
    private District district;
    
    // Constructors
    public Sector() {}
    
    public Sector(String code, String districtCode, String name) {
        this.code = code;
        this.districtCode = districtCode;
        this.name = name;
    }
    
    // Getters and Setters
    public String getCode() {
        return code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    
    public String getDistrictCode() {
        return districtCode;
    }
    
    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public District getDistrict() {
        return district;
    }
    
    public void setDistrict(District district) {
        this.district = district;
    }
    
    @Override
    public String toString() {
        return "Sector{" +
                "code='" + code + '\'' +
                ", districtCode='" + districtCode + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}


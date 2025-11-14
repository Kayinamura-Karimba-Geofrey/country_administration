package com.example.Country.Administrative.System.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "districts")
public class District {
    
    @Id
    @Column(name = "code", length = 4)
    @NotBlank
    @Size(min = 4, max = 4)
    private String code;
    
    @Column(name = "province_code", length = 2, nullable = false)
    @NotBlank
    @Size(min = 2, max = 2)
    private String provinceCode;
    
    @Column(name = "name", nullable = false)
    @NotBlank
    private String name;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "province_code", insertable = false, updatable = false)
    private Province province;
    
    // Constructors
    public District() {}
    
    public District(String code, String provinceCode, String name) {
        this.code = code;
        this.provinceCode = provinceCode;
        this.name = name;
    }
    
    // Getters and Setters
    public String getCode() {
        return code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    
    public String getProvinceCode() {
        return provinceCode;
    }
    
    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Province getProvince() {
        return province;
    }
    
    public void setProvince(Province province) {
        this.province = province;
    }
    
    @Override
    public String toString() {
        return "District{" +
                "code='" + code + '\'' +
                ", provinceCode='" + provinceCode + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}

package com.example.Country.Administrative.System.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "provinces")
public class Province {
    
    @Id
    @Column(name = "code", length = 2)
    @NotBlank
    @Size(min = 2, max = 2)
    private String code;
    
    @Column(name = "name", nullable = false)
    @NotBlank
    private String name;
    
    // Constructors
    public Province() {}
    
    public Province(String code, String name) {
        this.code = code;
        this.name = name;
    }
    
    // Getters and Setters
    public String getCode() {
        return code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public String toString() {
        return "Province{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}

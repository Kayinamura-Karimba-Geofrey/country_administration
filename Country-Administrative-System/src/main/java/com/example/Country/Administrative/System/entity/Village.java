package com.example.Country.Administrative.System.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "villages")
public class Village {
    
    @Id
    @Column(name = "code", length = 10)
    @NotBlank
    @Size(min = 10, max = 10)
    private String code;
    
    @Column(name = "cell_code", length = 8, nullable = false)
    @NotBlank
    @Size(min = 8, max = 8)
    private String cellCode;
    
    @Column(name = "name", nullable = false)
    @NotBlank
    private String name;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cell_code", insertable = false, updatable = false)
    private Cell cell;
    
    // Constructors
    public Village() {}
    
    public Village(String code, String cellCode, String name) {
        this.code = code;
        this.cellCode = cellCode;
        this.name = name;
    }
    
    // Getters and Setters
    public String getCode() {
        return code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    
    public String getCellCode() {
        return cellCode;
    }
    
    public void setCellCode(String cellCode) {
        this.cellCode = cellCode;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Cell getCell() {
        return cell;
    }
    
    public void setCell(Cell cell) {
        this.cell = cell;
    }
    
    @Override
    public String toString() {
        return "Village{" +
                "code='" + code + '\'' +
                ", cellCode='" + cellCode + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}


package com.example.Country.Administrative.System;

import com.example.Country.Administrative.System.entity.Province;
import com.example.Country.Administrative.System.service.ProvinceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class CountryAdministrativeSystemApplicationTests {

    @Autowired
    private ProvinceService provinceService;

    @Test
    void contextLoads() {
        // This test verifies that the Spring context loads successfully
        assertNotNull(provinceService);
    }

    @Test
    void testProvinceService() {
        // Test basic province service functionality
        long initialCount = provinceService.count();
        assertTrue(initialCount >= 0);
    }
}
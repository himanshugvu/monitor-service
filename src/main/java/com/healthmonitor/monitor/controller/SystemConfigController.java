package com.healthmonitor.monitor.controller;

import com.healthmonitor.monitor.model.*;
import com.healthmonitor.monitor.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/system")
@CrossOrigin(origins = "*")
public class SystemConfigController {
    
    @Autowired
    private CountryConfigRepository countryConfigRepository;
    
    @Autowired
    private AppCodeConfigRepository appCodeConfigRepository;
    
    @Autowired
    private AppConfigurationRepository appConfigurationRepository;

    @GetMapping("/config")
    public ResponseEntity<SystemConfig> getSystemConfig() {
        List<CountryConfig> countries = countryConfigRepository.findAll();
        List<AppCodeConfig> appCodes = appCodeConfigRepository.findAll();
        List<AppConfiguration> apps = appConfigurationRepository.findAll();
        
        SystemConfig systemConfig = new SystemConfig(countries, appCodes, apps, LocalDateTime.now());
        return ResponseEntity.ok(systemConfig);
    }

    @GetMapping("/countries")
    public ResponseEntity<List<CountryConfig>> getCountries() {
        return ResponseEntity.ok(countryConfigRepository.findAll());
    }

    @GetMapping("/countries/{countryCode}/appcodes")
    public ResponseEntity<List<AppCodeConfig>> getAppCodesByCountry(@PathVariable String countryCode) {
        return ResponseEntity.ok(appCodeConfigRepository.findByCountryCode(countryCode));
    }

    @GetMapping("/appcodes/{appCode}/country/{countryCode}/apps")
    public ResponseEntity<List<AppConfiguration>> getAppsByAppCode(@PathVariable String appCode, 
                                                                  @PathVariable String countryCode) {
        Optional<AppConfiguration> appOpt = appConfigurationRepository.findByAppCode(appCode);
        if (appOpt.isPresent()) {
            return ResponseEntity.ok(List.of(appOpt.get()));
        }
        return ResponseEntity.ok(List.of());
    }
}
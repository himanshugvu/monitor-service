package com.healthmonitor.monitor.controller;

import com.healthmonitor.monitor.model.AppHealth;
import com.healthmonitor.monitor.model.HealthSummary;
import com.healthmonitor.monitor.service.HealthMonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/apps")
@CrossOrigin(origins = "http://localhost:3000")
public class HealthMonitorController {
    
    @Autowired
    private HealthMonitorService healthMonitorService;
    
    @GetMapping("/health-summary")
    public ResponseEntity<HealthSummary> getHealthSummary() {
        HealthSummary summary = healthMonitorService.getHealthSummary();
        return ResponseEntity.ok(summary);
    }
    
    @GetMapping("/{appCode}/health")
    public ResponseEntity<AppHealth> getAppHealth(@PathVariable String appCode) {
        AppHealth appHealth = healthMonitorService.getAppHealth(appCode);
        
        if ("UNKNOWN".equals(appHealth.getOverallStatus())) {
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(appHealth);
    }
}
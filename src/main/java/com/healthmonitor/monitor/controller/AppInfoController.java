package com.healthmonitor.monitor.controller;

import com.healthmonitor.monitor.model.AppInfo;
import com.healthmonitor.monitor.service.AppConfigCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/apps")
@CrossOrigin(origins = "http://localhost:3000")
public class AppInfoController {

    @Autowired
    private AppConfigCacheService appConfigCacheService;

    @GetMapping("/info")
    public ResponseEntity<List<AppInfo>> getAllAppInfos() {
        List<AppInfo> appInfos = appConfigCacheService.getAllAppInfos();
        return ResponseEntity.ok(appInfos);
    }

    @PostMapping("/cache/refresh")
    public ResponseEntity<Map<String, Object>> refreshCache() {
        appConfigCacheService.refreshCache();
        
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Cache refreshed successfully");
        response.put("timestamp", System.currentTimeMillis());
        response.put("lastCacheUpdate", appConfigCacheService.getLastCacheUpdateTime());
        
        return ResponseEntity.ok(response);
    }

    @GetMapping("/cache/status")
    public ResponseEntity<Map<String, Object>> getCacheStatus() {
        Map<String, Object> status = new HashMap<>();
        status.put("lastCacheUpdate", appConfigCacheService.getLastCacheUpdateTime());
        status.put("isStale", appConfigCacheService.isCacheStale());
        status.put("currentTime", System.currentTimeMillis());
        
        return ResponseEntity.ok(status);
    }
}
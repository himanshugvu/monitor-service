package com.healthmonitor.monitor.service;

import com.healthmonitor.monitor.model.AppConfiguration;
import com.healthmonitor.monitor.model.AppInfo;
import com.healthmonitor.monitor.repository.AppConfigurationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class AppConfigCacheService {

    @Autowired
    private AppConfigurationRepository appConfigRepository;

    private final Map<String, AppConfiguration> appConfigCache = new ConcurrentHashMap<>();
    private volatile long lastCacheUpdate = 0;

    @Cacheable("appConfigurations")
    public List<AppConfiguration> getAllEnabledAppConfigurations() {
        return appConfigRepository.findByEnabledTrue();
    }

    @Cacheable("appInfos")
    public List<AppInfo> getAllAppInfos() {
        return appConfigRepository.findAllAppCodes().stream()
                .map(config -> new AppInfo(config.getAppCode(), config.getAppName()))
                .collect(Collectors.toList());
    }

    public AppConfiguration getAppConfiguration(String appCode) {
        // Try cache first
        if (appConfigCache.containsKey(appCode)) {
            return appConfigCache.get(appCode);
        }

        // If not in cache, fetch from database and cache it
        return appConfigRepository.findByAppCode(appCode)
                .map(config -> {
                    appConfigCache.put(appCode, config);
                    return config;
                })
                .orElse(null);
    }

    public Map<String, List<String>> getAllAppUrlMappings() {
        return getAllEnabledAppConfigurations().stream()
                .collect(Collectors.toMap(
                        AppConfiguration::getAppCode,
                        AppConfiguration::getUrls,
                        (existing, replacement) -> existing // Keep first occurrence in case of duplicates
                ));
    }

    @CacheEvict(value = {"appConfigurations", "appInfos"}, allEntries = true)
    public void refreshCache() {
        appConfigCache.clear();
        lastCacheUpdate = System.currentTimeMillis();
        
        // Pre-populate cache
        List<AppConfiguration> configs = appConfigRepository.findByEnabledTrue();
        configs.forEach(config -> appConfigCache.put(config.getAppCode(), config));
    }

    @Scheduled(fixedDelay = 3600000) // 1 hour = 3600000 ms
    public void autoRefreshCache() {
        refreshCache();
    }

    public long getLastCacheUpdateTime() {
        return lastCacheUpdate;
    }

    public boolean isCacheStale() {
        long oneHour = 3600000;
        return (System.currentTimeMillis() - lastCacheUpdate) > oneHour;
    }
}
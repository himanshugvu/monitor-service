package com.healthmonitor.monitor.repository;

import com.healthmonitor.monitor.model.AppCodeConfig;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppCodeConfigRepository extends MongoRepository<AppCodeConfig, String> {
    List<AppCodeConfig> findByCountryCode(String countryCode);
    AppCodeConfig findByAppCodeAndCountryCode(String appCode, String countryCode);
}
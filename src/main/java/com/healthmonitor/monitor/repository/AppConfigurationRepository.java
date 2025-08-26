package com.healthmonitor.monitor.repository;

import com.healthmonitor.monitor.model.AppConfiguration;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AppConfigurationRepository extends MongoRepository<AppConfiguration, String> {
    
    Optional<AppConfiguration> findByAppCode(String appCode);
    
    List<AppConfiguration> findByEnabledTrue();
    
    @Query("{ 'enabled': true }")
    List<AppConfiguration> findAllEnabled();
    
    @Query(value = "{}", fields = "{ 'appCode': 1, 'appName': 1 }")
    List<AppConfiguration> findAllAppCodes();
    
    boolean existsByAppCode(String appCode);
}
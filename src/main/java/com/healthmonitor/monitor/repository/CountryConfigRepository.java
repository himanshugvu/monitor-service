package com.healthmonitor.monitor.repository;

import com.healthmonitor.monitor.model.CountryConfig;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryConfigRepository extends MongoRepository<CountryConfig, String> {
    CountryConfig findByCountryCode(String countryCode);
}
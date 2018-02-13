package com.iliap.taxesManager.model.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.iliap.taxesManager.model.CountryTaxesCrud;

public interface CountryTaxesRepository extends MongoRepository<CountryTaxesCrud, Integer>{
	List<CountryTaxesCrud> findByCountry(String country);
}

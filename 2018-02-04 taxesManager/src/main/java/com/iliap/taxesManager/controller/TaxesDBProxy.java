package com.iliap.taxesManager.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import com.iliap.taxesManager.TaxesManagerOperations;
import com.iliap.taxesManager.common.CountryDTO;
import com.iliap.taxesManager.model.CountryTaxesCrud;
import com.iliap.taxesManager.model.repo.CountryTaxesRepository;

@Service
public class TaxesDBProxy implements ApplicationRunner {
	private static Map<String,List<CountryTaxesCrud>> taxesProxyMap = new HashMap<>();
	
	@Autowired
	CountryTaxesRepository taxesRepo;	
	@Autowired
	TaxesManagerOperations taxesOper;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("Making proxy db map");
		List<CountryDTO> countries = taxesOper.getActualCountries();	
		countries.forEach(x->{
			List<CountryTaxesCrud> taxes = new ArrayList<>();
			//System.out.println(x.getName());
			taxes = taxesRepo.findByCountry(x.getName());
			if(taxes.size()>0){
				taxesProxyMap.put(x.getName(), taxes);
			}
		});		
	}

	public static Map<String, List<CountryTaxesCrud>> getTaxesProxyMap() {
		return taxesProxyMap;
	}
	
	
	
	
}

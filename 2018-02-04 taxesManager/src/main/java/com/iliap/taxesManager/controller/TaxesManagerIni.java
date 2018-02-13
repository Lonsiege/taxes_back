package com.iliap.taxesManager.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.iliap.taxesManager.common.Countries;
import com.iliap.taxesManager.model.BatchSaver;
import com.iliap.taxesManager.model.CountryTaxesCrud;
import com.iliap.taxesManager.model.repo.CountryTaxesRepository;

@Component
public class TaxesManagerIni<T> implements ApplicationRunner {
	@Autowired
	CountryTaxesRepository taxesRepo;
	@Autowired
	BatchSaver<T> batchSaver;
	@Value("${ini_ini}")
	Boolean isIni;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		if (isIni) addInitialDataToDb();
		
	}
	
	private void addInitialDataToDb(){
		System.out.println("Adding initial data to database");
		List<CountryTaxesCrud> taxesList = new ArrayList<>();
		taxesList.add(new CountryTaxesCrud(Countries.RUSSIA, 100000, 1000000, 0.13f));
		taxesList.add(new CountryTaxesCrud(Countries.ISRAEL, 0, 6240, 0.1f));
		taxesList.add(new CountryTaxesCrud(Countries.ISRAEL, 6240, 8950, 0.14f));
		taxesList.add(new CountryTaxesCrud(Countries.ISRAEL, 8950, 14360, 0.2f));
		taxesList.add(new CountryTaxesCrud(Countries.ISRAEL, 14360, 19960, 0.31f));
		taxesList.add(new CountryTaxesCrud(Countries.ISRAEL, 19960, 41530, 0.35f));
		taxesList.add(new CountryTaxesCrud(Countries.ISRAEL, 41530, 53490, 0.47f));
		taxesList.add(new CountryTaxesCrud(Countries.ISRAEL, 53490, 10000000, 0.5f));
		taxesList.add(new CountryTaxesCrud(Countries.USA, 1, 1000000, 0.23f));
		
		
		taxesList = taxesList.stream().map(CountryTaxesCrud::calculateStepSum).collect(Collectors.toList());
		batchSaver.batchSave(taxesRepo, taxesList);
	}
	
}

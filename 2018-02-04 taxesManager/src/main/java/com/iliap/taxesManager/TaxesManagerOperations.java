package com.iliap.taxesManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iliap.taxesManager.common.Countries;
import com.iliap.taxesManager.common.CountryDTO;
import com.iliap.taxesManager.common.SalaryDTO;
import com.iliap.taxesManager.controller.RestOperationsTaxes;
import com.iliap.taxesManager.controller.TaxesDBProxy;
import com.iliap.taxesManager.model.CountryTaxesCrud;

@Service
public class TaxesManagerOperations implements RestOperationsTaxes{
	@Autowired
	TaxesDBProxy dbProxy;
	@Override
	public SalaryDTO getNetSalary(String country, double gross) {
		SalaryDTO netSal;
		Countries countryX = getCountryFromString(country);
		
		List<CountryTaxesCrud> taxesList = dbProxy.getTaxesProxyMap().get(countryX.toString());
		netSal = searchNet(gross, taxesList);
		return netSal;
	}
	
	private SalaryDTO searchNet(double amount, List<CountryTaxesCrud> taxes) {
		CountryTaxesCrud res = taxes.get(0);
		Double netSalary = 0.;
		for(int i=0;i<taxes.size()-1;i++){	
			if(taxes.get(i).getMaxAmount() > amount){
				netSalary += (amount - taxes.get(i).getMinAmount())*(1-taxes.get(i).getTax());
				return new SalaryDTO(amount, netSalary.intValue());
			}
			netSalary += taxes.get(i).getStepSum();
		}
		return new SalaryDTO();	
	}

	@Override
	public List<CountryDTO> getActualCountries(){
		List<CountryDTO> res = new ArrayList<>();
		Countries[] countArr = Countries.values();
		for(int i=0;i<Countries.values().length;i++){
			res.add(new CountryDTO(countArr[i].toString()));
		}
		Comparator nameCompare = Comparator.comparing(CountryDTO::getName);
		Collections.sort(res,nameCompare);
		return res;
	}
	
	private static Countries getCountryFromString(String country) {
		Countries countryRes = Countries.USA;
		try {
			countryRes = Countries.valueOf(country.toUpperCase());
		} catch (IllegalArgumentException e) {
		}
		return countryRes;
	}

	@Override
	public int getClosestHigher(int number, int step) {
		int max = number+step;
		for(int i=number+1;i<=max;i++){
			if(i % step == 0) return i;
		}
		return number;
	}

	@Override
	public int getClosestLower(int number, int step) {
		int min = number-step;
		for(int i=number-1;i>=min;i--){
			if(i % step == 0) return i;
		}
		return number;
	}
	




}

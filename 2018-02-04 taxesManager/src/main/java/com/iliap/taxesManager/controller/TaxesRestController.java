package com.iliap.taxesManager.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iliap.taxesManager.common.CommonDTO;
import com.iliap.taxesManager.common.Countries;
import com.iliap.taxesManager.common.CountryDTO;
import com.iliap.taxesManager.common.SalaryDTO;
import com.iliap.taxesManager.model.CountryTaxesCrud;

import static com.iliap.taxesManager.common.PathConstants.*;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class TaxesRestController {
	
	@Autowired
	RestOperationsTaxes taxesManager;
	
	//@RequestMapping(value=GET_AV_COUNTRIES,method=RequestMethod.GET)
	@GetMapping(value = GET_AV_COUNTRIES)
	public CommonDTO getActualCountries() {	
		List<CountryDTO> list = taxesManager.getActualCountries();
		return list.size() == 0 ? new CommonDTO(false, "No data available") : new CommonDTO(true, list);
	}

	@GetMapping(value = GET_NET)
	public CommonDTO getNetSalary(@RequestParam(name=COUNTRY) String country, @RequestParam(name=GROSS) double gross) {
		SalaryDTO netSal = taxesManager.getNetSalary(country, gross);
		return netSal == null ? new CommonDTO(false, "No data available") : new CommonDTO(true, netSal);
	}
	
	@GetMapping(value = GET_NETS)
	public CommonDTO getNetSalary(@RequestParam(name=COUNTRY) String country, @RequestParam(name=MINGROSS) Double mingross, 
			@RequestParam(name=MAXGROSS) Double maxgross, @RequestParam(name=STEP) int step) {
		List<SalaryDTO> netSals = new ArrayList<>();
		int minVal = (mingross < maxgross) ? mingross.intValue() : maxgross.intValue();	
		int maxVal = (mingross > maxgross) ? mingross.intValue() : maxgross.intValue();
		netSals.add(taxesManager.getNetSalary(country, minVal));
		int stepL = taxesManager.getClosestLower(maxVal, step);
		int stepF = taxesManager.getClosestHigher(minVal, step);
		for(int i = stepF; i<=stepL; i+=step){
			netSals.add(taxesManager.getNetSalary(country, i));
		}
		netSals.add(taxesManager.getNetSalary(country, maxVal));		
		return netSals.size() == 0 ? new CommonDTO(false, "No data available") : new CommonDTO(true, netSals);
	}

	
	
	

}

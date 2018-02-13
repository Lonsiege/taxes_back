package com.iliap.taxesManager.controller;


import java.util.List;

import com.iliap.taxesManager.common.Countries;
import com.iliap.taxesManager.common.CountryDTO;
import com.iliap.taxesManager.common.SalaryDTO;

public interface RestOperationsTaxes {

	public SalaryDTO getNetSalary(String country, double gross);
	public List<CountryDTO> getActualCountries();
	public int getClosestHigher(int number, int step);
	public int getClosestLower(int number, int step);
}

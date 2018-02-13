package com.iliap.taxesManager;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.iliap.taxesManager.common.Countries;
import com.iliap.taxesManager.common.SalaryDTO;
import com.iliap.taxesManager.model.CountryTaxesCrud;
import com.iliap.taxesManager.model.repo.CountryTaxesRepository;

@RunWith(SpringRunner.class)
@SpringBootTest()

public class TaxesManagerApplicationTests {
	
	@Autowired
	private CountryTaxesRepository taxesRepo;
	@Autowired
	private TaxesManagerOperations manager;

	@Before
	public void setUp() {
	}

	@Test
	public void contextLoads() {
	}
	
	@Test
	public void getSomething(){
		SalaryDTO salary = manager.getNetSalary("isRael", 1000);
		System.out.println(salary);
		/*SalaryDTO salary = manager.getNetSalary("isRael", 1000);
		System.out.println(salary);
		SalaryDTO salary1 = manager.getNetSalary("isRael", 10000);
		System.out.println(salary1);
		SalaryDTO salary2 = manager.getNetSalary("isRael", 20000);
		System.out.println(salary2);*/
		//List<CountryTaxesCrud> test = taxesRepo.findByCountry("Russia");
		//System.out.println(test);
	}
	
	@After
	public void cleanRoom(){
		//this.taxesRepo.deleteAll();
	}

}

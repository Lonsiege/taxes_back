package com.iliap.taxesManager.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.iliap.taxesManager.common.Countries;


//The @Document annotation is optional, and if we don’t use one, the collection will be named with the class name. 
// то есть будет смотреть по репозиторию ^_^
@Document(collection="countryTaxes") //for RelativeDB we are using JPA @Entity here
@CompoundIndex(def="{'country':1,'minAmount':1}",name="compound_ind1",unique=true)
public class CountryTaxesCrud implements CrudAbstract{
	@Id
	private String id;
	
	private Countries country;
	private int minAmount;
	private int maxAmount;
	private float tax;
	private float stepSum;
	
	public CountryTaxesCrud() {	}

	public CountryTaxesCrud(Countries country, int minAmount, int maxAmount, float tax) {
		super();
		this.country = country;
		this.minAmount = minAmount;
		this.maxAmount = maxAmount;
		this.tax = tax;
	}

	public String getId() {
		return id;
	}

	public Countries getCountry() {
		return country;
	}

	public int getMinAmount() {
		return minAmount;
	}

	public int getMaxAmount() {
		return maxAmount;
	}

	public float getTax() {
		return tax;
	}

	public float getStepSum() {
		return stepSum;
	}

	public void setStepSum(float stepSum) {
		this.stepSum = stepSum;
	}

	@Override
	public String toString() {
		return "CountryTaxesCrud [id=" + id + ", country=" + country + ", minAmount=" + minAmount + ", maxAmount="
				+ maxAmount + ", tax=" + tax + ", stepSum=" + stepSum + "]";
	}	
	
	public CountryTaxesCrud calculateStepSum(){
		setStepSum((this.getMaxAmount()-this.getMinAmount())*(1-this.getTax()));
		return this;
	}
	
	
	
	
	
	
	
	
}

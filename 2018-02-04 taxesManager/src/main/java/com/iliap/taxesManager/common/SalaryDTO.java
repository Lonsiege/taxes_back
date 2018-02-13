package com.iliap.taxesManager.common;

public class SalaryDTO {
	private double grossSalary;
	private int netSalary;	

	public SalaryDTO(){}

	public SalaryDTO(double grossSalary, int netSalary) {
		super();
		this.grossSalary = grossSalary;
		this.netSalary = netSalary;
	}

	public double getGrossSalary() {
		return grossSalary;
	}

	public double getNetSalary() {
		return netSalary;
	}

	@Override
	public String toString() {
		return "SalaryDTO [grossSalary=" + grossSalary + ", netSalary=" + netSalary + "]";
	}

	
	
}

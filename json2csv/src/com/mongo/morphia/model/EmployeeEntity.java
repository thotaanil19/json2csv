package com.mongo.morphia.model;

import java.util.List;

import org.mongodb.morphia.annotations.Entity;

@Entity("EmployeeDetails")
public class EmployeeEntity extends BaseEntity {

	private static final long serialVersionUID = 6018459947384933451L;
	
	private String name;
	private List<String> phones;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void addPhone(String number) {
		phones.add(number);
	}
	public List<String> getPhones() {
		return phones;
	}
	public void setPhones(List<String> phones) {
		this.phones = phones;
	}
	@Override
	public String toString() {
		return "EmployeeEntity [name=" + name + ", phones="
				+ phones + "]";
	}
	
	
	
	
	
	
}

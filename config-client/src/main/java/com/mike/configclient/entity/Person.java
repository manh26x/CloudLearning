package com.mike.configclient.entity;

import org.springframework.data.relational.core.mapping.Table;

@Table(value="Person")
public class Person {
	
	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String toString() {
		return this.name;
	}
}

package com.rdbusiness.rest.bean;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@XmlRootElement
@Document
public class Person {
	@Id
	private String id;
	private String name;
	private String lastName;
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String firstName) {
		this.name = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj instanceof Person) {
			Person p = (Person) obj;

			if (this.getId().equals(p.getId()) && this.getName().equals(p.getName())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return "{\"id\":\"" + id + "\",\"firstName\":\"" + name + "\",\"lastName\":\"" + lastName + "\"}";
	}

}
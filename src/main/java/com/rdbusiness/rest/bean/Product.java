package com.rdbusiness.rest.bean;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

@XmlRootElement
@Document
@TypeAlias("o_product")
public class Product {
	@Id
	private String id;
	private String name;
	private String category;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj instanceof Product) {
			Product p = (Product) obj;

			if (this.getId().equals(p.getId()) &&
					this.getName().equals(p.getName())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return "{\"id\":\"" + id + "\",\"name\":\"" + name + "\",\"category\":\"" + category + "\"}";
	}

}
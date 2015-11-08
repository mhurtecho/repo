package com.rdbusiness.rest.bean;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Document
public class BeanSample extends Bean{
	@Id
	private String id;
	private String field1;
	private int field2;
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getField1() {
		return field1;
	}

	public void setField1(String firstName) {
		this.field1 = firstName;
	}

	public int getField2() {
		return field2;
	}

	public void setField2(int field2) {
		this.field2 = field2;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj instanceof BeanSample) {
			BeanSample p = (BeanSample) obj;

			if (this.getId().equals(p.getId()) && this.getField1().equals(p.getField1())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return "BeanSample{" +
				"id='" + id + '\'' +
				", field1='" + field1 + '\'' +
				", field2=" + field2 +
				'}' +
				super.toString();
	}
}
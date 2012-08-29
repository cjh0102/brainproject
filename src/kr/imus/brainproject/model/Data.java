package kr.imus.brainproject.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Data {
	
	private String name;
	private String gender;
	private List<String> items;
	private Date timestamp;
	private int numberofItems;
	
	public Data() {
		this(null, null, new ArrayList<String>(), 0, null);
	}
	
	public Data(String name, String gender, List<String> items, int numberOfItems, Date timestamp) {
		super();
		this.name = name;
		this.gender = gender;
		this.items = items;
		this.timestamp = timestamp;
		this.numberofItems = numberOfItems;
		
	}
	
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public List<String> getItems() {
		return items;
	}
	
	public void setItems(List<String> items) {
		this.items = items;
	}
	
	public Date getTimestamp() {
		return timestamp;
	}
	
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	

	public int getNumberofItems() {
		return numberofItems;
	}

	public void setNumberofItems(int numberofItems) {
		this.numberofItems = numberofItems;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((timestamp == null) ? 0 : timestamp.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Data other = (Data) obj;
		if (gender == null) {
			if (other.gender != null)
				return false;
		} else if (!gender.equals(other.gender))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (timestamp == null) {
			if (other.timestamp != null)
				return false;
		} else if (!timestamp.equals(other.timestamp))
			return false;
		return true;
	}

}

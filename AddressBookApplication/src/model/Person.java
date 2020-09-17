package model;

import java.io.Serializable;
import java.util.Comparator;

public class Person implements Serializable {

	private static final long serialVersionUID = 8684279557418429156L;
	private String firstname;
	private String lastname;
	private String address;
	private String city;
	private String state;
	private String zip;
	private String phone;
	
	@Override
	public String toString() {
		return firstname + "\t\t" + lastname + "\t\t" + address + "\t\t" + city + "\t\t" + state + "\t\t" + zip + "\t\t" + phone;
	}
	
	public String getFirstname() {
		return firstname;
	}
	
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	public String getLastname() {
		return lastname;
	}
	
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public static Comparator<Person> sortbyname = new Comparator<Person>() 
	{

		public int compare(Person s1, Person s2) 
		{
			int res = s1.getLastname().compareToIgnoreCase(s2.getLastname());
		
			if( res != 0)
			{
				return s1.getLastname().compareToIgnoreCase(s2.getLastname());
			}
			
			return s1.getFirstname().compareToIgnoreCase(s2.getFirstname());
		}
	};
	
	public static Comparator<Person> sortbyzip = new Comparator<Person>() 
	{
	
		public int compare(Person s1, Person s2) 
		{
			return s1.getZip().compareTo(s2.getZip());
		}
		
	};
	
}

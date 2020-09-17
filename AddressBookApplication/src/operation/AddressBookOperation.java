package operation;

import java.io.*;
import java.util.*;

import model.Person;

interface Operation 
{
	
	void createAddressBook();
	void addPerson();
	void displayAddressBook();
	void editPerson();
	void searchPerson();
	void deletePerson();
	void sortByName();
	void sortByZip();
}

public class AddressBookOperation implements Operation 
{
	
	int countAddressbook = 0;
	String addressKey = "";
	String[] addressBook = new String[20];
	List<Person> list = new LinkedList<Person>();
	Map<String, List<Person>> map = new HashMap<String, List<Person>>();
	Scanner sc = new Scanner(System.in);
	
	// File Writer
	public void fileWriter() 
	{	
		
		try 
		{		
			FileOutputStream fos = new FileOutputStream("C:\\Users\\Anik Chowdhury\\Github\\AddressBookApplications\\AddressBookApplication\\addressbook.txt");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(map);
			oos.writeObject(addressBook);
			oos.writeInt(countAddressbook);
			oos.writeObject(addressKey);
			oos.flush();
			oos.close();	
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
	}
	
	// File Reader
	public void fileReader() 
	{
		
		try 
		{
			FileInputStream fis = new FileInputStream("C:\\Users\\Anik Chowdhury\\Github\\AddressBookApplications\\AddressBookApplication\\addressbook.txt");
			ObjectInputStream ois = new ObjectInputStream(fis);
			map = (HashMap) ois.readObject();
			addressBook = (String[]) ois.readObject();
			countAddressbook = ois.readInt();
			addressKey = (String) ois.readObject();
			ois.close();
			fis.close();
		} 
		catch (EOFException e) 
		{
			
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	// Create new address book
	public void createAddressBook() 
	{
		fileReader();
		System.out.println("Enter the name of address book do you want to create: ");
		addressBook[countAddressbook] = sc.nextLine();
		map.put(addressBook[countAddressbook], new LinkedList<Person>());
		countAddressbook++;
		fileWriter();
		System.out.println("Address Book Created");	
	}
	
	public void getAddressBook() 
	{
		System.out.println("Your Address Book are: ");
		
		for (int addressSelect = 0; addressSelect < countAddressbook; addressSelect++) {
			System.out.println(addressSelect+1 + " " + addressBook[addressSelect]);
		}
		
	}
	
	public void selectAddressBook()
	{
		int correctChoice=0;
		
		while ( correctChoice==0 ) 
		{
			System.out.println("Please Select Your address book");
			int select = sc.nextInt();
			addressKey = addressBook[select-1];
			sc.nextLine();
			
			if (map.get(addressKey)!=null)
			{
				correctChoice=1;
			}
		
			else
			{
				System.out.println("Please select correct addressbook");
			
			}
			
		}
		
	}
	
	// add person to address book
	public void addPerson() 
	{
		fileReader();
	
		if (countAddressbook == 0)
		{
			System.out.println("There is No Address Book present.");
			System.out.println("Do you want to create new address Book. Press 1 for Yes.");
			int press = sc.nextInt();
			sc.nextLine();
		
			if(press == 1) {
				createAddressBook();
			}
			
			else 
			{
				return;
			}
		}
		
		getAddressBook();
		selectAddressBook();
		
		Person person = new Person();
		
		System.out.println("Enter the First name");
		String firstname = sc.nextLine();
		person.setFirstname(firstname);

		System.out.println("Enter the Last name");
		String lastname = sc.nextLine();
		person.setLastname(lastname);

		System.out.println("Enter your Address");
		String address = sc.nextLine();
		person.setAddress(address);

		System.out.println("Enter your City");
		String city = sc.nextLine();
		person.setCity(city);

		System.out.println("Enter your State");
		String state = sc.nextLine();
		person.setState(state);

		System.out.println("Enter your Zip Code");
		String zipcode = sc.nextLine();
		person.setZip(zipcode);

		System.out.println("Enter your Phone Number");
		String phone = sc.nextLine();
		person.setPhone(phone);
		
		List<Person> list = map.get(addressKey);
		
		for (int i = 0; i < list.size(); i++) 
		{
			if(list.get(i).getPhone().equals(phone))
			{
				System.out.println("Phone number already exist");
				return;
			}
		}
		
		list.add(person);
		map.put(addressKey, list);
		fileWriter();
	}
	
	// display all person in addressbook
	public void displayAddressBook()
	{	
		fileReader();
		getAddressBook();
		selectAddressBook();
		
		List<Person> list = map.get(addressKey);

		System.out.println("FIRSTNAME \t LASTNAME \t\tADDRESS \tCITY \t\t STATE \t\t\t ZIP \t\t PHONE \n");
		for (int i = 0; i < list.size(); i++) 
		{
			System.out.println(list.get(i));
		}
					
	}
	
	// edit person details
	public void editPerson() 
	{
		fileReader();
		getAddressBook();
		selectAddressBook();
		
		List<Person> list = map.get(addressKey);

		System.out.println("FIRSTNAME \t LASTNAME \t\tADDRESS \tCITY \t\t STATE \t\t\t ZIP \t\t PHONE \n");
		for (int i = 0; i < list.size(); i++) 
		{
			System.out.println(list.get(i));
		}
			
		int ch = 0, choice, contactFound=0;
		System.out.println("Enter the phone number for updation");
		String phone = sc.nextLine();
			
		for (int i = 0; i < list.size(); i++) 
		{
			String str = map.get(addressKey).get(i).getPhone();
			
			if (str.equalsIgnoreCase(phone))
			{
				Person persondetails = map.get(addressKey).get(i);
				System.out.println("Please select field you need to edit..!");
				contactFound++;
				
				do {
					System.out.println("1. Edit Address");
					System.out.println("2. Edit City");
					System.out.println("3. Edit State");
					System.out.println("4. Edit Zipcode");
					System.out.println("5. Edit Phone Number");
					System.out.println("Enter Your choice: ");
					ch = sc.nextInt();
					sc.nextLine();

					switch (ch) 
					{
						case 1:
							System.out.println("Enter your Address");
							String address = sc.nextLine();
							persondetails.setAddress(address);
							fileWriter();
							System.out.println("Address Updated");
							break;
						case 2:
							System.out.println("Enter your City ");
							String city = sc.nextLine();
							persondetails.setCity(city);
							fileWriter();
							System.out.println("City Updated");
							break;
						case 3:
							System.out.println("Enter your State");
							String state = sc.next();
							persondetails.setState(state);
							fileWriter();
							System.out.println("State Updated");
							break;
						case 4:
							System.out.println("Enter Your Zipcode");
							String zipcode = sc.next();
							persondetails.setZip(zipcode);
							fileWriter();
							System.out.println("Zipcode Updated");
							break;
						case 5:
							System.out.println("Enter Phone Number");
							String phone1 = sc.next();
							persondetails.setPhone(phone1);
							fileWriter();
							System.out.println("Phone Number Updated");
							break;

					}

					System.out.println("Do You want to continue? If yes Press 1");
					choice = sc.nextInt();
					sc.nextLine();
				} while (choice == 1);
					
			}
				
		}
			
		if (contactFound==0)
		{
			System.out.println("Contact not found");
		}
				
	}
	
	//search person in addressbook
	public void searchPerson() 
	{
		fileReader();
		getAddressBook();
		selectAddressBook();
			
		List<Person> list = map.get(addressKey);
		
		System.out.println("Enter the First name you want to search");
		String name = sc.nextLine();
		int found_count = 0;
		
		System.out.println("FIRSTNAME\t LASTNAME\t\tADDRESS \tCITY \t\t STATE \t\t\t ZIP \t\t PHONE \n");
		for (int i = 0; i < list.size(); i++) 
		{
			String str = map.get(addressKey).get(i).getFirstname();
			
			if (str.equalsIgnoreCase(name)) 
			{
				System.out.println(map.get(addressKey).get(i));
				found_count++;
			}
				
		}
			
		if (found_count == 0) 
		{
			System.out.println("No contact found");
		}
			
	}
	
	//Delete person from addressbook
	public void deletePerson() 
	{	
		fileReader();
		getAddressBook();
		selectAddressBook();
		
		List<Person> list = map.get(addressKey);
		
		System.out.println("FIRSTNAME \t LASTNAME \t\tADDRESS \tCITY \t\t STATE \t\t\t ZIP \t\t PHONE \n");
		for (int i = 0; i < list.size(); i++) 
		{
			System.out.println(list.get(i));
		}
			
		System.out.println("Enter the phone number do you want to delete: ");
		String phone = sc.nextLine();
			
		int phone_remove=0;
					
		for (int i = 0; i < list.size(); i++) 
		{
			
			if (phone.equals(list.get(i).getPhone())) 
			{
				list.remove(i);
				System.out.println("Phone number removed");
				phone_remove=1;
			}
				
		}
			
		if (phone_remove==0) 
		{
			System.out.println("Phone Number not found");
		}
			
		fileWriter();
		
	}

	public void sortByName()
	{
		fileReader();
		getAddressBook();
		selectAddressBook();
		
		List<Person> list = map.get(addressKey);
		Collections.sort(list, Person.sortbyname);
		fileWriter();
		
		System.out.println("FIRSTNAME \t LASTNAME \t\tADDRESS \tCITY \t\t STATE \t\t\t ZIP \t\t PHONE \n");
		for (int i = 0; i < list.size(); i++) 
		{
			System.out.println(list.get(i));
		}
		
	}
	
	public void sortByZip()
	{
		fileReader();
		getAddressBook();
		selectAddressBook();
		
		List<Person> list = map.get(addressKey);
		Collections.sort(list, Person.sortbyzip);
		fileWriter();
		
		System.out.println("FIRSTNAME \t LASTNAME \t\tADDRESS \tCITY \t\t STATE \t\t\t ZIP \t\t PHONE \n");
		for (int i = 0; i < list.size(); i++) 
		{
			System.out.println(list.get(i));
		}
		
	}
	
}

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
	void deleteAddressBook();
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
			try
			{
				addressKey = addressBook[select-1];
			}
			catch(ArrayIndexOutOfBoundsException e)
			{
				System.out.println("Please select correct addressbook ");
				continue;
			}
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
		
		int firstNameCheck=0;
		while(firstNameCheck==0)
		{
			System.out.println("Enter the First name");
			String firstname = sc.nextLine();
			if(Pattern.matches("[a-zA-Z]{3,}", firstname))
			{
				person.setFirstname(firstname);
				firstNameCheck=1;
			}
			else
			{
				System.out.println("You didn't entered valid First Name");
				System.out.println("Please enter valid First Name");
			}
		}
		
		int lastNameCheck=0;
		while(lastNameCheck==0)
		{
			System.out.println("Enter the Last name");
			String lastname = sc.nextLine();
			if(Pattern.matches("[a-zA-Z]{3,}", lastname))
			{
				person.setLastname(lastname);
				lastNameCheck=1;
			}
			else
			{
				System.out.println("You didn't entered valid Last Name");
				System.out.println("Please enter valid Last Name");
			}
		}
		
		int addressCheck=0;
		while (addressCheck==0)
		{
			System.out.println("Enter your Address");
			String address = sc.nextLine();
			if(Pattern.matches("([a-zA-Z0-9]+(\\s[a-zA-Z]+)*)", address))
			{
				person.setAddress(address);
				addressCheck=1;
			}
			else
			{
				System.out.println("You didn't entered valid address");
				System.out.println("Please enter valid address");
			}
		}
		
		int cityCheck=0;
		while(cityCheck==0)
		{
			System.out.println("Enter your City");
			String city = sc.nextLine();
			if(Pattern.matches("([a-zA-Z]+|[a-zA-Z]+\\s[a-zA-Z]+)", city))
			{
				person.setCity(city);
				cityCheck=1;
			}
			else
			{
				System.out.println("You didn't entered valid City");
				System.out.println("Please enter valid City");
			}
		}

		int stateCheck=0;
		while(stateCheck==0)
		{
			System.out.println("Enter your State");
			String state = sc.nextLine();
			if(Pattern.matches("([a-zA-Z]+|[a-zA-Z]+\\s[a-zA-Z]+)", state))
			{
				person.setState(state);
				stateCheck=1;
			}
			else
			{
				System.out.println("You didn't entered valid State");
				System.out.println("Please enter valid State");
			}
		}

		int zipCodeCheck=0;
		while (zipCodeCheck==0)
		{
			System.out.println("Enter your Zip Code");
			String zipcode = sc.nextLine();
			if(Pattern.matches("[0-9]{6}", zipcode))
			{
				person.setZip(zipcode);
				zipCodeCheck=1;
			}
			else
			{
				System.out.println("You didn't entered valid zipcode");
				System.out.println("Please enter valid zip code");
			}
		}

		int phoneNumberCheck=0;
		while(phoneNumberCheck==0)
		{
			System.out.println("Enter your Phone Number");
			String phone = sc.nextLine();
			if(Pattern.matches("[0-9]{8,11}", phone))
			{
				person.setPhone(phone);
				phoneNumberCheck=1;
			}
			else
			{
				System.out.println("You didn't entered valid phone");
				System.out.println("Please enter valid phone");
			}	
		}
		
		List<Person> list = map.get(addressKey);
		
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

		if(list.size()==0)
		{
			System.out.println("No Contact Found");
			return;
		}
		else
		{
			for (int i = 0; i < list.size(); i++) 
			{
				System.out.println(list.get(i));
			}
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

		if(list.size()==0)
                {
                        System.out.println("No Contact Found");
                        return;
                }
                else
                {
                        for (int i = 0; i < list.size(); i++)
                        {
                                System.out.println(list.get(i));
                        }
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
							int addressCheck=0;
							while (addressCheck==0)
							{
								System.out.println("Enter your Address");
								String address = sc.nextLine();
								if(Pattern.matches("([a-zA-Z0-9]+(\\s[a-zA-Z]+)*)", address))
								{
									persondetails.setAddress(address);
									addressCheck=1;
								}
								else
								{
									System.out.println("You didn't entered valid address");
									System.out.println("Please enter valid address");
								}
							}
							fileWriter();
							System.out.println("Address Updated");
							break;
						case 2:
							int cityCheck=0;
							while(cityCheck==0)
							{
								System.out.println("Enter your City");
								String city = sc.nextLine();
								if(Pattern.matches("([a-zA-Z]+|[a-zA-Z]+\\s[a-zA-Z]+)", city))
								{
									persondetails.setCity(city);
									cityCheck=1;
								}
								else
								{
									System.out.println("You didn't entered valid City");
									System.out.println("Please enter valid City");
								}
							}
							fileWriter();
							System.out.println("City Updated");
							break;
						case 3:
							int stateCheck=0;
							while(stateCheck==0)
							{
								System.out.println("Enter your State");
								String state = sc.nextLine();
								if(Pattern.matches("([a-zA-Z]+|[a-zA-Z]+\\s[a-zA-Z]+)", state))
								{
									persondetails.setState(state);
									stateCheck=1;
								}
								else
								{
									System.out.println("You didn't entered valid State");
									System.out.println("Please enter valid State");
								}
							}
							fileWriter();
							System.out.println("State Updated");
							break;
						case 4:
							int zipCodeCheck=0;
							while (zipCodeCheck==0)
							{
								System.out.println("Enter your Zip Code");
								String zipcode = sc.nextLine();
								if(Pattern.matches("[0-9]{6}", zipcode))
								{
									persondetails.setZip(zipcode);
									zipCodeCheck=1;
								}
								else
								{
									System.out.println("You didn't entered valid zipcode");
									System.out.println("Please enter valid zip code");
								}
							}
							fileWriter();
							System.out.println("Zipcode Updated");
							break;
						case 5:
							int phoneNumberCheck=0;
							while(phoneNumberCheck==0)
							{
								System.out.println("Enter your Phone Number");
								String phone1 = sc.nextLine();
								if(Pattern.matches("[0-9]{8,11}", phone1))
								{
									persondetails.setPhone(phone1);
									phoneNumberCheck=1;
								}
								else
								{
									System.out.println("You didn't entered valid phone");
									System.out.println("Please enter valid phone");
								}
							}
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

		if(list.size()==0)
                {
                        System.out.println("No Contact Found");
                        return;
                }
                else
                {
                        for (int i = 0; i < list.size(); i++)
                        {
                                System.out.println(list.get(i));
                        }
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
		
		System.out.println("FIRSTNAME \t LASTNAME \t\tADDRESS \tCITY \t\t STATE \t\t\t ZIP \t\t PHONE \n");
		if(list.size()==0)
		{
			System.out.println("No Contact Found");
			return;
		}
		else
		{
			Collections.sort(list, Person.sortbyname);
			fileWriter();
		
			for (int i = 0; i < list.size(); i++) 
			{
				System.out.println(list.get(i));
			}
		}		
	}
	
	public void sortByZip()
	{
		fileReader();
		getAddressBook();
		selectAddressBook();
		
		List<Person> list = map.get(addressKey);

		System.out.println("FIRSTNAME \t LASTNAME \t\tADDRESS \tCITY \t\t STATE \t\t\t ZIP \t\t PHONE \n");
		if(list.size()==0)
		{
			System.out.println("No Contact Found");
			return;
		}
		else
		{
			Collections.sort(list, Person.sortbyzip);
			fileWriter();
		
			for (int i = 0; i < list.size(); i++) 
			{
				System.out.println(list.get(i));
			}
		}		
	}
	
	public void deleteAddressBook()
	{
		fileReader();
		if(countAddressbook==0)
		{
			System.out.println("There is No Address Book present.");
			return;
		}
		getAddressBook();

		int correctChoice=0;
		
		while ( correctChoice==0 ) 
		{
			System.out.println("Please Select address book do you want to delete");
			int select = sc.nextInt();
			sc.nextLine();
			try
			{
				addressKey = addressBook[select-1];
			}
			catch(Exception e )
			{
				System.out.println("Please select correct addressbook ");
				continue;
			}
			
			if (map.get(addressKey)!=null)
			{
				map.remove(addressKey);
				for (int i= select-1;i<countAddressbook;i++)
				{
					addressBook[i]=addressBook[i+1];
				}
				countAddressbook--;
				System.out.println("Address Book Deleted");
				fileWriter();
				correctChoice=1;
			}
		
			else
			{
				System.out.println("Please select correct addressbook");
			
			}
			
		}
		
	}
}

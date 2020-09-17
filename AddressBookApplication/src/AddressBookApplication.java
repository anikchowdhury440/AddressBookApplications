import operation.*;
import java.util.Scanner;

public class AddressBookApplication {

	public static void main(String[] args) {
		
		int choice;
		AddressBookOperation addressoperation=new AddressBookOperation();
		Scanner scanner = new Scanner(System.in);
		System.out.println("Welcome to Address Book");
		do {
			System.out.println("1.Create New Address Book");
			System.out.println("2.Add Person to Address Book");
			System.out.println("3.Display Address Book");
			System.out.println("4.Edit All Person From Address Book");
			System.out.println("5.Search Person from Address Book");
			System.out.println("6.Delete Person from Address Book");
			System.out.println("7.Sort Address Book by Last Name");
			System.out.println("8.Sort Address Book by ZIP Code");
			System.out.println("9.Exit from Application");
			System.out.println("Enter Your Choice: ");
			choice = scanner.nextInt();

			switch (choice) 
			{
				case 1:
					addressoperation.createAddressBook();
					break;
				case 2:
					addressoperation.addPerson();
					break;
				case 3:
					addressoperation.displayAddressBook();
					break;
				case 4:
					addressoperation.editPerson();
					break;
				case 5:
					addressoperation.searchPerson();
					break;
				case 6:
					addressoperation.deletePerson();
					break;
				case 7:
					addressoperation.sortByName();
					break;
				case 8:
					addressoperation.sortByZip();
					break;
				case 9:
					System.out.println("Exit from program");
					break;
				default:
					System.out.println("Invalid Choice! Enter Again");
					break;
			}

		} while (choice != 9);

	}

}

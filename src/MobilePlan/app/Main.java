package MobilePlan.app;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import MobilePlan.entities.*;
import MobilePlan.provided.*;
import MobilePlan.util.MobilePhoneContractHomeAddressMatcher;

/**
 * 
 * MobilePhoneContract demo application.
 *
 */
public class Main {
	/**
	 * Demo application.
	 * 
	 * <ul>
	 * <li>creates some demo MobilePhoneContract data using {@link #init()}</li>
	 * <li>prints the test data using {@link #print(List)}</li>
	 * <li>sorts them by natural order</li>
	 * <li>prints them again (sorted) using {@link #print(List)}</li>
	 * <li>filters MobilePhoneContracts, keeping those with customer's home address &quot;Wien&quot; using {@link #filter(List, Matcher)}</li>
	 * <li>prints the filtered MobilePhoneContracts again using {@link #print(List)}</li>
	 * <li>prints the filtered and sorted MobilePhoneContracts using {@link #print(List)}</li>
	 * <li>exports the filtered and sorted MobilePhoneContracts to file
	 * &quot;MobilePlans_Wien_by_additionalFee.txt&quot; and displays the number of
	 * deliveries exported using {@link #export(List, String)}</li>
	 * <li>tops up all prepaid cards with 20€ and prints the test data using {@link #print(List)}</li> 
	 * </ul>
	 * 
	 * @param args (not used)
	 */
	public static void main(String[] args) {
		List<MobilePhoneContract> contracts = init();
		print(contracts);

		contracts.sort(MobilePhoneContract::compareTo);
		print(contracts);

		//List<MobilePhoneContract> Vienna = filter(contracts, contract -> "Wien".equals(contract.getCustomer().getHome().getCity()));
		List<MobilePhoneContract> Vienna = filter(contracts, new MobilePhoneContractHomeAddressMatcher("Wien"));
		print(Vienna);

		Vienna.sort(MobilePhoneContract::compareTo);
		print(Vienna);

		System.out.println("Exported " + export(Vienna, "MobilePlans_Wien_by_additionalFee.txt") + " Mobile Plans");

		for(MobilePhoneContract contract : contracts) {
			if(contract instanceof PrePaidCard){
				((PrePaidCard) contract).topUp(20);
			}
		}
		print(contracts);



 
		// - create some demo MobilePhoneContract data using init()
		// - print the test data 
		// - sort them by natural order
		// - print them again (sorted) 
		// - filter MobilePhoneContracts, keeping those with customer's home address "Wien"
		// - print the filtered MobilePhoneContracts again 
		// - print the filtered and sorted MobilePhoneContracts 
		// - export the filtered and sorted MobilePhoneContracts to file
		// "MobilePlans_Wien_by_additionalFee.txt" and displays the number of deliveries exported
		// -top up all prepaid cards with 20€ and prints the test data 
		

	}


	

	/**
	 * Prints MobilePhoneContracts line by line.<br>
	 * <br>
	 * Adds a short header before printing the String representation of all
	 * MobilePhoneContracts line by line. For a single MobilePhoneContract the standard string
	 * representation provided by MobilePhoneContract.toString() is used.<br>
	 * <br>
	 * 
	 * @param mobilePhoneContracts the MobilePhoneContracts to print.
	 * 
	 * @ProgrammingProblem.Hint provided
	 */
	public static void print(List<MobilePhoneContract> mobilePhoneContracts) {
		System.out.println("\n--- MobilePhoneContracts ---");
		for (MobilePhoneContract a : mobilePhoneContracts)
			System.out.println(a);
		System.out.println("\n");

	}

	
	/**
	 * Creates some demo data.
	 * 
	 * 
	 * @return the demo data
	 *
	 * @ProgrammingProblem.Hint provided
	 */
	public static List<MobilePhoneContract> init() {
		List<Address> addresses = new LinkedList<>();
		addresses.add(new Address("Stephansplatz 1", 1010, "Wien", "Wien"));
		addresses.add(new Address("Brigittapassage 3c", 1200, "Wien", "Wien"));
		addresses.add(new Address("Postplatz", 2300, "Klein Wien", "Niederoesterreich"));
		addresses.add(new Address("Lange Gasse", 2700, "Wiener Neustadt", "Niederoesterreich"));
		addresses.add(new Address("Wiener Strasse 4", 8010, "Graz", "Steiermark"));
		addresses.add(new Address("Parkallee", 6020, "Innsbruck", "Tirol")); //5
		
		List<MobilePhoneContract> mobilePhoneContracts = new LinkedList<>();
		mobilePhoneContracts.add(new MonthlyContract(new Customer("Markus", 32, addresses.get(0)), "351756051523999", "A1", 5));
		mobilePhoneContracts.add(new MonthlyContract(new Customer("Martina", 18, addresses.get(3)), "987289743254583", "3AT",15));
		mobilePhoneContracts.add(new MonthlyContract(new Customer("Eduard", 45, addresses.get(4)), "90342189315843", "3AT",  10));

		mobilePhoneContracts.add(new PrePaidCard(new Customer("Maxi", 14, addresses.get(4)), "843534588453985", "bob",  20));
		mobilePhoneContracts.add(new PrePaidCard(new Customer("Franziska", 26, addresses.get(2)), "527348889299384", "A1",  10));
		mobilePhoneContracts.add(new PrePaidCard(new Customer("Anna", 75, addresses.get(1)), "223419892837377", "A1", 30));


		
		new Connection(mobilePhoneContracts.get(0), mobilePhoneContracts.get(1), ConnectionType.STANDARD, new DateTime(2022,07,12,0,0),72);
		new Connection(mobilePhoneContracts.get(2), mobilePhoneContracts.get(1), ConnectionType.STANDARD, new DateTime(2022,07,12,2,0),2);
		new Connection(mobilePhoneContracts.get(3), mobilePhoneContracts.get(1), ConnectionType.STANDARD, new DateTime(2022,07,12,0,0),14);
		new Connection(mobilePhoneContracts.get(0), mobilePhoneContracts.get(1), ConnectionType.PREMIUM, new DateTime(2022,07,12,1,0),12);
		new Connection(mobilePhoneContracts.get(0), mobilePhoneContracts.get(2), ConnectionType.STANDARD, new DateTime(2022,07,12,0,0),7);
		new Connection(mobilePhoneContracts.get(1), mobilePhoneContracts.get(4), ConnectionType.STANDARD, new DateTime(2022,07,12,0,0),12);
		new Connection(mobilePhoneContracts.get(1), mobilePhoneContracts.get(0), ConnectionType.STANDARD, new DateTime(2022,07,12,3,0),99);
		new Connection(mobilePhoneContracts.get(1), mobilePhoneContracts.get(3), ConnectionType.STANDARD, new DateTime(2022,07,12,4,0),15);



		return mobilePhoneContracts;
	}

	public static <T> java.util.List<T> filter(java.util.List<T> list, Matcher<? super T> m) {
		List<T> filteredList = new LinkedList<>();
		for (T element : list) {
			if (m.matches(element)) {
				filteredList.add(element);
			}
		}
		return filteredList;
	}
	public static int export (List<MobilePhoneContract> mobilePhoneContracts, String filename) {
		int counter = 0;
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(filename))){
			for (MobilePhoneContract contract : mobilePhoneContracts) {
				bw.write(contract.toString());
				bw.newLine();
				counter++;
			}
		}
		catch(Exception e){
			System.err.println(e.getMessage());
		}
		return counter;
    }
}

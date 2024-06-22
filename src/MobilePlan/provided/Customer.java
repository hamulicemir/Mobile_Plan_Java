package MobilePlan.provided;

/**
 * A skeleton implementation of a person (as a customer).
 * 
 * @ProgrammingProblem.Hint provided
 * @author Thomas Mandl, Edin Pezerovic
 *
 */
public class Customer {
	private String name;
	private int age;
	private Address home;

	public Customer(String name, int age, Address home) {
		super();
		this.name = name;
		this.age = age;
		this.home = home;
	}

	/**
	 * Copy constructor.<br>
	 * 
	 * Creates a deep copy of a customer.
	 * 
	 * @param orig the customer to copy
	 */
	public Customer(Customer orig) {
		name = new String(orig.name);
		age = orig.age;
		home = new Address(orig.home);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Address getHome() {
		return home;
	}

	public void setHome(Address home) {
		this.home = home;
	}

	
	@Override
	public String toString() {
		return String.format("[%s %d %s]", name, age, home);
	}
}

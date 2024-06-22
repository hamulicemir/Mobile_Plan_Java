package MobilePlan.provided;

/**
 * a skeleton implementation of a postal address.
 * 
 * @ProgrammingProblem.Hint provided
 */
public class Address {
	private String line_1;
	private int zip;
	private String city;
	private String state;

	public Address(String line_1, int zip, String city, String state) {
		super();
		this.line_1 = line_1;
		this.zip = zip;
		this.city = city;
		this.state = state;
	}

	/**
	 * Copy constructor.<br>
	 * 
	 * Create a deep copy of an address.
	 * 
	 * @param orig the address to copy
	 */
	public Address(Address orig) {
		line_1 = orig.line_1;
		zip = orig.zip;
		city = orig.city;
		state = orig.state;
	}

	public String getLine_1() {
		return line_1;
	}

	public void setLine_1(String line_1) {
		this.line_1 = line_1;
	}

	public int getZip() {
		return zip;
	}

	public void setZip(int zip) {
		this.zip = zip;
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

	@Override
	public String toString() {
		return String.format("[%s; %d %s, %s]", line_1, zip, city, state);
	}

}

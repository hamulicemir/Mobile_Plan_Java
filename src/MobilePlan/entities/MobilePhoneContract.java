package MobilePlan.entities;

import java.util.LinkedList;
import java.util.List;

import MobilePlan.provided.Customer;

/**
 * A MobilePhoneContract base class.<br>
 * Concrete sub types must implement the units and fee structure.
 */
public abstract class MobilePhoneContract  implements Comparable<MobilePhoneContract> {
	private String carrier;
	private List<Connection> connections;
	private Customer customer;
	private String imei;

	public MobilePhoneContract(Customer customer, String imei, String carrier) throws IllegalArgumentException {
		if (customer == null) {
			throw new IllegalArgumentException("Customer cannot be null");
		}
		if (imei == null) {
			throw new IllegalArgumentException("Imei cannot be null");
		}
		if(carrier == null) {
			throw new IllegalArgumentException("Carrier cannot be null");
		}
		this.customer = customer;
		this.imei = imei;
		this.carrier = carrier;
		this.connections = new LinkedList<>(); // Initialize the connections list
	}
	public boolean addConnection(Connection connection) {
		if (connection == null || connections.contains(connection)) {
			return false;
		}
		connections.add(connection);
		return true;
	}

	public List<Connection> outgoingConnections() {
		List<Connection> outgoingConnections = new LinkedList<>();
		for (Connection connection : connections) {
			if(connection.getCaller().equals(this)) {
				outgoingConnections.add(connection);
			}
		}
		return outgoingConnections;
	}
	public abstract int unitsLeft();
	public abstract float additionalFee();

	public int getUsedUnits() {
		int usedUnits = 0;
		for (Connection connection : outgoingConnections()) {
			usedUnits += connection.getUnits();
		}
		return usedUnits;
	}


	public int compareTo(MobilePhoneContract contract) {
		return Integer.compare(this.getUsedUnits(), contract.unitsLeft());
	}

	public String getImei() {
		return imei;
	}

	public Customer getCustomer() {
		return customer;
	}

	/**
	 * Creates a string representation of this MobilePhoneContract.<br>
	 * 
	 * The returned string contains the values
	 * <ul>
	 * <li>customer string</li>
	 * <li>imei number</li>
	 * <li>carrier name</li>
	 * <li>used units</li>
	 * <li>units left</li>
	 * <li>additional fees to pay</li> 
	 * <li>a summary of all outgoing calls</li>
	 * </ul>
	 * 
	 * @ProgrammingProblem.Hint provided
	 * 
	 */
	@Override
	public String toString() {
		return "MobilePhoneContract [\ncarrier=" + carrier  + ", customer=" + customer
				+ ", imei=" + imei + ", usedUnits="+ getUsedUnits() +",\nconnections=" + outgoingConnections() + "\n]";
	}



}

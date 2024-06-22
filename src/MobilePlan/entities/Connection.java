package MobilePlan.entities;

import MobilePlan.provided.ConnectionType;
import MobilePlan.provided.DateTime;

/**
 * 
 * A connection, aka a call between two contracts. A "phone call".<br>
 * 
 */
public class Connection {
    private final MobilePhoneContract callee;
    private final MobilePhoneContract caller;
    private final DateTime dateTime;
    private final ConnectionType type;
    private final int units;

    public Connection(MobilePhoneContract caller, MobilePhoneContract callee, ConnectionType type, DateTime dt, int units) throws IllegalArgumentException {
        if(caller == null){
            throw new IllegalArgumentException("caller is null");
        }
        if(callee == null){
            throw new IllegalArgumentException("callee is null");
        }
        if(dt == null){
            throw new IllegalArgumentException("dt is null");
        }
        this.caller = caller;
        this.callee = callee;
        this.dateTime = dt;
        this.type = type;
        this.units = units;
    }

    public int getUnits(){
        return this.units;
    }
    public MobilePhoneContract getCaller(){
        return this.caller;
    }

    /**
     *  Creates a string representation of this connection.<br>
     */
    @Override
    public String toString() {
        return "\nConnection [caller=" + caller.getImei() + ", callee=" + callee.getImei() + ", type=" + type
                + ", units=" + units + ", DateTime=" + dateTime + "]";
    }

}

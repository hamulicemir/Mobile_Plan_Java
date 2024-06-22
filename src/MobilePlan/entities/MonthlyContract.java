package MobilePlan.entities;

import MobilePlan.provided.Customer;

public class MonthlyContract extends MobilePhoneContract{
    private int includedUnits;


    public MonthlyContract(Customer customer, String imei, String carrier, int includedUnits) {
        super(customer, imei, carrier);
        this.includedUnits = includedUnits;
    }

    public void setIncludedUnits(int includedUnits) {
        if(includedUnits > 0){
            this.includedUnits = includedUnits;
        }
    }


    @Override
    public int unitsLeft() {
        int unitsleft = this.includedUnits - this.getUsedUnits();
        if(unitsleft < 0)
            unitsleft = 0;

        return unitsleft;
    }

    @Override
    public float additionalFee() {
        if (this.unitsLeft() > 0)
            return 0;
        else{
            float additionalunits = getUsedUnits() - this.includedUnits;
            return additionalunits * (float) 0.2;
        }
    }
}

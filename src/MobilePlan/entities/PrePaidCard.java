package MobilePlan.entities;

import MobilePlan.provided.Customer;

public class PrePaidCard extends MobilePhoneContract {

    float preloadedAmount;
    private static final float UNIT_COST = 0.4f;

    public PrePaidCard(Customer customer, String imei, String carrier) {
        super(customer, imei, carrier);
    }

    public PrePaidCard(Customer customer, String imei, String carrier, float preloadedAmount) {
        super(customer, imei, carrier);
        this.preloadedAmount = preloadedAmount;
    }

    private void setPreloadedAmount(float preloadedAmount) {
        this.preloadedAmount = preloadedAmount;
    }

    public void topUp(float additionalAmount) {
        if(additionalAmount > 0 && additionalAmount % 5 == 0){
            this.preloadedAmount += additionalAmount;
        }
    }

    @Override
    public int unitsLeft() {
        float units = preloadedAmount / UNIT_COST - getUsedUnits();
        return (int) units;
    }

    @Override
    public float additionalFee() {
        if (unitsLeft() < 0) {
            return 0;
        } else {
            int usedUnits = getUsedUnits();
            float deficit = usedUnits * UNIT_COST - preloadedAmount;
            return deficit;
        }
    }

    @Override
    public String toString() {
        return "PrePaidCard{" +
                "preloadedAmount=" + preloadedAmount +
                '}';
    }
}

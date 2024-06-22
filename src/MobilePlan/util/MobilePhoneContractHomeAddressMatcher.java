package MobilePlan.util;

import MobilePlan.entities.MobilePhoneContract;
import MobilePlan.provided.Matcher;

public class MobilePhoneContractHomeAddressMatcher implements Matcher<MobilePhoneContract> {
    private String pattern;
    public MobilePhoneContractHomeAddressMatcher(String pattern) {
        if (pattern == null) {
            throw new IllegalArgumentException("pattern cannot be null");
        }
        this.pattern = pattern;
    }
    public boolean matches(MobilePhoneContract t) {
        if(t == null || t.getCustomer() == null){
            return false;
        }
        if(t.getCustomer().getHome().getState().contains(pattern) || t.getCustomer().getHome().getCity().contains(pattern)){
            return true;
        }
        else
            return false;
    }
}

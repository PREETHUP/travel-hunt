package com.ndchack.travelhunt.ui.domain.Ancilaries;

/**
 * Created by a-6281 on 2/24/18.
 */
public class UserAncillaryDetail implements Comparable<UserAncillaryDetail> {

    String ancillaryName;
    Float totalAmount;
    Float discountedAmount;
    Float savedAmount;

    public String getAncillaryName() {
        return ancillaryName;
    }

    public void setAncillaryName(String ancillaryName) {
        this.ancillaryName = ancillaryName;
    }

    public Float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Float getDiscountedAmount() {
        return discountedAmount;
    }

    public void setDiscountedAmount(Float discountedAmount) {
        this.discountedAmount = discountedAmount;
    }

    public Float getSavedAmount() {
        return savedAmount;
    }

    public void setSavedAmount(Float savedAmount) {
        this.savedAmount = savedAmount;
    }

    @Override
    public int compareTo(UserAncillaryDetail userAncillaryDetail) {
        return this.getAncillaryName().compareTo(userAncillaryDetail.getAncillaryName());
    }


}

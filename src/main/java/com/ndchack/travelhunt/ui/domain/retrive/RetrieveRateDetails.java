package com.ndchack.travelhunt.ui.domain.retrive;

/**
 * Created by a-6281 on 2/24/18.
 */
public class RetrieveRateDetails {
    Float basefare;
    Float taxes;
    Float total;
    String currency;

    public Float getBasefare() {
        return basefare;
    }

    public void setBasefare(Float basefare) {
        this.basefare = basefare;
    }

    public Float getTaxes() {
        return taxes;
    }

    public void setTaxes(Float taxes) {
        this.taxes = taxes;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}

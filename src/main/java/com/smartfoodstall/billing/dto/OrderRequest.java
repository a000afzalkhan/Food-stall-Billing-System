package com.smartfoodstall.billing.dto;

import java.util.HashMap;
import java.util.Map;

public class OrderRequest {

    private Map<Long, Integer> quantities = new HashMap<>();
    private boolean gstEnabled = true;

    public Map<Long, Integer> getQuantities() {
        return quantities;
    }

    public void setQuantities(Map<Long, Integer> quantities) {
        this.quantities = quantities;
    }

    public boolean isGstEnabled() {
        return gstEnabled;
    }

    public void setGstEnabled(boolean gstEnabled) {
        this.gstEnabled = gstEnabled;
    }
}

package com.customerBatch.domain;

public class CustomerUpdate {
    protected final long customerId;
    public CustomerUpdate(long customerId) {
        this.customerId = customerId;
    }

    public long getCustomerId(){
        return this.customerId;
    }
}